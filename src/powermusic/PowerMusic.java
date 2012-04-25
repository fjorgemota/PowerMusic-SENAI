 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package powermusic;
import fases.MenuFases;
import fases.TelaInicial;
import fases.Ajuda;
import fases.FaseEasy;
import fases.FaseEasyMusica1;
import fases.FaseHard;
import fases.FaseMedium;
import fases.MenuMidi;
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
        //GameEngine.getInstance().addGameStateController( 1 , new faseteste());
        GameEngine.getInstance().addGameStateController( 2 , new TelaInicial());
        GameEngine.getInstance().addGameStateController( 3 , new Ajuda());
        GameEngine.getInstance().addGameStateController( 4 , new MenuFases());
        GameEngine.getInstance().addGameStateController( 5 , new FaseEasy());
        GameEngine.getInstance().addGameStateController( 6 , new FaseMedium());
        GameEngine.getInstance().addGameStateController( 7 , new FaseHard());
        GameEngine.getInstance().addGameStateController( 8 , new MenuMidi());
        GameEngine.getInstance().addGameStateController( 9 , new FaseEasyMusica1());
        GameEngine.getInstance().setStartingGameStateController( 5 );
        
        GameEngine.getInstance().setFramesPerSecond( 10 );

        GameEngine.getInstance().run();
    }
}
