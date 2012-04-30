/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fases;

/**
 *
 * @author Samara
 */

import guitarra.Guitarra;
import java.awt.Component;
import java.awt.Graphics;
import java.util.ArrayList;
import javaPlay.GameEngine;
import javaPlay.GameStateController;
import javaPlayExtras.Imagem;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import utilidades.MIDIReader;
import utilidades.Video;
// MÚSICAS DA FASE
//Its my life
//Swit of mine
//Admiravel chip novo

public class FaseMedium1 implements GameStateController {

    private Imagem bgImageFundo;
 
    private Imagem bgImageGuitarra;
    private Imagem bgImageFundo1;
    private Imagem bgImageFundo2;


    private JLabel bgImageFundoEsquerda;
    private JLabel progresso;
    private Imagem bgImageFundoDireita;
    private Guitarra guitarra;
    private boolean musicLoaded = false;
    private boolean videoStarted = false;
    private MIDIReader musica;
    private Video video;
    private JPanel thePanel;
    private Component theVideo;

    public void load() {

        try {

            this.bgImageFundo = new Imagem("img_cenario/fundo.png");
      
            this.bgImageFundo1 = new Imagem("img_cenario/FOTOS_BANDAS/guns/guns1.png");
            this.bgImageFundo2 = new Imagem("img_cenario/FOTOS_BANDAS/guns/guns2.png");





        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }

    public void unload() {
    }

    public void start() {
    }

    public void step(long timeElapsed) {

      
    }

    public void draw(Graphics g) {
        g.fillRect(0, 0, 3000, 2400);
        this.bgImageFundo.draw(g, 0, 0);

  
        this.bgImageFundo1.draw(g, 0, 0);
        this.bgImageFundo2.draw(g, 426, 0);
    }

    public void stop() {
    }
}

