/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package powermusic;
import fases.faseteste;
import javaPlay.GameEngine;
/**
 *
 * @author fernando_mota
 */
public class PowerMusic {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Fase umaFase = new Fase();       
        GameEngine.getInstance().addGameStateController( 1 , new faseteste());
        
        GameEngine.getInstance().setStartingGameStateController( 1 );
        
        GameEngine.getInstance().setFramesPerSecond( 30 );
        GameEngine.getInstance().run();
    }
}
