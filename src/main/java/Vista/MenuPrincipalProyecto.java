/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package Vista;


import java.sql.SQLException;


/**
 *
 * @author LaMaquina
 */
public class MenuPrincipalProyecto {

    public static void main(String[] args) throws SQLException, Exception {
         /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaPrincipal().setVisible(true);
            }
        });
       
    }
}
