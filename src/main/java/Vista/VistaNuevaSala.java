/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Vista;

import Modelo.Sala;
import Persistencia.SalaData;
import javax.swing.JOptionPane;
import javax.swing.JInternalFrame;
import javax.swing.JCheckBox;

public class VistaNuevaSala extends javax.swing.JInternalFrame {

    private final SalaData salaData;
    private final VistaSala vistaPrincipal;
    
    public VistaNuevaSala(SalaData salaData, VistaSala vistaPrincipal) {
        initComponents();
        this.salaData = salaData;
        this.vistaPrincipal = vistaPrincipal;
        this.setTitle("Crear nueva Sala");
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNroSala = new javax.swing.JLabel();
        lblCapacidad = new javax.swing.JLabel();
        txtNroSala = new javax.swing.JTextField();
        txtCapacidad = new javax.swing.JTextField();
        chkApta3D = new javax.swing.JCheckBox();
        lblApta3D = new javax.swing.JLabel();
        lblEstado = new javax.swing.JLabel();
        chkEstado = new javax.swing.JCheckBox();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblTitulo = new javax.swing.JLabel();

        lblNroSala.setText("NroSala:");

        lblCapacidad.setText("Capacidad:");

        txtCapacidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCapacidadActionPerformed(evt);
            }
        });

        lblApta3D.setText("¿Es apta  para 3D?:");

        lblEstado.setText("Estado");

        chkEstado.setText("Activo");
        chkEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkEstadoActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTitulo.setText("Crear Nueva Sala");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnGuardar)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblApta3D)
                                        .addComponent(lblEstado)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(37, 37, 37)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(chkEstado)
                                            .addComponent(chkApta3D)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(100, 100, 100)
                                        .addComponent(btnCancelar))))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblCapacidad)
                                    .addGap(74, 74, 74)
                                    .addComponent(txtCapacidad))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(lblNroSala)
                                    .addGap(89, 89, 89)
                                    .addComponent(txtNroSala, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(lblTitulo)))
                .addContainerGap(100, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNroSala)
                    .addComponent(txtNroSala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCapacidad)
                    .addComponent(txtCapacidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkApta3D)
                    .addComponent(lblApta3D))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEstado)
                    .addComponent(chkEstado))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar))
                .addContainerGap(75, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chkEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkEstadoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {
            // 1. Validar y obtener los datos de los campos
            int nroSala = Integer.parseInt(txtNroSala.getText().trim());
            int capacidad = Integer.parseInt(txtCapacidad.getText().trim());
            boolean apta3d = chkApta3D.isSelected(); // True si el checkbox está marcado
            boolean estado = chkEstado.isSelected(); // True si el checkbox está marcado (Activa)

            // 2. Crear el objeto Sala
            // Usamos el constructor que solo requiere los datos, no el codSala autoincremental
           
            Sala nuevaSala = new Sala(nroSala,apta3d,capacidad,estado);
            
            if (salaData.insertarSala(nuevaSala)) {
                JOptionPane.showMessageDialog(this, "Sala guardada exitosamente con CodSala: " + nuevaSala.getCodSala(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
                
              
                if (vistaPrincipal != null) {
                    vistaPrincipal.cargarTablaCompleta(); 
                }
                
                // 5. Cerrar la ventana
                this.dispose();
                
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo guardar la sala.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El Nro Sala y la Capacidad deben ser números enteros válidos.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error inesperado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
       
        this.dispose(); 
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtCapacidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCapacidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCapacidadActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JCheckBox chkApta3D;
    private javax.swing.JCheckBox chkEstado;
    private javax.swing.JLabel lblApta3D;
    private javax.swing.JLabel lblCapacidad;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblNroSala;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtCapacidad;
    private javax.swing.JTextField txtNroSala;
    // End of variables declaration//GEN-END:variables
}
