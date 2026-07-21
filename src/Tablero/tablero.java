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
public class tablero {
    private Carta[][] cartas;
    private int filas;
    private int columnas;
    private Nivel nivel;

    // ===== CONSTRUCTOR =====
    public tablero(Nivel nivel) {
        this.nivel = nivel;
        definirDimensiones();
        inicializarTablero();
    }

    // ===== MÉTODOS (void) =====
    private void definirDimensiones() {
        switch (nivel) {
            case PRINCIPIANTE:
                filas = 4;
                columnas = 4;
                break;
            case INTERMEDIO:
                filas = 4;
                columnas = 8;
                break;
            case AVANZADO:
                filas = 8;
                columnas = 8;
                break;
        }
    }

    public void inicializarTablero() {
        cartas = new Carta[filas][columnas];
        distribuirParejas();
    }

    public void distribuirParejas() {
        int totalCartas = filas * columnas;
        String[] imagenes = new String[totalCartas];

        int index = 0;
        for (int i = 1; i <= nivel.getCantidadParejas(); i++) {
            imagenes[index] = "img" + i;
            index++;
            imagenes[index] = "img" + i;
            index++;
        }

        for (int i = imagenes.length - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            String temp = imagenes[i];
            imagenes[i] = imagenes[j];
            imagenes[j] = temp;
        }

        int indice = 0;
        for (int fila = 0; fila < filas; fila++) {
            for (int col = 0; col < columnas; col++) {
                cartas[fila][col] = new Carta(imagenes[indice]);
                indice++;
            }
        }
    }

    public void reiniciarTablero() {
        distribuirParejas();
    }

    // ===== FUNCIONES (con retorno) =====
    public Carta obtenerCarta(int fila, int col) {
        return cartas[fila][col];
    }

    public boolean compararCartas(Carta c1, Carta c2) {
        return c1.getImagen().equals(c2.getImagen());
    }

    public boolean juegoFinalizado() {
        for (int fila = 0; fila < filas; fila++) {
            for (int col = 0; col < columnas; col++) {
                if (!cartas[fila][col].isEncontrado()) {
                    return false;
                }
            }
        }
        return true;
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }
}