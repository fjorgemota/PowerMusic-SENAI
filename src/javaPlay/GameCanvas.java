/*
 * GameCanvas
 */

package javaPlay;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author VisionLab/PUC-Rio
 */
public class GameCanvas extends JFrame
{
    private final int defaultScreenWidth = 800;
    private final int defaultScreenHeight = 640;
    private Graphics g;
    private BufferStrategy bf;
    private int renderScreenStartX;
    private int renderScreenStartY;
    private JComponent panel;
    private Canvas video;
    public GameCanvas(GraphicsConfiguration gc)
    {
        super();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(defaultScreenWidth, defaultScreenHeight);
        setLayout(null);
        
        setResizable(false);
        setVisible(true);
        
        //createBufferStrategy(2);

        renderScreenStartX = this.getContentPane().getLocationOnScreen().x;
        renderScreenStartY = this.getContentPane().getLocationOnScreen().y;

        
        this.video = new Canvas(gc);
        this.video.setVisible(true);
        this.video.setBounds(0, 0, defaultScreenWidth, defaultScreenHeight);
        add(this.video);
        this.video.createBufferStrategy(2);
        bf = this.video.getBufferStrategy();
    }

    public int getRenderScreenStartX()
    {
        return renderScreenStartX;
    }

    public int getRenderScreenStartY()
    {
        return renderScreenStartY;
    }

    public Graphics getGameGraphics()
    {        
        this.video.requestFocusInWindow();
        g = bf.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        return g;
    }
    public void setPanel(JComponent panel){
        if(panel == null){
            if(this.panel == null){
                return;
            }
            this.remove(this.panel);
            this.video.setBounds(0, 0, defaultScreenWidth, defaultScreenHeight);
            this.setVisible(false);
            this.setVisible(true);
            this.panel = panel;
        }
        else{
            //this.remove(video);           
            this.video.setFocusable(true);
            Dimension d = panel.getSize();
            this.video.setBounds(d.width, 0, defaultScreenWidth-d.width, defaultScreenHeight);
            this.add(panel);
            this.video.repaint();
            panel.repaint();
            this.repaint();
            this.setVisible(false);
            this.setVisible(true);
        }
        this.panel = panel;
    }
    public void addMouseListener(MouseListener l){
        this.video.addMouseListener(l);
        
    }
    public void addMouseMotionListener(MouseMotionListener l){
        this.video.addMouseMotionListener(l);
    }
    public void addKeyListener(KeyListener l){
        this.video.addKeyListener(l);
    }
    public void swapBuffers()
    {
        bf.show();
        g.dispose();       
        Toolkit.getDefaultToolkit().sync();
    }
}
