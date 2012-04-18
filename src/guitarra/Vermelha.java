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
class Vermelha extends Esfera {
    protected static int serie;
    protected static Imagem imagemCache;
    public Vermelha(){
        super();
        if(Vermelha.imagemCache==null){
            try{
                Vermelha.imagemCache = new Imagem("img_cenario/vermelha"+(this.isEspecial()?"-especial":"")+".png");
            }
            catch(Exception e){
                Utilidades.alertar(e.getMessage());
            }
        }
        this.imagem = Vermelha.imagemCache;
    }
    public Vermelha(int serie){
        this();
        Vermelha.serie = serie;
    }
    public int getSerie(){
        return Vermelha.serie;
    }
    public void setSerie(int serie){
        Vermelha.serie = serie;
    }
    public Esfera getNewInstance(){
        return new Vermelha();
    }
}
