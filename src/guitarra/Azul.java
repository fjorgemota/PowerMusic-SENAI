/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guitarra;

/**
 *
 * @author fernando_mota
 */
class Azul extends Esfera {
    protected static int serie;
    public Azul(){
        super();
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
