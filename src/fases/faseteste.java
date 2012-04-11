/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fases;

import java.awt.Color;
import java.awt.Graphics;
import javaPlay.GameStateController;
import javax.swing.JOptionPane;
import utilidades.Utilidades;

/**
 *
 * @author fernando_mota
 */
public class faseteste implements GameStateController{

    @Override
    public void load() {
    }

    @Override
    public void unload() {
    }

    @Override
    public void start() {
    }

    @Override
    public void step(long timeElapsed) {
        if(Utilidades.estaClicandoEm(50,50,100,100)){
            JOptionPane.showMessageDialog(null, "Testando");
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawRect(0, 0, 800, 600);
        g.setColor(Color.BLACK);
        g.drawRect(50, 50, 100, 100);
    }

    @Override
    public void stop() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
