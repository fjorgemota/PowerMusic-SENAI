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
    private Imagem bgimage;
    public boolean podeEspecial() {
        boolean pode = Utilidades.sorteia();
        if(!pode){
            return false;
        }
        return this.getProgresso(10)==100;
    }
    protected int[][] notas; // Guarda uma matriz com as notas disponiveis para serem tocadas
    protected Esfera[] esferas; // Guarda um vetor com as esferas disponiveis para serem mostradas
    protected int level; // Guarda a dificuldade (numero de notas a serem exibidas) mostradas na tela simultaneamente
    protected ArrayList<Esfera> notasEsferasAtuais; // Guarda uma lista com as esferas mostradas na tela atualmente
    protected ArrayList<Esfera> notasEsferas; // Guarda uma lista com as esferas mostradas durante toda a execucao da musica
    protected long timeElapsed;// guarda o numero de milisegundos passados desde a ultima trocagem das notas
    protected int pontos;// guarda o numero de pontos
    protected int lastSecond; // guarda o ultimo segundo no qual foi adicionado uma nova nota
    public Guitarra(){
        this.esferas = new Esfera[5];
        this.esferas[0] = new Verde(0);
        this.esferas[1] = new Vermelha(1);
        this.esferas[2] = new Amarela(2);
        this.esferas[3] = new Azul(3);
        this.esferas[4] = new Laranja(4);
        for(int c=0;c<this.esferas.length;c++){
            this.esferas[c].setSerie(c+1);
        }
        try{
            this.bgimage = new Imagem("img_cenario/guitarra.png");
        }
        catch(Exception e){
            Utilidades.alertar(e.getMessage());
        }
        this.notasEsferas = new ArrayList<Esfera>();
    }
    public static Guitarra getInstance() {
        if(Guitarra.instancia == null){
            Guitarra.instancia = new Guitarra();
        }
        return Guitarra.instancia;
    }
    public void setLevel(int level){
        this.level = level+1;
    }
    private Esfera[] getNotas(){
        for(int[] nota: notas){
            if(nota.length<this.level){
                continue;//Ignora notas com menos de 1 elemento
            }
            if(this.getSecondsElapsed() == nota[0]){//Verifica se é a nota à ser considerada
                Esfera[] esferasNotas = new Esfera[this.level];
                for(int c=0;c<this.level;c++){
                    esferasNotas[c] = this.esferas[nota[c]].getNewInstance();
                }
                return esferasNotas;
            }
        }
        return new Esfera[0];
    }
    protected int getSecondsElapsed(){
        return (int) (this.timeElapsed/1000);
    }
    public void step(long timeElapsed) {
        this.timeElapsed += timeElapsed;
        ArrayList<Esfera> notasAntigas = new ArrayList<Esfera>();
        for(Esfera nota: this.notasEsferasAtuais){
            nota.step(timeElapsed);
            if(nota.getY()>600){
                notasAntigas.add(nota);
            }
        }
        for(Esfera nota: notasAntigas){
            this.notasEsferas.add(nota);
            this.notasEsferasAtuais.remove(nota);
        }
        if(this.getSecondsElapsed() != this.lastSecond){
            this.lastSecond = this.getSecondsElapsed();
            Esfera[] novasNotas = this.getNotas();
            for(int c=0;c<novasNotas.length;c++){
                this.notasEsferasAtuais.add(novasNotas[c]);
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        this.bgimage.draw(g,0,0);
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
        return this.notasEsferas.size()/count;
    }
}
