/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guitarra;

import javaPlay.GameEngine;
import javaPlay.Keyboard;
import javaPlay.Sprite;
import javaPlayExtras.Imagem;
import javaPlayExtras.Keys;
import utilidades.Utilidades;

/**
 *
 * @author fernando_mota
 */
class Verde extends Esfera {

    protected static int serie;
    public Verde() {
        super();
        try {
            this.imagem = new Sprite("img_cenario/Sprites/" + (this.isEspecial() ? "efeito" : "sprite") + "_verde.png", 9, 49, 28);
        } catch (Exception e) {
            Utilidades.alertar(e.getMessage());
        }
        this.tecla = Keys.A;
    }

    public Verde(int serie) {
        this();
        Verde.serie = serie;
    }

    public int getSerie() {
        return Verde.serie;
    }

    public void setSerie(int serie) {
        Verde.serie = serie;
    }

    public Esfera getNewInstance() {
        System.out.println("Retornando nova instancia de esfera Verde");
        return new Verde();
    }

    public void step(long timeElapsed) {
        if (teclado.keyDown(this.tecla)) {
            this.pressionar();
        }
        super.preLocate(timeElapsed);
        this.x -= -20 + ((165 / 620.00000f) * this.y);
    }

}
