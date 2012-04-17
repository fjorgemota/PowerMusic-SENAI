/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guitarra;

/**
 *
 * @author fernando_mota
 */
class Vermelha extends Esfera {
    protected static int serie;
    public Vermelha(){
        super();
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
