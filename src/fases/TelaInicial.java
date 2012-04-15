package fases;

import java.awt.Graphics;
import javaPlay.GameEngine;
import javaPlay.GameObject;
import javaPlay.GameStateController;
import javaPlay.Mouse;
import javaPlayExtras.Imagem;
import javax.swing.JOptionPane;
import utilidades.Utilidades;

public class TelaInicial implements GameStateController {

    private Imagem bgImageHelp;
    private Imagem bgImage;
    private Mouse mouse;

    public void load() {
        try {
            this.bgImageHelp = new Imagem("img_cenario/help.png");
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
        if(Utilidades.estaClicandoEm(600, 490, 89, 75)){
            GameEngine.getInstance().setNextGameStateController(3);
        }
    }

    public void draw(Graphics g) {
        g.fillRect(0, 0, 3000, 2400);

        this.bgImage.draw(g, 0, 0);
        this.bgImageHelp.draw(g, 600, 490);
    }

    public void stop() {
    }
}
