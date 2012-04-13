/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guitarra;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaPlay.GameObject;
import utilidades.Utilidades;

/**
 *
 * @author fernando_mota
 */
public abstract class Esfera extends GameObject{
    protected boolean especial;
    protected static int serie;
    protected Color cor;
    protected boolean pressionado;
    private int width = 40;
    private int margem = 15;
    private int height;
    private Rectangle rect;
    public Esfera(int serie){
        this();
        this.serie = serie;
    }
    public Esfera(){
        this.especial = Guitarra.getInstance().podeEspecial();
    }
    public int getSerie(){
         try {
            return this.getClass().getField("serie").getInt(null);
        } catch (Exception ex) {
            return 0;
        } 
    }
    public boolean isEspecial(){
        return this.especial;
    }
    public int getPontos(){
        return 10+(this.especial?5:0);
    }
    public Esfera getNewInstance(){
        try {
            return this.getClass().newInstance();
        } catch (Exception ex) {
            return null;
        } 
    }
    public void setSerie(int serie){
        try {
            this.getClass().getField("serie").set(null, serie);
        } catch (Exception ex) {
            return;
        } 
    }
    public void pressionar(){
        Guitarra.getInstance().adicionaPontos(this.getPontos());
        this.pressionado = true;
        
    }
    public void step(long timeElapsed) {
        this.y += 10; 
        try {
            //A linha abaixo captura o valor do atributo estatico 'serie' e 
            //multiplica pela largura de cada botao, somando com a margem
            //e posicionando a esfera corretamente
            this.x = this.getClass().getField("serie").getInt(null)*this.width+this.margem;
        } catch (Exception ex) {
        } 
    }

    public void draw(Graphics g) {
    }
    public Rectangle getRectangle(){
        if(this.rect == null){
            this.rect = new Rectangle(this.width, this.height);
        }
        this.rect.setLocation(this.x, this.y);
        return this.rect;
    }
}
