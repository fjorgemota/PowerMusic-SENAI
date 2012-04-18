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
class Azul extends Esfera {
    protected static int serie;
    protected static Imagem imagemCache;
    public Azul(){
        super();
        if(Azul.imagemCache==null){
            try{
                Azul.imagemCache = new Imagem("img_cenario/azul"+(this.isEspecial()?"-especial":"")+".png");
            }
            catch(Exception e){
                Utilidades.alertar(e.getMessage());
            }
        }
        this.imagem = Azul.imagemCache;
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
    
}
