
package Vista;

import javax.swing.JInternalFrame;
import Persistencia.CompradorData;
import Persistencia.DetalleTicketData;
import Persistencia.FuncionData;
import Persistencia.LugarData;
import Persistencia.PeliculaData;
import Persistencia.SalaData;
import Persistencia.TicketData;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;



//La vistaPrincipal se encarga de ser la venta principal de la app, de esta vista se abren todas las demas
//ademas recibe todos los datas para pasarlos a las vistas a medida que las necesite.
public class VistaPrincipal extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VistaPrincipal.class.getName());
    private final CompradorData CompradorData;
    private final DetalleTicketData detalleTicketData;
    private final FuncionData funcionData;
    private final LugarData lugarData;
    private final PeliculaData peliculaData;
    private final SalaData salaData;
    private final TicketData ticketData;

    public VistaPrincipal(CompradorData CompradorData, DetalleTicketData detalleTicketData, FuncionData funcionData, LugarData lugarData, PeliculaData peliculaData, SalaData salaData, TicketData ticketData) {

        initComponents();
        this.CompradorData = CompradorData;
        this.detalleTicketData = detalleTicketData;
        this.funcionData = funcionData;
        this.lugarData = lugarData;
        this.peliculaData = peliculaData;
        this.salaData = salaData;
        this.ticketData = ticketData;
    }
    //Es para abrir una ventana y que se cierren las otras, para evitar ventanas duplicadas
    public void abrirInternal(JInternalFrame nuevo) {
        for (JInternalFrame frame : Escritorio.getAllFrames()) {
            frame.dispose();
        }
        Escritorio.add(nuevo);
        nuevo.setVisible(true);
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ImageIcon icono = new ImageIcon(getClass().getResource("/Fondos/img/cine.jpg"));
        Image miImagen = icono.getImage();
        Escritorio = new javax.swing.JDesktopPane(){
            public void paintComponent(Graphics g){
                g.drawImage(miImagen,0,0, getWidth(), getHeight(),this);
            }
        };
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuAdministracion = new javax.swing.JMenu();
        CompradorMenuItem = new javax.swing.JMenuItem();
        itemTicket = new javax.swing.JMenuItem();
        itemLugar = new javax.swing.JMenuItem();
        itemFuncion = new javax.swing.JMenuItem();
        itemSala = new javax.swing.JMenuItem();
        itemPelicula = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        menuTaquilla = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Calibri", 3, 48)); // NOI18N
        jLabel1.setText("El Cuyanito ");

        Escritorio.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout EscritorioLayout = new javax.swing.GroupLayout(Escritorio);
        Escritorio.setLayout(EscritorioLayout);
        EscritorioLayout.setHorizontalGroup(
            EscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EscritorioLayout.createSequentialGroup()
                .addGap(422, 422, 422)
                .addComponent(jLabel1)
                .addContainerGap(464, Short.MAX_VALUE))
        );
        EscritorioLayout.setVerticalGroup(
            EscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EscritorioLayout.createSequentialGroup()
                .addGap(410, 410, 410)
                .addComponent(jLabel1)
                .addContainerGap(625, Short.MAX_VALUE))
        );

        menuAdministracion.setText("Administracion");
        menuAdministracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAdministracionActionPerformed(evt);
            }
        });

        CompradorMenuItem.setText("Comprador");
        CompradorMenuItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CompradorMenuItemMouseClicked(evt);
            }
        });
        CompradorMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CompradorMenuItemActionPerformed(evt);
            }
        });
        menuAdministracion.add(CompradorMenuItem);

        itemTicket.setText("Ticket");
        itemTicket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemTicketActionPerformed(evt);
            }
        });
        menuAdministracion.add(itemTicket);

        itemLugar.setText("Lugar");
        itemLugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemLugarActionPerformed(evt);
            }
        });
        menuAdministracion.add(itemLugar);

        itemFuncion.setText("Funcion");
        itemFuncion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemFuncionActionPerformed(evt);
            }
        });
        menuAdministracion.add(itemFuncion);

        itemSala.setText("Sala");
        itemSala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSalaActionPerformed(evt);
            }
        });
        menuAdministracion.add(itemSala);

        itemPelicula.setText("Pelicula");
        itemPelicula.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itemPeliculaMouseClicked(evt);
            }
        });
        itemPelicula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemPeliculaActionPerformed(evt);
            }
        });
        menuAdministracion.add(itemPelicula);

        jMenuItem1.setText("Informes");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menuAdministracion.add(jMenuItem1);

        jMenuBar1.add(menuAdministracion);

        menuTaquilla.setText("Taquilla");
        menuTaquilla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuTaquillaMouseClicked(evt);
            }
        });
        jMenuBar1.add(menuTaquilla);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Escritorio)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Escritorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itemSalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSalaActionPerformed
        // TODO add your handling code here:
        VistaSala vs = new VistaSala(salaData);
        abrirInternal(vs);
    }//GEN-LAST:event_itemSalaActionPerformed

    private void CompradorMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CompradorMenuItemActionPerformed
        // TODO add your handling code here:
        VistaComprador vc = new VistaComprador(CompradorData);
        abrirInternal(vc);
    }//GEN-LAST:event_CompradorMenuItemActionPerformed

    private void CompradorMenuItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CompradorMenuItemMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_CompradorMenuItemMouseClicked

    private void menuAdministracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAdministracionActionPerformed

    }//GEN-LAST:event_menuAdministracionActionPerformed

    private void itemPeliculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemPeliculaActionPerformed
        VistaPelicula vp = new VistaPelicula(peliculaData);
        abrirInternal(vp);
    }//GEN-LAST:event_itemPeliculaActionPerformed

    private void itemPeliculaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemPeliculaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_itemPeliculaMouseClicked

    private void itemLugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemLugarActionPerformed
        VistaLugar vl = new VistaLugar(peliculaData, funcionData, lugarData);
        abrirInternal(vl);
    }//GEN-LAST:event_itemLugarActionPerformed

    private void itemFuncionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemFuncionActionPerformed
         VistaFuncion vf = new VistaFuncion(funcionData);
         abrirInternal(vf);
    }//GEN-LAST:event_itemFuncionActionPerformed

    private void menuTaquillaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuTaquillaMouseClicked

        ModoCompra selectorModo = new ModoCompra(this, true);
        selectorModo.setVisible(true);
        String modoElegido = selectorModo.getModoSeleccionado();
        if (modoElegido != null) {
            VistaTaquilla vt = new VistaTaquilla(peliculaData, funcionData, lugarData, ticketData, detalleTicketData, CompradorData, modoElegido);
            abrirInternal(vt);
            vt.setVisible(true);

            vt.toFront();
        }
    }//GEN-LAST:event_menuTaquillaMouseClicked

    private void itemTicketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemTicketActionPerformed
        // TODO add your handling code here:
        VistaTicket vt = new VistaTicket(ticketData, CompradorData, detalleTicketData);
        abrirInternal(vt);
    }//GEN-LAST:event_itemTicketActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        VistaInformes vi = new VistaInformes(peliculaData, ticketData, detalleTicketData, CompradorData,funcionData);
        abrirInternal(vi);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem CompradorMenuItem;
    private javax.swing.JDesktopPane Escritorio;
    private javax.swing.JMenuItem itemFuncion;
    private javax.swing.JMenuItem itemLugar;
    private javax.swing.JMenuItem itemPelicula;
    private javax.swing.JMenuItem itemSala;
    private javax.swing.JMenuItem itemTicket;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenu menuAdministracion;
    private javax.swing.JMenu menuTaquilla;
    // End of variables declaration//GEN-END:variables
}
