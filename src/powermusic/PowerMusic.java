/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package powermusic;
import fases.TelaInicial;
import fases.Ajuda;
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
        GameEngine.getInstance().addGameStateController( 1 , new TelaInicial());
        GameEngine.getInstance().addGameStateController( 2 , new Ajuda());
        GameEngine.getInstance().setStartingGameStateController( 1 );
        
        GameEngine.getInstance().setFramesPerSecond( 30 );

        GameEngine.getInstance().run();
    }
}
