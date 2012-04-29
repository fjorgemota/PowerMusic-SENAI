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
import javaPlay.GameStateController;
import java.awt.Graphics;
import javaPlay.GameEngine;
import javaPlay.GameStateController;
import javaPlayExtras.Imagem;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import utilidades.Utilidades;
public class FaseHard3 extends Musica {

    public FaseHard3(){
        super("musicas/HeroesOfOurTime.mid", "videos/HeroesOfOurTime.mpg", "img_cenario/FOTOS_BANDAS/DragonForce/dragon1.png", "img_cenario/FOTOS_BANDAS/DragonForce/dragon2.png", 5);
    }

    public void gameOver() {
        GameOver.getInstance().setMusica(23);
        GameEngine.getInstance().setNextGameStateController(24);
    }

    public void nextMusic() {
        Utilidades.alertar("Parabens! Voce acabou de concluir o nivel 'Dificil'!");
        GameEngine.getInstance().setNextGameStateController(2);
        
    }
}
