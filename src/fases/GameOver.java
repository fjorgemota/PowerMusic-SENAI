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
    private int faseMusica = 2;
    public static GameOver instancia;
    public static GameOver getInstance() {
        if(GameOver.instancia == null){
            GameOver.instancia = new GameOver();
        }
        return GameOver.instancia;
    }
    public void setMusica(int musica){
        this.faseMusica = musica;
    }
    public void load() {
        
         try {

            this.bgImagegameover = new Imagem("img_cenario/gameover.png");
            
            

             this.bgImageReiniciar1 = new Imagem("img_cenario/reiniciar.png");
             this.bgImageReiniciar2 = new Imagem("img_cenario/efeito_reiniciar.png");
             this.bgImageReiniciar =this.bgImageReiniciar1;
             


        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
                
    }

     
   
     public void unload() {
    }

    
    public void start() {     
    }
    
    
    public void step(long timeElapsed) {
        
        
        if (Utilidades.estaClicandoEm(60, 535, 140, 60)) {
            GameEngine.getInstance().setNextGameStateController(this.faseMusica);
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
    



