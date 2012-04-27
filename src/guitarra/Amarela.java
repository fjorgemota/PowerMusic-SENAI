/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guitarra;

import javaPlay.Sprite;
import javaPlayExtras.Imagem;
import javaPlayExtras.Keys;
import utilidades.Utilidades;

/**
 *
 * @author fernando_mota
 */
class Amarela extends Esfera {
    protected static int serie;
    protected int tecla = Keys.D;
    public Amarela(){
        super();
        try{
            this.imagem = new Sprite("img_cenario/Sprites/"+(this.isEspecial()?"efeito":"sprite")+"_amarelo.png",9,49,28);
        }
        catch(Exception e){
            Utilidades.alertar(e.getMessage());
        }
    }
    public Amarela(int serie){
        this();
        Amarela.serie = serie;
    }
    public int getSerie(){
        return Amarela.serie;
    }
    public void setSerie(int serie){
        Amarela.serie = serie;
    }
    public Esfera getNewInstance(){
        return new Amarela();
    }
    public void step(long timeElapsed){
        super.preLocate(timeElapsed);
        this.x -= 20+((70/620.0000f)*this.y);
         if(teclado.keyDown(  Keys.D ) && this.podePressionar()){
           this.pressionar();
        }
    }
   
}
