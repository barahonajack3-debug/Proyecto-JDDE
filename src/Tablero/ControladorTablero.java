/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tablero;

import Nivel.Nivel;
import cartas.Carta;

/**
 *
 * @author Usuario
 */
public class ControladorTablero {
    
     private tablero tableroModelo;


    public int getFilas() {
        return tableroModelo.getFilas();
    }

    public int getColumnas() {
        return tableroModelo.getColumnas();
    }

    public void reiniciarTablero() {
        tableroModelo.reiniciarTablero();
    }

    public ControladorTablero(Nivel nivel) {
        this.tableroModelo = new tablero(nivel);
    }

    public Carta obtenerCarta(int fila, int col) {
        return tableroModelo.obtenerCarta(fila, col);
    }

    public boolean compararCartas(Carta c1, Carta c2) {
        return tableroModelo.compararCartas(c1, c2);
    }

    public boolean juegoFinalizado() {
        return tableroModelo.juegoFinalizado();
    }
}
