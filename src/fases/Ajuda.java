package fases;

import java.awt.Color;
import java.awt.Graphics;
import javaPlay.GameEngine;
import javaPlay.GameObject;
import javaPlay.GameStateController;
import javaPlay.Mouse;
import javaPlayExtras.Imagem;
import javax.swing.JOptionPane;
import utilidades.Utilidades;

public class Ajuda implements GameStateController {

    private Imagem bgImageHelp_desc;
    private Imagem bgImageVoltar;
    private Imagem bgImageVoltar1;
    private Imagem bgImageVoltar2;
    private Imagem bgImagePiano;
    private Mouse mouse;

    public void load() {
        try {
            this.bgImagePiano = new Imagem("imagens/musica.gif");
            
            this.bgImageVoltar1 = new Imagem("imagens/voltar.png");
            this.bgImageVoltar2 = new Imagem("imagens/voltar2.png");
            this.bgImageVoltar = this.bgImageVoltar1;
            
            this.bgImageHelp_desc = new Imagem("imagens/help_desc.png");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }

    public void unload() {
    }

    public void start() {
    }

    public void step(long timeElapsed) {
        
        if(Utilidades.estaClicandoEm(600,530,89,75)){
           GameEngine.getInstance().setNextGameStateController(2);
        }
        
         if(Utilidades.estaComOMouseEm(600,530,89,75)){
           this.bgImageVoltar = this.bgImageVoltar2;
         }
         else{
             this.bgImageVoltar = this.bgImageVoltar1;
         }
    }

    public void draw(Graphics g) {
        
        g.fillRect(0, 0, 3000, 2400);
        
        this.bgImageHelp_desc.draw(g,0, 8);
        this.bgImageVoltar.draw(g, 600, 530);
        this.bgImagePiano.draw(g, 6, 2);
        this.bgImagePiano.draw(g, 85, 2);
        this.bgImagePiano.draw(g, 165, 2);
        this.bgImagePiano.draw(g, 245, 2);
        this.bgImagePiano.draw(g, 325, 2);
        this.bgImagePiano.draw(g, 420, 2);
        this.bgImagePiano.draw(g, 520, 2);
        this.bgImagePiano.draw(g, 620, 2);
        this.bgImagePiano.draw(g, 700, 2);
    }

    public void stop() {
    }
}
