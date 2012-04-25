package fases;

import java.awt.Graphics;
import javaPlay.GameEngine;
import javaPlay.GameStateController;
import javaPlayExtras.Imagem;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import utilidades.Utilidades;

//MÚSICAS DA FASE


public class FaseEasy1 implements GameStateController {

    private Imagem bgImageFundo;
    private Imagem bgImagePlay;
    private Imagem bgImagePlay1;
    private Imagem bgImagePlay2;
    private Imagem bgImageGuitarra;
    private Imagem bgImagePlayEfeito;
    private Imagem bgImageFundoEsquerda;
    private Imagem bgImageFundoDireita;

    public void load() {

        try {

            this.bgImageFundo = new Imagem("img_cenario/fundo.png");



             this.bgImagePlay1 = new Imagem("img_cenario/play.png");
             this.bgImagePlay2= new Imagem("img_cenario/play_efeito.png");
             this.bgImagePlay = this.bgImagePlay1;
            

             this.bgImagePlay = new Imagem("img_cenario/play.png");
              this.bgImageFundoEsquerda = new Imagem("img_cenario/FOTOS_BANDAS/acdc/acdc1.png");
             this.bgImageFundoDireita = new Imagem("img_cenario/FOTOS_BANDAS/acdc/acdc2.png");
             this.bgImagePlayEfeito = new Imagem("img_cenario/play.png");

            this.bgImagePlay1 = new Imagem("img_cenario/play.png");
            this.bgImagePlay2 = new Imagem("img_cenario/play_efeito.png");
            this.bgImagePlay = this.bgImagePlay1;


          




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
        this.bgImageFundoEsquerda.draw(g, 0, 0);
        this.bgImageFundoDireita.draw(g, 427, 0);
    }

    public void stop() {
    }
}
