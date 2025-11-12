/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Vista;

import Modelo.Comprador;
import Modelo.Funcion;
import Modelo.Lugar;
import Modelo.Pelicula;
import Persistencia.CompradorData;
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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author FRANCO
 */
public class VistaTaquilla extends javax.swing.JInternalFrame {

    private final PeliculaData peliculaData;
    private final FuncionData funcionData;
    private final LugarData lugarData;
    private final TicketData ticketData;
    private final DetalleTicketData detalleTicketData;
    private final CompradorData compradorData;
    private Comprador comprador;
    private Funcion funcion;
    private Lugar lugar;

    public VistaTaquilla(PeliculaData peliculaData, FuncionData funcionData, LugarData lugarData, TicketData ticketData, DetalleTicketData detalleTicketData, CompradorData compradorData) {
        this.peliculaData = peliculaData;
        this.funcionData = funcionData;
        this.lugarData = lugarData;
        this.ticketData = ticketData;
        this.detalleTicketData = detalleTicketData;
        this.compradorData = compradorData;
        initComponents();
        cargarPeliculas();
    }

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
        labelPorDefecto = new javax.swing.JLabel();
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
        boxPeliculas = new javax.swing.JComboBox<>();
        labelCombo3 = new javax.swing.JLabel();
        dateHorario = new com.toedter.calendar.JDateChooser();
        labelCombo4 = new javax.swing.JLabel();
        labelCombo5 = new javax.swing.JLabel();
        dateFecha = new com.toedter.calendar.JDateChooser();
        butBuscarFecha = new javax.swing.JButton();
        butBuscarHorario = new javax.swing.JButton();
        fondo = new javax.swing.JLabel();
        jDesktopPane1 = new javax.swing.JDesktopPane();

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

        butBuscar.setIcon(new javax.swing.ImageIcon("C:\\Users\\FRANCO\\Documents\\NetBeansProjects\\CineCentro\\GP7-proyectoCinemaCentro\\img\\lupa.png")); // NOI18N
        butBuscar.setText("Buscar");
        butBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butBuscarActionPerformed(evt);
            }
        });
        getContentPane().add(butBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 80, -1, -1));

        butNuevoComprador.setIcon(new javax.swing.ImageIcon("C:\\Users\\FRANCO\\Documents\\NetBeansProjects\\CineCentro\\GP7-proyectoCinemaCentro\\img\\agregar-usuario.png")); // NOI18N
        butNuevoComprador.setText("Nuevo Comprador");
        butNuevoComprador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butNuevoCompradorActionPerformed(evt);
            }
        });
        getContentPane().add(butNuevoComprador, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 80, -1, -1));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 470, 1080, 20));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 1080, 20));
        getContentPane().add(txtPrecio3d, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 700, 100, -1));

        labelPorDefecto.setFont(new java.awt.Font("Calibri", 3, 18)); // NOI18N
        labelPorDefecto.setForeground(new java.awt.Color(255, 255, 255));
        labelPorDefecto.setText("Por Defecto (No)");
        getContentPane().add(labelPorDefecto, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 540, -1, -1));
        getContentPane().add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 600, 1080, 10));

        jLabel4.setFont(new java.awt.Font("Calibri", 3, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Venta de Entrada");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, -1, -1));

        labelComprador.setFont(new java.awt.Font("Calibri", 3, 24)); // NOI18N
        labelComprador.setForeground(new java.awt.Color(255, 255, 255));
        labelComprador.setText("Comprador");
        getContentPane().add(labelComprador, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 90, -1, -1));

        butCancelar.setIcon(new javax.swing.ImageIcon("C:\\Users\\FRANCO\\Documents\\NetBeansProjects\\CineCentro\\GP7-proyectoCinemaCentro\\img\\icons8-cross-mark-48.png")); // NOI18N
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

        butComboSiNo.setText("Si / No");
        getContentPane().add(butComboSiNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 530, 130, 50));
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
        getContentPane().add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 690, 140, -1));
        getContentPane().add(txtPrecio2d, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 660, 100, -1));

        butLugaresDisponibles.setIcon(new javax.swing.ImageIcon("C:\\Users\\FRANCO\\Documents\\NetBeansProjects\\CineCentro\\GP7-proyectoCinemaCentro\\img\\silla.png")); // NOI18N
        butLugaresDisponibles.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        butLugaresDisponibles.setBorderPainted(false);
        butLugaresDisponibles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butLugaresDisponiblesActionPerformed(evt);
            }
        });
        getContentPane().add(butLugaresDisponibles, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 520, 60, 70));

        butGenerarTicket.setIcon(new javax.swing.ImageIcon("C:\\Users\\FRANCO\\Documents\\NetBeansProjects\\CineCentro\\GP7-proyectoCinemaCentro\\img\\iconticket.jpg")); // NOI18N
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
                "nroSala", "Pelicula", "Fecha", "Idioma", "Subtitulada", "Es3D", "HoraInicio", "HoraFin"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaFunciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaFuncionesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaFunciones);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 240, 820, 220));

        boxPeliculas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxPeliculasActionPerformed(evt);
            }
        });
        getContentPane().add(boxPeliculas, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 190, 190, -1));

        labelCombo3.setFont(new java.awt.Font("Calibri", 3, 24)); // NOI18N
        labelCombo3.setForeground(new java.awt.Color(255, 255, 255));
        labelCombo3.setText("Combo 2x1");
        getContentPane().add(labelCombo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 500, -1, -1));

        dateHorario.setDateFormatString("HH:mm");
        getContentPane().add(dateHorario, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 190, -1, -1));

        labelCombo4.setFont(new java.awt.Font("Calibri", 3, 18)); // NOI18N
        labelCombo4.setForeground(new java.awt.Color(255, 255, 255));
        labelCombo4.setText("Peliculas");
        getContentPane().add(labelCombo4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 160, -1, -1));

        labelCombo5.setFont(new java.awt.Font("Calibri", 3, 18)); // NOI18N
        labelCombo5.setForeground(new java.awt.Color(255, 255, 255));
        labelCombo5.setText("Horario");
        getContentPane().add(labelCombo5, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 160, -1, -1));

        dateFecha.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                dateFechaAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        getContentPane().add(dateFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 190, 120, -1));

        butBuscarFecha.setIcon(new javax.swing.ImageIcon("C:\\Users\\FRANCO\\Documents\\NetBeansProjects\\CineCentro\\GP7-proyectoCinemaCentro\\img\\lupa.png")); // NOI18N
        butBuscarFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butBuscarFechaActionPerformed(evt);
            }
        });
        getContentPane().add(butBuscarFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 180, 40, -1));

        butBuscarHorario.setIcon(new javax.swing.ImageIcon("C:\\Users\\FRANCO\\Documents\\NetBeansProjects\\CineCentro\\GP7-proyectoCinemaCentro\\img\\lupa.png")); // NOI18N
        butBuscarHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butBuscarHorarioActionPerformed(evt);
            }
        });
        getContentPane().add(butBuscarHorario, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 180, 40, -1));

        fondo.setIcon(new javax.swing.ImageIcon("C:\\Users\\FRANCO\\Documents\\NetBeansProjects\\CineCentro\\GP7-proyectoCinemaCentro\\img\\fondo.jpg")); // NOI18N
        getContentPane().add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -30, -1, 790));
        getContentPane().add(jDesktopPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, -20, 1080, 760));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cargarTabla(List<Funcion> listaFunciones) {
        DefaultTableModel modelo = (DefaultTableModel) tablaFunciones.getModel();
        modelo.setRowCount(0);

        for (Funcion f : listaFunciones) {
            Object[] fila = {f.getSalaFuncion().getNroSala(), f.getPelicula().getTitulo(), f.getFecha(), f.getIdioma(), f.isSubtitulada(), f.isEs3d(), f.getHoraInicio(), f.getHoraFin()};
            modelo.addRow(fila);
        }
    }
    private void butCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butCancelarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_butCancelarActionPerformed

    private void butLugaresDisponiblesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butLugaresDisponiblesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_butLugaresDisponiblesActionPerformed

    private void butGenerarTicketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butGenerarTicketActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_butGenerarTicketActionPerformed

    private void boxPeliculasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxPeliculasActionPerformed
        String peliculaSeleccionada = boxPeliculas.getSelectedItem().toString();
        String[] partes = peliculaSeleccionada.split(" - ");
        String txtCodPelicula = partes[0];
        int codPelicula = Integer.parseInt(txtCodPelicula);

        List<Funcion> listaFunciones = null;
        try {
            listaFunciones = funcionData.listarFuncionesPorPelicula(codPelicula);
        } catch (SQLException ex) {
            Logger.getLogger(VistaTaquilla.class.getName()).log(Level.SEVERE, null, ex);
        }

        cargarTabla(listaFunciones);
    }//GEN-LAST:event_boxPeliculasActionPerformed

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
        comprador = null;
        comprador = compradorData.buscarCompradorPorDni(dni);

        if (comprador == null) {
            JOptionPane.showMessageDialog(this, "No se encontro el comprador con ese id", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            JOptionPane.showMessageDialog(this, "Comprador encontrado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }


    }//GEN-LAST:event_butBuscarActionPerformed

    private void butNuevoCompradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butNuevoCompradorActionPerformed
        NuevoComprador nv = new NuevoComprador(compradorData);
        this.add(nv);
        nv.setVisible(true);
    }//GEN-LAST:event_butNuevoCompradorActionPerformed

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
        try {
            listaFunciones = funcionData.listarFunciones();
        } catch (SQLException ex) {
            Logger.getLogger(VistaTaquilla.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        List<Funcion> filtradas = new ArrayList<>();
        for (Funcion f : listaFunciones) {
            if (f.getHoraInicio().equals(horaBuscada)) {
                filtradas.add(f);
            }
        }

        cargarTabla(filtradas);
    }//GEN-LAST:event_butBuscarHorarioActionPerformed

    private void dateFechaAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_dateFechaAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_dateFechaAncestorAdded

    private void butBuscarFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butBuscarFechaActionPerformed
         Date horaSeleccionada = dateFecha.getDate();

        if (horaSeleccionada == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una hora para buscar.");
            return;
        }

        
        LocalDate horaBuscada = horaSeleccionada.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

    
        List<Funcion> listaFunciones = null;
        try {
            listaFunciones = funcionData.listarFunciones();
        } catch (SQLException ex) {
            Logger.getLogger(VistaTaquilla.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        List<Funcion> filtradas = new ArrayList<>();
        for (Funcion f : listaFunciones) {
            if (f.getHoraInicio().equals(horaBuscada)) {
                filtradas.add(f);
            }
        }

        cargarTabla(filtradas);
    }//GEN-LAST:event_butBuscarFechaActionPerformed

    private void tablaFuncionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaFuncionesMouseClicked
      
       
    }//GEN-LAST:event_tablaFuncionesMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> boxPeliculas;
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
    private javax.swing.JDesktopPane jDesktopPane1;
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
    private javax.swing.JLabel labelPorDefecto;
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
