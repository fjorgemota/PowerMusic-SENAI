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
import javaPlay.GameEngine;
import javaPlay.GameObject;
import javaPlay.Keyboard;
import javaPlay.Sprite;
import javaPlayExtras.Imagem;
import javaPlayExtras.Keys;
import utilidades.Utilidades;

/**
 *
 * @author fernando_mota
 * [[0,4],[1,2,3],[2,7]]
 */
public abstract class Esfera extends GameObject {

    protected boolean especial;
    protected Sprite imagem;
    protected boolean pressionado;
    protected Esfera anterior;
    protected Esfera proximo;
    protected int second;
    private int width = 40;
    private int margem = 15;
    private int height;
    private Rectangle rect;
    private int tecla = 0;
    private Sprite explosao;
    Keyboard teclado = GameEngine.getInstance().getKeyboard();
    private int frame;
    private int timeElapsedInMiliseconds;

    public Esfera() {
        this.especial = Guitarra.getInstance().podeEspecial();
        this.second = Guitarra.getInstance().getSecondsElapsed();
        try {
            this.explosao = new Sprite("img_cenario/explosao.png", 24, 65, 48);
        } catch (Exception ex) {
            Utilidades.alertar(ex.getMessage());
        }
    }

    public abstract int getSerie();

    public boolean isEspecial() {
        return this.especial;
    }

    public int getPontos() {
        return 10 + (this.especial ? 5 : 0);
    }

    public abstract void setSerie(int serie);

    public void pressionar() {
        Guitarra.getInstance().adicionaPontos(this.getPontos());
        this.pressionado = true;
    }
    //public abstract void pressionar();

    public abstract void step(long timeElapsed);

    public boolean podePressionar() {
        return this.getY() >= 414 && this.getY() <= 450;
    }

    public void preLocate(long timeElapsed) {
        this.y += 1;
        this.imagem.setCurrAnimFrame(this.getCurrentStep());
        //A linha abaixo captura o valor do atributo estatico 'serie' e 
        //multiplica pela largura de cada botao, somando com a margem
        //e posicionando a esfera corretamente
        this.x = 82 + this.getSerie() * this.width + this.margem;
        //Este metodo e sobreescrito e re-utilizado para permitir o alinhamento
        //Correto dos botoes
        Keyboard teclado = GameEngine.getInstance().getKeyboard();
        if (teclado.keyDown(this.tecla)) {
            this.pressionar();
        }
        if (this.foiPressionado()) {
            if (this.frame >= 24) {
                return; //Parou animação
            }
            this.timeElapsedInMiliseconds += timeElapsed*100;
            if (this.timeElapsedInMiliseconds > 100) {
                this.frame = (this.frame + 1);
                this.explosao.setCurrAnimFrame(this.frame);
                this.timeElapsedInMiliseconds -= 100;
            }
        }
    }

    public int getCurrentStep() {
        return Math.round(this.y / 40);
    }

    public void draw(Graphics g) {
        this.imagem.draw(g, this.x, this.y);
        if (this.foiPressionado()) {

            this.explosao.draw(g, this.x, this.y);

        }
    }

    public Rectangle getRectangle() {
        if (this.rect == null) {
            this.rect = new Rectangle(this.width, this.height);
        }
        this.rect.setLocation(this.x, this.y);
        return this.rect;
    }

    public String getCor() {
        return this.getClass().getName();
    }

    public abstract Esfera getNewInstance();

    boolean foiPressionado() {

        return this.pressionado;
    }

    public int getSecond() {
        return this.second;
    }
}
