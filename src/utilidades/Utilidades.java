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
import java.util.Map;
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
    
            
    public static Player carregaVideo(String videopath){
        
        Player thePlayer = null;
        URL videoUrl;
        
        
        return thePlayer;
    }








}
