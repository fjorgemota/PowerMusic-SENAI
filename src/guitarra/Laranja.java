/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guitarra;

import javaPlayExtras.Imagem;
import utilidades.Utilidades;

/**
 *
 * @author fernando_mota
 */
class Laranja extends Esfera {
    protected static int serie;
    protected static Imagem imagemCache;
    public Laranja(){
        super();
        if(Laranja.imagemCache==null){
            try{
                Laranja.imagemCache = new Imagem("img_cenario/laranja"+(this.isEspecial()?"-especial":"")+".png");
            }
            catch(Exception e){
                Utilidades.alertar(e.getMessage());
            }
        }
        this.imagem = Laranja.imagemCache;
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
}
