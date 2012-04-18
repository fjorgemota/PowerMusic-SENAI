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
class Amarela extends Esfera {
    protected static int serie;
    protected static Imagem imagemCache;
    public Amarela(){
        super();
        if(Amarela.imagemCache==null){
            try{
                Amarela.imagemCache = new Imagem("img_cenario/amarelo"+(this.isEspecial()?"-especial":"")+".png");
            }
            catch(Exception e){
                Utilidades.alertar(e.getMessage());
            }
        }
        this.imagem = Amarela.imagemCache;
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
    public Esfera getNewInstance(){
        return new Amarela();
    }
    
}
