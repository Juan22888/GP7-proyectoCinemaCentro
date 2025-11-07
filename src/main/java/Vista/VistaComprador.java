/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Vista;
import Modelo.Comprador;
import Persistencia.CompradorData;
import java.sql.SQLException;
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

        modelo.setColumnIdentifiers(new Object[]{"DNI", "Nombre", "Fecha de Nacimiento", "Password"});
        compradorTable.setModel(modelo);
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
                c.getPassword()
            });
        }
    }
    
    private void cargarTablaFiltrada(Comprador c) {
        modelo.setRowCount(0); 
        modelo.addRow(new Object[]{
            c.getDni(),
            c.getNombre(),
            c.getFechaNacimiento(),
            c.getPassword()
        });
    }                                        
  
    /**
     * Botón Dar de Baja/Alta: (Baja Lógica)
     */
    private void btnAltaBajaActionPerformed(java.awt.event.ActionEvent evt) {                                            
        
        JOptionPane.showMessageDialog(this, 
                "Función no disponible.\nPara 'Dar de Baja/Alta', la tabla 'comprador' necesita \nuna columna 'estado' (activa/inactiva).", 
                "Función no implementada", 
                JOptionPane.INFORMATION_MESSAGE);
    }
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        compradorTable = new javax.swing.JTable();
        txtBucar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();

        setClosable(true);
        setMaximizable(true);
        setResizable(true);

        compradorTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "DNI", "Nombre", "Fecha De Nacimiento", "Password"
            }
        ));
        compradorTable.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(compradorTable);
        compradorTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jLabel1.setText("Buscar Comprador (DNI):");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        jDesktopPane1.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(txtBucar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btnBuscar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btnNuevo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btnActualizar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btnEliminar, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtBucar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(btnBuscar))
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBucar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(btnBuscar))
                .addGap(72, 72, 72)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(96, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDesktopPane1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        int filaSeleccionada = compradorTable.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un comprador de la tabla.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int dni = (Integer) modelo.getValueAt(filaSeleccionada, 0);
        String nombre = (String) modelo.getValueAt(filaSeleccionada, 1);

        int confirm = JOptionPane.showConfirmDialog(this,
            "¿Está seguro que desea ELIMINAR permanentemente a " + nombre + " (DNI: " + dni + ")?",
            "Confirmar Eliminación",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.ERROR_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                // Llamamos al NUEVO método
                boolean exito = this.CompradorData.eliminarCompradorPorDni(dni);
                if (exito) {
                    JOptionPane.showMessageDialog(this, "Comprador eliminado exitosamente.");
                    cargarTablaCompleta(); // Recargamos la tabla
                } else {
                    JOptionPane.showMessageDialog(this, "Error: No se pudo eliminar al comprador (DNI no encontrado).", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                // Esto puede pasar si el comprador tiene tickets asociados (Fallo de Foreign Key)
                JOptionPane.showMessageDialog(this, "Error: No se puede eliminar al comprador. \nPuede que tenga tickets o compras asociadas.", "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        int filaSeleccionada = compradorTable.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un comprador de la tabla para actualizar.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (compradorTable.isEditing()) {
            compradorTable.getCellEditor().stopCellEditing();
        }

        try {

            int dni = (Integer) modelo.getValueAt(filaSeleccionada, 0);
            String nombre = (String) modelo.getValueAt(filaSeleccionada, 1);

            Object fechaObj = modelo.getValueAt(filaSeleccionada, 2);
            java.time.LocalDate fechaNac;
            if (fechaObj instanceof java.time.LocalDate) {
                fechaNac = (java.time.LocalDate) fechaObj;
            } else {
                fechaNac = java.time.LocalDate.parse(fechaObj.toString());
            }

            String password = (String) modelo.getValueAt(filaSeleccionada, 3);

            

            boolean exito = CompradorData.actualizarCompradorPorDni(dni, nombre, fechaNac, password);

            if (exito) {
                JOptionPane.showMessageDialog(this, "Comprador actualizado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(this, "Error: No se pudo actualizar al comprador.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (java.time.format.DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Error en el formato de la fecha. Use YYYY-MM-DD.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al procesar los datos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        cargarTablaCompleta();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "Aquí se debe abrir el formulario 'VistaNuevoComprador'.");
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        String dniTexto = txtBucar.getText();

        if (dniTexto.isEmpty()) {
            cargarTablaCompleta();
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
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JTable compradorTable;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtBucar;
    // End of variables declaration//GEN-END:variables
}
