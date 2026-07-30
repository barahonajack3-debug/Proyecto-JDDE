/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cronometro;

/**
 *
 * @author Dario R
 */
public class Cronometro {
     private int segundos;
 
    public Cronometro() {
        this.segundos = 0;
    }
 
    public void reiniciar() {
        this.segundos = 0;
    }
 
    public void incrementar() {
        this.segundos++;
    }
 
    public int getSegundos() {
        return segundos;
    }
    public String getTiempo() {
        int minutos = segundos / 60;
        int segsRestantes = segundos % 60;
        return String.format("%02d:%02d", minutos, segsRestantes);
    }
}
