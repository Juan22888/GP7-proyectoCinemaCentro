/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Vista;

import Modelo.Sala;
import Persistencia.SalaData;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
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
        lblEstado = new javax.swing.JLabel();
        chkEstado = new javax.swing.JCheckBox();
        ImageIcon icono = new ImageIcon(getClass().getResource("/Fondos/img/fondo-administracion2.jpg"));
        Image miImagen = icono.getImage();
        jDesktopPane1 = new javax.swing.JDesktopPane(){
            public void paintComponent(Graphics g){
                g.drawImage(miImagen,0,0, getWidth(), getHeight(),this);
            }

        };
        lblTitulo = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        lblApta3D = new javax.swing.JLabel();
        chkApta3D = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblNroSala.setForeground(new java.awt.Color(255, 255, 255));
        lblNroSala.setText("NroSala:");
        getContentPane().add(lblNroSala, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 109, -1, -1));

        lblCapacidad.setForeground(new java.awt.Color(255, 255, 255));
        lblCapacidad.setText("Capacidad:");
        getContentPane().add(lblCapacidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 160, -1, -1));
        getContentPane().add(txtNroSala, new org.netbeans.lib.awtextra.AbsoluteConstraints(179, 106, 109, -1));

        txtCapacidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCapacidadActionPerformed(evt);
            }
        });
        getContentPane().add(txtCapacidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(179, 157, 110, -1));

        lblEstado.setForeground(new java.awt.Color(255, 255, 255));
        lblEstado.setText("Estado");
        getContentPane().add(lblEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 256, -1, -1));

        chkEstado.setText("Activo");
        chkEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkEstadoActionPerformed(evt);
            }
        });
        getContentPane().add(chkEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(213, 254, -1, -1));

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setText("Crear Nueva Sala");

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Fondos/img/icons8-cross-mark-48.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Fondos/img/agregar.png"))); // NOI18N
        btnGuardar.setText("Agregar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        lblApta3D.setForeground(new java.awt.Color(255, 255, 255));
        lblApta3D.setText(" ¿Es apta  para 3D?:");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Fondos/img/lentes.png"))); // NOI18N

        jDesktopPane1.setLayer(lblTitulo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btnCancelar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btnGuardar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(lblApta3D, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(chkApta3D, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(127, 127, 127)
                .addComponent(lblTitulo)
                .addContainerGap(170, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addComponent(lblApta3D)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkApta3D)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1))
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(btnCancelar)))
                .addGap(79, 79, 79))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkApta3D, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblApta3D, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(71, 71, 71)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(41, 41, 41))
        );

        getContentPane().add(jDesktopPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 430, 400));

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
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblApta3D;
    private javax.swing.JLabel lblCapacidad;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblNroSala;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtCapacidad;
    private javax.swing.JTextField txtNroSala;
    // End of variables declaration//GEN-END:variables
}
