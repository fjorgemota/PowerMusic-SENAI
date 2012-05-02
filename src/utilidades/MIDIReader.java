/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import com.sun.media.sound.MidiUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import javax.sound.midi.*;

/**
 *
 * @author mota
 */
public class MIDIReader {

    private String filename;
    private Sequence sequencia;
    private Sequencer player;
    private Receiver recebedor;
    private MidiUtils.TempoCache tempoProcessor;
    private float duration;
    private ArrayList<MIDINote> notas; // Cria um ArrayList com as notas, que devem vir a ser a matriz com as notas por si so
    private float interval;
    private boolean canPlay;
    private int maxNote;
    private int notesLength;

    public MIDIReader(String filename) {
        this.filename = filename;
        try {
            this.recebedor = MidiSystem.getReceiver();
            this.sequencia = MidiSystem.getSequence(new File(filename));
            this.tempoProcessor = new MidiUtils.TempoCache(sequencia);
            this.player = MidiSystem.getSequencer(true);
            this.player.setSequence(sequencia);
            this.player.open();
            this.interval = 0.5f;
            this.loadNotes();
            this.duration = this.getRealDuration();
        } catch (Exception ex) {
            Utilidades.alertar(ex.getMessage());
        }
    }

    private void loadNotes() {
        int program = 0;
        HashMap<Integer, Float> lastTimeNote = new HashMap<Integer, Float>();
        HashMap<Float, Integer> secondsNotes = new HashMap<Float, Integer>();
        this.notas = new ArrayList<MIDINote>();
        for (Track track : sequencia.getTracks()) {
            for (int c = 0; c < track.size(); ++c) {
                MidiEvent event = track.get(c);
                MidiMessage msg = event.getMessage();
                if (msg instanceof ShortMessage) {
                    ShortMessage shortmsg = (ShortMessage) msg;
                    if (shortmsg.getCommand() == ShortMessage.PROGRAM_CHANGE) {
                        program = shortmsg.getData1();
                    } else {
                        //else if(program>=25 && program <= 40){
                        //else if(program== 30){
                        if (shortmsg.getCommand() == ShortMessage.NOTE_ON) {
                            MIDINote midiNote = new MIDINote(event, sequencia, tempoProcessor, program);
                            //tocador.start();
                            int noteChord = midiNote.getChord();
                            float noteSecond = midiNote.getSecond();
                            if (!lastTimeNote.containsKey(noteChord)) {
                                lastTimeNote.put(noteChord, 0.0f);
                            }
                            if (noteSecond - lastTimeNote.get(noteChord).floatValue() <= this.interval) {
                                continue;
                            }
                            lastTimeNote.put(noteChord, noteSecond);
                            //System.out.println("Play chord "+noteChord+" in "+noteSecond+" seconds");
                            notas.add(midiNote);
                            if (!secondsNotes.containsKey(noteSecond)) {
                                secondsNotes.put(noteSecond, 1);
                            }
                            secondsNotes.put(noteSecond, secondsNotes.get(noteSecond).intValue() + 1);
                        }

                    }

                }
            }
        }
        
        //System.out.println("tamanho da pista "+notas.size()+" e track "+maxNote);
        for (float second : secondsNotes.keySet()) {
            int repeated = secondsNotes.get(second).intValue();
            if (repeated > maxNote) {
                this.maxNote = repeated;
            }
        }
        this.notesLength = secondsNotes.size();
        // GameEngine.getInstance().setFramesPerSecond((int)(((tocador.getMicrosecondLength()/1000000)/(notas.size()*1.0))*4000));
        // System.out.println("(int)(("+sequencia.getMicrosecondLength()+"/1000000)/"+notas.size()+"="+(int)((sequencia.getMicrosecondLength()/1000000)/notas.size()))
    }

    public void setInterval(float interval) {
        this.interval = interval;
    }

    public float getInterval() {
        return this.interval;
    }

    public void refresh() {
        this.loadNotes();
        this.duration = this.getRealDuration();
    }

    public float[][] getNotes() {
        float audioDuration = this.getRealDuration();
        float ratio = audioDuration / this.duration;
        float[][] novaNotas = new float[this.notesLength][this.maxNote];
        boolean exists;
        float lastSecond;
        int theIndex;
        ArrayList<Number> lastNote;
        ArrayList<ArrayList<Number>> realNotas = new ArrayList<ArrayList<Number>>(); // Cria um ArrayList com as notas compactadas..usando tecnicas de MapReduce (que sao bem simples)

        for (MIDINote nota : notas) {
            exists = false;
            lastSecond = (float) 0.0;
            theIndex = 0;
            if (realNotas.size() > 0) {
                lastNote = realNotas.get(realNotas.size() - 1);
                for (ArrayList<Number> aNota : realNotas) {
                    if (aNota.get(0).floatValue() == nota.getSecond()) {
                        exists = true;
                        lastNote = aNota;
                    } else if (lastSecond < nota.getSecond() && aNota.get(0).floatValue() > nota.getSecond()) {
                        exists = false;
                        break;
                    }
                    lastSecond = aNota.get(0).floatValue();
                    theIndex++;
                }
                if (exists) {
                    if (!lastNote.contains(nota.getChord())) {
                        lastNote.add(nota.getChord());
                    }
                } else {
                    lastNote = new ArrayList<Number>();
                    lastNote.add(nota.getSecond());
                    lastNote.add(nota.getChord());
                    realNotas.add(theIndex, lastNote);
                }

            } else {
                lastNote = new ArrayList<Number>();
                lastNote.add(nota.getSecond());
                lastNote.add(nota.getChord());
                realNotas.add(lastNote);
            }
        }
        for(int c=0;c<realNotas.size();++c){
            ArrayList<Number> notasTrack = realNotas.get(c);
            for(int c2=0; c2<notasTrack.size(); ++c2){
                if(c2 == 0){
                    //Primeiro item do vetor contem o tempo..portanto..
                    novaNotas[c][c2] = (float)notasTrack.get(c2).floatValue()/ratio;
                }
                else{
                    //Os outros item nao contem o tempo..portanto, contem o mesmo valor
                    novaNotas[c][c2] = (float)notasTrack.get(c2).floatValue();
                }
            }
        }
        return novaNotas;
    }

    public ArrayList<ArrayList<Object>> getTheNotes() {
        float audioDuration = this.getRealDuration();
        float ratio = audioDuration / this.duration;
        ArrayList<ArrayList<Object>> novaNotas = new ArrayList<ArrayList<Object>>();
        boolean exists;
        float lastSecond;
        int theIndex;
        ArrayList<Object> lastNote;
        ArrayList<ArrayList<Object>> realNotas = new ArrayList<ArrayList<Object>>(); // Cria um ArrayList com as notas compactadas..usando tecnicas de MapReduce (que sao bem simples)

        for (MIDINote nota : notas) {
            exists = false;
            lastSecond = (float) 0.0;
            theIndex = 0;
            if (realNotas.size() > 0) {
                lastNote = realNotas.get(realNotas.size() - 1);
                for (ArrayList<Object> aNota : realNotas) {
                    if (((Float)aNota.get(0)).floatValue() == nota.getSecond()) {
                        exists = true;
                        lastNote = aNota;
                    } else if (lastSecond < nota.getSecond() && ((Float)aNota.get(0)).floatValue() > nota.getSecond()) {
                        exists = false;
                        break;
                    }
                    lastSecond = ((Float)aNota.get(0)).floatValue();
                    theIndex++;
                }
                if (exists) {
                    if (!lastNote.contains(nota.getNote())) {
                        lastNote.add(nota.getNote());
                    }
                } else {
                    lastNote = new ArrayList<Object>();
                    lastNote.add(nota.getSecond());
                    lastNote.add(nota);
                    realNotas.add(theIndex, lastNote);
                }

            } else {
                lastNote = new ArrayList<Object>();
                lastNote.add(nota.getSecond());
                lastNote.add(nota.getNote());
                realNotas.add(lastNote);
            }
        }
        for(int c=0;c<realNotas.size();++c){
            ArrayList<Object> notasTrack = realNotas.get(c);
            ArrayList<Object> novaNotasTrack = new ArrayList<Object>();
            for(int c2=0; c2<notasTrack.size(); ++c2){
                if(c2 == 0){
                    //Primeiro item do vetor contem o tempo..portanto..
                    novaNotasTrack.add(((Float)notasTrack.get(c2)).floatValue()/ratio);
                }
                else{
                    //Os outros item nao contem o tempo..portanto, contem o mesmo valor
                    novaNotasTrack.add(notasTrack.get(c2));
                }
            }
            novaNotas.add(novaNotasTrack);
        }
        return novaNotas;
    }

    public float[][] getRealNotes() {
        float[][] novaNotas = new float[this.notesLength][this.maxNote];
        boolean exists;
        float lastSecond;
        int theIndex;
        ArrayList<Number> lastNote;
        ArrayList<ArrayList<Number>> realNotas = new ArrayList<ArrayList<Number>>(); // Cria um ArrayList com as notas compactadas..usando tecnicas de MapReduce (que sao bem simples)

        for (MIDINote nota : notas) {
            exists = false;
            lastSecond = (float) 0.0;
            theIndex = 0;
            if (realNotas.size() > 0) {
                lastNote = realNotas.get(realNotas.size() - 1);
                for (ArrayList<Number> aNota : realNotas) {
                    if (aNota.get(0).floatValue() == nota.getSecond()) {
                        exists = true;
                        lastNote = aNota;
                    } else if (lastSecond < nota.getSecond() && aNota.get(0).floatValue() > nota.getSecond()) {
                        exists = false;
                        break;
                    }
                    lastSecond = aNota.get(0).floatValue();
                    theIndex++;
                }
                if (exists) {
                    if (!lastNote.contains(nota.getChord())) {
                        lastNote.add(nota.getChord());
                    }
                } else {
                    lastNote = new ArrayList<Number>();
                    lastNote.add(nota.getSecond());
                    lastNote.add(nota.getChord());
                    realNotas.add(theIndex, lastNote);
                }

            } else {
                lastNote = new ArrayList<Number>();
                lastNote.add(nota.getSecond());
                lastNote.add(nota.getChord());
                realNotas.add(lastNote);
            }
        }
        
        for(int c=0;c<realNotas.size();++c){
            ArrayList<Number> notasTrack = realNotas.get(c);
            for(int c2=0; c2<notasTrack.size(); ++c2){
                //Os outros item nao contem o tempo..portanto, contem o mesmo valor
                novaNotas[c][c2] = (float)notasTrack.get(c2).floatValue();
            }
        }
        return novaNotas;
    }

    public ArrayList<ArrayList<Object>> getTrueNotes() {
        float audioDuration = this.getRealDuration();
        float ratio = audioDuration / this.duration;
        ArrayList<ArrayList<Object>> novaNotas = new ArrayList<ArrayList<Object>>();
        boolean exists;
        float lastSecond;
        int theIndex;
        ArrayList<Object> lastNote;
        ArrayList<ArrayList<Object>> realNotas = new ArrayList<ArrayList<Object>>(); // Cria um ArrayList com as notas compactadas..usando tecnicas de MapReduce (que sao bem simples)

        for (MIDINote nota : notas) {
            exists = false;
            lastSecond = (float) 0.0;
            theIndex = 0;
            if (realNotas.size() > 0) {
                lastNote = realNotas.get(realNotas.size() - 1);
                for (ArrayList<Object> aNota : realNotas) {
                    if (((Float)aNota.get(0)).floatValue() == nota.getSecond()) {
                        exists = true;
                        lastNote = aNota;
                    } else if (lastSecond < nota.getSecond() && ((Float)aNota.get(0)).floatValue() > nota.getSecond()) {
                        exists = false;
                        break;
                    }
                    lastSecond = ((Float)aNota.get(0)).floatValue();
                    theIndex++;
                }
                if (exists) {
                    if (!lastNote.contains(nota.getNote())) {
                        lastNote.add(nota.getNote());
                    }
                } else {
                    lastNote = new ArrayList<Object>();
                    lastNote.add(nota.getSecond());
                    lastNote.add(nota);
                    realNotas.add(theIndex, lastNote);
                }

            } else {
                lastNote = new ArrayList<Object>();
                lastNote.add(nota.getSecond());
                lastNote.add(nota.getNote());
                realNotas.add(lastNote);
            }
        }
        for(int c=0;c<realNotas.size();++c){
            ArrayList<Object> notasTrack = realNotas.get(c);
            ArrayList<Object> novaNotasTrack = new ArrayList<Object>();
            for(int c2=0; c2<notasTrack.size(); ++c2){
                if(c2 == 0){
                    //Primeiro item do vetor contem o tempo..portanto..
                    novaNotasTrack.add(((Float)notasTrack.get(c2)).floatValue());
                }
                else{
                    //Os outros item nao contem o tempo..portanto, contem o mesmo valor
                    novaNotasTrack.add(notasTrack.get(c2));
                }
            }
            novaNotas.add(novaNotasTrack);
        }
        return novaNotas;
    }

    public float getDuration() {
        return this.duration;
    }

    public float getRealDuration() {
        if (this.notesLength == 0) {
            return 0;
        }
        float[][] notes = this.getRealNotes();
        return notes[notes.length - 1][0];
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public void setDuration() {
        this.duration = this.getRealDuration();
    }

    public void play() {
        this.canPlay = true;
        player.start();
    }

    public void stop() {
        this.canPlay = false;
        player.stop();
    }
    public void reset() {
        stop();
        player.setMicrosecondPosition(0);
    }
    //Permite a execucao de uma nota do video
    public void tocar(float seconds, int corda) {
        if (!this.canPlay) {
            return;
        }
        corda = 6-corda;
        try {
            for (MIDINote nota : this.notas) {
                
                if (nota.getSecond() == seconds && nota.getChord() == corda) {
                     this.recebedor.send(nota.getShortMessage(), -1);
                     return;
                }
            }
        } catch (Exception ex) {
            Utilidades.alertar("Erro na execucao do MIDI: " + ex.getMessage());
        }
    }
}
