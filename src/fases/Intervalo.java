/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fases;

import guitarra.Guitarra;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javaPlay.GameEngine;
import javaPlay.GameStateController;
import javaPlayExtras.Imagem;
import javax.swing.JOptionPane;
import utilidades.Utilidades;

/**
 *
 * @author mota
 */
public class Intervalo implements GameStateController{
    private Imagem bgImageEasy2;
    private Imagem bgImagePlay;
    private Imagem bgImagePlay1;
    private Imagem bgImagePlay2;
    private Imagem bgImageVoltar;
    private Imagem bgImageVoltar1;
    private Imagem bgImageVoltar2;
    private int nextMusic;
    private String theImagem;
    public Intervalo(int nextMusic, String bgImage){
        this.nextMusic = nextMusic;
        this.theImagem = bgImage;
    }
    public void load() {
    }
    public void unload() {
    }
    public void start() {
        try {

            this.bgImageEasy2 = new Imagem(theImagem);


             this.bgImagePlay1 = new Imagem("imagens/play.png");
             this.bgImagePlay2= new Imagem("imagens/play_efeito.png");
             this.bgImagePlay = this.bgImagePlay1;
            

             this.bgImageVoltar1 = new Imagem("imagens/voltar.png");
             this.bgImageVoltar2 = new Imagem("imagens/voltar2.png");
             this.bgImageVoltar =this.bgImageVoltar1;
             

        } catch (Exception ex) {
            Utilidades.alertar(ex.getMessage());
        }
        if(!theImagem.contains("1")){
            Final.getInstance().atualiza();
        }
    }
    public void step(long timeElapsed) {
         if (Utilidades.estaClicandoEm(650, 520, 89, 75)) {
            GameEngine.getInstance().setNextGameStateController(this.nextMusic);
        }

        if (Utilidades.estaComOMouseEm(650, 520, 89, 75)) {
            this.bgImagePlay = this.bgImagePlay2;
        } else {
            this.bgImagePlay = this.bgImagePlay1;
        }
        
        if (Utilidades.estaClicandoEm(50, 520, 89, 75)) {
            Final.getInstance().reset();
            GameEngine.getInstance().setNextGameStateController(2);
        }

        if (Utilidades.estaComOMouseEm(50, 520, 89, 75)) {
            this.bgImageVoltar = this.bgImageVoltar2;
        } else {
            this.bgImageVoltar = this.bgImageVoltar1;
        }
    }

    public void draw(Graphics g) {
        g.fillRect(0, 0, 800, 620);
        this.bgImageEasy2.draw(g, 0, 0);

        this.bgImagePlay.draw(g,650, 520);
        
        this.bgImageVoltar.draw(g, 50, 520);
        if(!this.theImagem.contains("1")){
            g.setColor(Color.BLACK);
            g.setFont(g.getFont().deriveFont(Font.BOLD,36.0f));
            g.drawString(((int)Guitarra.getInstance().getProgresso())+"%",365,260);
            Final.getInstance().reset();
        }
    }

    public void stop() {
    }
    
}
