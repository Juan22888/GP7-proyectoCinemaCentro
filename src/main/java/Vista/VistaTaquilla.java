/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Vista;

import Modelo.Comprador;
import Modelo.DetalleTicket;
import Modelo.Funcion;
import Modelo.Lugar;
import Modelo.Pelicula;
import Modelo.TicketCompra;
import Persistencia.CompradorData;
import Persistencia.DetalleTicketData;
import Persistencia.FuncionData;
import Persistencia.LugarData;
import Persistencia.PeliculaData;
import Persistencia.TicketData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class VistaTaquilla extends javax.swing.JInternalFrame {

   private final PeliculaData pData;
    private final FuncionData fData;
    private final LugarData lData;
    private final CompradorData cData;
    private final TicketData tData;
    private final DetalleTicketData dtData;
    
   public VistaTaquilla(PeliculaData pData, FuncionData fData, LugarData lData, CompradorData cData, TicketData tData) {
        initComponents();
        this.pData = pData;
        this.fData = fData;
        this.lData = lData;
        this.cData = cData;
        this.tData = tData;
        this.dtData = new DetalleTicketData(lData); 
        
        cargarPeliculas();
        BoxMetodoPago.addItem("Contado");
        BoxMetodoPago.addItem("Débito");
        
        BoxFunciones.setEnabled(false);
        txtDniComprador.setEnabled(false);
        BoxMetodoPago.setEnabled(false);
        ButComprar.setEnabled(false);
    }

    private void cargarPeliculas() {
        BoxPeliculas.removeAllItems();
        BoxPeliculas.addItem("Seleccione Película");
        try {
            List<Pelicula> lista = pData.listarPeliculasEnCartelera();
            for (Pelicula p : lista) {
                BoxPeliculas.addItem(p.getCodPelicula() + " - " + p.getTitulo());
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar las películas: " + e.getMessage());
        }
    }
    private void cargarFunciones(int codPelicula) {
        BoxFunciones.removeAllItems();
        BoxFunciones.addItem("Seleccione Función");
        BoxFunciones.setEnabled(true);
        List<Funcion> listaCompleta = fData.listarFunciones();
        for (Funcion f : listaCompleta) {
            if (f.getPelicula().getCodPelicula() == codPelicula && f.isEstado()) {
                String item = f.getCodFuncion() + " - " + f.getSalaFuncion().getNroSala() + " - " + f.getHoraInicio();
                BoxFunciones.addItem(item);
            }
        }
    }
    private void actualizarDetallesFuncion() {
        String seleccion = (String) BoxFunciones.getSelectedItem();
        if (seleccion == null || seleccion.equals("Seleccione Función")) {
            txtPrecio.setText("");
            txtLugaresDisponibles.setText("");
            txtTotal.setText("");
            txtDniComprador.setEnabled(false);
            ButComprar.setEnabled(false);
            return;
        }

        try {
            int codFuncion = Integer.parseInt(seleccion.split(" - ")[0].trim());
            Funcion f = fData.buscarFuncion(codFuncion);

            if (f != null) {
                txtPrecio.setText(String.valueOf(f.getPrecioLugar()));
                
                // Buscar lugares disponibles para la función
                List<Lugar> todosLugares = lData.obtenerLugaresPorFuncion(codFuncion);
                long disponibles = 0; // Usaremos el estado 'estado' (true=ocupado, false=libre)
                for(Lugar l : todosLugares){
                    if(!lData.buscarLugar(l.getCodLugar()).isEstado()){
                        disponibles++;
                    }
                }
                
                txtLugaresDisponibles.setText(String.valueOf(disponibles));
                
                // Si solo se puede comprar 1 entrada, el total es igual al precio.
                if (disponibles > 0) {
                     txtTotal.setText(String.valueOf(f.getPrecioLugar()));
                     txtDniComprador.setEnabled(true);
                     BoxMetodoPago.setEnabled(true);
                } else {
                     txtTotal.setText("0.0");
                     txtDniComprador.setEnabled(false);
                     BoxMetodoPago.setEnabled(false);
                     JOptionPane.showMessageDialog(this, "No hay lugares disponibles para esta función.", "Asientos Agotados", JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al obtener detalles de la función: " + e.getMessage());
        }
    }
    private Lugar encontrarLugarDisponible(int codFuncion) {
        List<Lugar> lugares = lData.obtenerLugaresPorFuncion(codFuncion);
        for(Lugar l : lugares){
            // Se revisa el estado actual en la BD, no en el objeto recuperado inicialmente si es necesario
            if(!lData.buscarLugar(l.getCodLugar()).isEstado()){
                return l; // Retorna el primer lugar libre encontrado
            }
        }
        return null;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        lblPelicula = new javax.swing.JLabel();
        BoxPeliculas = new javax.swing.JComboBox<>();
        lblFuncion = new javax.swing.JLabel();
        BoxFunciones = new javax.swing.JComboBox<>();
        lblLugaresDisponibles = new javax.swing.JLabel();
        txtLugaresDisponibles = new javax.swing.JTextField();
        lblPrecio = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        lblTotal = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        lblDniComprador = new javax.swing.JLabel();
        txtDniComprador = new javax.swing.JTextField();
        ButBuscarComprador = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtNombreComprador = new javax.swing.JTextField();
        ButComprar = new javax.swing.JButton();
        lblMetodoPago = new javax.swing.JLabel();
        BoxMetodoPago = new javax.swing.JComboBox<>();

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTitulo.setText("Taquilla");

        lblPelicula.setText("Pelicula");

        lblFuncion.setText("Funcion");

        lblLugaresDisponibles.setText("Lugares Disponibles");

        txtLugaresDisponibles.setEditable(false);
        txtLugaresDisponibles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLugaresDisponiblesActionPerformed(evt);
            }
        });

        lblPrecio.setText("Precio");

        txtPrecio.setEditable(false);
        txtPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioActionPerformed(evt);
            }
        });

        lblTotal.setText("Total");

        lblDniComprador.setText("DNI del Comprador");

        ButBuscarComprador.setText("Buscar");

        jLabel1.setText("Nombre del Comprador");

        ButComprar.setText("Comprar");

        lblMetodoPago.setText("Metodo de Pago");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(200, 200, 200)
                        .addComponent(lblTitulo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lblLugaresDisponibles)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lblPelicula)
                                            .addComponent(lblFuncion)))
                                    .addComponent(lblTotal)
                                    .addComponent(lblPrecio)
                                    .addComponent(lblDniComprador)
                                    .addComponent(jLabel1)
                                    .addComponent(lblMetodoPago))
                                .addGap(43, 43, 43)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(BoxPeliculas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(BoxFunciones, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtLugaresDisponibles)
                                        .addComponent(txtPrecio)
                                        .addComponent(txtTotal)
                                        .addComponent(txtDniComprador)
                                        .addComponent(txtNombreComprador))
                                    .addComponent(BoxMetodoPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(ButComprar))
                        .addGap(18, 18, 18)
                        .addComponent(ButBuscarComprador, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(234, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(lblTitulo)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPelicula)
                    .addComponent(BoxPeliculas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFuncion)
                    .addComponent(BoxFunciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLugaresDisponibles)
                    .addComponent(txtLugaresDisponibles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrecio)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotal)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDniComprador)
                    .addComponent(txtDniComprador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButBuscarComprador))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNombreComprador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMetodoPago)
                    .addComponent(BoxMetodoPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(ButComprar)
                .addContainerGap(148, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtLugaresDisponiblesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLugaresDisponiblesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLugaresDisponiblesActionPerformed

    private void txtPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> BoxFunciones;
    private javax.swing.JComboBox<String> BoxMetodoPago;
    private javax.swing.JComboBox<Object> BoxPeliculas;
    private javax.swing.JButton ButBuscarComprador;
    private javax.swing.JButton ButComprar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblDniComprador;
    private javax.swing.JLabel lblFuncion;
    private javax.swing.JLabel lblLugaresDisponibles;
    private javax.swing.JLabel lblMetodoPago;
    private javax.swing.JLabel lblPelicula;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTextField txtDniComprador;
    private javax.swing.JTextField txtLugaresDisponibles;
    private javax.swing.JTextField txtNombreComprador;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
