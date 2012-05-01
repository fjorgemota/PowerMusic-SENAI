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
import javax.swing.JOptionPane;


//MÚSICAS DA FASE
//Um minuto para o fim do mundo
//Throught the fire and flames
//Heroes of our time
public class FaseHard1 extends Musica {

    public FaseHard1(){
        super("musicas/UmMinutoParaOFimDoMundo.mid", "videos/UmMinutoParaOFimDoMundo.mpg", "imagens/FOTOS_BANDAS/cpm22/cpm1.png", "imagens/FOTOS_BANDAS/cpm22/cpm2.png", 5);
    }

    public void gameOver() {
        GameOver.getInstance().setMusica(21);
        GameEngine.getInstance().setNextGameStateController(24);
    }

    public void nextMusic() {
        GameEngine.getInstance().setNextGameStateController(22);
        
    }
}