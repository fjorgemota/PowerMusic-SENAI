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
    private int tecla = Keys.K;
    public Laranja(){
        super();
        try{
            this.imagem = new Sprite("img_cenario/Sprites/"+(this.isEspecial()?"efeito":"sprite")+"_laranja.png",9,49,28);
        }
        catch(Exception e){
            Utilidades.alertar(e.getMessage());
        }
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
    public Esfera getNewInstance(){
        return new Laranja();
    }
    public void step(long timeElapsed){
        super.preLocate(timeElapsed);
        this.x += ((25/620.00000f)*this.y)-62;
    }
    public void pressionar() {
        super.prePressionar();
        //Adiciona efeito aqui
    }
}
