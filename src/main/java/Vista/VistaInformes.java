package Vista;

import Modelo.Comprador;
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
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class VistaInformes extends javax.swing.JInternalFrame {

    private final PeliculaData peliculaData;
    private final TicketData ticketData;
    private final DetalleTicketData detalleTicketData;
    private final CompradorData compradorData;
    private final FuncionData funcionData;

    private DefaultTableModel modelo;

    public VistaInformes(PeliculaData peliculaData, TicketData ticketData, DetalleTicketData detalleTicketData, CompradorData compradorData, FuncionData funcionData) {
        this.peliculaData = peliculaData;
        this.ticketData = ticketData;
        this.detalleTicketData = detalleTicketData;
        this.compradorData = compradorData;
        this.funcionData = funcionData;
        initComponents();
        prepararTabla();
        cargarPeliculasComboBox();
        cargarFuncionesComboBox();
    }

    private void prepararTabla() {
        modelo = new DefaultTableModel();
        reporteTable.setModel(modelo);
    }

    private void cargarPeliculasComboBox() {

        boxPeliculas.removeAllItems();
        boxPeliculas.addItem("Seleccione una Película...");
        try {
            List<Pelicula> lista = peliculaData.listarPeliculas();
            for (Pelicula p : lista) {

                boxPeliculas.addItem(p.getCodPelicula() + " - " + p.getTitulo());
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar las películas para el filtro: " + ex.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarReporteProximosEstrenos() {
        modelo.setRowCount(0);
        modelo.setColumnIdentifiers(new Object[]{"Título", "Director", "Género", "Estreno"});

        try {
            List<Pelicula> lista = peliculaData.listarProximosEstrenos();

            if (lista.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay próximos estrenos registrados.", "Información", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            for (Pelicula p : lista) {
                modelo.addRow(new Object[]{
                    p.getTitulo(),
                    p.getDirector(),
                    p.getGenero(),
                    p.getEstreno()
                });
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar el reporte de estrenos: " + ex.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Buscar tickets por fecha
    private void ticketPorFecha() {

        Date fechaSeleccionada = dateChooserFecha.getDate();

        if (fechaSeleccionada == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una fecha para el informe.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        LocalDate fechaLocal = fechaSeleccionada.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        modelo.setRowCount(0);
        modelo.setColumnIdentifiers(new Object[]{"N° Ticket", "Comprador", "Fecha Compra", "Monto Total", "Método Pago"});

        try {
            List<TicketCompra> lista = ticketData.listarTicketsPorFecha(fechaLocal);

            if (lista.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No se encontraron tickets vendidos en esa fecha (" + fechaLocal.toString() + ").", "Información", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            for (TicketCompra t : lista) {
                String metodo = t.isMetodoPago() ? "Tarjeta/Débito" : "Efectivo";

                modelo.addRow(new Object[]{
                    t.getCodTicket(),
                    t.getComprador().getNombre(),
                    t.getFechaCompra(),
                    t.getMonto(),
                    metodo
                });
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al generar el informe por fecha: " + ex.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
        }
    }

    //buscar por la cantidad de entradas vendidas de cada pelicula
    private void ticketPorPelicula() {
        String seleccion = (String) boxPeliculas.getSelectedItem();

        if (seleccion == null || seleccion.startsWith("Seleccione")) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una película.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int codPelicula = Integer.parseInt(seleccion.split(" - ")[0].trim());

            modelo.setRowCount(0);
            modelo.setColumnIdentifiers(new Object[]{"N° Ticket", "Comprador", "Fecha Compra", "Monto Total", "Método Pago"});

            List<TicketCompra> lista = ticketData.listarTicketsPorPelicula(codPelicula);

            if (lista.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No se encontraron tickets vendidos para la película seleccionada.", "Información", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            for (TicketCompra t : lista) {
                String metodo = t.isMetodoPago() ? "Tarjeta/Débito" : "Efectivo";

                modelo.addRow(new Object[]{
                    t.getCodTicket(),
                    t.getComprador().getNombre(),
                    t.getFechaCompra(),
                    t.getMonto(),
                    metodo
                });
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error al procesar el código de la película.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al generar el informe por película: " + ex.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Filtrar por la cantidad de asistencia en las peliculas
    private void fechaAsistencia() {
        Date fechaSeleccionada = dateHorario1.getDate();

        if (fechaSeleccionada == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una fecha de asistencia.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        LocalDate fechaAsistencia = fechaSeleccionada.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        modelo.setRowCount(0);
        modelo.setColumnIdentifiers(new Object[]{"DNI", "Nombre Comprador", "Fecha de Asistencia"});

        try {
            // Usamos el método de persistencia que consulta por fecha de la FUNCIÓN (asistencia)
            List<Comprador> lista = detalleTicketData.listarCompradoresPorFechaAsistencia(fechaAsistencia);

            if (lista.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ningún comprador asistió a una función en esa fecha.", "Información", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            for (Comprador c : lista) {
                modelo.addRow(new Object[]{
                    c.getDni(),
                    c.getNombre(),
                    fechaAsistencia
                });
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al generar el informe de compradores: " + ex.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarFuncionesComboBox() {
      
        boxFuncionesConsulta.removeAllItems();
        boxFuncionesConsulta.addItem("Seleccione la Función...");
        try {
            List<Funcion> lista = funcionData.listarFunciones();
            for (Funcion f : lista) {
               
                String item = f.getCodFuncion() + " - " + f.getPelicula().getTitulo() + " - Sala " + f.getSalaFuncion().getNroSala() + " (" + f.getHoraInicio() + ")";
                boxFuncionesConsulta.addItem(item);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar las funciones para consulta: " + ex.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void consultarLugaresPorFuncion() {
       String seleccion = (String) boxFuncionesConsulta.getSelectedItem(); 

        if (seleccion == null || seleccion.startsWith("Seleccione")) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una Función específica para consultar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            
            int codFuncion = Integer.parseInt(seleccion.split(" - ")[0].trim());
            
           
            Funcion funcionCompleta = funcionData.buscarFuncion(codFuncion);
            
            
            LugarData lugarData = new LugarData(); 
            List<Lugar> lugaresDisponibles = lugarData.obtenerLugaresDisponiblesPorFuncion(codFuncion);

            int count = lugaresDisponibles.size();
            
            
            modelo.setRowCount(0);
            modelo.setColumnIdentifiers(new Object[]{"Fila", "Número", "Disponibilidad", "Cod Lugar"});

            if (count == 0) {
                JOptionPane.showMessageDialog(this, 
                    "¡ASIENTOS AGOTADOS! No hay lugares disponibles para la función de '" + funcionCompleta.getPelicula().getTitulo() + "'.", 
                    "Consulta de Asientos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            
            for (Lugar l : lugaresDisponibles) {
                modelo.addRow(new Object[]{
                    l.getFila(),
                    l.getNumero(),
                    "DISPONIBLE",
                    l.getCodLugar()
                });
            }
            
            JOptionPane.showMessageDialog(this, 
                "Función: " + funcionCompleta.getPelicula().getTitulo() + ". Asientos vacíos encontrados: " + count, 
                "Resultado de la Consulta", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error al procesar el código de la función.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al consultar la base de datos: " + ex.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        reporteTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        btnProximosEstrenos = new javax.swing.JButton();
        btnBuscarPorFecha = new javax.swing.JButton();
        btnTicketPeliculas = new javax.swing.JButton();
        dateChooserFecha = new com.toedter.calendar.JDateChooser();
        boxPeliculas = new javax.swing.JComboBox<>();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        btnCompradoresAsistencia = new javax.swing.JButton();
        dateHorario1 = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        btnConsultarLugares = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        boxFuncionesConsulta = new javax.swing.JComboBox<>();

        setClosable(true);

        reporteTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(reporteTable);

        jLabel2.setFont(new java.awt.Font("Calibri", 3, 18)); // NOI18N
        jLabel2.setText("Informes y Consultas");

        btnProximosEstrenos.setText("Proximos Estrenos");
        btnProximosEstrenos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProximosEstrenosActionPerformed(evt);
            }
        });

        btnBuscarPorFecha.setText("Buscar Por Fecha");
        btnBuscarPorFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarPorFechaActionPerformed(evt);
            }
        });

        btnTicketPeliculas.setText("Peliculas");
        btnTicketPeliculas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTicketPeliculasActionPerformed(evt);
            }
        });

        dateChooserFecha.setDateFormatString("dd/MM/yyyy");
        dateChooserFecha.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                dateChooserFechaAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        boxPeliculas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxPeliculasActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Calibri", 3, 14)); // NOI18N
        jLabel1.setText("Informes de Venta");

        btnCompradoresAsistencia.setText("Listar Compradores");
        btnCompradoresAsistencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompradoresAsistenciaActionPerformed(evt);
            }
        });

        dateHorario1.setDateFormatString("dd/MM/yyyy");
        dateHorario1.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                dateHorario1AncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jLabel3.setFont(new java.awt.Font("Calibri", 3, 12)); // NOI18N
        jLabel3.setText("Filtrar los tickets que se emitieron en una fecha:");

        jLabel4.setFont(new java.awt.Font("Calibri", 3, 12)); // NOI18N
        jLabel4.setText("Filtrar por tickets vendidos en una pelicula:");

        jLabel5.setFont(new java.awt.Font("Calibri", 3, 12)); // NOI18N
        jLabel5.setText("Filtrar por compradores asociados a una funcion:");

        btnConsultarLugares.setText("Consultar");
        btnConsultarLugares.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarLugaresActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Calibri", 3, 12)); // NOI18N
        jLabel6.setText("Consultar lugares vacios:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(310, 310, 310)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 688, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(290, 290, 290)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(btnTicketPeliculas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnBuscarPorFecha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(boxPeliculas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(dateChooserFecha, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)))
                            .addComponent(jLabel3))
                        .addGap(89, 89, 89)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCompradoresAsistencia)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dateHorario1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 699, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 699, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnProximosEstrenos, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator6))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(boxFuncionesConsulta, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnConsultarLugares, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnConsultarLugares, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(btnProximosEstrenos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boxFuncionesConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnCompradoresAsistencia, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                            .addComponent(btnBuscarPorFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTicketPeliculas, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(boxPeliculas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(dateChooserFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(jLabel4)
                                .addGap(75, 75, 75))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(dateHorario1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(100, 100, 100)))
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(85, 85, 85)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dateChooserFechaAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_dateChooserFechaAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_dateChooserFechaAncestorAdded

    private void dateHorario1AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_dateHorario1AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_dateHorario1AncestorAdded

    private void btnProximosEstrenosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProximosEstrenosActionPerformed
        // TODO add your handling code here:
        cargarReporteProximosEstrenos();
    }//GEN-LAST:event_btnProximosEstrenosActionPerformed

    private void btnBuscarPorFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPorFechaActionPerformed
        // TODO add your handling code here:
        ticketPorFecha();
    }//GEN-LAST:event_btnBuscarPorFechaActionPerformed

    private void btnTicketPeliculasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTicketPeliculasActionPerformed
        // TODO add your handling code here:
        ticketPorPelicula();
    }//GEN-LAST:event_btnTicketPeliculasActionPerformed

    private void btnCompradoresAsistenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompradoresAsistenciaActionPerformed
        // TODO add your handling code here:
        fechaAsistencia();
    }//GEN-LAST:event_btnCompradoresAsistenciaActionPerformed

    private void btnConsultarLugaresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarLugaresActionPerformed
        // TODO add your handling code here:
        consultarLugaresPorFuncion();
    }//GEN-LAST:event_btnConsultarLugaresActionPerformed

    private void boxPeliculasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxPeliculasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boxPeliculasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> boxFuncionesConsulta;
    private javax.swing.JComboBox<String> boxPeliculas;
    private javax.swing.JButton btnBuscarPorFecha;
    private javax.swing.JButton btnCompradoresAsistencia;
    private javax.swing.JButton btnConsultarLugares;
    private javax.swing.JButton btnProximosEstrenos;
    private javax.swing.JButton btnTicketPeliculas;
    private com.toedter.calendar.JDateChooser dateChooserFecha;
    private com.toedter.calendar.JDateChooser dateHorario1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JTable reporteTable;
    // End of variables declaration//GEN-END:variables
}
