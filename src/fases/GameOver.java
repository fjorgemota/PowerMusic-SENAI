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
    }
    
}
