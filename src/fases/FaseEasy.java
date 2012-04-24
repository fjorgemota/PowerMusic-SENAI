package fases;

import java.awt.Graphics;
import javaPlay.GameEngine;
import javaPlay.GameStateController;
import javaPlayExtras.Imagem;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class FaseEasy implements GameStateController {

    private Imagem bgImageFundo;
    private Imagem bgImagePlay;
    private Imagem bgImageGuitarra;

    public void load() {
       JPanel pteste = new JPanel();
        JLabel lteste = new JLabel("Teste");
        lteste.setVisible(true);
        lteste.setBounds(50,20,100,100);
        pteste.add(lteste);
        pteste.setBounds(0,0,100, 100);
        pteste.setVisible(true);
        
        GameEngine.getInstance().getGameCanvas().setPanel(pteste);
        try {

            this.bgImageFundo = new Imagem("img_cenario/fundo.png");
            this.bgImageGuitarra = new Imagem("img_cenario/guitarra_fundo.png");

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
          this.bgImageGuitarra.draw(g, 50, 0);

    }

    public void stop() {
    }
}
