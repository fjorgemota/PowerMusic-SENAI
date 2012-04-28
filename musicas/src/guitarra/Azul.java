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
class Azul extends Esfera {
    protected static int serie;
    public Azul(){
        super();
        try{
            this.imagem = new Sprite("img_cenario/Sprites/"+(this.isEspecial()?"efeito":"sprite")+"_azul.png",9,49,28);
        }
        catch(Exception e){
            Utilidades.alertar(e.getMessage());
        }
        this.tecla = Keys.J;
    }
    public Azul(float second){
        super(second);
        try{
            this.imagem = new Sprite("img_cenario/Sprites/"+(this.isEspecial()?"efeito":"sprite")+"_azul.png",9,49,28);
        }
        catch(Exception e){
            Utilidades.alertar(e.getMessage());
        }
        this.tecla = Keys.J;
    }
    public Azul(int serie){
        this();
        Azul.serie = serie;
    }
    public int getSerie(){
        return Azul.serie;
    }
    public void setSerie(int serie){
        Azul.serie = serie;
    }
    public Esfera getNewInstance(float second){
        return new Azul(second);
    }
    public void step(long timeElapsed){
        if (teclado.keyDown(this.tecla) && this.podePressionar()) {
            this.pressionar();
        }
        super.preLocate(timeElapsed);
        this.x -= 45+(20/620.000f)*this.y;
    }
   
    
}
