/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juego;
import Nivel.Nivel;
import Tablero.ControladorTablero;
import cartas.Controladorcarta;
import jugadores.ControladorJugador;
/**
 *
 * @author Dario R
 */
public class ControladorJuego {
   private ControladorTablero controladorTablero;
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

    public void iniciarPartida (Nivel nivel) {
    this.nivelActual = nivel;
    this.controladorTablero = new ControladorTablero(nivel);
    this.filaSeleccionada= -1;
    this.colSeleccionada = -1;
    this.esperandoSegundaCarta = false;
}

    public void reiniciarPartida(){
        if (nivelActual== null){
            return;
        }
        controladorTablero.reiniciarTablero();
        filaSeleccionada= -1;
        colSeleccionada = -1;
        esperandoSegundaCarta = false;
        controladorJugador.reiniciarJugador();
    }
    
    public ResultadoSeleccion selecionarCarta (int fila, int col){
        Controladorcarta carta = controladorTablero.obtenerCarta(fila, col);
         if (carta.estaEncontrada() || carta.estaVisible()) {
            return ResultadoSeleccion.SELECCION_INVALIDA;
        }

        carta.mostrarCarta();
        
        if (!esperandoSegundaCarta){
            filaSeleccionada= fila;
            colSeleccionada = col;
            esperandoSegundaCarta = true;
            return ResultadoSeleccion.PRIMERA_CARTA;
        }
        Controladorcarta primeraCarta = controladorTablero.obtenerCarta(filaSeleccionada, colSeleccionada);
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
    
     public void ocultarCartas(int fila1, int col1, int fila2, int col2) {
        controladorTablero.obtenerCarta(fila1, col1).ocultarCarta();
        controladorTablero.obtenerCarta(fila2, col2).ocultarCarta();
        filaSeleccionada = -1;
        colSeleccionada = -1;
    }

    private boolean verificarPareja(Controladorcarta c1, Controladorcarta c2) {
        return controladorTablero.compararCartas(c1, c2);
    }
    
    private void actualizarJugador(boolean acierto){
         controladorJugador.registrarIntentos(acierto);
    }

     public boolean juegoTerminado() {
        return controladorTablero.juegoFinalizado();
     }
     
      public ControladorTablero getTablero() {
        return controladorTablero;
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


