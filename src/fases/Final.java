/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fases;

import guitarra.Guitarra;
import java.awt.Font;
import java.awt.Graphics;
import javaPlay.GameEngine;
import javaPlay.GameStateController;
import javaPlayExtras.Imagem;
import javax.swing.JOptionPane;
import utilidades.Utilidades;

/**
 *
 * @author Samara
 */
public class Final implements GameStateController{
    
    private Imagem bgImageMenu;
    private Imagem bgImageMenu1;
    private Imagem bgImageMenu2;
    public static Final instancia;
    private Imagem img;
    private int totalPontos;
    public static Final getInstance(){
        if(Final.instancia == null){
            Final.instancia = new Final();
        }
        return Final.instancia;
    }
    public void atualiza(){
        this.totalPontos += Guitarra.getInstance().getPontuacao();
    }
    public void reset(){
        this.totalPontos = 0;
    }
    public void load() {  
        try {
        this.bgImageMenu1 = new Imagem("imagens/menu.png");
             this.bgImageMenu2 = new Imagem("imagens/efeito_menu.png");
             this.bgImageMenu =this.bgImageMenu1;
    }  catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
                
    }

    public void unload() {
    }

    public void start() {
        this.atualiza();
        try{
            this.img = new Imagem("imagens/final.png");
        }
        catch(Exception ex){
            Utilidades.alertar("Erro ao carregar a imagem de final:"+ex.getMessage());
        }
    }

    public void step(long timeElapsed) {
        
        if (Utilidades.estaClicandoEm(60, 535, 140, 60)) {
            GameEngine.getInstance().setNextGameStateController(2);
        }

        if (Utilidades.estaComOMouseEm(60, 535, 140, 60)) {
            this.bgImageMenu = this.bgImageMenu1;
        } else {
            this.bgImageMenu = this.bgImageMenu2;
        }
        
    }
    public void draw(Graphics g) {
        this.img.draw(g, 0,0);
        g.setFont(g.getFont().deriveFont(Font.BOLD,36.0f));

        g.drawString(this.totalPontos+"",590,560);
        this.bgImageMenu.draw(g, 60, 535);

        g.drawString(this.totalPontos+"",640,520);

    }

    public void stop() {
    }
    
}
