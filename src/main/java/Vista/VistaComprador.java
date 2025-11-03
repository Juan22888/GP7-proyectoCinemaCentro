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

        modelo.setColumnIdentifiers(new Object[]{"DNI", "Nombre", "Fecha de Nacimiento", "Password", "Metodo de Pago"});
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
                c.getPassword(),
                c.isMetodoPago() ? "Online" : "Taquilla" 
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
            c.isMetodoPago() ? "Online" : "Taquilla"
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

        jLabel1 = new javax.swing.JLabel();
        txtBucar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        compradorTable = new javax.swing.JTable();
        btnNuevo = new javax.swing.JButton();
        btnAltaBaja = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();

        jLabel1.setText("Buscar Comprador (DNI):");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        compradorTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "DNI", "Nombre", "Fecha De Nacimiento", "Password", "Metodo de Pago"
            }
        ));
        compradorTable.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(compradorTable);
        compradorTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnAltaBaja.setText("Dar de Baja/Alta");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBucar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnAltaBaja)
                                .addGap(18, 18, 18)
                                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtBucar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(104, 104, 104)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAltaBaja, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(96, 96, 96))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            
          
            Object metodoObj = modelo.getValueAt(filaSeleccionada, 4);
            boolean metodoPago;
            if (metodoObj instanceof Boolean) {
                metodoPago = (Boolean) metodoObj;
            } else {
                metodoPago = metodoObj.toString().equalsIgnoreCase("Online");
            }

      
            boolean exito = CompradorData.actualizarCompradorPorDni(dni, nombre, fechaNac, password, metodoPago);

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAltaBaja;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JTable compradorTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtBucar;
    // End of variables declaration//GEN-END:variables
}
