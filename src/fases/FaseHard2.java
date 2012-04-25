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

public class FaseHard2 implements GameStateController {

    private Imagem bgImageFundo;
    private Imagem bgImagePlay;
    private Imagem bgImagePlay1;
    private Imagem bgImagePlay2;
    private Imagem bgImageGuitarra;
    private Imagem bgImagePlayEfeito;
    private Imagem bgImageFundo1;
    private Imagem bgImageFundo2;

    public void load() {

        try {

            this.bgImageFundo = new Imagem("img_cenario/fundo.png");

            this.bgImagePlay1 = new Imagem("img_cenario/play.png");
            this.bgImagePlay2 = new Imagem("img_cenario/play_efeito.png");
            this.bgImagePlay = this.bgImagePlay1;


            this.bgImagePlay = new Imagem("img_cenario/play.png");
            this.bgImageFundo1 = new Imagem("img_cenario/FOTOS_BANDAS/cpm22/cpm1.png");
            this.bgImageFundo2 = new Imagem("img_cenario/FOTOS_BANDAS/cpm22/cpm2.png");
            this.bgImagePlayEfeito = new Imagem("img_cenario/play.png");




        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }

    public void unload() {
    }

    public void start() {
    }

    public void step(long timeElapsed) {

        if (Utilidades.estaClicandoEm(200, 300, 89, 75)) {
            GameEngine.getInstance().setNextGameStateController(9);
        }

        if (Utilidades.estaComOMouseEm(200, 300, 89, 75)) {
            this.bgImagePlay = this.bgImagePlay2;
        } else {
            this.bgImagePlay = this.bgImagePlay1;
        }
    }

    public void draw(Graphics g) {
        g.fillRect(0, 0, 3000, 2400);
        this.bgImageFundo.draw(g, 0, 0);

        this.bgImagePlay.draw(g, 200, 300);
        this.bgImageFundo1.draw(g, 0, 0);
        this.bgImageFundo2.draw(g, 426, 0);
    }

    public void stop() {
    }
}

