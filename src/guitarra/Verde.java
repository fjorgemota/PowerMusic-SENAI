/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guitarra;

/**
 *
 * @author fernando_mota
 */
class Verde extends Esfera {
    protected static int serie;
    public Verde(){
        super();
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
