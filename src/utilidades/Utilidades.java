/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import java.awt.Component;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaPlay.GameEngine;
import javaPlay.Mouse;
import javax.media.Format;
import javax.media.Manager;
import javax.media.Player;
import javax.media.PlugInManager;
import javax.sound.midi.*;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import net.sourceforge.jffmpeg.AudioDecoder;
import net.sourceforge.jffmpeg.VideoDecoder;

/**
 *
 * @author fernando_mota
 */
public class Utilidades {
    public static Random randObj=null;
    public static void criaRandomObj(){
        if(Utilidades.randObj == null){
            Utilidades.randObj = new Random();//Cria o objeto Random - para numeros e etc aleatorios - caso ainda não esteja criado
        }
    }
    public static int getNumeroRandomico(int minimo, int maximo){
        Utilidades.criaRandomObj();
        return minimo+Utilidades.randObj.nextInt(maximo-minimo);
    }
    public static boolean sorteia(){
        Utilidades.criaRandomObj();
        return randObj.nextBoolean();
    }
    public static boolean estaClicandoEm(int x, int y, int width, int height){
        Mouse mouse = GameEngine.getInstance().getMouse();
        if(!mouse.isLeftButtonPressed()){
            return false;
        }
        Point ponto = mouse.getMousePos();
        Rectangle rect = new Rectangle(x, y, width, height);
        return rect.contains(ponto);
    }
    public static boolean estaComOMouseEm(int x, int y, int width, int height){
        Mouse mouse = GameEngine.getInstance().getMouse();
        Point ponto = mouse.getMousePos();
        Rectangle rect = new Rectangle(x, y, width, height);
        return rect.contains(ponto);
    }
    public static void alertar(String mensagem){
        JOptionPane.showMessageDialog(null, mensagem);
    }
    public static void alertar(String mensagem, String titulo){
        JOptionPane.showMessageDialog(null, mensagem, titulo, 0);
    }
    public static int[][] loadNotesFromMIDI(String filename){
        Sequence sequencia;
        try {
            sequencia = MidiSystem.getSequence(new File(filename));
        } catch (Exception ex) {
            Utilidades.alertar(ex.getMessage());
            return null;
        }
        float divisionType = sequencia.getDivisionType();
        int trackNumber = 0, program = 0;
        float timeElapsed = 0;
        HashMap notesPlayed = new HashMap();
        ArrayList< ArrayList<Integer> > notas = new ArrayList< ArrayList<Integer>>(); // Cria um ArrayList com as notas, que devem vir a ser a matriz com as notas por si so
        int[] chords = new int[]{64, 69, 74, 79, 83, 88};
        float beatsPerMinute = 120.0f;
        int ticksPerBeat = 0;
        float ticksBySecond = 0;
        int maxNote = 0;
        for (Track track:  sequencia.getTracks()) {
            for(int c=0;c<track.size();++c){
                MidiEvent event = track.get(c);
                MidiMessage msg = event.getMessage();
                if(msg instanceof MetaMessage){
                    MetaMessage metamsg = (MetaMessage) msg;
                    byte[] abData = metamsg.getData();
                    if(metamsg.getType() == 81){
                        float	nTempo = ((abData[0] & 0xFF) << 16)
					| ((abData[1] & 0xFF) << 8)
					| (abData[2] & 0xFF);           // tempo in microseconds per beat
			if (nTempo <= 0) {
                            nTempo = 0.1f;
                        }
			// truncate it to 2 digits after dot
			beatsPerMinute = (float) (Math.round((60000000.0f / nTempo)*100.0f)/100.0f);
                        System.out.println("Set Tempo "+beatsPerMinute+"bpm )");
                    }
                    else if(metamsg.getType() == 88){
                        ticksPerBeat = abData[2] & 0xFF;
                        System.out.println(ticksPerBeat+"tpb");
                        ticksBySecond = (beatsPerMinute*sequencia.getResolution())/60;
                        System.out.println("Tick "+event.getTick()+" Ticks por segundo: "+ticksBySecond);
                    }
                }
                else if(msg instanceof ShortMessage){
                    ShortMessage shortmsg = (ShortMessage) msg;
                    if(shortmsg.getCommand() == ShortMessage.PROGRAM_CHANGE){
                        program = shortmsg.getData1();
                    }
                    else if(shortmsg.getCommand() == ShortMessage.TIMING_CLOCK){
                        System.out.println("Timing clock!");
                    }
                    else if(program==28){
                        if(shortmsg.getCommand() == ShortMessage.NOTE_ON){
                            int note = shortmsg.getData1();
                            int noteChord = 1;
                            for (int chord: chords){
                                if (note < chord){
                                    break;
                                }
                                noteChord++; 
                            }
                            int noteSecond = Math.round(event.getTick()/ticksBySecond);
                            //System.out.println("Play chord "+noteChord+" in "+noteSecond+" seconds");
                            ArrayList<Integer> lastNote = new ArrayList<Integer>();
                            if(notas.size() > 0){
                                lastNote = notas.get(notas.size()-1);
                                if(lastNote.get(0) == noteSecond){
                                    lastNote.add(noteChord);
                                }
                                else{
                                    lastNote = new ArrayList<Integer>();
                                    lastNote.add(noteSecond);
                                    lastNote.add(noteChord);
                                    notas.add(lastNote);
                                }
                            }
                            else{
                                lastNote = new ArrayList<Integer>();
                                lastNote.add(noteSecond);
                                lastNote.add(noteChord);
                                notas.add(lastNote);
                            }
                            if(maxNote < lastNote.size()){
                                maxNote = lastNote.size();
                            }
                        }
                        
                    }
                    
                }
            }
        }
        System.out.println("tamanho da pista "+notas.size()+" e track "+maxNote);
        int[][] notasVetor = new int[notas.size()][maxNote];
        for(int c=0;c < notas.size(); ++c){
            ArrayList<Integer> notasTrack = notas.get(c);
            for(int c2=0; c2<notasTrack.size(); ++c2){
                notasVetor[c][c2] = (int)notasTrack.get(c2);
                System.out.println("notasVetor["+c+"]["+c2+"] = "+notasVetor[c][c2]);
            }
        }
        return notasVetor;
    }
            
    public static Player carregaVideo(String videopath){
        VideoDecoder video = new VideoDecoder();
        ArrayList<Format> formatsvideo = new ArrayList<Format>();
        
        for(Format input: video.getSupportedInputFormats()){
            formatsvideo.addAll(Arrays.asList(video.getSupportedOutputFormats(input)));
        }
        AudioDecoder audio = new AudioDecoder();
        ArrayList<Format> formatsaudio = new ArrayList<Format>();
        for(Format input: audio.getSupportedInputFormats()){
            formatsaudio.addAll(Arrays.asList(audio.getSupportedOutputFormats(input)));
        }
        Format[] formatvoutput = new Format[formatsvideo.size()];
        for(Format output: formatsvideo){
            formatvoutput[formatsvideo.indexOf(output)] = output;
        }
        Format[] formataoutput = new Format[formatsaudio.size()];
        for(Format output: formatsaudio){
            formataoutput[formatsaudio.indexOf(output)] = output;
        }
        PlugInManager.addPlugIn("net.sourceforge.jffmpeg.VideoDecoder", video.getSupportedInputFormats(), formatvoutput, PlugInManager.CODEC);
        PlugInManager.addPlugIn("net.sourceforge.jffmpeg.AudioDecoder", audio.getSupportedInputFormats(), formataoutput, PlugInManager.CODEC);
        //PlugInManager.addPlugIn("net.sourceforge.jffmpeg.VideoDecoder", video.getSupportedInputFormats(), PlugInManager.getSupportedOutputFormats("net.sourceforge.jffmpeg.VideoDecoder", PlugInManager.CODEC), PlugInManager.CODEC);
        //PlugInManager.addPlugIn("net.sourceforge.jffmpeg.AudioDecoder", audio.getSupportedInputFormats(), PlugInManager.getSupportedOutputFormats("net.sourceforge.jffmpeg.AudioDecoder", PlugInManager.CODEC), PlugInManager.CODEC);
        try {
            PlugInManager.commit();
        } catch (Exception ex) {
            Utilidades.alertar("Alerta ao instalar o Codec:"+ex.getMessage());
            return null;
        }
        Player thePlayer = null;
        URL videoUrl;
        try {
            videoUrl = new URL("file://"+System.getProperty("user.dir")+"/videos/"+videopath);
        } catch (Exception ex) {
            Utilidades.alertar("Alerta 2:"+ex.getMessage());
            return null;
        }
        try {
            thePlayer = Manager.createRealizedPlayer(videoUrl);
        } catch (Exception ex) {
            Utilidades.alertar("Alerta 3:"+ex.getMessage()+" with file "+videoUrl.toString());
            return null;
        }
        return thePlayer;
    }
}
