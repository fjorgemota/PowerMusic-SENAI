/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guitarra;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javaPlay.GameObject;
import javaPlayExtras.Imagem;
import utilidades.Utilidades;

/**
 *
 * @author fernando_mota
 * [[0,4],[1,2,3],[2,7]]
 */
public abstract class Esfera extends GameObject{
    protected boolean especial;
    protected Imagem imagem;
    protected boolean pressionado;
    protected Esfera anterior;
    protected Esfera proximo;
    private int width = 40;
    private int margem = 15;
    private int height;
    private Rectangle rect;
    public Esfera(){
        this.especial = Guitarra.getInstance().podeEspecial();
    }
    public abstract int getSerie();
    public boolean isEspecial(){
        return this.especial;
    }
    public int getPontos(){
        return 10+(this.especial?5:0);
    }
    public abstract void setSerie(int serie);
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
            this.x = this.getSerie()*this.width+this.margem;
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

    public abstract Esfera getNewInstance();
}
