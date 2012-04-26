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
import com.sun.media.sound.MidiUtils;
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
    public static float[][] loadNotesFromMIDI(String filename){
        Sequence sequencia;
        Sequencer tocador;
        MidiUtils.TempoCache tempo;
        try {
            sequencia = MidiSystem.getSequence(new File(filename));
            tempo = new MidiUtils.TempoCache(sequencia);
            tocador = MidiSystem.getSequencer(true);
            tocador.setSequence(sequencia);
            tocador.open();
        } catch (Exception ex) {
            Utilidades.alertar(ex.getMessage());
            return null;
        }
        int program = 0;
        ArrayList< ArrayList<Number> > notas = new ArrayList< ArrayList<Number>>(); // Cria um ArrayList com as notas, que devem vir a ser a matriz com as notas por si so
        int[] chords = new int[]{64, 69, 74, 79, 83, 88};
        int maxNote = 0;
        for (Track track:  sequencia.getTracks()) {
            for(int c=0;c<track.size();++c){
                MidiEvent event = track.get(c);
                MidiMessage msg = event.getMessage();
                if(msg instanceof ShortMessage){
                    ShortMessage shortmsg = (ShortMessage) msg;
                    if(shortmsg.getCommand() == ShortMessage.PROGRAM_CHANGE){
                        program = shortmsg.getData1();
                    }
                    else if(program>=0 && program <= 128){
                    //else if(program>=25 && program <= 40){
                    //else if(program== 30){
                        if(shortmsg.getCommand() == ShortMessage.NOTE_ON){
                            int note = shortmsg.getData1();
                            int noteChord = 1;
                            for (int chord: chords){
                                if (note < chord){
                                    break;
                                }
                                noteChord++; 
                            }
                            tocador.setTickPosition(event.getTick());
                            //tocador.start();
                            float noteSecond = MidiUtils.tick2microsecond(sequencia, event.getTick(), tempo)/1000000.0f;
                            //System.out.println("Play chord "+noteChord+" in "+noteSecond+" seconds");
                            ArrayList<Number> lastNote = new ArrayList<Number>();
                            if(notas.size() > 0){
                                lastNote = notas.get(notas.size()-1);
                                int theIndex = 0;
                                float lastSecond = (float) 0.0;
                                boolean exists = false;
                                for(ArrayList<Number> aNota: notas){
                                    if(aNota.get(0).floatValue() == noteSecond){
                                        exists = true;
                                        lastNote = aNota;
                                    }
                                    else if(lastSecond<noteSecond && aNota.get(0).floatValue()>noteSecond){
                                        exists = false;
                                        break;
                                    }
                                    lastSecond = aNota.get(0).floatValue();
                                    theIndex++;
                                }
                                if(exists){
                                    if(!lastNote.contains(noteChord)){
                                        lastNote.add(noteChord);
                                    }
                                }
                                else{
                                    lastNote = new ArrayList<Number>();
                                    lastNote.add(noteSecond);
                                    lastNote.add(noteChord);
                                    notas.add(theIndex,lastNote);
                                }
                            }
                            else{
                                lastNote = new ArrayList<Number>();
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
        //System.out.println("tamanho da pista "+notas.size()+" e track "+maxNote);
        float[][] notasVetor = new float[notas.size()][maxNote];
        for(int c=0;c < notas.size(); ++c){
            ArrayList<Number> notasTrack = notas.get(c);
            for(int c2=0; c2<notasTrack.size(); ++c2){
                notasVetor[c][c2] = (float)notasTrack.get(c2).floatValue();
                System.out.println("notasVetor["+c+"]["+c2+"] = "+notasVetor[c][c2]);
            }
        }
       // GameEngine.getInstance().setFramesPerSecond((int)(((tocador.getMicrosecondLength()/1000000)/(notas.size()*1.0))*4000));
       // System.out.println("(int)(("+sequencia.getMicrosecondLength()+"/1000000)/"+notas.size()+"="+(int)((sequencia.getMicrosecondLength()/1000000)/notas.size()))
        return notasVetor;
    }
    public static float[][] loadNotesFromMIDI(String filename, float videoDuration){
        float[][] notas = loadNotesFromMIDI(filename);
        if(notas.length == 0){
            return notas;
        }
        float audioDuration  = notas[notas.length-1][0];
        float ratio = audioDuration/videoDuration;
        System.out.println("O Ratio é de "+ratio);
        float[][] novaNotas = new float[notas.length][notas[0].length];
        int c = 0;
        for(float[] nota: notas){
            novaNotas[c] = nota;
            novaNotas[c][0] = novaNotas[c][0]/ratio;
            ++c;
        }
        return novaNotas;
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
