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

        setClosable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblNroSala.setText("NroSala:");
        getContentPane().add(lblNroSala, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 61, -1, -1));

        lblCapacidad.setText("Capacidad:");
        getContentPane().add(lblCapacidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 112, -1, -1));
        getContentPane().add(txtNroSala, new org.netbeans.lib.awtextra.AbsoluteConstraints(209, 58, 109, -1));

        txtCapacidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCapacidadActionPerformed(evt);
            }
        });
        getContentPane().add(txtCapacidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(208, 109, 110, -1));
        getContentPane().add(chkApta3D, new org.netbeans.lib.awtextra.AbsoluteConstraints(213, 160, -1, -1));

        lblApta3D.setText("¿Es apta  para 3D?:");
        getContentPane().add(lblApta3D, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 160, -1, -1));

        lblEstado.setText("Estado");
        getContentPane().add(lblEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 208, -1, -1));

        chkEstado.setText("Activo");
        chkEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkEstadoActionPerformed(evt);
            }
        });
        getContentPane().add(chkEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(213, 206, -1, -1));

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(104, 260, -1, -1));

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(213, 260, -1, -1));

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTitulo.setText("Crear Nueva Sala");
        getContentPane().add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 6, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chkEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkEstadoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {
         
            int nroSala = Integer.parseInt(txtNroSala.getText().trim());
            int capacidad = Integer.parseInt(txtCapacidad.getText().trim());
            boolean apta3d = chkApta3D.isSelected(); 
            boolean estado = chkEstado.isSelected();

        
           
            Sala nuevaSala = new Sala(nroSala,apta3d,capacidad,estado);
            
            if (salaData.insertarSala(nuevaSala)) {
                JOptionPane.showMessageDialog(this, "Sala guardada exitosamente con CodSala: " + nuevaSala.getCodSala(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
                
              
                if (vistaPrincipal != null) {
                    vistaPrincipal.cargarTablaCompleta(); 
                }
                
              
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
