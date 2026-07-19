/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cartas;

/**
 *
 * @author EMMAXZZ
 */
public class Carta {
    private String imagen;
    private boolean visible;
    private boolean encontrado;

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isEncontrado() {
        return encontrado;
    }

    public void setEncontrado(boolean encontrado) {
        this.encontrado = encontrado;
    }

    public Carta(String imagen) {
        this.imagen = imagen;
        this.visible = false;
        this.encontrado = false;
    }
    
    
    public void mostrarCarta(){
        this.visible = true;
    }
    
    public void ocultar(){
        if(this.encontrado == false){
            this.visible = false;
        } 
    }
    
      public void marcarEncontrada(){
       this.encontrado = true;
        this.visible = true;
        
    }
    
}
