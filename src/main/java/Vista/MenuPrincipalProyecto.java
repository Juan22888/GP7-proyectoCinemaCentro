/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package Vista;


import Persistencia.CompradorData;



/**
 *
 * @author LaMaquina
 */
public class MenuPrincipalProyecto {

    public static void main(String[] args) {
         /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               CompradorData CompradorData = new CompradorData();
               new VistaPrincipal(CompradorData).setVisible(true);
            }
        });
       
    }
}
