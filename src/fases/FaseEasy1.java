package fases;

import guitarra.Guitarra;
import java.awt.Component;
import java.awt.Graphics;
import javaPlay.GameEngine;
import javaPlay.GameStateController;
import javaPlayExtras.Imagem;
import javax.media.Player;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import utilidades.Utilidades;

//MÚSICAS DA FASE
// Gone
//Tnt
//Independencia

public class FaseEasy1 implements GameStateController {

    private Imagem bgImageFundo;
    private Imagem bgImagePlay;
    private Imagem bgImagePlay1;
    private Imagem bgImagePlay2;
    private Imagem bgImageGuitarra;
    private Imagem bgImagePlayEfeito;
    private JLabel bgImageFundoEsquerda;
    private Imagem bgImageFundoDireita;
    private Player thePlayer;
    private Guitarra guitarra;
    private boolean musicLoaded = false;
    public void load() {
        this.bgImageFundoEsquerda =  new JLabel(new ImageIcon("img_cenario/FOTOS_BANDAS/acdc/acdc1.png"));
        try {

            this.bgImageFundo = new Imagem("img_cenario/fundo.png");

             //this.bgImagePlay1 = new Imagem("img_cenario/play.png");
             //this.bgImagePlay2= new Imagem("img_cenario/play_efeito.png");
             //this.bgImagePlay = this.bgImagePlay1;
            

             //this.bgImagePlay = new Imagem("img_cenario/play.png");
             this.bgImageFundoDireita = new Imagem("img_cenario/FOTOS_BANDAS/acdc/acdc2.png");
             this.bgImagePlayEfeito = new Imagem("img_cenario/play.png");


        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
    }
    
    public void unload() {
    }

    public void start() {
        this.guitarra = Guitarra.getInstance();
        this.guitarra.setLevel(5);
        JPanel pteste = new JPanel();
        pteste.setLayout(null);
        thePlayer = Utilidades.carregaVideo("SweetChildOMine.mpg");
        Component theVideo = thePlayer.getVisualComponent();
                
          
        pteste.add(this.bgImageFundoEsquerda);
        pteste.add(theVideo);
        
        pteste.setVisible(true);
        theVideo.setVisible(true);
        this.bgImageFundoEsquerda.setVisible(true);
        this.bgImageFundoEsquerda.setBounds(0,0,429,301);
        theVideo.setBounds(0,301,429,319);
        pteste.setBounds(0,0,429, 620); 
        
        theVideo.repaint();
        this.bgImageFundoEsquerda.repaint();
        pteste.repaint();
        GameEngine.getInstance().getGameCanvas().setPanel(pteste);
        GameEngine.getInstance().setFramesPerSecond(375);
    }

    public void step(long timeElapsed) {
        
        this.guitarra.step(timeElapsed);
        if(!this.musicLoaded){
            this.guitarra.reset();
            /*int[][] notas = new int[Utilidades.getNumeroRandomico(5, 200)][6];
            for(int c=0;c<notas.length;++c){
                notas[c][0] = Utilidades.getNumeroRandomico(1, 300);
                for(int c2 = 1;c2<Utilidades.getNumeroRandomico(1, 5); ++c2){
                    notas[c][c2] = Utilidades.sorteia()?c2:0;
                }
            }*/
            float[][] notas = Utilidades.loadNotesFromMIDI("musicas/gunsnroses-sweet_child_o_mine.mid",(float)thePlayer.getDuration().getSeconds());
            for(int c=0;c<notas.length;++c){
                //notas[c][0] = notas[c][0]/1.05f;
            }
            this.guitarra.setNotas(notas);
            this.musicLoaded = true;
            thePlayer.start();
        }
    }

    public void draw(Graphics g) {
       // g.fillRect(0, 0, 3000, 2400);
        //this.bgImageFundo.draw(g, 0, 0);
       
        this.bgImageFundoDireita.draw(g, 0, 0);
        this.guitarra.draw(g);
        if (Utilidades.estaComOMouseEm(200, 300, 89, 75)) {
            this.bgImagePlay = this.bgImagePlay2;
        } else {
            this.bgImagePlay = this.bgImagePlay1;
        }
    }
    public void stop() {
        thePlayer.stop();
    }
}
