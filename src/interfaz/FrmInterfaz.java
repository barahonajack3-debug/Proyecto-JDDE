/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package interfaz;
import Nivel.Nivel;
import juego.ControladorJuego;
import cronometro.Cronometro;
/**
 *
 * @author USER
 */
public class FrmInterfaz extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FrmInterfaz.class.getName());
    /**
     * Creates new form FrmInterfaz
     */
    public FrmInterfaz() {
        initComponents();
        this.setExtendedState(6);
        inicializarArregloBotones();
        juego = new ControladorJuego(panelJugador1.getControlador());
        juego.iniciarPartida(Nivel.PRINCIPIANTE);
        construirTableroVisual();
        iniciarCronometro();
}
    
    private ControladorJuego juego;
    private final Cronometro cronometro = new Cronometro();
    private javax.swing.Timer timerCronometro;
    private javax.swing.JButton[] botones;
    
    private void inicializarArregloBotones() {
    botones = new javax.swing.JButton[]{
        jButton1, jButton2, jButton3, jButton4, jButton5, jButton6, jButton7, jButton8,
        jButton9, jButton11, jButton12, jButton13, jButton14, jButton15, jButton16,
        jButton17, jButton18, jButton19, jButton20, jButton21, jButton22, jButton23, jButton24,
        jButton25, jButton26, jButton27, jButton28, jButton29, jButton30, jButton31, jButton32,
        jButton33, jButton34, jButton35, jButton36, jButton37, jButton38, jButton39, jButton40,
        jButton41, jButton42, jButton43, jButton44, jButton45, jButton46, jButton47, jButton48,
        jButton49, jButton50, jButton51, jButton52, jButton53, jButton54, jButton55, jButton56,
        jButton57, jButton58, jButton59, jButton60, jButton61, jButton62, jButton63, jButton64, jButton65
    };
}
    //Funcion para iniciar el cronometro
    private void iniciarCronometro() {
        if (timerCronometro != null) {
            timerCronometro.stop();
        }
        cronometro.reiniciar();
        jLabel2.setText(cronometro.getTiempoFormateado());
        timerCronometro = new javax.swing.Timer(1000, evt -> {
            cronometro.incrementar();
            jLabel2.setText(cronometro.getTiempoFormateado());
        });
        timerCronometro.start();
    }
    //Funcion para detener el cronometro
    private void detenerCronometro() {
        if (timerCronometro != null) {
            timerCronometro.stop();
        }
    }
    
    //Funcion para construir tablero
    private void construirTableroVisual() {
        int filas = juego.getTablero().getFilas();
        int columnas = juego.getTablero().getColumnas();
        int totalCartas = filas * columnas;
        for (int i = 0; i < botones.length; i++) {
        if (i < totalCartas) {
            final int fila = i / columnas;
            final int col = i % columnas;
            botones[i].setVisible(true);
            botones[i].setEnabled(true);
            botones[i].setIcon(obtenerIcono("reverso"));
            for (java.awt.event.ActionListener listener : botones[i].getActionListeners()) {
                botones[i].removeActionListener(listener);
            }
            botones[i].addActionListener(evt -> manejarClickCarta(fila, col));
        }else{
            botones[i].setVisible(false);
        }
    }
}
    //Funcion para actualizar boton 
    private void actualizarBoton(int fila, int col) {
        int columnas = juego.getTablero().getColumnas();
        int indice = fila * columnas + col;
        cartas.Carta carta = juego.getTablero().obtenerCarta(fila, col);

        if (carta.isVisible() || carta.isEncontrado()) {
        botones[indice].setIcon(obtenerIcono(carta.getImagen()));
        }else{
        botones[indice].setIcon(obtenerIcono("reverso"));
        }
        if (carta.isEncontrado()) {
        botones[indice].setEnabled(false);
        }
    }
   
    private boolean bloqueado = false;
    private void manejarClickCarta(int fila, int col) {
        if (bloqueado) {
        return;
        }
        final int filaPrevia = juego.getFilaSeleccionada();
        final int colPrevia = juego.getColSeleccionada();
        ControladorJuego.ResultadoSeleccion resultado = juego.selecionarCarta(fila, col);
        switch (resultado) {
            case SELECCION_INVALIDA:
                break;
            case PRIMERA_CARTA:
                actualizarBoton(fila, col);
                break;
            case PAREJA_ENCONTRADA:
                actualizarBoton(filaPrevia, colPrevia);
                actualizarBoton(fila, col);
                break;
            case JUEGO_FINALIZADO:
                actualizarBoton(filaPrevia, colPrevia);
                actualizarBoton(fila, col);
                detenerCronometro();
                javax.swing.JOptionPane.showMessageDialog(this,
                        "¡Felicidades, encontraste todas las parejas!",
                        "Juego finalizado", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                break;
            case PAREJA_INCORRECTA:
                actualizarBoton(filaPrevia, colPrevia);
                actualizarBoton(fila, col);
                bloqueado = true;
                // Pequeña pausa para que el jugador vea ambas cartas
                javax.swing.Timer timer = new javax.swing.Timer(800, evt -> {
                    juego.ocultarCartas(filaPrevia, colPrevia, fila, col);
                    actualizarBoton(filaPrevia, colPrevia);
                    actualizarBoton(fila, col);
                    bloqueado = false; 
                });
                timer.setRepeats(false);
                timer.start();
                break;
        }
    }
    
    //Funcion para obtener icono
    private javax.swing.ImageIcon obtenerIcono(String nombreImagen) {
        javax.swing.ImageIcon iconoOriginal = new javax.swing.ImageIcon(
        getClass().getResource("/Icon/IconosM/" + nombreImagen + ".png"));
        java.awt.Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(48, 48, java.awt.Image.SCALE_SMOOTH);
    return new javax.swing.ImageIcon(imagenEscalada);
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        jButton31 = new javax.swing.JButton();
        jButton32 = new javax.swing.JButton();
        jButton33 = new javax.swing.JButton();
        jButton34 = new javax.swing.JButton();
        jButton35 = new javax.swing.JButton();
        jButton36 = new javax.swing.JButton();
        jButton37 = new javax.swing.JButton();
        jButton38 = new javax.swing.JButton();
        jButton39 = new javax.swing.JButton();
        jButton40 = new javax.swing.JButton();
        jButton41 = new javax.swing.JButton();
        jButton42 = new javax.swing.JButton();
        jButton43 = new javax.swing.JButton();
        jButton44 = new javax.swing.JButton();
        jButton45 = new javax.swing.JButton();
        jButton46 = new javax.swing.JButton();
        jButton47 = new javax.swing.JButton();
        jButton48 = new javax.swing.JButton();
        jButton49 = new javax.swing.JButton();
        jButton50 = new javax.swing.JButton();
        jButton51 = new javax.swing.JButton();
        jButton52 = new javax.swing.JButton();
        jButton53 = new javax.swing.JButton();
        jButton54 = new javax.swing.JButton();
        jButton55 = new javax.swing.JButton();
        jButton56 = new javax.swing.JButton();
        jButton57 = new javax.swing.JButton();
        jButton58 = new javax.swing.JButton();
        jButton59 = new javax.swing.JButton();
        jButton60 = new javax.swing.JButton();
        jButton61 = new javax.swing.JButton();
        jButton62 = new javax.swing.JButton();
        jButton63 = new javax.swing.JButton();
        jButton64 = new javax.swing.JButton();
        jButton65 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        panelJugador1 = new jugadores.PanelJugador();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Juego Memoria");
        setBackground(new java.awt.Color(153, 153, 153));
        setExtendedState(6);

        jPanel1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        jPanel1.setLayout(new java.awt.GridLayout(8, 8, 8, 8));
        jPanel1.add(jButton1);
        jPanel1.add(jButton2);
        jPanel1.add(jButton3);
        jPanel1.add(jButton4);
        jPanel1.add(jButton5);
        jPanel1.add(jButton6);
        jPanel1.add(jButton7);
        jPanel1.add(jButton8);
        jPanel1.add(jButton9);
        jPanel1.add(jButton11);
        jPanel1.add(jButton12);
        jPanel1.add(jButton13);
        jPanel1.add(jButton14);
        jPanel1.add(jButton15);
        jPanel1.add(jButton16);
        jPanel1.add(jButton17);
        jPanel1.add(jButton18);
        jPanel1.add(jButton19);
        jPanel1.add(jButton20);
        jPanel1.add(jButton21);
        jPanel1.add(jButton22);
        jPanel1.add(jButton23);
        jPanel1.add(jButton24);
        jPanel1.add(jButton25);
        jPanel1.add(jButton26);
        jPanel1.add(jButton27);
        jPanel1.add(jButton28);
        jPanel1.add(jButton29);
        jPanel1.add(jButton30);
        jPanel1.add(jButton31);
        jPanel1.add(jButton32);
        jPanel1.add(jButton33);
        jPanel1.add(jButton34);
        jPanel1.add(jButton35);
        jPanel1.add(jButton36);
        jPanel1.add(jButton37);
        jPanel1.add(jButton38);
        jPanel1.add(jButton39);
        jPanel1.add(jButton40);
        jPanel1.add(jButton41);
        jPanel1.add(jButton42);
        jPanel1.add(jButton43);
        jPanel1.add(jButton44);
        jPanel1.add(jButton45);
        jPanel1.add(jButton46);
        jPanel1.add(jButton47);
        jPanel1.add(jButton48);
        jPanel1.add(jButton49);
        jPanel1.add(jButton50);
        jPanel1.add(jButton51);
        jPanel1.add(jButton52);
        jPanel1.add(jButton53);
        jPanel1.add(jButton54);
        jPanel1.add(jButton55);
        jPanel1.add(jButton56);
        jPanel1.add(jButton57);
        jPanel1.add(jButton58);
        jPanel1.add(jButton59);
        jPanel1.add(jButton60);
        jPanel1.add(jButton61);
        jPanel1.add(jButton62);
        jPanel1.add(jButton63);
        jPanel1.add(jButton64);
        jPanel1.add(jButton65);

        jPanel2.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10)), javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        jComboBox1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Principiante", "Intermedio", "Avanzado" }));
        jComboBox1.addActionListener(this::jComboBox1ActionPerformed);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("00:00");

        jButton10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton10.setText("Reiniciar");
        jButton10.addActionListener(this::jButton10ActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButton10))
                .addGap(0, 51, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton10))
        );

        panelJugador1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelJugador1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 11, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelJugador1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 50, Short.MAX_VALUE)))
                .addContainerGap())
        );

        setBounds(0, 0, 879, 359);
    }// </editor-fold>//GEN-END:initComponents
    
    //Boton para cambiar de nivel
    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        String seleccion = (String) jComboBox1.getSelectedItem();
        Nivel nivelSeleccionado = Nivel.valueOf(seleccion.toUpperCase());
        juego.iniciarPartida(nivelSeleccionado);
        panelJugador1.getControlador().reiniciarJugador();
        construirTableroVisual();
        iniciarCronometro();
    }//GEN-LAST:event_jComboBox1ActionPerformed
    
    //Boton para reiniciar
    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        juego.reiniciarPartida();
        construirTableroVisual();
        iniciarCronometro();
        bloqueado = false;
    }//GEN-LAST:event_jButton10ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new FrmInterfaz().setVisible(true));
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton41;
    private javax.swing.JButton jButton42;
    private javax.swing.JButton jButton43;
    private javax.swing.JButton jButton44;
    private javax.swing.JButton jButton45;
    private javax.swing.JButton jButton46;
    private javax.swing.JButton jButton47;
    private javax.swing.JButton jButton48;
    private javax.swing.JButton jButton49;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton50;
    private javax.swing.JButton jButton51;
    private javax.swing.JButton jButton52;
    private javax.swing.JButton jButton53;
    private javax.swing.JButton jButton54;
    private javax.swing.JButton jButton55;
    private javax.swing.JButton jButton56;
    private javax.swing.JButton jButton57;
    private javax.swing.JButton jButton58;
    private javax.swing.JButton jButton59;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton60;
    private javax.swing.JButton jButton61;
    private javax.swing.JButton jButton62;
    private javax.swing.JButton jButton63;
    private javax.swing.JButton jButton64;
    private javax.swing.JButton jButton65;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private jugadores.PanelJugador panelJugador1;
    // End of variables declaration//GEN-END:variables
}