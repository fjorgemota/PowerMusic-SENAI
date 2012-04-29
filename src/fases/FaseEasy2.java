/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fases;

/**
 *
 * @author Samara
 */
import java.awt.Graphics;
import javaPlay.GameEngine;
import javaPlay.GameStateController;
import javaPlayExtras.Imagem;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import utilidades.Utilidades;

public class FaseEasy2 extends Musica {
    public FaseEasy2(){
        super("musicas/Gone.mid", "videos/Gone.mpg", "img_cenario/FOTOS_BANDAS/Switchfoot/switchfoot1.png", "img_cenario/FOTOS_BANDAS/Switchfoot/switchfoot2.png", 3);
    }

    public void gameOver() {
        GameOver.getInstance().setMusica(16);
        GameEngine.getInstance().setNextGameStateController(24);
    }

    public void nextMusic() {
        GameEngine.getInstance().setNextGameStateController(17);
        
    }
}

