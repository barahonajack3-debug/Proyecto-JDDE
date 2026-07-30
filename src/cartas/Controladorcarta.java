/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cartas;

/**
 *
 * @author EMMAXZZ
 */
public class Controladorcarta {
    
    private Carta carta;

    public Controladorcarta(Carta carta) {
        this.carta = carta;
    }

    public void mostrarCarta() {
        carta.setVisible(true);
    }

    public void ocultarCarta() {
        if (!carta.isEncontrado()) {
            carta.setVisible(false);
        }
    }

    public void marcarEncontrada() {
        carta.setEncontrado(true);
        carta.setVisible(true);
    }

    public boolean estaVisible() {
        return carta.isVisible();
    }

    public boolean estaEncontrada() {
        return carta.isEncontrado();
    }

    public String obtenerImagen() {
        return carta.getImagen();
    }
}
