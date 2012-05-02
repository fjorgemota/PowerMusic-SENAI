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
public class FaseMedium2 extends Musica {

    public FaseMedium2(){
        super("musicas/AdmiravelChipNovo.mid", "videos/AdmiravelChipNovo.mpg", "imagens/FOTOS_BANDAS/Pitty/pitty1.png", "imagens/FOTOS_BANDAS/Pitty/pitty2.png", 4);
    }

    public void gameOver() {
        GameOver.getInstance().setMusica(19);
        GameEngine.getInstance().setNextGameStateController(24);
    }

    public void nextMusic() {
        GameEngine.getInstance().setNextGameStateController(20);
        
    }
}