/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guitarra;

import java.awt.event.KeyEvent;
import javaPlay.Keyboard;
import javaPlay.Sprite;
import javaPlayExtras.Imagem;
import javaPlayExtras.Keys;
import utilidades.Utilidades;

/**
 *
 * @author fernando_mota
 */
class Vermelha extends Esfera {
    protected static int serie;
    protected int tecla = Keys.S;
    public Vermelha(){
        super();
        try{
            this.imagem = new Sprite("img_cenario/Sprites/"+(this.isEspecial()?"efeito":"sprite")+"_vermelho.png",9,49,28);
        }
        catch(Exception e){
            Utilidades.alertar(e.getMessage());
        }
    }
    public Vermelha(int serie){
        this();
        Vermelha.serie = serie;
    }
    public int getSerie(){
        return Vermelha.serie;
    }
    public void setSerie(int serie){
        Vermelha.serie = serie;
    }
    public Vermelha getNewInstance(){
        return new Vermelha();
    }
    public void step(long timeElapsed){
        if (teclado.keyDown(this.tecla) && this.podePressionar()) {
            this.pressionar();
        }
        super.preLocate(timeElapsed);
        this.x -= (122/620.000f)*this.y;
    }
}
