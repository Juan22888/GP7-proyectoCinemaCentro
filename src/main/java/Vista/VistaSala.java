/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Vista;

import Modelo.Sala;
import Persistencia.SalaData;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class VistaSala extends javax.swing.JInternalFrame {

    //Sala: NroSala, apta3D, capacidad, estado (Clase Opcional Grupos 3)
    private final SalaData salaData;
    private DefaultTableModel modelo;

    public VistaSala(SalaData salaData) {
        initComponents();
        this.salaData = salaData;
        
        prepararTabla();
        cargarTablaCompleta();
    }

    private void prepararTabla() {
        modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0;
            }
        };

        modelo.setColumnIdentifiers(new Object[]{"CodSala", "Nro Sala", "Apta3D", "Capacidad", "Estado"});
        salaTable.setModel(modelo);
    }

    public void cargarTablaCompleta() {
        modelo.setRowCount(0); 

        List<Sala> salas = salaData.listarSalas();

        if (salas != null && !salas.isEmpty()) {
            for (Sala s : salas) {
                modelo.addRow(new Object[]{
                    s.getCodSala(),
                    s.getNroSala(),
                    s.isApta3d() ? "Sí" : "No",
                    s.getCapacidad(),
                    s.isEstado() ? "Activa" : "Inactiva"
                });
            }
        } else {
 
          JOptionPane.showMessageDialog(this, "No hay salas registradas.", "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    private void cargarTablaFiltrada(Sala s) {
        modelo.setRowCount(0); // Limpiamos la tabla
        modelo.addRow(new Object[]{
            s.getCodSala(),
            s.getNroSala(),
            s.isApta3d() ? "Sí" : "No",
            s.getCapacidad(),
            s.isEstado() ? "Activa" : "Inactiva"
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        salaTable = new javax.swing.JTable();
        txtBuscarSalas = new javax.swing.JTextField();
        btnBuscarSalas = new javax.swing.JButton();
        btnNuevaSala = new javax.swing.JButton();
        btnBajaAlta = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();

        setClosable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Administracion de Salas del Cine");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Buscar Salas:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 121, -1, -1));

        salaTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "codSala", "Nro Sala", "Apta3D", "Capacidad", "Estado"
            }
        ));
        jScrollPane1.setViewportView(salaTable);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 159, 740, -1));
        getContentPane().add(txtBuscarSalas, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 120, 130, -1));

        btnBuscarSalas.setText("Buscar");
        btnBuscarSalas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarSalasActionPerformed(evt);
            }
        });
        getContentPane().add(btnBuscarSalas, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 120, -1, -1));

        btnNuevaSala.setText("Nueva Sala");
        btnNuevaSala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaSalaActionPerformed(evt);
            }
        });
        getContentPane().add(btnNuevaSala, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 640, 100, 50));

        btnBajaAlta.setText("Dar de baja/Alta");
        btnBajaAlta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBajaAltaActionPerformed(evt);
            }
        });
        getContentPane().add(btnBajaAlta, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 640, -1, 50));

        btnActualizar.setText("Guardar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        getContentPane().add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 640, 135, 50));

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 640, 136, 50));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarSalasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarSalasActionPerformed
        // TODO add your handling code here:
        String nroSalaTexto = txtBuscarSalas.getText().trim();
        
        if (nroSalaTexto.isEmpty()) {
            cargarTablaCompleta();
            return;
        }
        
        try {
            int nroSala = Integer.parseInt(nroSalaTexto);
            Sala sala = salaData.buscarSalaPorNroSala(nroSala); 
            
            if (sala != null) {
                cargarTablaFiltrada(sala); 
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró ninguna sala con el número: " + nroSala, "Búsqueda", JOptionPane.WARNING_MESSAGE);
                modelo.setRowCount(0); 
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un número de sala válido (solo números).", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnBuscarSalasActionPerformed

    private void btnNuevaSalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaSalaActionPerformed
      VistaNuevaSala vnSala = new VistaNuevaSala(salaData, this); 
        
    
        this.getDesktopPane().add(vnSala); 
        
        vnSala.setVisible(true);
        vnSala.toFront();
    }//GEN-LAST:event_btnNuevaSalaActionPerformed

    private void btnBajaAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBajaAltaActionPerformed
        
        int filaSeleccionada = salaTable.getSelectedRow();
        
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione una sala de la tabla.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int codSala = (Integer) modelo.getValueAt(filaSeleccionada, 0);
        String estadoActualStr = (String) modelo.getValueAt(filaSeleccionada, 4); 
        boolean estadoActual = estadoActualStr.equalsIgnoreCase("Activa");
        
        String accion = estadoActual ? "dar de baja" : "dar de alta";
        int confirm = JOptionPane.showConfirmDialog(this, 
                "¿Está seguro que desea " + accion + " la sala Cod: " + codSala + "?",
                "Confirmar Acción", 
                JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            boolean nuevoEstado = !estadoActual; 
            if (salaData.cambiarEstadoSala(codSala, nuevoEstado)) {
                JOptionPane.showMessageDialog(this, "Estado de sala actualizado exitosamente.");
                cargarTablaCompleta(); 
            } else {
                JOptionPane.showMessageDialog(this, "Error al cambiar el estado de la sala.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnBajaAltaActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        
        int filaSeleccionada = salaTable.getSelectedRow();
        
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione una sala de la tabla para actualizar.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        
        if (salaTable.isEditing()) {
            salaTable.getCellEditor().stopCellEditing();
        }

        try {
            int codSala = (Integer) modelo.getValueAt(filaSeleccionada, 0); 
            int nroSala = Integer.parseInt(modelo.getValueAt(filaSeleccionada, 1).toString()); 
            boolean apta3D = modelo.getValueAt(filaSeleccionada, 2).toString().equalsIgnoreCase("Sí"); 
            int capacidad = Integer.parseInt(modelo.getValueAt(filaSeleccionada, 3).toString()); 
            boolean estado = modelo.getValueAt(filaSeleccionada, 4).toString().equalsIgnoreCase("Activa");

            
            boolean exito = salaData.actualizarSala(codSala, nroSala, apta3D, capacidad, estado);
            
            if (exito) {
                JOptionPane.showMessageDialog(this, "Sala Cod: " + codSala + " actualizada exitosamente.");
            } else {
                JOptionPane.showMessageDialog(this, "Error: No se pudo actualizar la sala Cod: " + codSala, "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error de formato en Nro. Sala o Capacidad. Ingrese números válidos.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error al guardar los cambios: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        cargarTablaCompleta(); 
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
      
        int filaSeleccionada = salaTable.getSelectedRow();
        
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione una sala de la tabla para eliminar.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int codSala = (Integer) modelo.getValueAt(filaSeleccionada, 0);
        int nroSala = (Integer) modelo.getValueAt(filaSeleccionada, 1);

        int confirm = JOptionPane.showConfirmDialog(this, 
                "¿Está seguro que desea ELIMINAR permanentemente la sala Nro: " + nroSala + " (Cod: " + codSala + ")?",
                "Confirmar Eliminación", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.ERROR_MESSAGE);
        
        if (confirm == JOptionPane.YES_OPTION) {
            if (salaData.eliminarSala(codSala)) {
                JOptionPane.showMessageDialog(this, "Sala Cod: " + codSala + " eliminada exitosamente.");
                cargarTablaCompleta(); 
            } else {
              
                JOptionPane.showMessageDialog(this, "Error: No se pudo eliminar la sala Cod: " + codSala + ". \nPuede que esté asociada a funciones o registros.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBajaAlta;
    private javax.swing.JButton btnBuscarSalas;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnNuevaSala;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable salaTable;
    private javax.swing.JTextField txtBuscarSalas;
    // End of variables declaration//GEN-END:variables
}
