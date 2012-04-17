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

    private Imagem bgImageFundo;
    private Imagem bgImageNewGame;
    private Imagem bgImageExit;
    private Imagem bgImage;

    private Mouse mouse;

    public void load() {
        try {
            this.bgImageHelp = new Imagem("img_cenario/help.png");

            this.bgImageFundo = new Imagem("img_cenario/logicamentefeito.png");

            this.bgImageNewGame = new Imagem("img_cenario/newgame.png");
            this.bgImageExit = new Imagem("img_cenario/exit.png");
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
        
        if(Utilidades.estaClicandoEm(548,385,200,75)){
            GameEngine.getInstance().setNextGameStateController(4);
        }
        
        if(Utilidades.estaClicandoEm(590, 466, 89, 75)){
            GameEngine.getInstance().setNextGameStateController(3);
        }
                
        if(Utilidades.estaClicandoEm(590,555,89,75)){
            System.exit(0);
        }
    }

    public void draw(Graphics g) {
        g.fillRect(0, 0, 3000, 2400);


        this.bgImageFundo.draw(g, 0, 0);
        this.bgImageHelp.draw(g, 450, 290);
        this.bgImage.draw(g, 0, 0);
        this.bgImageNewGame.draw(g,548, 385);
        this.bgImageHelp.draw(g,590, 466);
        this.bgImageExit.draw(g,590, 555);

    }

    public void stop() {
    }
}
