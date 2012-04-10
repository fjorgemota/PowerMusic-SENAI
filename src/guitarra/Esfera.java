/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guitarra;

import java.awt.Color;
import java.awt.Graphics;
import javaPlay.GameObject;

/**
 *
 * @author fernando_mota
 */
public class Esfera extends GameObject{
    protected boolean especial;
    protected int serie;
    protected Color cor;
    protected boolean pressionado;
    public Esfera(boolean especial, int serie){
        this.especial = especial;
        this.serie = serie;
    }
    public int getSerie(){
        return this.serie;
    }
    public boolean isEspecial(){
        return this.especial;
    }
    public int getPontos(){
        return 10+(this.especial?5:0);
    }
    public abstract Esfera getInstance();
    public void pressionar(){
        Guitarra.getInstance().adicionaPontos(this.getPontos());
        this.pressionado = true;
    }
    public void step(long timeElapsed) {
        this.y += 10; 
    }

    public void draw(Graphics g) {
    }
}
