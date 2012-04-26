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

public class IntervaloInicial implements GameStateController {

    private Imagem bgImageEasy1;
    private Imagem bgImagePlay;
    private Imagem bgImagePlay1;
    private Imagem bgImagePlay2;
    private Imagem bgImageVoltar;
    private Imagem bgImageVoltar1;
    private Imagem bgImageVoltar2;
    
    public void load() {
        
         try {

            this.bgImageEasy1 = new Imagem("img_cenario/Intervalos/easy1.png");


             this.bgImagePlay1 = new Imagem("img_cenario/play.png");
             this.bgImagePlay2= new Imagem("img_cenario/play_efeito.png");
             this.bgImagePlay = this.bgImagePlay1;
            

             this.bgImageVoltar1 = new Imagem("img_cenario/voltar.png");
             this.bgImageVoltar2 = new Imagem("img_cenario/voltar2.png");
             this.bgImageVoltar =this.bgImageVoltar1;
             






        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }
    
   

    
    public void unload() {
    }

    
    public void start() {     
    }
    
    
    public void step(long timeElapsed) {
        
        if (Utilidades.estaClicandoEm(650, 520, 89, 75)) {
            GameEngine.getInstance().setNextGameStateController(5);
        }

        if (Utilidades.estaComOMouseEm(650, 520, 89, 75)) {
            this.bgImagePlay = this.bgImagePlay2;
        } else {
            this.bgImagePlay = this.bgImagePlay1;
        }
        
        if (Utilidades.estaClicandoEm(50, 520, 89, 75)) {
            GameEngine.getInstance().setNextGameStateController(2);
        }

        if (Utilidades.estaComOMouseEm(50, 520, 89, 75)) {
            this.bgImageVoltar = this.bgImageVoltar2;
        } else {
            this.bgImageVoltar = this.bgImageVoltar1;
        }
        
    }

    
    public void draw(Graphics g) {
       
        g.fillRect(0, 0, 3000, 2400);
        this.bgImageEasy1.draw(g, 0, 0);

        this.bgImagePlay.draw(g,650, 520);
        
        this.bgImageVoltar.draw(g, 50, 520);
    }

    
    public void stop() {
        
    }
}