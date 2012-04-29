 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package powermusic;

import fases.*;
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
        GameEngine.getInstance().addGameStateController(24, GameOver.getInstance());
        GameEngine.getInstance().addGameStateController(25, MusicMIDI.getInstance());
        GameEngine.getInstance().setStartingGameStateController(2);
        GameEngine.getInstance().setFramesPerSecond(200);
        GameEngine.getInstance().run();
    }
}
