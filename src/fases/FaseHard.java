

package fases;

import java.awt.Graphics;
import javaPlay.GameStateController;


public class FaseHard implements GameStateController {

    @Override
    public void load() {
       
    }

    @Override
    public void unload() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void start() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void step(long timeElapsed) {
       System.exit(0);
    }

    @Override
    public void draw(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void stop() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
