/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cronometro;

/**
 *
 * @author Usuario
 */
public class ControladorCronometro {

    private Cronometro cronometroModelo;

    public int getSegundos() {
        return cronometroModelo.getSegundos();
    }

    public String getTiempo() {
        return cronometroModelo.getTiempo();
    }


    public void reiniciar() {
        cronometroModelo.reiniciar();
    }

    public void incrementar() {
        cronometroModelo.incrementar();
    }

    public ControladorCronometro() {
        this.cronometroModelo = new Cronometro();
    }
}
