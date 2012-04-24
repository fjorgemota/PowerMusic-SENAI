package fases;

import java.awt.Component;
import java.awt.Graphics;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaPlay.GameEngine;
import javaPlay.GameObject;
import javaPlay.GameStateController;
import javaPlay.Mouse;
import javaPlayExtras.Imagem;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import utilidades.Utilidades;
import javax.media.Player;
import javax.media.Manager;
import javax.media.PlugInManager;
import javax.media.Format;
import javax.swing.*;
import net.sourceforge.jffmpeg.AudioDecoder;
import net.sourceforge.jffmpeg.VideoDecoder;
public class TelaInicial implements GameStateController {

    private Imagem bgImageHelp;
    private Imagem bgImageHelp1;
    private Imagem bgImageHelp2;
    private Imagem bgImageFundo;
    private Imagem bgImageNewGame;
    private Imagem bgImageNewGame1;
    private Imagem bgImageNewGame2;
    private Imagem bgImageExit;
    private Imagem bgImageExit1;
    private Imagem bgImageExit2;
    private Imagem bgImage;
    private Mouse mouse;

    public void load() {
        
        JPanel pteste = new JPanel();
        Player thePlayer = Utilidades.loadVideo("ItsMyLife.mpg");
        Component theVideo = thePlayer.getVisualComponent();
        pteste.add(theVideo);
        theVideo.setVisible(true);
        theVideo.setBounds(0,0,200,200);
        pteste.setBounds(0,0,200, 200); 
        GameEngine.getInstance().getGameCanvas().setPanel(pteste);
        pteste.setVisible(true);
        pteste.repaint();
        thePlayer.start();
        try {
            this.bgImageHelp1 = new Imagem("img_cenario/help.png");
            this.bgImageHelp2 = new Imagem("img_cenario/Help2.png");
            this.bgImageHelp = this.bgImageHelp1;

            this.bgImageFundo = new Imagem("img_cenario/logicamentefeito.png");

            this.bgImageNewGame1 = new Imagem("img_cenario/newgame.png");
            this.bgImageNewGame2 = new Imagem("img_cenario/newgame2.png");
            this.bgImageNewGame = this.bgImageNewGame1;



            this.bgImageExit1 = new Imagem("img_cenario/exit.png");
            this.bgImageExit2 = new Imagem("img_cenario/exit2.png");
            this.bgImageExit  = this.bgImageExit1;
         
            
            this.bgImage = new Imagem("img_cenario/logicamentefeito.png");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }

    public void unload() {
    }

    public void start() {
    }
    
    public void step(long timeElapsed) {

        if (Utilidades.estaClicandoEm(548, 385, 200, 75)) {
            GameEngine.getInstance().setNextGameStateController(4);
        }

        if (Utilidades.estaClicandoEm(590, 466, 89, 75)) {
            GameEngine.getInstance().setNextGameStateController(3);
        }

        if (Utilidades.estaClicandoEm(590, 555, 89, 75)) {
            System.exit(0);
        }

        if (Utilidades.estaComOMouseEm(548, 385, 200, 75)) {
            GameEngine.getInstance().getGameCanvas().setPanel(null);
            this.bgImageNewGame = this.bgImageNewGame2;
        } else {
            this.bgImageNewGame = this.bgImageNewGame1;
        }

        if (Utilidades.estaComOMouseEm(590, 466, 89, 75)) {
            this.bgImageHelp = this.bgImageHelp2;
        } else {
            this.bgImageHelp = this.bgImageHelp1;
        }
        
        if (Utilidades.estaComOMouseEm(590, 555, 89, 75)) {
            this.bgImageExit = this.bgImageExit2;
        } else {
            this.bgImageExit = this.bgImageExit1;
        }
    }

    public void draw(Graphics g) {
        g.fillRect(0, 0, 3000, 2400);


        this.bgImageFundo.draw(g, 0, 0);
        
        this.bgImage.draw(g, 0, 0);
        this.bgImageNewGame.draw(g, 548, 385);
        this.bgImageHelp.draw(g, 590, 466);
        this.bgImageExit.draw(g, 590, 555);

    }

    public void stop() {
    }
}
