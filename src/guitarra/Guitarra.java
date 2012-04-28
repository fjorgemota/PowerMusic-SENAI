/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guitarra;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import javaPlay.GameEngine;
import javaPlay.GameObject;
import javaPlay.Keyboard;
import javaPlayExtras.Imagem;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import utilidades.Utilidades;

/**
 *
 * @author fernando_mota
 */
public class Guitarra extends GameObject{
    public static Guitarra instancia = null;
    private boolean firstNotePlayed = false;
    private float lastVideoTime;
    public boolean podeEspecial() {
        boolean pode = Utilidades.sorteia();
        if(!pode){
            return false;
        }
        return this.getProgresso(20)==100;
    }
    protected float[][] notas = new float[0][0]; // Guarda uma matriz com as notas disponiveis para serem tocadas
    protected Esfera[] esferas; // Guarda um vetor com as esferas disponiveis para serem mostradas
    protected int level = 0; // Guarda a dificuldade (numero de notas a serem exibidas) mostradas na tela simultaneamente
    protected ArrayList<Esfera> notasEsferasAtuais; // Guarda uma lista com as esferas mostradas na tela atualmente
    protected ArrayList<Esfera> notasEsferas; // Guarda uma lista com as esferas mostradas durante toda a execucao da musica
    protected long timeElapsed;// guarda o numero de milisegundos passados desde a ultima trocagem das notas
    protected int pontos;// guarda o numero de pontos
    protected float lastSecond; // guarda o ultimo segundo no qual foi adicionado uma nova nota
    protected float lastNote; // guarda o ultimo indice no qual foi adicionado uma nova nota
    protected float minorTime; // guarda o tempo que uma esfera demora ate descer
    protected HashMap<Integer, Integer> blocked; // Guarda o tempo em que cada esfera foi bloqueada
    protected JLabel progressos[];
    public Guitarra(){
    }
    public void load(){
        this.notasEsferas = new ArrayList<Esfera>();
        this.notasEsferasAtuais = new ArrayList<Esfera>();
        this.esferas = new Esfera[6];
        this.esferas[1] = new Verde();
        this.esferas[2] = new Vermelha();
        this.esferas[3] = new Amarela();
        this.esferas[4] = new Azul();
        this.esferas[5] = new Laranja();
        this.blocked = new HashMap<Integer, Integer>();
        for(int c=1;c<this.esferas.length;c++){
            this.esferas[c].setSerie(c);
            this.blocked.put(this.esferas[c].getTecla(),0);
        }
        this.progressos = new JLabel[7];
        for(int c=0;c<7;++c){
            this.progressos[c] = new JLabel(new ImageIcon("img_cenario/BARRINHAS/barra"+c+".png"));
        }
        
    }
    public static Guitarra getInstance() {
        if(Guitarra.instancia == null){
            Guitarra.instancia = new Guitarra();
            Guitarra.instancia.load();
        }
        return Guitarra.instancia;
    }
    public void setLevel(int level){
        this.level = level+1;
    }
    public JLabel getImageProgress(){
        int progresso = (int) this.getProgresso(30);
        JLabel imagem;
        if(progresso == 100){
            imagem = this.progressos[6];
        }
        else if(progresso >= 90){
            imagem = this.progressos[5];
        }
        else if(progresso >= 75){
            imagem = this.progressos[4];
        }
        else if(progresso >= 55){
            imagem = this.progressos[3];
        }
        else if(progresso >= 40){
            imagem = this.progressos[2];
        }
        else{
            imagem = this.progressos[1];
        }
        return imagem;
    }
    private Esfera[] getNotas(){
        for(float[] nota: notas){
            if(nota.length==0 || nota[0] <= lastNote){
                continue;//Ignora notas com menos de 1 elemento
            }
            if(this.getPrecisionSecondsElapsed() >= nota[0] && lastNote != nota[0]){//Verifica se é a nota à ser considerada
                lastNote = nota[0];
                Esfera[] esferasNotas = new Esfera[this.level];
                for(int c=1;c<nota.length;c++){
                    int corda = (int)nota[c];
                    if(corda == 0 || corda >= this.esferas.length){
                        continue;
                    }
                    corda = this.esferas.length-corda;
                    esferasNotas[c-1] = this.esferas[corda].getNewInstance(lastNote);
                }
                return esferasNotas;
            }
        }
        /*
         * [
         *  [3, 0,1,2], // Quando passar 3 segundos, envia a nota Verde, vermelha e amarela (veja lista de notas da linha 38 a 42 deste arquivo)
         *  [5, 2], // Quando passar 5 segundos, envia a nota amarela (veja lista de notas da linha 38 a 42 deste arquivo).
         *  [8, 4]
         *  [6, 9],
         *  [7, 9]
         * 
         * ]
         * if(secondsElapsed == 3){
         *  return new Esferas[]{new Verde(), new Vermelha(), new Amarela()}
         * }
         * else if(secondsElapsed == 5){
         *  return new Esferas[]{new Amarela()}
         * }
         */
        return new Esfera[0];
    }
    public void setVideoTime(float seconds){
        this.timeElapsed = (long)seconds;
    }
    public void setVideoTime(double seconds){
        this.timeElapsed = (long)seconds;
    }
    public void setNotas(float[][] notas) {
        this.notas = notas;
       for(int c=0;c<this.notas.length;++c){
           this.notas[c][0] = this.notas[c][0]-this.minorTime;
           
       }
    }
    public float getAutoMinorTime(){
        return (620/(float)GameEngine.getInstance().getFramesPerSecond());
    }
    public void setMinorTime(){
        this.minorTime = this.getAutoMinorTime();
        System.out.println("Cada musica tera subtraido cerca de "+this.minorTime+" segundos");
    }
    public void setMinorTime(float minorTime){
        this.minorTime = minorTime;
        System.out.println("Cada musica tera subtraido cerca de "+this.minorTime+" segundos");
    }
    public void reset(){
        this.timeElapsed = 0;
        this.lastSecond = 0;
        this.notas = new float[0][0];
        this.notasEsferas.clear();
        this.notasEsferasAtuais.clear();
    }
    public int getSecondsElapsed(){
        return (int) (this.timeElapsed/1000);
    }
    public float getPrecisionSecondsElapsed(){
        return (this.timeElapsed/1000.0f);
    }
    public void addVideoTime(float seconds){
        this.timeElapsed += seconds-this.lastVideoTime;
        this.setLastVideoTime(seconds);
    }
    public void setLastVideoTime(float seconds){
        this.lastVideoTime = seconds;
    } 
    public void step(long timeElapsed) {
        this.timeElapsed += timeElapsed;
        if(this.getProgresso()<20){
            GameEngine.getInstance().getGameCanvas().setPanel(null);
            GameEngine.getInstance().setNextGameStateController(24);
        }
        if(this.getPrecisionSecondsElapsed() != this.lastSecond){
            this.lastSecond = this.getPrecisionSecondsElapsed();
            Esfera[] novasNotas = this.getNotas();
            for(int c=0;c<novasNotas.length;c++){
                if(novasNotas[c] == null){
                    continue;
                }
                this.notasEsferasAtuais.add(novasNotas[c]);
            }
        }        
        ArrayList<Esfera> notasAntigas = new ArrayList<Esfera>();
        Keyboard teclado = GameEngine.getInstance().getKeyboard();
        HashMap<Integer, ArrayList<Boolean>> pressionado = new HashMap<Integer, ArrayList<Boolean>>();
        for(Esfera nota: this.notasEsferasAtuais){
            nota.step(timeElapsed);
            if(nota.getY()>400){
                this.firstNotePlayed = true;
            }
            if(!pressionado.containsKey(nota.getTecla())){
                pressionado.put(nota.getTecla(), new ArrayList<Boolean>());
            }
            pressionado.get(nota.getTecla()).add(nota.podePressionar());
            if(nota.getY()>620){
                notasAntigas.add(nota);
            }
        }   
        int framesSec = GameEngine.getInstance().getFramesPerSecond()/2; // Numero de frames diferentes
        for(int tecla: pressionado.keySet()){
            if(this.blocked.get(tecla).intValue() >= framesSec && !pressionado.get(tecla).contains(true) && teclado.keyDown(tecla)){
                Collections.reverse(this.notasEsferasAtuais);
                for(Esfera nota: this.notasEsferasAtuais){
                    if(tecla == nota.getTecla()){
                        nota.bloquearTecla();
                        blocked.put(tecla, 0);
                        break;
                    }
                }
                Collections.reverse(this.notasEsferasAtuais);
            }
            this.blocked.put(tecla,this.blocked.get(tecla).intValue()+1);
        }
        for(Esfera nota: notasAntigas){
            this.notasEsferas.add(nota);
            this.notasEsferasAtuais.remove(nota);
        }
        
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawString(this.getProgresso(20)+"% (total: "+this.getProgresso()+")", 10, 10);
        for(Esfera nota: this.notasEsferasAtuais){
            nota.draw(g);
        }
    }

    public void adicionaPontos(int pontos) {
        this.pontos += pontos;
    }

    public float getProgresso() {
        float count = 0;
        for(Esfera nota: this.notasEsferas){
            count += nota.foiPressionado()?1:0;
        }
        return (((count+1)/(float)(this.notasEsferas.size()+1))*100.0f);
    }
    public float getProgresso(int lastSeconds){
        float count = 0, total = 0;
        for(Esfera nota: this.notasEsferas){   
            if(nota.getSecond()<this.getPrecisionSecondsElapsed()-lastSeconds){
                continue;
            }
            count += nota.foiPressionado()?1:0;
            total += 1;
        }
        return ((count+1)/(float)(total+1))*100.0f;
    }

    public boolean isFirstNotePlayed() {
        return this.firstNotePlayed;
    }
}
