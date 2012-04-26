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
import javaPlay.GameStateController;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;
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
        int trackNumber = 0, program = 0;
        HashMap notesPlayed = new HashMap();
        ArrayList< ArrayList > notas = new ArrayList< ArrayList>(); // Cria um ArrayList com as notas, que devem vir a ser a matriz com as notas por si so
        int[] chords = new int[]{64, 69, 74, 79, 83, 88};
        for (Track track:  sequencia.getTracks()) {
            for(int c=0;c<track.size();++c){
                MidiEvent event = track.get(c);
                MidiMessage msg = event.getMessage();
                if(msg instanceof ShortMessage){
                    ShortMessage shortmsg = (ShortMessage) msg;
                    if(shortmsg.getCommand() == ShortMessage.PROGRAM_CHANGE){
                        program = shortmsg.getData1();
                    }
                    else if(program>=25 && program <= 32){
                        if(shortmsg.getCommand() == ShortMessage.NOTE_ON){
                            int note = shortmsg.getData1();
                            int noteChord;
                            for (int chord: chords){
                                if (note < chord){
                                    break;
                                      //  noteChord = chord; 
                                      
                                     
                            }
                        }
                    }
                }
            }
        }

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
