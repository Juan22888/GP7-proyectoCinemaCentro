
package Vista;

import Modelo.Comprador;
import Modelo.DetalleTicket;
import Modelo.Funcion;
import Modelo.Lugar;
import Modelo.Pelicula;
import Modelo.TicketCompra;
import Persistencia.CompradorData;
import java.awt.Frame;
import javax.swing.SwingUtilities;
import Persistencia.DetalleTicketData;
import Persistencia.FuncionData;
import Persistencia.LugarData;
import Persistencia.PeliculaData;
import Persistencia.TicketData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

//La vista principal para la venta de entradas, es la vista mas importante.
//Maneja todo el flujo de la app, busca comprador, lo puede crear, elige pelicula, funcion, seleccion asientos, etc.
public class VistaTaquilla extends javax.swing.JInternalFrame {

    private final PeliculaData peliculaData;
    private final FuncionData funcionData;
    private final LugarData lugarData;
    private final TicketData ticketData;
    private final DetalleTicketData detalleTicketData;
    private final CompradorData compradorData;
    //variables de estado, para ir guardando lo que selecciona el usuario.
    private Comprador comprador;
    private Funcion funcion;
    private Lugar lugar;
    private boolean tarjetaIngresada = false;
    private String numTarjeta;
    private String titularTarjeta;
    private String fechaExpTarjeta;
    private String codVerificaTarjeta;
    //---
    private String modoVenta;
    private List<Lugar> lugaresReservados;

    public VistaTaquilla(PeliculaData peliculaData, FuncionData funcionData, LugarData lugarData, TicketData ticketData, DetalleTicketData detalleTicketData, CompradorData compradorData, String modoVenta) {
        this.peliculaData = peliculaData;
        this.funcionData = funcionData;
        this.lugarData = lugarData;
        this.ticketData = ticketData;
        this.detalleTicketData = detalleTicketData;
        this.compradorData = compradorData;
        this.modoVenta = modoVenta;
        this.lugaresReservados = new ArrayList<>();
        this.funcion = new Funcion();

        initComponents();
        cargarPeliculas();
        cargarMetodoPago();
        
        if (this.modoVenta.equals("Taquilla")) {
            // Si es taquilla, ocultamos el boton de pago con tarjeta
            btnCargarTarjeta.setVisible(false);
        
        } else if (this.modoVenta.equals("Online")) {
            // Si es online, mostramos el boton de pago con tarjeta.
            btnCargarTarjeta.setVisible(true);
            boxMetodoPago.setVisible(false);
            labelMetodoPago.setVisible(false);
        }

    }
    //Carga las peliculas en el combobox pero solo las que estan en cartelera
    private void cargarPeliculas() {
        try {
            List<Pelicula> lista = peliculaData.listarPeliculasEnCartelera();
            for (Pelicula p : lista) {
                boxPeliculas.addItem(p.getCodPelicula() + " - " + p.getTitulo());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los ComboBox: " + e.getMessage());
        }

    }
    //para cargar la tabla
    private void cargarTabla(List<Funcion> listaFunciones) {
        DefaultTableModel modelo = (DefaultTableModel) tablaFunciones.getModel();
        modelo.setRowCount(0);

        for (Funcion f : listaFunciones) {
            Object[] fila = {f.getCodFuncion(), f.getSalaFuncion().getNroSala(), f.getPelicula().getTitulo(), f.getFecha(), f.getIdioma(), f.isSubtitulada(), f.isEs3d(), f.getHoraInicio(), f.getHoraFin()};
            modelo.addRow(fila);
        }
    }
    //carga el combobox con los metodos de pago
    private void cargarMetodoPago() {
        boxMetodoPago.addItem("Seleccionar método de pago");
        boxMetodoPago.addItem("Efectivo");
        boxMetodoPago.addItem("Débito");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        button1 = new java.awt.Button();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        labelPrecios = new javax.swing.JLabel();
        butBuscar = new javax.swing.JButton();
        butNuevoComprador = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        txtPrecio3d = new javax.swing.JTextField();
        labelMetodoPago = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        labelComprador = new javax.swing.JLabel();
        butCancelar = new javax.swing.JButton();
        labellLugaresDisponibles = new javax.swing.JLabel();
        butComboSiNo = new javax.swing.JToggleButton();
        jSeparator4 = new javax.swing.JSeparator();
        labellLugaresDisponibles1 = new javax.swing.JLabel();
        txtDni = new javax.swing.JTextField();
        labelCombo = new javax.swing.JLabel();
        labelCombo1 = new javax.swing.JLabel();
        labelCombo2 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        txtPrecio2d = new javax.swing.JTextField();
        butLugaresDisponibles = new javax.swing.JButton();
        butGenerarTicket = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaFunciones = new javax.swing.JTable();
        labelCombo3 = new javax.swing.JLabel();
        labelCombo4 = new javax.swing.JLabel();
        labelCombo5 = new javax.swing.JLabel();
        butBuscarFecha = new javax.swing.JButton();
        butBuscarHorario = new javax.swing.JButton();
        dateHorario = new com.toedter.calendar.JDateChooser();
        boxMetodoPago = new javax.swing.JComboBox<>();
        dateFecha = new com.toedter.calendar.JDateChooser();
        labelPorDefecto1 = new javax.swing.JLabel();
        btnCargarTarjeta = new javax.swing.JButton();
        boxPeliculas = new javax.swing.JComboBox<>();
        fondo = new javax.swing.JLabel();
        Escritorio = new javax.swing.JDesktopPane();

        button1.setLabel("button1");

        jLabel2.setText("jLabel2");

        setClosable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Calibri", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Dni");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 90, -1, -1));

        labelPrecios.setFont(new java.awt.Font("Calibri", 3, 24)); // NOI18N
        labelPrecios.setForeground(new java.awt.Color(255, 255, 255));
        labelPrecios.setText("Precios");
        getContentPane().add(labelPrecios, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 630, -1, -1));

        butBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Fondos/img/lupa.png"))); // NOI18N
        butBuscar.setText("Buscar");
        butBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butBuscarActionPerformed(evt);
            }
        });
        getContentPane().add(butBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 80, -1, -1));

        butNuevoComprador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Fondos/img/agregar-usuario.png"))); // NOI18N
        butNuevoComprador.setText("Nuevo Comprador");
        butNuevoComprador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butNuevoCompradorActionPerformed(evt);
            }
        });
        getContentPane().add(butNuevoComprador, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 80, -1, -1));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 470, 1080, 20));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 1080, 20));

        txtPrecio3d.setEnabled(false);
        getContentPane().add(txtPrecio3d, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 700, 100, -1));

        labelMetodoPago.setFont(new java.awt.Font("Calibri", 3, 18)); // NOI18N
        labelMetodoPago.setForeground(new java.awt.Color(255, 255, 255));
        labelMetodoPago.setText("Metodo de pago");
        getContentPane().add(labelMetodoPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 630, -1, -1));
        getContentPane().add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 600, 1080, 10));

        jLabel4.setFont(new java.awt.Font("Calibri", 3, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Venta de Entrada");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, -1, -1));

        labelComprador.setBackground(new java.awt.Color(0, 0, 0));
        labelComprador.setFont(new java.awt.Font("Calibri", 3, 24)); // NOI18N
        labelComprador.setForeground(new java.awt.Color(255, 255, 255));
        labelComprador.setText("Comprador");
        getContentPane().add(labelComprador, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 90, -1, -1));

        butCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Fondos/img/icons8-cross-mark-48.png"))); // NOI18N
        butCancelar.setText("Cancelar");
        butCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(butCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 670, 140, 50));

        labellLugaresDisponibles.setFont(new java.awt.Font("Calibri", 3, 24)); // NOI18N
        labellLugaresDisponibles.setForeground(new java.awt.Color(255, 255, 255));
        labellLugaresDisponibles.setText("Total");
        getContentPane().add(labellLugaresDisponibles, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 690, -1, -1));

        butComboSiNo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Fondos/img/acceso.png"))); // NOI18N
        butComboSiNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butComboSiNoActionPerformed(evt);
            }
        });
        getContentPane().add(butComboSiNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 510, 80, 70));
        getContentPane().add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 1070, 20));

        labellLugaresDisponibles1.setFont(new java.awt.Font("Calibri", 3, 24)); // NOI18N
        labellLugaresDisponibles1.setForeground(new java.awt.Color(255, 255, 255));
        labellLugaresDisponibles1.setText("Lugares Disponibles");
        getContentPane().add(labellLugaresDisponibles1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, -1, -1));
        getContentPane().add(txtDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 90, 140, -1));

        labelCombo.setFont(new java.awt.Font("Calibri", 3, 24)); // NOI18N
        labelCombo.setForeground(new java.awt.Color(255, 255, 255));
        labelCombo.setText("3D");
        getContentPane().add(labelCombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 700, -1, -1));

        labelCombo1.setFont(new java.awt.Font("Calibri", 3, 18)); // NOI18N
        labelCombo1.setForeground(new java.awt.Color(255, 255, 255));
        labelCombo1.setText("Fecha");
        getContentPane().add(labelCombo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 160, -1, -1));

        labelCombo2.setFont(new java.awt.Font("Calibri", 3, 24)); // NOI18N
        labelCombo2.setForeground(new java.awt.Color(255, 255, 255));
        labelCombo2.setText("2D");
        getContentPane().add(labelCombo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 660, -1, -1));

        txtTotal.setEnabled(false);
        getContentPane().add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 690, 140, -1));

        txtPrecio2d.setEnabled(false);
        getContentPane().add(txtPrecio2d, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 660, 100, -1));

        butLugaresDisponibles.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Fondos/img/silla.png"))); // NOI18N
        butLugaresDisponibles.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        butLugaresDisponibles.setBorderPainted(false);
        butLugaresDisponibles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butLugaresDisponiblesActionPerformed(evt);
            }
        });
        getContentPane().add(butLugaresDisponibles, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 520, 60, 70));

        butGenerarTicket.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Fondos/img/iconticket.jpg"))); // NOI18N
        butGenerarTicket.setText(" Generar Ticket");
        butGenerarTicket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butGenerarTicketActionPerformed(evt);
            }
        });
        getContentPane().add(butGenerarTicket, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 670, 190, 50));

        tablaFunciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "codFuncion", "nroSala", "Pelicula", "Fecha", "Idioma", "Subtitulada", "Es3D", "HoraInicio", "HoraFin"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaFunciones.setShowGrid(true);
        tablaFunciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaFuncionesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaFunciones);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 240, 840, 220));

        labelCombo3.setFont(new java.awt.Font("Calibri", 3, 24)); // NOI18N
        labelCombo3.setForeground(new java.awt.Color(255, 255, 255));
        labelCombo3.setText("Combo 2x1");
        getContentPane().add(labelCombo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 480, -1, -1));

        labelCombo4.setFont(new java.awt.Font("Calibri", 3, 18)); // NOI18N
        labelCombo4.setForeground(new java.awt.Color(255, 255, 255));
        labelCombo4.setText("Peliculas");
        getContentPane().add(labelCombo4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 160, -1, -1));

        labelCombo5.setFont(new java.awt.Font("Calibri", 3, 18)); // NOI18N
        labelCombo5.setForeground(new java.awt.Color(255, 255, 255));
        labelCombo5.setText("Horario");
        getContentPane().add(labelCombo5, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 160, -1, -1));

        butBuscarFecha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Fondos/img/lupa.png"))); // NOI18N
        butBuscarFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butBuscarFechaActionPerformed(evt);
            }
        });
        getContentPane().add(butBuscarFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 180, 40, -1));

        butBuscarHorario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Fondos/img/lupa.png"))); // NOI18N
        butBuscarHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butBuscarHorarioActionPerformed(evt);
            }
        });
        getContentPane().add(butBuscarHorario, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 180, 40, -1));

        dateHorario.setDateFormatString("HH:mm");
        dateHorario.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                dateHorarioAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        getContentPane().add(dateHorario, new org.netbeans.lib.awtextra.AbsoluteConstraints(472, 190, 110, -1));

        boxMetodoPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxMetodoPagoActionPerformed(evt);
            }
        });
        getContentPane().add(boxMetodoPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 630, 150, -1));

        dateFecha.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                dateFechaAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        getContentPane().add(dateFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(755, 190, 110, -1));

        labelPorDefecto1.setFont(new java.awt.Font("Calibri", 3, 18)); // NOI18N
        labelPorDefecto1.setForeground(new java.awt.Color(255, 255, 255));
        labelPorDefecto1.setText("Por Defecto (No)");
        getContentPane().add(labelPorDefecto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 530, -1, -1));

        btnCargarTarjeta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Fondos/img/tarjeta-bancaria.png"))); // NOI18N
        btnCargarTarjeta.setText("Cargar Tarjeta");
        btnCargarTarjeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarTarjetaActionPerformed(evt);
            }
        });
        getContentPane().add(btnCargarTarjeta, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 80, -1, -1));

        boxPeliculas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxPeliculasActionPerformed(evt);
            }
        });
        getContentPane().add(boxPeliculas, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 190, 210, -1));

        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Fondos/fondo.jpg"))); // NOI18N
        getContentPane().add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -30, -1, 790));
        getContentPane().add(Escritorio, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, -20, 1080, 760));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Filtra la tabla por la hora seleccionada.
    private void butBuscarHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butBuscarHorarioActionPerformed
        Date horaSeleccionada = dateHorario.getDate();

        if (horaSeleccionada == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una hora para buscar.");
            return;
        }

        LocalTime horaBuscada = horaSeleccionada.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalTime();

        List<Funcion> listaFunciones = null;
        
        List<Funcion> listaFuncionesEnCartelera = new ArrayList<>();
        
        try {
            listaFunciones = funcionData.listarFunciones();//trae todas ls funciones
            
            for (int i = 0; i < listaFunciones.size(); i++) {
                if (listaFunciones.get(i).getPelicula().isEnCartelera() == true) {
                    listaFuncionesEnCartelera.add(listaFunciones.get(i));
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(VistaTaquilla.class.getName()).log(Level.SEVERE, null, ex);
        }
        //filtra las funciones que coincidan con la hora
        List<Funcion> filtradas = new ArrayList<>();
        for (Funcion f : listaFuncionesEnCartelera) {
            if (f.getHoraInicio().equals(horaBuscada)) {
                filtradas.add(f);
            }
        }
        //obviamente carga la tabla solo con las peliculas que se filtraron
        cargarTabla(filtradas);
    }//GEN-LAST:event_butBuscarHorarioActionPerformed
    //parecida a la anterior, pero filtra por fecha
    private void butBuscarFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butBuscarFechaActionPerformed
        Date horaSeleccionada = dateFecha.getDate();

        if (horaSeleccionada == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una fecha para buscar.");
            return;
        }

        LocalDate horaBuscada = horaSeleccionada.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        List<Funcion> listaFunciones = null;
        List<Funcion> listaFuncionesEnCartelera = new ArrayList<>();
        try {
            listaFunciones = funcionData.listarFunciones();

            for (int i = 0; i < listaFunciones.size(); i++) {
                if (listaFunciones.get(i).getPelicula().isEnCartelera() == true) {
                    listaFuncionesEnCartelera.add(listaFunciones.get(i));
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(VistaTaquilla.class.getName()).log(Level.SEVERE, null, ex);
        }

        List<Funcion> filtradas = new ArrayList<>();
        for (Funcion f : listaFuncionesEnCartelera) {
            if (f.getFecha().equals(horaBuscada)) {
                filtradas.add(f);
            }
        }

        cargarTabla(filtradas);
    }//GEN-LAST:event_butBuscarFechaActionPerformed
 //...
    private void dateFechaAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_dateFechaAncestorAdded

    }//GEN-LAST:event_dateFechaAncestorAdded

    //Filtra las peliculas elegidas en el cbox.
    private void boxPeliculasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxPeliculasActionPerformed
        String peliculaSeleccionada = boxPeliculas.getSelectedItem().toString();//las convierte en String
        //separar el id
        String[] partes = peliculaSeleccionada.split(" - ");
        String txtCodPelicula = partes[0];
        int codPelicula = Integer.parseInt(txtCodPelicula);

        List<Funcion> listaFunciones = null;
        try {
            //filtramos en la db para mostrar solo la pelicula que corresponde al codPelicula
            listaFunciones = funcionData.listarFuncionesPorPelicula(codPelicula);
        } catch (SQLException ex) {
            Logger.getLogger(VistaTaquilla.class.getName()).log(Level.SEVERE, null, ex);
        }

        cargarTabla(listaFunciones);
    }//GEN-LAST:event_boxPeliculasActionPerformed

    //para capturar la funcion que el usuario quiere comporar
    private void tablaFuncionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaFuncionesMouseClicked

        int filaSeleccionada = tablaFunciones.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una fila para modificar.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int codFuncion = 0;

        try {

            codFuncion = Integer.parseInt(tablaFunciones.getValueAt(filaSeleccionada, 0).toString());

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error en el formato del ID de la funcion.", "Error de Datos", JOptionPane.ERROR_MESSAGE);
        }

        try {
            funcion = funcionData.buscarFuncion(codFuncion);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al buscar la funcion en la base de datos.", "Error de Datos", JOptionPane.ERROR_MESSAGE);
        }

        double precio = funcion.getPrecioLugar();
        //para actualizar los precios
        if (funcion.isEs3d()) {
            txtPrecio3d.setText(String.valueOf(precio));
        } else {
            txtPrecio2d.setText(String.valueOf(precio));
        }
    }//GEN-LAST:event_tablaFuncionesMouseClicked

    
    //Es el que genera el ticket cuando el usuario termina de comprar
    private void butGenerarTicketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butGenerarTicketActionPerformed
        
        //hacemos varias verificaciones.
        //Si es online, que cargue la tarjeta
        if (!tarjetaIngresada && this.modoVenta.equals("Online")) {
            JOptionPane.showMessageDialog(this, "Debe cargar los datos de la tarjeta...", "Error de Pago", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //que seleccione comprador
        if (comprador == null) {
            JOptionPane.showMessageDialog(this, "Error! No se cargó al comprador.", "Error de Datos", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //que elija asiento
        if (lugaresReservados == null || lugaresReservados.isEmpty()) { // Comprobación importante
            JOptionPane.showMessageDialog(this, "Error! No hay lugares reservados.", "Error de Datos", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //que se elija un metodo de pago
        String seleccionado = (String) boxMetodoPago.getSelectedItem();
        if (seleccionado.equals("Seleccionar método de pago") && boxMetodoPago.isVisible()==true) {
            JOptionPane.showMessageDialog(this, "Debe elegir un método de pago.",
                    "Error de Datos", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean metodoPago = false;
        if ("Débito".equals(seleccionado)) {
            metodoPago = true;
        }
        double total = Double.parseDouble(txtTotal.getText());

        try {
            total = Double.parseDouble(txtTotal.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Tipo de dato no valido.",
                    "Error de Datos", JOptionPane.ERROR_MESSAGE);
            return;
        }

        TicketCompra ticket = new TicketCompra(-1, LocalDate.now(), total, comprador, metodoPago);
        try {
            //guarda el ticket en la db
            ticketData.insertarTicket(ticket);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al crear ticket en la bd.", "Error de Datos", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        //aca se crean todos los detalles
        List<DetalleTicket> detallesTicket = new ArrayList<>();
        //vemos que lugares eligio el usuario
        for (int i = 0; i < lugaresReservados.size(); i++) {
            try {
                //marca como ocupado el asiento en la db
                lugarData.actualizarLugar(lugaresReservados.get(i).getCodLugar(), true);
            } catch (SQLException ex) {
                Logger.getLogger(VistaTaquilla.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //se cree el objeto detalle pasandole el lugar y el ticket
            DetalleTicket dt = new DetalleTicket(-1, lugaresReservados.get(i), ticket, true);
            detallesTicket.add(dt);
        }
        try {
            detalleTicketData.crearDetallesTicket(detallesTicket);
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(this,
                    "Error al crear Detalles Ticket en la BD.\n" + ex.getMessage(),
                    "Error de Datos",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        JOptionPane.showMessageDialog(this, "¡El ticket se generó correctamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        try {
            
            Frame parentFrame = (Frame) SwingUtilities.getWindowAncestor(this);

           //le pasamos los datos a la ventana detalleTicket para que muestre la info
            DialogDetalleTicket dialog = new DialogDetalleTicket(
                    parentFrame,
                    true,
                    ticket, 
                    this.comprador, 
                    this.funcion, 
                    this.lugaresReservados 
            );

            dialog.setVisible(true); // Mostrar la ventana dialogoDetalleTicket

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Ticket guardado, pero no se pudo mostrar el detalle: " + e.getMessage(),
                    "Error al ver Detalle",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        // Limpiamos la vista para otras ventas
        butCancelarActionPerformed(null);


    }//GEN-LAST:event_butGenerarTicketActionPerformed

    //abre la ventana para elegir los asientos
    private void butLugaresDisponiblesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butLugaresDisponiblesActionPerformed
        int filaSeleccionada = tablaFunciones.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila.");
            return;
        }

        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);

        // Crear el diálogo modal
        DialogLugaresDisponibles dialog = new DialogLugaresDisponibles(parentFrame, true, lugarData, funcionData, funcion);
        dialog.setVisible(true);

        // Recuperar los asientos seleccionados
        lugaresReservados = dialog.getLugaresReservados();
        if (!lugaresReservados.isEmpty()) {
            String mensaje = "Asientos reservados:\n\n";

            for (Lugar l : lugaresReservados) {
                mensaje += "Fila: " + l.getFila() + " - Número: " + l.getNumero() + "\n";

            }

            JOptionPane.showMessageDialog(
                    this,
                    mensaje,
                    "Atención",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
        
        int entradasC = lugaresReservados.size();
        
        String precioTotalStr = String.valueOf(entradasC * funcion.getPrecioLugar());
        txtTotal.setText(precioTotalStr);
        
        
    }//GEN-LAST:event_butLugaresDisponiblesActionPerformed
    //limpia y resetea toda la vista
    private void butCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butCancelarActionPerformed
        txtTotal.setText("");
        txtDni.setText("");
        txtPrecio2d.setText("");
        boxPeliculas.setSelectedIndex(0);
        boxMetodoPago.setSelectedIndex(0);
        DefaultTableModel modelo = (DefaultTableModel) tablaFunciones.getModel();
        modelo.setRowCount(0);
        dateHorario.setDate(null);
        dateFecha.setDate(null);

    }//GEN-LAST:event_butCancelarActionPerformed
    //abre la vista NuevoComprador
    private void butNuevoCompradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butNuevoCompradorActionPerformed
        NuevoComprador nv = new NuevoComprador(compradorData);
        this.getDesktopPane().add(nv);
        nv.setVisible(true);
        nv.toFront();
    }//GEN-LAST:event_butNuevoCompradorActionPerformed

    
    //para buscar un comprador por el dni
    private void butBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butBuscarActionPerformed
        String txtdni = txtDni.getText();

        if (txtdni.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el dni del comprador", "Atencion", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int dni = 0;
        try {
            dni = Integer.parseInt(txtdni);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Tipo de dato no valido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        comprador = null; //para limpiar al comprador anterior
        //buscar en la db
        comprador = compradorData.buscarCompradorPorDni(dni);

        if (comprador == null) {
            JOptionPane.showMessageDialog(this, "No se encontro el comprador con ese id", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            JOptionPane.showMessageDialog(this, "Comprador encontrado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_butBuscarActionPerformed

    //abre la ventana NuevaTarjeta, pero solo si se elige Online
    private void btnCargarTarjetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarTarjetaActionPerformed

        javax.swing.JFrame parentFrame = (javax.swing.JFrame) this.getDesktopPane().getTopLevelAncestor();

        NuevaTarjeta nuevaTarjeta = new NuevaTarjeta(parentFrame, true);
        nuevaTarjeta.setVisible(true);
        
        if (nuevaTarjeta.isDatosCargados()) {
            //se guardan los datos temporalmente
            this.numTarjeta = nuevaTarjeta.getNumero();
            this.titularTarjeta = nuevaTarjeta.getTitular();
            this.fechaExpTarjeta = nuevaTarjeta.getFechaExpira();
            this.codVerificaTarjeta = nuevaTarjeta.getCodVerifica();
            this.tarjetaIngresada = true;

        }
    }//GEN-LAST:event_btnCargarTarjetaActionPerformed

    private void dateHorarioAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_dateHorarioAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_dateHorarioAncestorAdded

    private void boxMetodoPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxMetodoPagoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boxMetodoPagoActionPerformed

    private void butComboSiNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butComboSiNoActionPerformed

        int entradasC = lugaresReservados.size();

        if(entradasC == 0){
          JOptionPane.showMessageDialog(this, "No hay lugares reservados.", "Error", JOptionPane.ERROR_MESSAGE);
        }else{
            if (this.isSelected()) {
            int cantidadEntradas = lugaresReservados.size();
            entradasC = (cantidadEntradas / 2) + (cantidadEntradas % 2);
        }

        String precioTotalStr = String.valueOf(entradasC * funcion.getPrecioLugar());
        txtTotal.setText(precioTotalStr);
            
        }
        

    }//GEN-LAST:event_butComboSiNoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane Escritorio;
    private javax.swing.JComboBox<String> boxMetodoPago;
    private javax.swing.JComboBox<String> boxPeliculas;
    private javax.swing.JButton btnCargarTarjeta;
    private javax.swing.JButton butBuscar;
    private javax.swing.JButton butBuscarFecha;
    private javax.swing.JButton butBuscarHorario;
    private javax.swing.JButton butCancelar;
    private javax.swing.JToggleButton butComboSiNo;
    private javax.swing.JButton butGenerarTicket;
    private javax.swing.JButton butLugaresDisponibles;
    private javax.swing.JButton butNuevoComprador;
    private java.awt.Button button1;
    private com.toedter.calendar.JDateChooser dateFecha;
    private com.toedter.calendar.JDateChooser dateHorario;
    private javax.swing.JLabel fondo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel labelCombo;
    private javax.swing.JLabel labelCombo1;
    private javax.swing.JLabel labelCombo2;
    private javax.swing.JLabel labelCombo3;
    private javax.swing.JLabel labelCombo4;
    private javax.swing.JLabel labelCombo5;
    private javax.swing.JLabel labelComprador;
    private javax.swing.JLabel labelMetodoPago;
    private javax.swing.JLabel labelPorDefecto1;
    private javax.swing.JLabel labelPrecios;
    private javax.swing.JLabel labellLugaresDisponibles;
    private javax.swing.JLabel labellLugaresDisponibles1;
    private javax.swing.JTable tablaFunciones;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtPrecio2d;
    private javax.swing.JTextField txtPrecio3d;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
