/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import java.awt.Component;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.*;
import net.sourceforge.jffmpeg.AudioDecoder;
import net.sourceforge.jffmpeg.VideoDecoder;
import sun.awt.PlatformFont;

/**
 *
 * @author fernando_mota
 */
public class Video implements ControllerListener, Runnable{
    private String filename;
    private Player player = null;
    private boolean canPlay;
    private Thread theThread;
    private boolean loop = false;
    private boolean terminated;
    public Video(String filename){
        super();
        this.filename = filename;
        this.terminated = false;
        this.install();
        this.load();
    }
    public URL getURL(){
        
        try {
            File f = new File(this.filename);
            return f.toURL();
        } catch (Exception ex) {
            Utilidades.alertar("Problema ao criar a URL para o video:"+ex.getMessage());
            return null;
        }
    }
    public void load(){
        theThread = new Thread(this);
        theThread.setDaemon(true);
        theThread.setPriority(Thread.MAX_PRIORITY);
        theThread.start();
    }
    public void run(){
        try {
            this.player = Manager.createPlayer(this.getURL());
            this.player.addControllerListener(this);
            this.player.realize();
        } catch (Exception ex) {
            Utilidades.alertar("Erro ao criar o player:"+ex.getMessage()+" para o arquivo "+this.filename);
        }
    }
    public void join(){
        try {
            theThread.join();
        } catch (Exception ex) {
            Utilidades.alertar("Erro ao aguardar pela Thread:"+ex.getMessage());
        }
    }
    public Component getSwingComponent(){
        while(!this.canPlay){
            continue;
        }
        return player.getVisualComponent();
    }
    public void setVolume(float volume){
        this.player.getGainControl().setLevel(volume);
    }
    public float getDuration(){
        return (float)player.getDuration().getSeconds();
    }
    public float getActualTime(){
        return (float)player.getMediaTime().getSeconds();
    }
    public void setLoop(boolean loop){
        this.loop = loop;
    }
    public boolean isTerminated(){
        return terminated;
    }
    public boolean install(){
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
            return false;
        }
        return true;
    }
    public void play(){
        this.player.start();
        this.terminated = false;
    }
    public void pause(){
        this.player.stop();
        
    }
    public void reset(){
        this.pause();
        this.player.setMediaTime(new Time(0));
    }
    public void close(){
       this.player.deallocate();
    }
    public void controllerUpdate(ControllerEvent ce) {
        switch(this.player.getState()){
            case Player.Realized:
                this.player.prefetch();
                this.canPlay = true;
                break;
            case Player.Prefetched:
            case Player.Realizing:
            case Player.Prefetching:
                break;  
        }
        if(ce instanceof EndOfMediaEvent){
            this.terminated = true;
        }
        if(loop && this.terminated){
            this.reset();
            this.play();
        }
    }
}
