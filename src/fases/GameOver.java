/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fases;

import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaPlay.GameEngine;
import javaPlay.GameStateController;
import javaPlayExtras.Imagem;
import utilidades.Utilidades;

/**
 *
 * @author Samara
 */
<<<<<<< HEAD
import java.awt.Graphics;
import javaPlay.GameEngine;
import javaPlay.GameStateController;
import javaPlayExtras.Imagem;
import javax.swing.JOptionPane;
import utilidades.Utilidades;

/**
 *
 * @author Samara
 */
public class GameOver implements GameStateController{
  
    private Imagem bgImagegameover;
    private Imagem bgImageReiniciar;
    private Imagem bgImageReiniciar1;
    private Imagem bgImageReiniciar2;
    
    public void load() {
        
         try {

            this.bgImagegameover = new Imagem("img_cenario/gameover.png");
            
            

             this.bgImageReiniciar1 = new Imagem("img_cenario/reiniciar.png");
             this.bgImageReiniciar2 = new Imagem("img_cenario/efeito_reiniciar.png");
             this.bgImageReiniciar =this.bgImageReiniciar1;
             






        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

=======
public class GameOver implements GameStateController{
    private Imagem imagem;
    public void load() {
        try {
            this.imagem = new Imagem("img_cenario/gameover.png");
        } catch (Exception ex) {
            Utilidades.alertar("Erro ao carregar a imagem de GameOver: "+ex.getMessage());
        }
    }

    public void unload() {
    }

    public void start() {
        
    }

    public void step(long timeElapsed) {
        if(Utilidades.estaClicandoEm(0, 0, 800, 620)){
            GameEngine.getInstance().setNextGameStateController(2);
        }
    }

    public void draw(Graphics g) {
        this.imagem.draw(g, 0, 0);
    }

    public void stop() {
>>>>>>> aef3ee6bd05ae587af52c647a732edd99fc3c4cb
    }
    
   

    
    public void unload() {
    }

    
    public void start() {     
    }
    
    
    public void step(long timeElapsed) {
        
        
        if (Utilidades.estaClicandoEm(60, 535, 140, 60)) {
            GameEngine.getInstance().setNextGameStateController(2);
        }

        if (Utilidades.estaComOMouseEm(60, 535, 140, 60)) {
            this.bgImageReiniciar = this.bgImageReiniciar1;
        } else {
            this.bgImageReiniciar = this.bgImageReiniciar2;
        }
        
    }

    
    public void draw(Graphics g) {
       
        g.fillRect(0, 0, 3000, 2400);
        this.bgImagegameover.draw(g, 0, 0);
        
        this.bgImageReiniciar.draw(g, 60, 535);
    }

    
    public void stop() {
        
    }
}
    



