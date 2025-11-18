
package Vista;

import Modelo.Comprador;
import Persistencia.CompradorData;
import java.awt.Graphics;
import java.awt.Image;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

//Esta vista se pone en administracion, es para mostrar, buscar, insertar, modificar, eliminar, etc, un comprador o compradores
public class VistaComprador extends javax.swing.JInternalFrame {

    private final CompradorData CompradorData;
    
   
    private DefaultTableModel modelo;

    public VistaComprador(CompradorData CompradorData) {
        initComponents();
        this.CompradorData = CompradorData;

        prepararTabla();
        cargarTablaCompleta();
    }

    private void prepararTabla() {
        modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //es para que la primera columna, la del id no se pueda modificar y las otras si
                return column != 0;
            }
        };
        //para definir los titulos de la columna, buena practica, por si se quiere reutilizar de nuevo los jtable
        //aunque en este caso no se hace
        modelo.setColumnIdentifiers(new Object[]{"DNI", "Nombre", "Fecha de Nacimiento", "Password", "Estado"});
        //seteamos la tabla para que utilice el modelo
        TablaCompradores.setModel(modelo);
    }

    private void cargarTablaCompleta() {
        modelo.setRowCount(0); //Limpia la tabla, simple

        // Trae todos los compradores de la db
        List<Comprador> compradores = this.CompradorData.listarCompradores();

        if (compradores == null) {
            JOptionPane.showMessageDialog(this, "Error al cargar la lista de compradores.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //carga todos los compradores en cada fila de la tabla
        for (Comprador c : compradores) {
            modelo.addRow(new Object[]{
                c.getDni(),
                c.getNombre(),
                c.getFechaNacimiento(),
                c.getPassword(),
                c.isEstado() ? "Activo" : "Inactivo"
            });
        }
    }
    //filtra un solo comprador
    private void cargarTablaFiltrada(Comprador c) {
        modelo.setRowCount(0);//siempre limpiamos la tabla o se rompe todo
        
        //llenamos con el comprador encontrado
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

        ImageIcon icono = new ImageIcon(getClass().getResource("/Fondos/img/fondo-administracion2.jpg"));
        Image miImagen = icono.getImage();
        CompradorDesktop = new javax.swing.JDesktopPane(){
            public void paintComponent(Graphics g){
                g.drawImage(miImagen,0,0, getWidth(), getHeight(),this);
            }

        };
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaCompradores = new javax.swing.JTable();
        txtBucar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnReiniciarTabla = new javax.swing.JButton();
        butAltaBaja = new javax.swing.JButton();

        setClosable(true);

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

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Buscar Comprador (DNI):");

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Fondos/img/lupa.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Fondos/img/agregar-usuario.png"))); // NOI18N
        btnNuevo.setText("Nuevo Comprador");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Fondos/img/verificado.png"))); // NOI18N
        btnActualizar.setText("Guardar Cambios");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Fondos/img/basura.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Gestion De Compradores");

        btnReiniciarTabla.setText("Reiniciar Tabla");
        btnReiniciarTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReiniciarTablaActionPerformed(evt);
            }
        });

        butAltaBaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Fondos/Iconos/001-dos-caminos.png"))); // NOI18N
        butAltaBaja.setText("Alta/Baja");
        butAltaBaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butAltaBajaActionPerformed(evt);
            }
        });

        CompradorDesktop.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        CompradorDesktop.setLayer(txtBucar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        CompradorDesktop.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        CompradorDesktop.setLayer(btnBuscar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        CompradorDesktop.setLayer(btnNuevo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        CompradorDesktop.setLayer(btnActualizar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        CompradorDesktop.setLayer(btnEliminar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        CompradorDesktop.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        CompradorDesktop.setLayer(btnReiniciarTabla, javax.swing.JLayeredPane.DEFAULT_LAYER);
        CompradorDesktop.setLayer(butAltaBaja, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout CompradorDesktopLayout = new javax.swing.GroupLayout(CompradorDesktop);
        CompradorDesktop.setLayout(CompradorDesktopLayout);
        CompradorDesktopLayout.setHorizontalGroup(
            CompradorDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CompradorDesktopLayout.createSequentialGroup()
                .addGroup(CompradorDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CompradorDesktopLayout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(butAltaBaja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(CompradorDesktopLayout.createSequentialGroup()
                        .addGroup(CompradorDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(CompradorDesktopLayout.createSequentialGroup()
                                .addGap(210, 210, 210)
                                .addComponent(jLabel2))
                            .addGroup(CompradorDesktopLayout.createSequentialGroup()
                                .addGap(90, 90, 90)
                                .addComponent(jLabel1)
                                .addGap(17, 17, 17)
                                .addComponent(txtBucar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(btnBuscar))
                            .addGroup(CompradorDesktopLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(btnReiniciarTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 8, Short.MAX_VALUE)))
                .addContainerGap())
        );
        CompradorDesktopLayout.setVerticalGroup(
            CompradorDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CompradorDesktopLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(CompradorDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(CompradorDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtBucar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBuscar)))
                .addGap(45, 45, 45)
                .addGroup(CompradorDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(CompradorDesktopLayout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(butAltaBaja, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(54, 54, 54)
                .addGroup(CompradorDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReiniciarTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(CompradorDesktop)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(CompradorDesktop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    //para eliminar al comprador
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        
        int filaSeleccionada = TablaCompradores.getSelectedRow(); //obtiene los datos de la fila que se selecciona

        //validacion para ver si se selecciono la vista, igual te avisa
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Error: Debe seleccionar un comprador de la tabla.", "Error al Eliminar", JOptionPane.ERROR_MESSAGE);
            return; // No hacer nada si no hay selección
        }
        //una comprobacion, por si uno se equivoca al tocar el boton
        int confirmacion = JOptionPane.showConfirmDialog(this,
                "¿seguro de que desea eliminar este comprador?",
                "Confirmar Eliminación",
                JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            try {

                int dni = (Integer) TablaCompradores.getValueAt(filaSeleccionada, 0);
                //eliminamos de la db mientras guardamos un true
                boolean exito = CompradorData.eliminarCompradorPorDni(dni);

               
                if (exito) {
                    JOptionPane.showMessageDialog(this, "Comprador eliminado exitosamente.");

                    // para actualizar despues de que se elimina
                    cargarTablaCompleta();
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo eliminar el comprador (dni no encontrado).", "Error", JOptionPane.WARNING_MESSAGE);
                }

            } catch (SQLException ex) {
               
                JOptionPane.showMessageDialog(this, "Error al eliminar: " + ex.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
            } catch (ClassCastException cce) {
                
                JOptionPane.showMessageDialog(this, "Error interno: No se pudo leer el ID de la tabla.", "Error de Tipo", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        
        //para que se guarde el valor cuando se modifica un valor de una celda
        if (TablaCompradores.isEditing()) {
            TablaCompradores.getCellEditor().stopCellEditing();
        }

        int filaSeleccionada = TablaCompradores.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una fila para modificar.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            //para capturar todos los datos de la fila seleccionada
            int dni = Integer.parseInt(TablaCompradores.getValueAt(filaSeleccionada, 0).toString());
            String nombre = TablaCompradores.getValueAt(filaSeleccionada, 1).toString();
            String password = TablaCompradores.getValueAt(filaSeleccionada, 3).toString();
            //para convertir el texto de la fecha, a un localdate
            LocalDate fechaNacimiento = LocalDate.parse(TablaCompradores.getValueAt(filaSeleccionada, 2).toString());
            boolean estado = Boolean.parseBoolean(TablaCompradores.getValueAt(filaSeleccionada, 4).toString());

            Comprador compradorModificado = null;
            
            
            //actualizamos el comprador directamente en la db
            compradorModificado = CompradorData.buscarCompradorPorDni(dni);
            
            compradorModificado.setDni(dni);
            compradorModificado.setNombre(nombre);
            compradorModificado.setPassword(password);
            compradorModificado.setFechaNacimiento(fechaNacimiento);
            compradorModificado.setEstado(estado);

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
            
            //capturamos varios errores, para ver de que se trata y solucionarlo
        } catch (NumberFormatException e) {
            
            JOptionPane.showMessageDialog(this, "Error en el formato de los datos de la tabla.", "Error de Datos", JOptionPane.ERROR_MESSAGE);
        } catch (java.time.format.DateTimeParseException e) {
            
            JOptionPane.showMessageDialog(this, "Error en el formato de la fecha. Use YYYY-MM-DD.", "Error de Datos", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            
            JOptionPane.showMessageDialog(this, "Error de validación: " + ex.getMessage(), "Datos Incorrectos", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(this, "Error al guardar en la base de datos: " + ex.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            
            JOptionPane.showMessageDialog(this, "Ocurrió un error inesperado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnActualizarActionPerformed
    //abre la ventana NuevoComprador
    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        NuevoComprador vc = new NuevoComprador(CompradorData);
        this.getDesktopPane().add(vc);
        vc.setVisible(true);
        vc.toFront();
    }//GEN-LAST:event_btnNuevoActionPerformed
    //para buscar por dni
    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        String dniTexto = txtBucar.getText();

        if (dniTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el dni del comprador", "Atencion", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int dni = Integer.parseInt(dniTexto);
           //llama a compradorData para buscar solo por dni, no por codComprador
            Comprador c = this.CompradorData.buscarCompradorPorDni(dni);

            if (c != null) {
                //para limpiar la tabla y cargar solo al comprador que se encontro
                cargarTablaFiltrada(c);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró ningún comprador con ese DNI.", "Búsqueda", JOptionPane.WARNING_MESSAGE);
                modelo.setRowCount(0); // Limpia la tabla si no hay resultados
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un DNI válido (solo números).", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnReiniciarTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReiniciarTablaActionPerformed
       cargarTablaCompleta();
    }//GEN-LAST:event_btnReiniciarTablaActionPerformed

    //para cambiar el estado del comprador, un boton switch
    private void butAltaBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butAltaBajaActionPerformed
            if (TablaCompradores.isEditing()) {
            TablaCompradores.getCellEditor().stopCellEditing();
        }

        int filaSeleccionada = TablaCompradores.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una fila para modificar.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int dni = Integer.parseInt(TablaCompradores.getValueAt(filaSeleccionada, 0).toString());
        

             try {
                 if(CompradorData.buscarCompradorPorDni(dni).isEstado()==true){
                 CompradorData.bajaLogicaComprador(dni);
                 JOptionPane.showMessageDialog(this, "¡El comprador se dio de baja correctamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                 }else{
                 CompradorData.altaLogicaComprador(dni);
                 JOptionPane.showMessageDialog(this, "¡El comprador se dio de alta correctamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                 }
          
                cargarTablaCompleta();
             } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "No se pudo dar de alta o baja al comprador =?", "Error de BD", JOptionPane.ERROR_MESSAGE);
             }
        
        
    }//GEN-LAST:event_butAltaBajaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane CompradorDesktop;
    private javax.swing.JTable TablaCompradores;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnReiniciarTabla;
    private javax.swing.JButton butAltaBaja;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtBucar;
    // End of variables declaration//GEN-END:variables
}
