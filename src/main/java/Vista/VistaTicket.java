/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Vista;

import Modelo.Comprador;
import Modelo.TicketCompra;
import Persistencia.CompradorData;
import Persistencia.TicketData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.sql.Date;

public class VistaTicket extends javax.swing.JInternalFrame {

    private final TicketData ticketData;
    private final CompradorData compradorData;
    private final DefaultTableModel modelo = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {

            return column == 1 || column == 2;
        }
    };
    private List<Comprador> listaCompradores;
    private List<TicketCompra> listaTickets;

    public VistaTicket(TicketData ticketData, CompradorData compradorData) {
        initComponents();

        this.ticketData = ticketData;
        this.compradorData = compradorData;
        this.listaCompradores = new ArrayList<>();
        this.listaTickets = new ArrayList<>();
        String[] titulos = {"Comprador", "Fecha de Compra", "Monto"};
        this.modelo.setColumnIdentifiers(titulos);
        ticketTable.setModel(modelo);

        cargarCompradores();
        cargarTicket();

    }

    public void cargarCompradores() {
        try {

            List<Comprador> compradores = compradorData.listarCompradores();

            CboxComprador.removeAllItems();
            listaCompradores.clear();

            for (Comprador c : compradores) {

                String textoComprador = c.getNombre() + " " + " (DNI: " + c.getDni() + ")";

                CboxComprador.addItem(textoComprador);
                listaCompradores.add(c);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar compradores: " + e.getMessage());
        }
    }

    private void cargarTicket() {
        modelo.setRowCount(0);
        listaTickets.clear();
        List<TicketCompra> tickets = ticketData.listarTickets();

        for (TicketCompra t : tickets) {
            listaTickets.add(t);
            modelo.addRow(new Object[]{
                t.getComprador().getNombre(),
                t.getFechaCompra(),
                t.getMonto()});

        }
    }

    private void buscarComprador() {
        int indiceSeleccionado = CboxComprador.getSelectedIndex();

        if (indiceSeleccionado == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un comprador.");
            return;
        }

        try {

            Comprador compradorSeleccionado = listaCompradores.get(indiceSeleccionado);
            int codComprador = compradorSeleccionado.getCodComprador();

            modelo.setRowCount(0);

            for (TicketCompra t : listaTickets) {
                if (t.getComprador().getCodComprador() == codComprador) {
                    modelo.addRow(new Object[]{
                        t.getComprador().getNombre(),
                        t.getFechaCompra(),
                        t.getMonto()
                    });
                }
            }

            if (modelo.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "No se encontraron tickets para el comprador seleccionado.");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al buscar tickets: " + e.getMessage());
        }
    }

    private void borrarTicket() {
        int filaSeleccionada = ticketTable.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una fila para borrar.");
            return;
        }

        try {

            TicketCompra ticketSeleccionado = listaTickets.get(filaSeleccionada);

            int codTicket = ticketSeleccionado.getCodTicket();
            String nombreComprador = ticketSeleccionado.getComprador().getNombre();

            int confirm = JOptionPane.showConfirmDialog(this,
                    "¿Está seguro de eliminar PERMANENTEMENTE el ticket de " + nombreComprador + "?",
                    "Confirmar Eliminación",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.ERROR_MESSAGE);

            if (confirm == JOptionPane.YES_OPTION) {

                if (this.ticketData.eliminarTicket(codTicket)) {
                    JOptionPane.showMessageDialog(this, "Ticket Cod: " + codTicket + " eliminado con éxito.");
                    cargarTicket();
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo eliminar el ticket (ID no encontrado).", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (IndexOutOfBoundsException e) {

            JOptionPane.showMessageDialog(this, "Error interno: El índice de la tabla no coincide con la lista de tickets.", "Error Interno", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al eliminar el ticket: " + ex.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error inesperado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarTicket() {
        if (ticketTable.isEditing()) {
            ticketTable.getCellEditor().stopCellEditing();
        }

        int filaSeleccionada = ticketTable.getSelectedRow();

        if (filaSeleccionada == -1) {

            cargarTicket();
            JOptionPane.showMessageDialog(this, "Datos recargados desde la base de datos.");
            return;
        }

        try {
            TicketCompra ticketOriginal = listaTickets.get(filaSeleccionada);
            int codTicket = ticketOriginal.getCodTicket();

            boolean cambiosRealizados = false;

            String nuevoMontoStr = modelo.getValueAt(filaSeleccionada, 2).toString();
            double nuevoMonto = Double.parseDouble(nuevoMontoStr);

            if (nuevoMonto <= 0) {
                JOptionPane.showMessageDialog(this, "El monto debe ser un valor positivo.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
                cargarTicket();
                return;
            }

            if (nuevoMonto != ticketOriginal.getMonto()) {
                if (this.ticketData.actualizarTicket(codTicket, "Monto", nuevoMonto)) {
                    cambiosRealizados = true;
                }
            }

            Object fechaObj = modelo.getValueAt(filaSeleccionada, 1);
            LocalDate nuevaFecha;

            if (fechaObj instanceof String) {

                nuevaFecha = LocalDate.parse((String) fechaObj);
            } else if (fechaObj instanceof LocalDate) {
                nuevaFecha = (LocalDate) fechaObj;
            } else {

                nuevaFecha = ticketOriginal.getFechaCompra();
            }

            java.sql.Date sqlDate = Date.valueOf(nuevaFecha);

            if (!nuevaFecha.isEqual(ticketOriginal.getFechaCompra())) {
                if (this.ticketData.actualizarTicket(codTicket, "FechaCompra", sqlDate)) {
                    cambiosRealizados = true;
                }
            }

            if (cambiosRealizados) {
                JOptionPane.showMessageDialog(this, "Ticket Cod: " + codTicket + " actualizado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No se detectaron cambios para actualizar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error de formato: Ingrese un número válido para el Monto.", "Error de Datos", JOptionPane.ERROR_MESSAGE);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Error de formato de fecha: Use el formato YYYY-MM-DD (Ej: 2025-10-25).", "Error de Datos", JOptionPane.ERROR_MESSAGE);
        } catch (IndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(this, "Error interno: El índice de la tabla no coincide con la lista de tickets.", "Error Interno", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error al actualizar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        cargarTicket();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ticketTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        ButActualizar = new javax.swing.JButton();
        ButBorrar = new javax.swing.JButton();
        ButMostrar = new javax.swing.JButton();
        btnDetalles = new javax.swing.JButton();
        CboxComprador = new javax.swing.JComboBox();
        ButBuscar1 = new javax.swing.JButton();

        setTitle("Vista Ticket");

        jDesktopPane1.setForeground(new java.awt.Color(51, 51, 51));
        jDesktopPane1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 2, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 153, 153));
        jLabel1.setText("Gestion de Ticket");
        jDesktopPane1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(188, 0, 214, -1));

        ticketTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Comprador", "Funcion", "Fecha de Comprar", "Precio"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(ticketTable);

        jDesktopPane1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 149, 580, 195));

        jLabel2.setText("Comprador:");
        jDesktopPane1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, -1, 31));

        ButActualizar.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        ButActualizar.setForeground(new java.awt.Color(51, 51, 51));
        ButActualizar.setText("Actualizar");
        ButActualizar.setBorderPainted(false);
        ButActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButActualizarActionPerformed(evt);
            }
        });
        jDesktopPane1.add(ButActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 390, -1, -1));

        ButBorrar.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        ButBorrar.setForeground(new java.awt.Color(51, 51, 51));
        ButBorrar.setText("Borrar");
        ButBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButBorrarActionPerformed(evt);
            }
        });
        jDesktopPane1.add(ButBorrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 390, -1, -1));

        ButMostrar.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        ButMostrar.setForeground(new java.awt.Color(51, 51, 51));
        ButMostrar.setText("Mostrar Todos");
        ButMostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButMostrarActionPerformed(evt);
            }
        });
        jDesktopPane1.add(ButMostrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 390, -1, -1));

        btnDetalles.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        btnDetalles.setText("Ver Detalles");
        btnDetalles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetallesActionPerformed(evt);
            }
        });
        jDesktopPane1.add(btnDetalles, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 110, -1, -1));

        CboxComprador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CboxCompradorActionPerformed(evt);
            }
        });
        jDesktopPane1.add(CboxComprador, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, 260, -1));

        ButBuscar1.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        ButBuscar1.setText("Buscar");
        ButBuscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButBuscar1ActionPerformed(evt);
            }
        });
        jDesktopPane1.add(ButBuscar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 110, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CboxCompradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CboxCompradorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CboxCompradorActionPerformed

    private void btnDetallesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetallesActionPerformed
  
    }//GEN-LAST:event_btnDetallesActionPerformed

    private void ButMostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButMostrarActionPerformed
        // TODO add your handling code here:
        cargarTicket();
        cargarCompradores();
    }//GEN-LAST:event_ButMostrarActionPerformed

    private void ButActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButActualizarActionPerformed
        // TODO add your handling code here:
        actualizarTicket();


    }//GEN-LAST:event_ButActualizarActionPerformed

    private void ButBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButBorrarActionPerformed
        // TODO add your handling code here:
        borrarTicket();
    }//GEN-LAST:event_ButBorrarActionPerformed

    private void ButBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButBuscar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ButBuscar1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButActualizar;
    private javax.swing.JButton ButBorrar;
    private javax.swing.JButton ButBuscar1;
    private javax.swing.JButton ButMostrar;
    private javax.swing.JComboBox CboxComprador;
    private javax.swing.JButton btnDetalles;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable ticketTable;
    // End of variables declaration//GEN-END:variables
}
