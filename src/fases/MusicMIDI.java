/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fases;

import guitarra.Guitarra;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaPlay.GameStateController;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Track;
import utilidades.Utilidades;

/**
 *
 * @author fernando_mota
 */
public class MusicMIDI implements GameStateController{
    private String filename;
    Guitarra guitarra;
    public MusicMIDI(String filename){
        this.filename = filename;
        this.guitarra = Guitarra.getInstance();
    }
    public String getFileName(){
        return this.filename;
    }
    public void load() {
        Sequence sequencia;
        try {
            sequencia = MidiSystem.getSequence(new File(this.getFileName()));
        } catch (Exception ex) {
            Utilidades.alertar(ex.getMessage());
            return;
        }
        int trackNumber = 0;
        for (Track track:  sequencia.getTracks()) {
            
        }
    }

    public void unload() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void start() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void step(long timeElapsed) {
        throw new UnsupportedOperationException("Not supported yet.");
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
