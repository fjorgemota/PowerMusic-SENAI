/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guitarra;

/**
 *
 * @author fernando_mota
 */
class Amarela extends Esfera {
    protected static int serie;
    public Amarela(){
        super();
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
