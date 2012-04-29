/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fases;

import guitarra.Guitarra;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaPlay.GameEngine;
import javaPlay.GameStateController;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import javax.swing.JFileChooser;
import utilidades.Utilidades;

/**
 *
 * @author fernando_mota
 */
public class MusicMIDI extends Musica{
    public static MusicMIDI instancia;
    private int level;
    private String musicFile;
    public MusicMIDI(){
    }
    public static MusicMIDI getInstance(){
        if(MusicMIDI.instancia == null){
            MusicMIDI.instancia = new MusicMIDI();
        }
        return MusicMIDI.instancia;
    }
    public void setLevel(int level){
        this.level = level;
    }
    public void setMusic(String musicFile){
        this.musicFile = musicFile;
    }
    public void start(){
        this.setMusica(this.musicFile,this.level);
        super.start();
    }
    public void gameOver() {
        GameOver.getInstance().setMusica(25);
        GameEngine.getInstance().setNextGameStateController(24);
    }

    public void nextMusic() {
        GameEngine.getInstance().setNextGameStateController(25);
        
    }
}
