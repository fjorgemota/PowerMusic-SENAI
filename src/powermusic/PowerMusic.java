 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package powermusic;

import fases.MenuFases;
import fases.TelaInicial;
import fases.Ajuda;
import fases.FaseEasy1;
import fases.FaseEasy2;
import fases.FaseEasy3;

import fases.FaseHard2;
import fases.FaseHard1;
import fases.FaseHard3;
import fases.FaseMedium1;
import fases.FaseMedium2;
import fases.FaseMedium3;
import fases.GameOver;
import fases.IntervaloHard1;
import fases.IntervaloHard2;
import fases.IntervaloHard3;
import fases.MenuMidi;
import fases.IntervaloInicial;
import fases.IntervaloInicial2;
import fases.IntervaloInicial3;
import fases.IntervaloMedium1;
import fases.IntervaloMedium2;
import fases.IntervaloMedium3;
import fases.GameOver;
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
        GameEngine.getInstance().addGameStateController(2, new TelaInicial());
        GameEngine.getInstance().addGameStateController(3, new Ajuda());
        GameEngine.getInstance().addGameStateController(4, new MenuFases());
        GameEngine.getInstance().addGameStateController(5, new FaseEasy1());
        GameEngine.getInstance().addGameStateController(6, new FaseMedium1());
        GameEngine.getInstance().addGameStateController(7, new FaseHard1());
        GameEngine.getInstance().addGameStateController(8, new MenuMidi());
        GameEngine.getInstance().addGameStateController(9, new FaseEasy2());
        GameEngine.getInstance().addGameStateController(10, new FaseEasy3());
        GameEngine.getInstance().addGameStateController(11, new FaseMedium2());
        GameEngine.getInstance().addGameStateController(12, new FaseMedium3());
        GameEngine.getInstance().addGameStateController(13, new FaseHard2());
        GameEngine.getInstance().addGameStateController(14, new FaseHard3());
        GameEngine.getInstance().addGameStateController(15, new IntervaloInicial());
        GameEngine.getInstance().addGameStateController(16, new IntervaloInicial2());
        GameEngine.getInstance().addGameStateController(17, new IntervaloInicial3());
        GameEngine.getInstance().addGameStateController(18, new IntervaloMedium1());
        GameEngine.getInstance().addGameStateController(19, new IntervaloMedium2());
        GameEngine.getInstance().addGameStateController(20, new IntervaloMedium3());
        GameEngine.getInstance().addGameStateController(21, new IntervaloHard1());
        GameEngine.getInstance().addGameStateController(22, new IntervaloHard2());
        GameEngine.getInstance().addGameStateController(23, new IntervaloHard3());
        GameEngine.getInstance().addGameStateController(24, new GameOver());
<<<<<<< HEAD

=======
>>>>>>> aef3ee6bd05ae587af52c647a732edd99fc3c4cb
        GameEngine.getInstance().setStartingGameStateController(2);

        GameEngine.getInstance().setFramesPerSecond(10);

        GameEngine.getInstance().run();
    }
}
