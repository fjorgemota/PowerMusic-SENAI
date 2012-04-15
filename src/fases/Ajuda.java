package fases;

import java.awt.Color;
import java.awt.Graphics;
import javaPlay.GameObject;
import javaPlay.GameStateController;
import javaPlay.Mouse;
import javaPlayExtras.Imagem;
import javax.swing.JOptionPane;
import utilidades.Utilidades;

public class Ajuda implements GameStateController {

    private Imagem bgImage;
    private Mouse mouse;

    public void load() {
        try {
            this.bgImage = new Imagem("img_cenario/logicamentefeito.png");
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
        g.setColor(Color.black);
        g.fillRect(0, 0, 3000, 2400);
    }

    public void stop() {
    }
}
