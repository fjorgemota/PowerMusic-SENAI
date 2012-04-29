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
import utilidades.Utilidades;

/**
 *
 * @author Samara
 */
public class Final implements GameStateController{
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
    }

    public void unload() {
    }

    public void start() {
        try{
            this.img = new Imagem("img_cenario/final_teste.png");
        }
        catch(Exception ex){
            Utilidades.alertar("Erro ao carregar a imagem de final:"+ex.getMessage());
        }
    }

    public void step(long timeElapsed) {
        if(Utilidades.estaClicandoEm(0, 0, 800, 620)){
            GameEngine.getInstance().setNextGameStateController(2);
        }
    }

    public void draw(Graphics g) {
        this.img.draw(g, 0,0);
        g.setFont(g.getFont().deriveFont(Font.BOLD,36.0f));
        g.drawString(this.totalPontos+"",575,506);
    }

    @Override
    public void stop() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
