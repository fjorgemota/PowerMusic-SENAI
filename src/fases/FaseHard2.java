

package fases;

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
public class FaseHard2 extends Musica {
    public FaseHard2(){
        super("musicas/ThroughTheFireAndFlames.mid", "videos/ThroughTheFireAndFlames.mpg", "imagens/FOTOS_BANDAS/DragonForce/dragon3.png", "imagens/FOTOS_BANDAS/DragonForce/dragon4.png", 5);
    }
    public void gameOver() {
        GameOver.getInstance().setMusica(22);
        GameEngine.getInstance().setNextGameStateController(24);
    }

    public void nextMusic() {
        GameEngine.getInstance().setNextGameStateController(23);
        
    }
}