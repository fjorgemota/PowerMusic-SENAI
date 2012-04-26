/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guitarra;

import java.awt.Graphics;
import java.util.ArrayList;
import javaPlay.GameObject;
import javaPlayExtras.Imagem;
import utilidades.Utilidades;

/**
 *
 * @author fernando_mota
 */
public class Guitarra extends GameObject{
    public static Guitarra instancia = null;
    public boolean podeEspecial() {
        boolean pode = Utilidades.sorteia();
        if(!pode){
            return false;
        }
        return this.getProgresso(10)==100;
    }
    protected float[][] notas = new float[0][0]; // Guarda uma matriz com as notas disponiveis para serem tocadas
    protected Esfera[] esferas; // Guarda um vetor com as esferas disponiveis para serem mostradas
    protected int level = 0; // Guarda a dificuldade (numero de notas a serem exibidas) mostradas na tela simultaneamente
    protected ArrayList<Esfera> notasEsferasAtuais; // Guarda uma lista com as esferas mostradas na tela atualmente
    protected ArrayList<Esfera> notasEsferas; // Guarda uma lista com as esferas mostradas durante toda a execucao da musica
    protected long timeElapsed;// guarda o numero de milisegundos passados desde a ultima trocagem das notas
    protected int pontos;// guarda o numero de pontos
    protected int lastSecond; // guarda o ultimo segundo no qual foi adicionado uma nova nota
    protected float lastNote; // guarda o ultimo indice no qual foi adicionado uma nova nota
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
        
        for(int c=1;c<this.esferas.length;c++){
            this.esferas[c].setSerie(c);
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
    private Esfera[] getNotas(){
        for(float[] nota: notas){
            if(nota.length==0){
                continue;//Ignora notas com menos de 1 elemento
            }
            if(this.getPrecisionSecondsElapsed() <= nota[0] && lastNote != nota[0]){//Verifica se é a nota à ser considerada
                lastNote = nota[0];
                Esfera[] esferasNotas = new Esfera[this.level];
                for(int c=1;c<nota.length;c++){
                    int corda = (int)nota[c];
                    if(corda == 0 || corda >= this.esferas.length){
                        continue;
                    }
                    corda = this.esferas.length-corda;
                    System.out.println("Criando esfera "+this.esferas[corda].getClass().getName()+"("+corda+")");
                    esferasNotas[c-1] = this.esferas[corda].getNewInstance();
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

    public void setNotas(float[][] notas) {
        this.notas = notas;
       
    }
    public void reset(){
        this.timeElapsed = 0;
        this.lastSecond = 0;
        this.notas = new float[0][0];
        this.notasEsferas.clear();
        this.notasEsferasAtuais.clear();
    }
    protected int getSecondsElapsed(){
        return (int) (this.timeElapsed/1000);
    }
    protected float getPrecisionSecondsElapsed(){
        return (this.timeElapsed/1000.0f);
    }
    public void step(long timeElapsed) {
        this.timeElapsed += timeElapsed;
        if(this.getSecondsElapsed() != this.lastSecond){
            System.out.println("Processando segundo "+this.lastSecond);
            this.lastSecond = this.getSecondsElapsed();
            Esfera[] novasNotas = this.getNotas();
            for(int c=0;c<novasNotas.length;c++){
                if(novasNotas[c] == null){
                    continue;
                }
                System.out.println("Adicionando esfera "+novasNotas[c].getCor());
                this.notasEsferasAtuais.add(novasNotas[c]);
            }
        }        
        ArrayList<Esfera> notasAntigas = new ArrayList<Esfera>();
        for(Esfera nota: this.notasEsferasAtuais){
            nota.step(timeElapsed);
            if(nota.getY()>620){
                System.out.println("Migrando esfera "+nota.getCor()+" pois ela esta com Y "+nota.getY());
                notasAntigas.add(nota);
            }
        }   
        for(Esfera nota: notasAntigas){
            this.notasEsferas.add(nota);
            this.notasEsferasAtuais.remove(nota);
        }
        
    }

    public void draw(Graphics g) {
        for(Esfera nota: this.notasEsferasAtuais){
            nota.draw(g);
        }
    }

    public void adicionaPontos(int pontos) {
        this.pontos += pontos;
    }

    public float getProgresso() {
        int count = 0;
        for(Esfera nota: this.notasEsferas){
            count += nota.foiPressionado()?1:0;
        }
        return this.notasEsferas.size()/count;
    }
    public float getProgresso(int lastSeconds){
        int count = 0;
        for(Esfera nota: this.notasEsferas){
            if(nota.getSecond()<this.getSecondsElapsed()-10){
                continue;
            }
            count += nota.foiPressionado()?1:0;
        }
        return ((count+1)/(this.notasEsferas.size()+1))*100.0f;
    }
}
