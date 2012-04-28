/*
 * Implementacao de teste duma esfera estendida
 */
package guitarra;

import javaPlay.Sprite;
import javaPlayExtras.Imagem;
import utilidades.Utilidades;

/**
 *
 * @author fernando_mota
 * 
 */
public class AmarelaEstendida extends Esfera{
    protected static int serie;
    private static Sprite imagemCache;
    public AmarelaEstendida(){
        super();
        if(AmarelaEstendida.imagemCache==null){
            try{
                //AmarelaEstendida.imagemCache = new Sprite("img_cenario/amareloinicio"+(this.isEspecial()?"-especial":"")+".png");
            }
            catch(Exception e){
                Utilidades.alertar(e.getMessage());
            }
        }
        this.imagem = AmarelaEstendida.imagemCache;
    }
    public AmarelaEstendida(int serie){
        this();
        Azul.serie = serie;
    }
    public int getSerie(){
        return AmarelaEstendida.serie;
    }
    public void setSerie(int serie){
        AmarelaEstendida.serie = serie;
    }

    public Esfera getNewInstance() {
        return new AmarelaEstendida();
    }

    public void step(long timeElapsed) {
       
    }
    public void pressionar() {
      //  super.prePressionar();
        //Adiciona efeito aqui
    }
}
