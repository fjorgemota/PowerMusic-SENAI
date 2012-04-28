

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

public class FaseHard2 implements GameStateController {

    private Imagem bgImageFundo;
   
    private Imagem bgImageGuitarra;

    private Imagem bgImageFundo1;
    private Imagem bgImageFundo2;

    public void load() {

        try {

            this.bgImageFundo = new Imagem("img_cenario/fundo.png");

         
            this.bgImageFundo1 = new Imagem("img_cenario/FOTOS_BANDAS/DragonForce/dragon3.png");
            this.bgImageFundo2 = new Imagem("img_cenario/FOTOS_BANDAS/DragonForce/dragon4.png");




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

