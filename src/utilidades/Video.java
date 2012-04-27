/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import java.awt.Component;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import javax.media.Format;
import javax.media.Manager;
import javax.media.Player;
import javax.media.PlugInManager;
import net.sourceforge.jffmpeg.AudioDecoder;
import net.sourceforge.jffmpeg.VideoDecoder;

/**
 *
 * @author fernando_mota
 */
public class Video{
    private String filename;
    Player player = null;
    public Video(String filename){
        this.filename = filename;
        this.install();
        this.load();
    }
    public URL getURL(){
        try {
            return new URL("file://"+System.getProperty("user.dir")+"/videos/"+this.filename);
        } catch (Exception ex) {
            Utilidades.alertar("Alerta 2:"+ex.getMessage());
            return null;
        }
    }
    public void load(){
        try {
            player = Manager.createRealizedPlayer(this.getURL());
            player.realize();
        } catch (Exception ex) {
            Utilidades.alertar("Alerta 3:"+ex.getMessage()+" with file "+this.filename);
        }
    }
    public Component getSwingComponent(){
        return player.getVisualComponent();
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
    public void stop(){
        this.player.stop();
    }
    public void close(){
        this.player.deallocate();
    }
}
