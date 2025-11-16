/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Vista;

import Modelo.Comprador;
import Persistencia.CompradorData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author feerl
 */
public class VistaComprador extends javax.swing.JInternalFrame {

    private final CompradorData CompradorData;
    private DefaultTableModel modelo;

    public VistaComprador(CompradorData CompradorData) {
        initComponents();
        this.CompradorData = CompradorData; // Asigna la instancia

        prepararTabla();
        cargarTablaCompleta();
    }

    private void prepararTabla() {
        modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Hacemos que la columna 0 (DNI) NO sea editable.
                // El resto (Nombre, FechaNac, Password, MetodoPago) SÍ lo serán.
                return column != 0;
            }
        };

        modelo.setColumnIdentifiers(new Object[]{"DNI", "Nombre", "Fecha de Nacimiento", "Password", "Estado"});
        TablaCompradores.setModel(modelo);
    }

    private void cargarTablaCompleta() {
        modelo.setRowCount(0); // Limpiamos la tabla

        // Llamamos al NUEVO método 
        List<Comprador> compradores = this.CompradorData.listarCompradores();

        if (compradores == null) {
            JOptionPane.showMessageDialog(this, "Error al cargar la lista de compradores.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        for (Comprador c : compradores) {
            modelo.addRow(new Object[]{
                c.getDni(),
                c.getNombre(),
                c.getFechaNacimiento(),
                c.getPassword(),
                c.isEstado()
            });
        }
    }

    private void cargarTablaFiltrada(Comprador c) {
        modelo.setRowCount(0);
        modelo.addRow(new Object[]{
            c.getDni(),
            c.getNombre(),
            c.getFechaNacimiento(),
            c.getPassword(),
            c.isEstado()
        });
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CompradorDesktop = new javax.swing.JDesktopPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaCompradores = new javax.swing.JTable();
        txtBucar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setClosable(true);

        CompradorDesktop.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TablaCompradores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "DNI", "Nombre", "Fecha De Nacimiento", "Password", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaCompradores.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(TablaCompradores);
        TablaCompradores.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        CompradorDesktop.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 163, 522, 256));
        CompradorDesktop.add(txtBucar, new org.netbeans.lib.awtextra.AbsoluteConstraints(239, 68, 150, -1));

        jLabel1.setText("Buscar Comprador (DNI):");
        CompradorDesktop.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(88, 71, -1, -1));

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        CompradorDesktop.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(424, 68, -1, -1));

        btnNuevo.setText("Nuevo Comprador");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        CompradorDesktop.add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 469, 145, 50));

        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/iconos netbeans/guardar.png"))); // NOI18N
        btnActualizar.setText("Guardar Cambios");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        CompradorDesktop.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(248, 469, 139, 49));

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/iconos netbeans/icons8-delete-64.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        CompradorDesktop.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 469, 115, 49));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 153));
        jLabel2.setText("Gestion De Compradores");
        CompradorDesktop.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(163, 9, -1, 41));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CompradorDesktop))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(CompradorDesktop)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // 1. Obtener la fila seleccionada
        int filaSeleccionada = TablaCompradores.getSelectedRow();

        // 2. Validar si hay una fila seleccionada
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Error: Debe seleccionar un comprador de la tabla.", "Error al Eliminar", JOptionPane.ERROR_MESSAGE);
            return; // No hacer nada si no hay selección
        }

     
        int confirmacion = JOptionPane.showConfirmDialog(this,
                "¿Está seguro de que desea eliminar este comprador?",
                "Confirmar Eliminación",
                JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
               
                int dni = (Integer) TablaCompradores.getValueAt(filaSeleccionada, 0);

            
                boolean exito = CompradorData.eliminarCompradorPorDni(dni);

                // 6. Informar resultado y actualizar la tabla
                if (exito) {
                    JOptionPane.showMessageDialog(this, "Comprador eliminado exitosamente.");

                    // Actualizar la tabla para que refleje el cambio
                    cargarTablaCompleta();
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo eliminar el comprador (dni no encontrado).", "Error", JOptionPane.WARNING_MESSAGE);
                }

            } catch (SQLException ex) {
                // Capturar error de la base de datos (ej: clave foránea)
                JOptionPane.showMessageDialog(this, "Error al eliminar: " + ex.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
            } catch (ClassCastException cce) {
                // Capturar error si la columna 0 no es un Integer
                JOptionPane.showMessageDialog(this, "Error interno: No se pudo leer el ID de la tabla.", "Error de Tipo", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed

        if (TablaCompradores.isEditing()) {
            TablaCompradores.getCellEditor().stopCellEditing();
        }

        int filaSeleccionada = TablaCompradores.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una fila para modificar.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {

            int dni = Integer.parseInt(TablaCompradores.getValueAt(filaSeleccionada, 0).toString());
            String nombre = TablaCompradores.getValueAt(filaSeleccionada, 1).toString();
            String password = TablaCompradores.getValueAt(filaSeleccionada, 3).toString();
            LocalDate fechaNacimiento = LocalDate.parse(TablaCompradores.getValueAt(filaSeleccionada, 2).toString());
            boolean estado = Boolean.parseBoolean(TablaCompradores.getValueAt(filaSeleccionada, 4).toString());

            Comprador compradorModificado = null;

            compradorModificado = CompradorData.buscarCompradorPorDni(dni);

            boolean exito = CompradorData.actualizarCompradorPorDni(compradorModificado);

            if (exito) {
                JOptionPane.showMessageDialog(this, "¡El comprador se modifico correctamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarTablaCompleta();
            } else {

                JOptionPane.showMessageDialog(this,
                        "El comprador no se pudo actualizar (0 filas afectadas).",
                        "Error de Actualización",
                        JOptionPane.WARNING_MESSAGE);
            }

        } catch (NumberFormatException e) {
            // Error si el ID o algún número de la tabla no es válido
            JOptionPane.showMessageDialog(this, "Error en el formato de los datos de la tabla.", "Error de Datos", JOptionPane.ERROR_MESSAGE);
        } catch (java.time.format.DateTimeParseException e) {
            // Error si la fecha de la tabla no tiene el formato "YYYY-MM-DD"
            JOptionPane.showMessageDialog(this, "Error en el formato de la fecha. Use YYYY-MM-DD.", "Error de Datos", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            // ¡Aquí capturamos NUESTRAS validaciones de negocio!
            // (Ej: "El título debe tener al menos 5 caracteres")
            JOptionPane.showMessageDialog(this, "Error de validación: " + ex.getMessage(), "Datos Incorrectos", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException ex) {
            // Error de la base de datos (conexión, clave duplicada, etc.)
            JOptionPane.showMessageDialog(this, "Error al guardar en la base de datos: " + ex.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            // Captura cualquier otro error inesperado
            JOptionPane.showMessageDialog(this, "Ocurrió un error inesperado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
         NuevoComprador vp = new NuevoComprador(CompradorData);
        CompradorDesktop.add(vp);
        vp.setVisible(true);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        String dniTexto = txtBucar.getText();
        
          if (dniTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el dni del comprador", "Atencion", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int dni = Integer.parseInt(dniTexto);
            // Llamamos al NUEVO método
            Comprador c = this.CompradorData.buscarCompradorPorDni(dni);

            if (c != null) {
                cargarTablaFiltrada(c);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró ningún comprador con ese DNI.", "Búsqueda", JOptionPane.WARNING_MESSAGE);
                modelo.setRowCount(0); // Limpia la tabla si no hay resultados
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un DNI válido (solo números).", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane CompradorDesktop;
    private javax.swing.JTable TablaCompradores;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtBucar;
    // End of variables declaration//GEN-END:variables
}
