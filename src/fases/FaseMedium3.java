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
public class FaseMedium3 extends Musica {

    public FaseMedium3(){
         super("musicas/ItsMyLife.mid", "videos/ItsMyLife.mpg", "img_cenario/FOTOS_BANDAS/bon_jovi/bonjovi1.png", "img_cenario/FOTOS_BANDAS/bon_jovi/bonjovi2.png", 4);
    }

    public void gameOver() {
        GameOver.getInstance().setMusica(19);
        GameEngine.getInstance().setNextGameStateController(24);
    }

    public void nextMusic() {
        Utilidades.alertar("Parabens! Voce acabou de concluir o nivel 'Medio'!");
        GameEngine.getInstance().setNextGameStateController(2);
        
    }
}
