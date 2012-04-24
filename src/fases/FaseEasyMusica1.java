/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fases;

import java.awt.Graphics;
import javaPlay.GameStateController;
import javaPlayExtras.Imagem;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class FaseEasyMusica1 implements GameStateController {

    private Imagem bgImageFundo;


    public void load() {

        try {

            this.bgImageFundo = new Imagem("img_cenario/fundo.png");
           

            

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
        this.bgImageFundo.draw(g, 0, 0);
    }

    @Override
    public void stop() {
      
    }

   
    
}
