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
//Sweet Child O' Mine
//Admiravel chip novo



public class FaseMedium1 extends Musica {


    public FaseMedium1(){
        super("musicas/SweetChildOMine.mid", "videos/SweetChildOMine.mpg", "imagens/FOTOS_BANDAS/guns/guns1.png", "imagens/FOTOS_BANDAS/guns/guns2.png", 4);
    }

    public void gameOver() {
        GameOver.getInstance().setMusica(18);
        GameEngine.getInstance().setNextGameStateController(24);
    }

    public void nextMusic() {
        GameEngine.getInstance().setNextGameStateController(19); 
    }
}

