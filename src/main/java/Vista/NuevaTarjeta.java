/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Vista;

import javax.swing.JOptionPane;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author feerl
 */
public class NuevaTarjeta extends javax.swing.JDialog {

    private boolean datosCargados = false;
    private String numero;
    private String titular;
    private String fechaExpira;
    private String codVerifica;

    /**
     * Creates new form NuevaTarjeta
     */
    public NuevaTarjeta(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setTitle("Ingreso de Tarjeta - Pago Online");
        this.setLocationRelativeTo(parent);
    }

    public boolean isDatosCargados() {
        return datosCargados;
    }

    public String getNumero() {
        return numero;
    }

    public String getTitular() {
        return titular;
    }

    public String getFechaExpira() {
        return fechaExpira;
    }

    public String getCodVerifica() {
        return codVerifica;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        txtTitular = new javax.swing.JTextField();
        txtCodVerif = new javax.swing.JTextField();
        btnPagar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        dateFechaExp = new com.toedter.calendar.JDateChooser();
        ImageIcon icono = new ImageIcon(getClass().getResource("/Fondos/img/fondo.jpg"));
        Image miImagen = icono.getImage();
        jDesktopPane2 = new javax.swing.JDesktopPane(){
            public void paintComponent(Graphics g){
                g.drawImage(miImagen,0,0, getWidth(), getHeight(),this);
            }

        };
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Numero de la Tarjeta:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 107, -1, -1));

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Titular:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 151, -1, -1));

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Fecha de Expiracion:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 195, -1, -1));

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Codigo de Verificacion:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 245, -1, -1));
        getContentPane().add(txtNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(182, 104, 200, -1));
        getContentPane().add(txtTitular, new org.netbeans.lib.awtextra.AbsoluteConstraints(182, 148, 200, -1));
        getContentPane().add(txtCodVerif, new org.netbeans.lib.awtextra.AbsoluteConstraints(182, 242, 74, -1));

        btnPagar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Fondos/img/tarjeta-de-credito.png"))); // NOI18N
        btnPagar.setText("Pagar");
        btnPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagarActionPerformed(evt);
            }
        });
        getContentPane().add(btnPagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 374, 120, 50));

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Fondos/img/icons8-cross-mark-48.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(246, 374, -1, 50));
        getContentPane().add(dateFechaExp, new org.netbeans.lib.awtextra.AbsoluteConstraints(182, 195, 100, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Cargar Tarjeta");

        jDesktopPane2.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane2Layout = new javax.swing.GroupLayout(jDesktopPane2);
        jDesktopPane2.setLayout(jDesktopPane2Layout);
        jDesktopPane2Layout.setHorizontalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addGap(148, 148, 148)
                .addComponent(jLabel1)
                .addContainerGap(200, Short.MAX_VALUE))
        );
        jDesktopPane2Layout.setVerticalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1)
                .addContainerGap(392, Short.MAX_VALUE))
        );

        getContentPane().add(jDesktopPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 510, 460));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagarActionPerformed
        // TODO add your handling code here:
java.util.Date fechaSeleccionada = dateFechaExp.getDate();
    
    if (txtNumero.getText().trim().isEmpty() || 
        txtTitular.getText().trim().isEmpty() ||
        fechaSeleccionada == null ||
        txtCodVerif.getText().trim().isEmpty()) {
        
        JOptionPane.showMessageDialog(this, "Debe completar todos los campos de la tarjeta.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MM/yy");
    String fechaExpiraStr = sdf.format(fechaSeleccionada);
    
    this.numero = txtNumero.getText().trim();
    this.titular = txtTitular.getText().trim();
    this.fechaExpira = fechaExpiraStr;
    this.codVerifica = txtCodVerif.getText().trim();
    this.datosCargados = true;
    this.dispose();
    }//GEN-LAST:event_btnPagarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        this.datosCargados = false; 
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NuevaTarjeta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NuevaTarjeta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NuevaTarjeta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NuevaTarjeta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() -> {
            NuevaTarjeta dialog = new NuevaTarjeta(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnPagar;
    private com.toedter.calendar.JDateChooser dateFechaExp;
    private javax.swing.JDesktopPane jDesktopPane2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField txtCodVerif;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtTitular;
    // End of variables declaration//GEN-END:variables
}
