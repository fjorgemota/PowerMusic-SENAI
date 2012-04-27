/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guitarra;

import javaPlay.Sprite;
import javaPlayExtras.Imagem;
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
    public Esfera getNewInstance(){
        return new Azul();
    }
    public void step(long timeElapsed){
        super.preLocate(timeElapsed);
        this.x -= 45+(20/620.000f)*this.y;
    }
    
}
