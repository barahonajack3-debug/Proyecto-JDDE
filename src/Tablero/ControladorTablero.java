/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tablero;

import Nivel.Nivel;
import cartas.Carta;
import cartas.Controladorcarta;

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

    public Controladorcarta obtenerCarta(int fila, int col) {
        Carta carta = tableroModelo.obtenerCarta(fila, col);
        return new Controladorcarta(carta);
    }

    public boolean compararCartas(Controladorcarta c1, Controladorcarta c2) {
        return c1.obtenerImagen().equals(c2.obtenerImagen());
    }

    public boolean juegoFinalizado() {
        return tableroModelo.juegoFinalizado();
    }
}