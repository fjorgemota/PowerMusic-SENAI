/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fases;

import java.awt.Color;
import java.awt.Graphics;
import javaPlay.GameEngine;
import javaPlay.GameStateController;
import javaPlay.Mouse;
import javaPlayExtras.Imagem;
import javax.swing.JOptionPane;
import utilidades.Utilidades;

/**
 *
 * @author fernando_mota
 */
public class MenuFases implements GameStateController{

    private Imagem bgImageFundo;
    private Imagem bgImagePergunta;
    
    private Imagem bgImageFacil;
    private Imagem bgImageFacil1;
    private Imagem bgImageFacil2;
    
    private Imagem bgImageMedio;
    private Imagem bgImageMedio1;
    private Imagem bgImageMedio2;
    
    private Imagem bgImageDificil;
    private Imagem bgImageDificil1;
    private Imagem bgImageDificil2;
    
    private Imagem bgImageVoltar;
    private Imagem bgImageVoltar1;
    private Imagem bgImageVoltar2;
    
    private Imagem bgImageDanca;
    private Imagem bgImageEstrela;
    private Imagem bgImagemMidi;
    private Mouse mouse;

    public void load() {
        try {
            this.bgImagePergunta = new Imagem("img_cenario/pergunta.png");
            
            this.bgImageFacil1 = new Imagem("img_cenario/facil.png");
            this.bgImageFacil2 = new Imagem("img_cenario/easy2.png");
            this.bgImageFacil = this.bgImageFacil1;
            
           
            this.bgImageMedio1 = new Imagem("img_cenario/medio.png");
            this.bgImageMedio2 = new Imagem("img_cenario/medium2.png");
            this.bgImageMedio = this.bgImageMedio1;
            
            this.bgImageDificil1 = new Imagem("img_cenario/dificil.png");
            this.bgImageDificil2 = new Imagem ("img_cenario/dificult2.png");
            this.bgImageDificil = this.bgImageDificil1;
            
            this.bgImageVoltar1 = new Imagem("img_cenario/voltar.png");
            this.bgImageVoltar2 = new Imagem("img_cenario/voltar2.png");
            this.bgImageVoltar = this.bgImageVoltar1;
            
            this.bgImageFundo = new Imagem("img_cenario/fundo.png");
            this.bgImageDanca = new Imagem("img_cenario/danca.gif");
            this.bgImageEstrela = new Imagem("img_cenario/estrela.gif");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }

    public void unload() {
    }

    public void start() {
    }

    public void step(long timeElapsed) {
        
        if(Utilidades.estaClicandoEm(330, 180, 89, 75)){
            System.exit(0);
        }
        if(Utilidades.estaClicandoEm(300,280,150,75)){
            System.exit(0);
        }
        
        if(Utilidades.estaClicandoEm(300,380,150,75)){
            System.exit(0);
        }
        
        if(Utilidades.estaClicandoEm(620,520,89,75)){
           GameEngine.getInstance().setNextGameStateController(2);
        }
          if (Utilidades.estaComOMouseEm(330, 180, 89, 75)) {
            this.bgImageFacil= this.bgImageFacil2;
        } else {
            this.bgImageFacil = this.bgImageFacil1;
        }
        
            if (Utilidades.estaComOMouseEm(300,280,150,75)) {
            this.bgImageMedio= this.bgImageMedio2;
        } else {
            this.bgImageMedio = this.bgImageMedio1;
        }
             
            if (Utilidades.estaComOMouseEm(300,380,150,75)) {
            this.bgImageDificil = this.bgImageDificil2;
        } else {
            this.bgImageDificil = this.bgImageDificil1;
        }
          
            if (Utilidades.estaComOMouseEm(620,520,89,75)) {
            this.bgImageVoltar = this.bgImageVoltar2;
        } else {
            this.bgImageVoltar = this.bgImageVoltar1;
        }
    }
    
    public void draw(Graphics g) {
      
        g.fillRect(0, 0, 3000, 2400);
    

        this.bgImageFundo.draw(g, 0, 0);
        this.bgImagePergunta.draw(g, 110, 70);
        this.bgImageFacil.draw(g, 330, 180);
        this.bgImageMedio.draw(g,300, 280);
        this.bgImageDificil.draw(g,300, 380);
        this.bgImageVoltar.draw(g,620, 520);
        this.bgImageDanca.draw(g,50, 520);
        this.bgImageEstrela.draw(g,295, 182);
        this.bgImageEstrela.draw(g,265, 288);
        this.bgImageEstrela.draw(g,265, 392);
        
    }

    public void stop() {
    }
}
