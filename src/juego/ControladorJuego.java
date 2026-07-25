/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juego;
import Nivel.Nivel;
import Tablero.tablero;
import cartas.Carta;
import jugadores.ControladorJugador;
/**
 *
 * @author Dario R
 */
public class ControladorJuego {
   private tablero tablero;
   private Nivel nivelActual;
   
  private final ControladorJugador controladorJugador;
  private int filaSeleccionada =-1;
  private int colSeleccionada =-1;   
  private boolean esperandoSegundaCarta = false;
  
public enum ResultadoSeleccion {
    SELECCION_INVALIDA,
    PRIMERA_CARTA,
    PAREJA_ENCONTRADA,
    PAREJA_INCORRECTA,
    JUEGO_FINALIZADO;
    }

    public ControladorJuego(ControladorJugador controladorJugador) {
        this.controladorJugador = controladorJugador;
    }

    //Iniciar partida//
    
    //Metodo de Inicio de partida//
    public void iniciarPartida (Nivel nivel) {
    this.nivelActual = nivel;
    this.tablero = new tablero(nivel);
    this.filaSeleccionada= -1;
    this.colSeleccionada = -1;
    this.esperandoSegundaCarta = false;
}

    //Metodo de reinicio de partida//
    public void reiniciarPartida(){
        if (nivelActual== null){
            return;
        }
        tablero.reiniciarTablero();
        filaSeleccionada= -1;
        colSeleccionada = -1;
        esperandoSegundaCarta = false;
        controladorJugador.reiniciarJugador();
    }
    
    //Metodo para selecionar las cartas//
    public ResultadoSeleccion selecionarCarta (int fila, int col){
        Carta carta= tablero.obtenerCarta(fila, col);
         if (carta.isEncontrado() || carta.isVisible()) {
            return ResultadoSeleccion.SELECCION_INVALIDA;
        }


        carta.mostrarCarta();
        
        if (!esperandoSegundaCarta){
            filaSeleccionada= fila;
            colSeleccionada = col;
            esperandoSegundaCarta = true;
            return ResultadoSeleccion.PRIMERA_CARTA;
        }
        //Metodo de comparacion de la primera carta//
        Carta primeraCarta = tablero.obtenerCarta(filaSeleccionada, colSeleccionada);
        boolean esPareja = verificarPareja(primeraCarta, carta);
 
        esperandoSegundaCarta = false;
        actualizarJugador(esPareja);
 
        if (esPareja) {
            primeraCarta.marcarEncontrada();
            carta.marcarEncontrada();
            filaSeleccionada = -1;
            colSeleccionada = -1;
 
            if (juegoTerminado()) {
                return ResultadoSeleccion.JUEGO_FINALIZADO;
            }
            return ResultadoSeleccion.PAREJA_ENCONTRADA;
        } else {
            return ResultadoSeleccion.PAREJA_INCORRECTA;
        }
     }
    
    //Metodo para ocultar las cartas
     public void ocultarCartas(int fila1, int col1, int fila2, int col2) {
        tablero.obtenerCarta(fila1, col1).ocultar();
        tablero.obtenerCarta(fila2, col2).ocultar();
        filaSeleccionada = -1;
        colSeleccionada = -1;
    }

    
    //Metodo para hacer la comprobacion de las parejas
    private boolean verificarPareja(Carta c1, Carta c2) {
        return tablero.compararCartas(c1, c2);
    }
    
    //Metodo para actualizar la informacion del jugador
    private void actualizarJugador(boolean acierto){
         controladorJugador.registrarIntentos(acierto);
    }
    //Metodo para finalizar el juego
     public boolean juegoTerminado() {
        return tablero.juegoFinalizado();
     }
     
//Metodos Getter
      public tablero getTablero() {
        return tablero;
    }
 
    public Nivel getNivelActual() {
        return nivelActual;
    }
 
    public int getFilaSeleccionada() {
        return filaSeleccionada;
    }
 
    public int getColSeleccionada() {
        return colSeleccionada;
    }

}


