/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import java.awt.Component;
import java.awt.Point;
import java.awt.Rectangle;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaPlay.GameEngine;
import javaPlay.Mouse;
import javax.media.Format;
import javax.media.Manager;
import javax.media.Player;
import javax.media.PlugInManager;
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
    public static Player loadVideo(String videopath){
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
