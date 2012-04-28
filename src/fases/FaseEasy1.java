package fases;

import guitarra.Guitarra;
import java.awt.Component;
import java.awt.Graphics;
import java.util.ArrayList;
import javaPlay.GameEngine;
import javaPlay.GameStateController;
import javaPlayExtras.Imagem;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import utilidades.MIDIReader;
import utilidades.Video;

//MÚSICAS DA FASE
//Gone
//Tnt
//Independencia

public class FaseEasy1 implements GameStateController {

    private Imagem bgImageFundo;
   
    private Imagem bgImageGuitarra;
    private Imagem bgImagePlayEfeito;
    private JLabel bgImageFundoEsquerda;
    private JLabel progresso;
    private Imagem bgImageFundoDireita;
    private Guitarra guitarra;
    private boolean musicLoaded = false;
    private boolean videoStarted = false;
    private MIDIReader musica;
    private Video video;
    private JPanel thePanel;
    private Component theVideo;
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
        String musicName = "SweetChildOMine";
        this.video = new Video(musicName+".mpg");
        this.musica = new MIDIReader("musicas/"+musicName+".mid");
    }
    
    public void unload() {
    }

    public void start() {
        this.guitarra = Guitarra.getInstance();
        this.guitarra.setLevel(5);
        thePanel = new JPanel();
        thePanel.setLayout(null);
        
        theVideo = this.video.getSwingComponent();
                
          
        thePanel.add(this.bgImageFundoEsquerda);
        thePanel.add(theVideo);
        
        thePanel.setVisible(true);
        theVideo.setVisible(true);
        this.bgImageFundoEsquerda.setVisible(true);
        this.bgImageFundoEsquerda.setBounds(0,0,429,301);
        theVideo.setBounds(0,301,429,319);
        thePanel.setBounds(0,0,429, 620); 
        
        theVideo.repaint();
        this.bgImageFundoEsquerda.repaint();
        thePanel.repaint();
        GameEngine.getInstance().getGameCanvas().setPanel(thePanel);
        GameEngine.getInstance().setFramesPerSecond(200);
        this.guitarra.setMinorTime();
        this.musica.setInterval(0.5f);
        this.musica.refresh();
        this.changeProgressImage();
    }
    private void changeProgressImage(){
        JLabel novoProgresso = this.guitarra.getImageProgress();
        if(this.progresso != null){
            thePanel.remove(this.progresso);
        }
        novoProgresso.setBounds(10,10,107,107);
        novoProgresso.setVisible(true);
        novoProgresso.setLayout(null);
        this.progresso = novoProgresso;
        thePanel.add(novoProgresso, new Integer(1),0);
        thePanel.repaint();
        
    }
    public void step(long timeElapsed) {
        this.changeProgressImage();
        if(this.musicLoaded==false){
            
            /*int[][] notas = new int[Utilidades.getNumeroRandomico(5, 200)][6];
            for(int c=0;c<notas.length;++c){
                notas[c][0] = Utilidades.getNumeroRandomico(1, 300);
                for(int c2 = 1;c2<Utilidades.getNumeroRandomico(1, 5); ++c2){
                    notas[c][c2] = Utilidades.sorteia()?c2:0;
                }
            }*/
            
            
            this.musica.setDuration(video.getDuration());
            float[][] notas = this.musica.getNotes();
            //float[][] notas = Utilidades.loadNotesFromMIDI("musicas/SweetChildOMine.mid");
            for(int c=0;c<notas.length;++c){
                //notas[c][0] = notas[c][0]/1.05f;
            }
            this.guitarra.reset();
            
            this.guitarra.setNotas(notas);
            this.musicLoaded = true;
        }
        else{
            if(this.guitarra.isFirstNotePlayed() && this.videoStarted == false){
                this.video.play();
                this.videoStarted = true;
            }
            else if(this.videoStarted == true){
                timeElapsed = 0;
                this.guitarra.addVideoTime(this.video.getActualTime()*1000);
            }
            this.guitarra.step(timeElapsed);
        }
        

        
    }

    public void draw(Graphics g) {
       // g.fillRect(0, 0, 3000, 2400);
        //this.bgImageFundo.draw(g, 0, 0);
       
        this.bgImageFundoDireita.draw(g, 0, 0);
        this.guitarra.draw(g);
       
    }
    public void stop() {
        video.pause();
    }
}
