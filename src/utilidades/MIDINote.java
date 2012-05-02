/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import com.sun.media.sound.MidiUtils;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.*;

/**
 *
 * @author mota
 */
public class MIDINote {
    private int channel;
    private int note;
    private int velocity;
    private int status;
    private float second;
    public MIDINote(int channel, int note, int velocity, float second) {
        this.channel = channel;
        this.note = note;
        this.velocity = velocity;
        this.second = second;
        this.status = ShortMessage.NOTE_ON;
    }
    public MIDINote(MidiEvent ev, Sequence sequencia, MidiUtils.TempoCache tempoProcessor){
        MidiMessage msg = ev.getMessage();
        ShortMessage smsg = (ShortMessage)msg;
        this.channel = smsg.getChannel();
        this.note = smsg.getData1();
        this.velocity = smsg.getData2();
        this.status = smsg.getStatus();
        this.second =  MidiUtils.tick2microsecond(sequencia, ev.getTick(), tempoProcessor)/1000000.0f;
    }
    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public int getChord() {
        int[] chords = new int[]{21, 43, 63, 84, 106, 128};   //int[] chords = new int[]{64, 69, 74, 79, 83, 88};
        int noteChord = 1;
        for(int chord : chords) {
            if (note < chord) {
                break;
            }
            noteChord++;
        }
        return noteChord;
    }
    public float getSecond(){
        return this.second;
    }
    public void setSecond(float second){
        this.second = second;
    }
    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    public ShortMessage getShortMessage(){
        try {

            return new ShortMessage(this.status,this.channel, this.note, this.velocity);
        } catch (Exception ex) {
            Utilidades.alertar("Erro durante o processamento do MIDI:"+ex.getMessage());
        }
        return null;
    }
    
}

