/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package interfaz;
import jugadores.PanelJugador;
import Nivel.Nivel;
import Tablero.tablero;
import javax.swing.JFrame;
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
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        juego = new ControladorJuego(panelJugador1.getControlador());
        juego.iniciarPartida(Nivel.PRINCIPIANTE);
        construirTableroVisual(Nivel.PRINCIPIANTE);
        iniciarCronometro();
    }
    private ControladorJuego juego;
    private javax.swing.JButton[][] botonesCartas;
    private final Cronometro cronometro = new Cronometro();
    private javax.swing.Timer timerCronometro;
    
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
 
     private void detenerCronometro() {
        if (timerCronometro != null) {
            timerCronometro.stop();
        }
    }
     
    private void construirTableroVisual(Nivel nivel) {
    int filas = juego.getTablero().getFilas();
    int columnas = juego.getTablero().getColumnas();
    jPanel1.removeAll();
    jPanel1.setLayout(new java.awt.GridLayout(filas, columnas, 5, 5));
    botonesCartas = new javax.swing.JButton[filas][columnas];
    for (int fila = 0; fila < filas; fila++) {
        for (int col = 0; col < columnas; col++) {
            javax.swing.JButton boton = new javax.swing.JButton("?");
            final int f = fila;
            final int c = col;
            boton.addActionListener(evt -> manejarClickCarta(f, c));
            botonesCartas[fila][col] = boton;
            jPanel1.add(boton);
        }
    }
    jPanel1.revalidate();
    jPanel1.repaint();
} private void actualizarBoton(int fila,int col){
        cartas.Carta carta = juego.getTablero().obtenerCarta(fila, col);
        if (carta.isVisible() || carta.isEncontrado()) {
            botonesCartas[fila][col].setText(carta.getImagen());
        } else {
            botonesCartas[fila][col].setText("?");
        }
        if (carta.isEncontrado()) {
            botonesCartas[fila][col].setEnabled(false);
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
                // clic repetido / carta ya encontrada: no hacemos nada
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
                // Pequeña pausa para que el jugador vea ambas cartas antes
                // de que el modelo las oculte de nuevo.
                javax.swing.Timer timer = new javax.swing.Timer(800, evt -> {
                    juego.ocultarCartas(filaPrevia, colPrevia, fila, col);
                    actualizarBoton(filaPrevia, colPrevia);
                    actualizarBoton(fila, col);
                });
                timer.setRepeats(false);
                timer.start();
                break;
        }
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        panelJugador1 = new jugadores.PanelJugador();

        jLabel1.setText("\"tablero\"");

        jButton5.setText("jButton5");
        jButton5.addActionListener(this::jButton5ActionPerformed);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Juego Memoria");

        jPanel1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 374, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 281, Short.MAX_VALUE)
        );

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
                .addComponent(jButton10)
                .addGap(0, 16, Short.MAX_VALUE))
        );

        panelJugador1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelJugador1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelJugador1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {
        String seleccion = (String) jComboBox1.getSelectedItem();
         Nivel nivelSeleccionado = Nivel.valueOf(seleccion.toUpperCase());
         juego.iniciarPartida(nivelSeleccionado);
         panelJugador1.getControlador().reiniciarJugador();
        construirTableroVisual(Nivel.PRINCIPIANTE);
        iniciarCronometro();
    }//GEN-LAST:event_jComboBox1ActionPerformed
    
    //Boton reiniciar
    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        juego.reiniciarPartida();
        construirTableroVisual(Nivel.PRINCIPIANTE);
        iniciarCronometro();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

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
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private jugadores.PanelJugador panelJugador1;
    // End of variables declaration//GEN-END:variables
}