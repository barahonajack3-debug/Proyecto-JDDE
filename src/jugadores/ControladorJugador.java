/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jugadores;

/**
 *
 * @author USER
 */
public class ControladorJugador {
    //Atributos
    private Jugador jugador;
    private PanelJugador vista;
    
    //M get
    public int getPuntaje() {
        return jugador.getPuntajeObtenido();
    }

    public int getIntentos() {
        return jugador.getCantidadIntentos();
    }

    public int getParejasEncontradas() {
        return jugador.getParejasEncontradas();
    }
    //M set
    public void setVista(PanelJugador vista){
        this.vista=vista;
        actualizarVista();
    }
    
    public ControladorJugador(){
        this.jugador = new Jugador();
    }
   
    public void registrarIntemtos(boolean aciertos){
        jugador.incrementarIntentos();
        jugador.restarPuntos(20);
        
        if(aciertos){
            jugador.sumarPuntos(100);
            jugador.incrementarParejas();
        }
        actualizarVista();
    }
    
    public void reiniciarJugador() {
        jugador.reiniciar();
        actualizarVista();
    }

    private void actualizarVista() {
        if (vista != null) {
            vista.actualizarPuntaje(jugador.getPuntajeObtenido());
            vista.actualizarIntentos(jugador.getCantidadIntentos());
            vista.actualizarParejas(jugador.getParejasEncontradas());
        }
    }
}