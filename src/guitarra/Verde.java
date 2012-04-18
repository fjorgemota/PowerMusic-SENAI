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
class Verde extends Esfera {
    protected static int serie;
    protected static Imagem imagemCache;
    public Verde(){
        super();
        if(Verde.imagemCache==null){
            try{
                Verde.imagemCache = new Imagem("img_cenario/verde"+(this.isEspecial()?"-especial":"")+".png");
            }
            catch(Exception e){
                Utilidades.alertar(e.getMessage());
            }
        }
        this.imagem = Verde.imagemCache;
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
        return new Verde();
    }
}
