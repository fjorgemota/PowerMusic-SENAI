package fases;

import java.awt.Component;
import java.awt.Graphics;
import javaPlay.GameEngine;
import javaPlay.GameStateController;
import javaPlayExtras.Imagem;
import javax.media.Player;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import utilidades.Utilidades;

public class FaseEasyMusica1 implements GameStateController {

    private Imagem bgImageFundoDireita;
    private Imagem bgImageFundoEsquerda;

    public void load() {


        try {

            this.bgImageFundoDireita = new Imagem("img_cenario/FOTOS_BANDAS/acdc/acdc2.png");
            this.bgImageFundoEsquerda = new Imagem("img_cenario/FOTOS_BANDAS/acdc/acdc1.png");



        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    @Override
    public void unload() {
    }

    @Override
    public void start() {

    }

    @Override
    public void step(long timeElapsed) {
    }

    @Override
    public void draw(Graphics g) {
        g.fillRect(0, 0, 3000, 2400);
        this.bgImageFundoDireita.draw(g, 427, 0);
        this.bgImageFundoEsquerda.draw(g, 0, 0);

    }

    @Override
    public void stop() {
    }
}
