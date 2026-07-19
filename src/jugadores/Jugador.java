/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jugadores;

/**
 *
 * @author USER
 */
public class Jugador {
    //Atributos
    private int PuntajeObtenido;
    private int CantidadIntentos;
    private int ParejasEncontradas;
    
    //M Get
    public int getPuntajeObtenido() {
        return PuntajeObtenido;
    }
    public int getCantidadIntentos() {
        return CantidadIntentos;
    }
    public int getParejasEncontradas() {
        return ParejasEncontradas;
    }
    
    //Constructor
    public Jugador() {
        this.PuntajeObtenido = 0;
        this.CantidadIntentos = 0;
        this.ParejasEncontradas = 0;
    }
    
    //Funciones
    public void sumarPuntos(int puntos){
        this.PuntajeObtenido+=puntos;
    }
    
    public void restarPuntos(int puntos){
        this.PuntajeObtenido-=puntos;
        if(this.PuntajeObtenido<0){
            this.PuntajeObtenido=0;
        }
    }
    
    public void incrementarIntentos(){
        this.CantidadIntentos++;
    }
    
    public void incrementarParejas(){
        this.ParejasEncontradas++;
    }
    
    public void reiniciar(){
        this.CantidadIntentos=0;
        this.ParejasEncontradas=0;
        this.PuntajeObtenido=0;
    }
    
    //to String
    @Override
    public String toString() {
        return "PuntajeObtenido:" + PuntajeObtenido + "\nCantidadIntentos:" + CantidadIntentos + "\nParejasEncontradas:" + ParejasEncontradas;
    }
}
