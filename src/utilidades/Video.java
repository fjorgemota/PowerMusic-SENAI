/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import java.awt.Component;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.Format;
import javax.media.Manager;
import javax.media.Player;
import javax.media.PlugInManager;
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
    public Video(String filename){
        super();
        this.filename = filename;
        this.install();
        this.load();
    }
    public URL getURL(){
        try {
            System.out.println("Criando URL para o player "+this.filename);
            URL url = new URL("file://"+System.getProperty("user.dir")+"/videos/"+this.filename);
            System.out.println("URL criado para o player "+this.filename);
            return url;
        } catch (Exception ex) {
            Utilidades.alertar("Alerta 2:"+ex.getMessage());
            return null;
        }
    }
    public void load(){
        System.out.println("Executando Thread..");
        theThread = new Thread(this);
        theThread.setDaemon(true);
        theThread.setPriority(Thread.MAX_PRIORITY);
        theThread.start();
        System.out.println("Terminou de executar a Thread");
    }
    public void run(){
        try {
            System.out.println("Criando player "+this.filename);
            this.player = Manager.createPlayer(this.getURL());
            System.out.println("Adicionando controlador ao player "+this.filename);
            this.player.addControllerListener(this);
            System.out.println("Realizando player "+this.filename);
            this.player.realize();
        } catch (Exception ex) {
            Utilidades.alertar("Alerta 3:"+ex.getMessage()+" with file "+this.filename);
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
    }
    public void pause(){
        this.player.stop();
    }

    public void controllerUpdate(ControllerEvent ce) {
        switch(this.player.getState()){
            case Player.Realized:
                System.out.println("Player "+this.filename+" Realizado!");
                //this.player.prefetch();
                this.canPlay = true;
                break;
            case Player.Prefetched:
                System.out.println("Player "+this.filename+" pre-capturado!");
                break;
            case Player.Realizing:
                System.out.println("Realizando Player "+this.filename+" ..");
                break;
            case Player.Prefetching:
                System.out.println("Pre-capturando Player "+this.filename+" ...");
                break;             
        }
    }
}
