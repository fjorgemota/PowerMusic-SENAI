/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guitarra;

import javaPlay.Sprite;
import javaPlayExtras.Imagem;
import javaPlayExtras.Keys;
import sun.net.www.http.KeepAliveCache;
import utilidades.Utilidades;

/**
 *
 * @author fernando_mota
 */
class Verde extends Esfera {
    protected static int serie;
    protected int tecla = Keys.A;
    public Verde(){
        super();
        try{
            this.imagem = new Sprite("img_cenario/Sprites/"+(this.isEspecial()?"efeito":"sprite")+"_verde.png",9,49,28);
        }
        catch(Exception e){
            Utilidades.alertar(e.getMessage());
        }
    }
    public Verde(int serie){
        this();
        Verde.serie = serie;
    }
    public int getSerie(){
        return Verde.serie;
    }
    public void setSerie(int serie){
        Verde.serie = serie;
    }
    public Esfera getNewInstance(){
        System.out.println("Retornando nova instancia de esfera Verde");
        return new Verde();
    }
     public void step(long timeElapsed){
        super.preLocate(timeElapsed);
        this.x -= -20+((165/620.00000f)*this.y);
    }

    public void pressionar() {
        super.prePressionar();
        //Adiciona efeito aqui
    }

}
