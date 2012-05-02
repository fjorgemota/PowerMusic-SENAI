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
class Laranja extends Esfera {
    protected static int serie;

    protected static Sprite imagemCache;
    public Laranja(){
        super();
        try{
            this.imagem = new Sprite("imagens/Sprites/"+(this.isEspecial()?"efeito":"sprite")+"_laranja.png",9,49,28);
        }
        catch(Exception e){
            Utilidades.alertar(e.getMessage());
        }
        this.tecla = Keys.K;
    }
    public Laranja(float second){
        super(second);
        try{
            this.imagem = new Sprite("imagens/Sprites/"+(this.isEspecial()?"efeito":"sprite")+"_laranja.png",9,49,28);
        }
        catch(Exception e){
            Utilidades.alertar(e.getMessage());
        }
        this.tecla = Keys.K;
    }
    public Laranja(int serie){
        this();
        Laranja.serie = serie;
    }
    public int getSerie(){
        return Laranja.serie;
    }
    public void setSerie(int serie){
        Laranja.serie = serie;
    }
    public Esfera getNewInstance(float second){
        return new Laranja(second);
    }
    public void step(long timeElapsed){
        if (teclado.keyDown(this.tecla) && this.podePressionar()) {
            this.pressionar();
        }
        super.preLocate(timeElapsed);
        this.x += ((25/620.00000f)*this.y)-62;
    }
   
}
