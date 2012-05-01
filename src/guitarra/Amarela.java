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
    public Amarela(){
        super();
        try{
            this.imagem = new Sprite("imagens/Sprites/"+(this.isEspecial()?"efeito":"sprite")+"_amarelo.png",9,49,28);
        }
        catch(Exception e){
            Utilidades.alertar(e.getMessage());
        }
        this.tecla = Keys.D;
    }
    public Amarela(float second){
        super(second);
        try{
            this.imagem = new Sprite("imagens/Sprites/"+(this.isEspecial()?"efeito":"sprite")+"_amarelo.png",9,49,28);
        }
        catch(Exception e){
            Utilidades.alertar(e.getMessage());
        }
        this.tecla = Keys.D;
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
    public Esfera getNewInstance(float second){
        return new Amarela(second);
    }
    public void step(long timeElapsed){
        if (teclado.keyDown(this.tecla) && this.podePressionar()) {
            this.pressionar();
        }
        super.preLocate(timeElapsed);
        this.x -= 20+((70/620.0000f)*this.y);
    }
   
}
