/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Vista;

import Modelo.Comprador;
import Modelo.DetalleTicket;
import Modelo.Funcion;
import Modelo.Lugar;
import Modelo.TicketCompra;
import Persistencia.DetalleTicketData;
import Persistencia.TicketData;
import java.awt.Frame;
import java.util.List;
import javax.swing.JDialog;
import java.sql.SQLException;
import javax.swing.JOptionPane;
        
public class DialogDetalleTicket extends javax.swing.JDialog {

    private final TicketCompra ticket;
    private final TicketData ticketData;
    private DetalleTicketData detalleTicketData;
    private Comprador comprador;
    private Funcion funcion;
    private List<Lugar> lugares;

    public DialogDetalleTicket(Frame parent, boolean modal, TicketData ticketData, DetalleTicketData detalleTicketData, TicketCompra ticket) {
        super(parent, modal);
        this.ticket = ticket;
        this.ticketData = ticketData;
        this.detalleTicketData = detalleTicketData;
        
        initComponents();
        this.setLocationRelativeTo(parent);
        cargarDetallesTicketExistente(); 
    }
   public DialogDetalleTicket(Frame parent, boolean modal, TicketCompra ticket, Comprador comprador, Funcion funcion, List<Lugar> lugares) {
        super(parent, modal);
        this.ticket = ticket;
        this.comprador = comprador;
        this.funcion = funcion;
        this.lugares = lugares;
      
        this.ticketData = null; 
        this.detalleTicketData = null;
        
        initComponents();
        this.setLocationRelativeTo(parent);
        cargarDetallesNuevaCompra(); 
    }
   private void cargarDetallesNuevaCompra() {
        if (this.ticket == null || this.comprador == null || this.funcion == null || this.lugares == null) {
            JOptionPane.showMessageDialog(this, "Error: Faltan datos para mostrar el detalle.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // --- DATOS DE LA FUNCIÓN ---
        txtPelicula.setText(funcion.getPelicula().getTitulo());
        txtSala.setText(String.valueOf(funcion.getSalaFuncion().getNroSala()));
        txtFechaFuncion.setText(funcion.getFecha().toString());
        txtHoraInicio.setText(funcion.getHoraInicio().toString());

        // --- DETALLES DE LA FUNCIÓN ---
        txtFormato.setText(funcion.isEs3d() ? "3D" : "2D");
        txtSubtitulada.setText(funcion.isSubtitulada() ? "Sí" : "No");
        txtIdioma.setText(funcion.getIdioma());
        
        // --- RESUMEN DE COMPRA ---
        txtNroTicket.setText(String.valueOf(ticket.getCodTicket()));
        txtNombreComprador.setText(comprador.getNombre());
        txtMetodoPago.setText(ticket.isMetodoPago() ? "Tarjeta / Online" : "Efectivo / Taquilla");
        txtFechaCompra.setText(ticket.getFechaCompra().toString());
        txtMontoTotal.setText("$ " + String.valueOf(ticket.getMonto())); // Agregamos $

        // --- ASIENTOS (LUGARES) ---
        StringBuilder asientosStr = new StringBuilder();
        for (Lugar lugar : lugares) {
            asientosStr.append("Fila: ").append(lugar.getFila());
            asientosStr.append(", Asiento: ").append(lugar.getNumero());
            asientosStr.append(" | ");
        }
        if (asientosStr.length() > 3) {
            asientosStr.setLength(asientosStr.length() - 3);
        }
        txtLugarAsignado.setText(asientosStr.toString());
        
        // Hacemos todos los campos no editables
        setCamposEditables(false);
    }
   private void cargarDetallesTicketExistente() {
        if (this.ticket == null) {
            JOptionPane.showMessageDialog(this, "No se pudo cargar el ticket.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // 1. Buscar todos los detalles (asientos) asociados a este ticket
            List<DetalleTicket> detalles = detalleTicketData.listarDetallesPorTicket(ticket.getCodTicket());

            if (detalles.isEmpty()) {
                // Si no hay asientos, al menos mostramos la info básica de la compra
                txtNroTicket.setText(String.valueOf(ticket.getCodTicket()));
                txtNombreComprador.setText(ticket.getComprador().getNombre());
                txtFechaCompra.setText(ticket.getFechaCompra().toString());
                txtMontoTotal.setText("$ " + String.valueOf(ticket.getMonto()));
                txtMetodoPago.setText(ticket.isMetodoPago() ? "Tarjeta / Online" : "Efectivo / Taquilla");
                txtLugarAsignado.setText("¡TICKET SIN ASIENTOS ASIGNADOS! (Error)");
                setCamposEditables(false);
                return;
            }

            // 2. Extraer la información de los objetos
            // (Asumimos que todos los detalles/lugares pertenecen a la misma función)
            this.funcion = detalles.get(0).getLugar().getFuncion();
            this.comprador = ticket.getComprador();

            // --- DATOS DE LA FUNCIÓN ---
            txtPelicula.setText(funcion.getPelicula().getTitulo());
            txtSala.setText(String.valueOf(funcion.getSalaFuncion().getNroSala()));
            txtFechaFuncion.setText(funcion.getFecha().toString());
            txtHoraInicio.setText(funcion.getHoraInicio().toString());

            // --- DETALLES DE LA FUNCIÓN ---
            txtFormato.setText(funcion.isEs3d() ? "3D" : "2D");
            txtSubtitulada.setText(funcion.isSubtitulada() ? "Sí" : "No");
            txtIdioma.setText(funcion.getIdioma());
            
            // --- RESUMEN DE COMPRA ---
            txtNroTicket.setText(String.valueOf(ticket.getCodTicket()));
            txtNombreComprador.setText(comprador.getNombre());
            txtMetodoPago.setText(ticket.isMetodoPago() ? "Tarjeta / Online" : "Efectivo / Taquilla");
            txtFechaCompra.setText(ticket.getFechaCompra().toString());
            txtMontoTotal.setText("$ " + String.valueOf(ticket.getMonto()));

            // --- ASIENTOS (LUGARES) ---
            StringBuilder asientosStr = new StringBuilder();
            for (DetalleTicket det : detalles) {
                Lugar lugar = det.getLugar();
                if (lugar != null) {
                    asientosStr.append("Fila: ").append(lugar.getFila());
                    asientosStr.append(", Asiento: ").append(lugar.getNumero());
                    asientosStr.append(" | ");
                }
            }
            if (asientosStr.length() > 3) {
                asientosStr.setLength(asientosStr.length() - 3);
            }
            txtLugarAsignado.setText(asientosStr.toString());

            // Hacemos todos los campos no editables
            setCamposEditables(false);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al buscar los detalles del ticket: " + ex.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error inesperado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace(); 
        }
    }
   private void setCamposEditables(boolean editable) {
        txtPelicula.setEditable(editable);
        txtSala.setEditable(editable);
        txtFechaFuncion.setEditable(editable);
        txtHoraInicio.setEditable(editable);
        txtLugarAsignado.setEditable(editable);
        txtFormato.setEditable(editable);
        txtSubtitulada.setEditable(editable);
        txtIdioma.setEditable(editable);
        txtNroTicket.setEditable(editable);
        txtNombreComprador.setEditable(editable);
        txtMetodoPago.setEditable(editable);
        txtFechaCompra.setEditable(editable);
        txtMontoTotal.setEditable(editable);
    }
  
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtFechaCompra = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtMontoTotal = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtMetodoPago = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtLugarAsignado = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtPelicula = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtHoraInicio = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtSala = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtFormato = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtIdioma = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtSubtitulada = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel13 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        txtNroTicket = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtNombreComprador = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtFechaFuncion = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Calibri", 3, 18)); // NOI18N
        jLabel1.setText("¡Gracias por su compra!");

        jLabel2.setFont(new java.awt.Font("Calibri", 3, 12)); // NOI18N
        jLabel2.setText("Fecha de la Compra:");

        txtFechaCompra.setEditable(false);

        jLabel3.setFont(new java.awt.Font("Calibri", 3, 12)); // NOI18N
        jLabel3.setText("Monto total: ");

        txtMontoTotal.setEditable(false);

        jLabel4.setFont(new java.awt.Font("Calibri", 3, 12)); // NOI18N
        jLabel4.setText("Metodo de pago:");

        txtMetodoPago.setEditable(false);

        jLabel5.setFont(new java.awt.Font("Calibri", 3, 12)); // NOI18N
        jLabel5.setText("Lugares asignado:");

        txtLugarAsignado.setEditable(false);

        jLabel6.setFont(new java.awt.Font("Calibri", 3, 12)); // NOI18N
        jLabel6.setText("Pelicula:");

        txtPelicula.setEditable(false);

        jLabel7.setFont(new java.awt.Font("Calibri", 3, 12)); // NOI18N
        jLabel7.setText("Hora de Inicio:");

        txtHoraInicio.setEditable(false);

        jLabel8.setFont(new java.awt.Font("Calibri", 3, 12)); // NOI18N
        jLabel8.setText("Sala:");

        txtSala.setEditable(false);

        jLabel9.setFont(new java.awt.Font("Calibri", 3, 12)); // NOI18N
        jLabel9.setText("Formato:");

        txtFormato.setEditable(false);

        jLabel10.setFont(new java.awt.Font("Calibri", 3, 12)); // NOI18N
        jLabel10.setText("Idioma:");

        txtIdioma.setEditable(false);
        txtIdioma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdiomaActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Calibri", 3, 12)); // NOI18N
        jLabel11.setText("Subtitulada:");

        txtSubtitulada.setEditable(false);

        jLabel12.setFont(new java.awt.Font("Calibri", 3, 14)); // NOI18N
        jLabel12.setText("Detalles de la Funcion:");

        jLabel13.setFont(new java.awt.Font("Calibri", 3, 14)); // NOI18N
        jLabel13.setText("Resumen de la Compra");

        jLabel14.setFont(new java.awt.Font("Calibri", 3, 12)); // NOI18N
        jLabel14.setText("N° de Ticket:");

        txtNroTicket.setEditable(false);
        txtNroTicket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNroTicketActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Calibri", 3, 12)); // NOI18N
        jLabel15.setText("Nombre del Comprador:");

        txtNombreComprador.setEditable(false);

        jLabel16.setFont(new java.awt.Font("Calibri", 3, 12)); // NOI18N
        jLabel16.setText("Fecha de la Funcion:");

        txtFechaFuncion.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator4)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 465, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txtMontoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtFechaCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel15)
                                .addComponent(jLabel14))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtMetodoPago, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                                .addComponent(txtNombreComprador)
                                .addComponent(txtNroTicket)))))
                .addGap(49, 49, 49))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel16)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel5))
                                        .addGap(40, 40, 40)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtFechaFuncion, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtPelicula, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(65, 65, 65)
                                        .addComponent(txtSala, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtHoraInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtLugarAsignado, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel12)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(txtFormato, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel11)))
                                        .addGap(18, 18, 18)
                                        .addComponent(txtSubtitulada, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtIdioma, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(289, 289, 289)
                        .addComponent(jLabel13))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(272, 272, 272)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 617, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(153, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPelicula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addComponent(txtSala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtFechaFuncion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(txtHoraInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtLugarAsignado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtFormato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txtSubtitulada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtIdioma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13)
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtNroTicket, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(txtNombreComprador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtMetodoPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtFechaCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtMontoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(197, 197, 197)
                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(321, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdiomaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdiomaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdiomaActionPerformed

    private void txtNroTicketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNroTicketActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNroTicketActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DialogDetalleTicket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DialogDetalleTicket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DialogDetalleTicket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DialogDetalleTicket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTextField txtFechaCompra;
    private javax.swing.JTextField txtFechaFuncion;
    private javax.swing.JTextField txtFormato;
    private javax.swing.JTextField txtHoraInicio;
    private javax.swing.JTextField txtIdioma;
    private javax.swing.JTextField txtLugarAsignado;
    private javax.swing.JTextField txtMetodoPago;
    private javax.swing.JTextField txtMontoTotal;
    private javax.swing.JTextField txtNombreComprador;
    private javax.swing.JTextField txtNroTicket;
    private javax.swing.JTextField txtPelicula;
    private javax.swing.JTextField txtSala;
    private javax.swing.JTextField txtSubtitulada;
    // End of variables declaration//GEN-END:variables
}
