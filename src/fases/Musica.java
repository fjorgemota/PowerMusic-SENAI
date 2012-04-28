/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fases;

import guitarra.Guitarra;
import java.awt.Component;
import java.awt.Graphics;
import javaPlay.GameEngine;
import javaPlay.GameStateController;
import javaPlayExtras.Imagem;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import utilidades.MIDIReader;
import utilidades.Video;

/**
 *
 * @author fernando_mota
 */
public class Musica implements GameStateController {
    private int level;
    private String musicFile;
    private String bgFile;
    private String guitarraFile;
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
    public Musica(String musicFile, String fundo, String guitarra, int level){
         this.musicFile = musicFile;
         this.bgFile = fundo;
         this.guitarraFile = guitarra;
         this.guitarra = Guitarra.getInstance();
         this.level = level;
    }
    @Override
    public void load() {
        this.musica = new MIDIReader("musicas/"+this.musicFile+".mid");
         this.video = new Video(this.musicFile+".mpg");
         
        try {
            this.bgImageFundoEsquerda =  new JLabel(new ImageIcon(this.bgFile));
            this.bgImageFundo = new Imagem("img_cenario/fundo.png");
            this.bgImageFundoDireita = new Imagem(this.guitarraFile);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
    }

    @Override
    public void unload() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void start() {
        this.guitarra.setLevel(this.level);
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
    @Override
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

    @Override
    public void draw(Graphics g) {
        // g.fillRect(0, 0, 3000, 2400);
        //this.bgImageFundo.draw(g, 0, 0);
       
        this.bgImageFundoDireita.draw(g, 0, 0);
        this.guitarra.draw(g);
    }

    @Override
    public void stop() {
        this.video.pause();
    }
    
}
