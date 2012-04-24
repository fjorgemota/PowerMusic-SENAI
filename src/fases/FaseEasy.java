package fases;

import java.awt.Graphics;
import javaPlay.GameEngine;
import javaPlay.GameStateController;
import javaPlayExtras.Imagem;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import utilidades.Utilidades;

public class FaseEasy implements GameStateController {

    private Imagem bgImageFundo;
    private Imagem bgImagePlay;
    private Imagem bgImageGuitarra;

    public void load() {
       
        try {

            this.bgImageFundo = new Imagem("img_cenario/fundo.png");
             this.bgImagePlay = new Imagem("img_cenario/play.png");
            this.bgImageGuitarra = new Imagem("img_cenario/guitarra_fundo.png");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }

    public void unload() {
    }

    public void start() {
     if (Utilidades.estaClicandoEm(330, 180, 400, 300)) {
           GameEngine.getInstance().setNextGameStateController(9);
        }
    }

    public void step(long timeElapsed) {
    }

    public void draw(Graphics g) {
        g.fillRect(0, 0, 3000, 2400);
        this.bgImageFundo.draw(g, 0, 0);
        this.bgImageGuitarra.draw(g, 50, 0);
        this.bgImagePlay.draw(g, 000, 300);

    }

    public void stop() {
    }
}
