package fases;

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

//MÚSICAS DA FASE
//Gone
//Tnt
//Independencia

public class FaseEasy1 extends Musica {

    public FaseEasy1(){
        super("musicas/TNT.mid", "videos/TNT.mpg", "img_cenario/FOTOS_BANDAS/acdc/acdc1.png", "img_cenario/FOTOS_BANDAS/acdc/acdc2.png", 3);
    }

    public void gameOver() {
        GameOver.getInstance().setMusica(15);
        GameEngine.getInstance().setNextGameStateController(24);
    }

    public void nextMusic() {
        GameEngine.getInstance().setNextGameStateController(16);
        
    }
}
