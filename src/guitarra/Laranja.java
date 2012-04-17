/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guitarra;

/**
 *
 * @author fernando_mota
 */
class Laranja extends Esfera {
    protected static int serie;
    public Laranja(){
        super();
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
