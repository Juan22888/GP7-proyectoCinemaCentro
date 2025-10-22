/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author LaMaquina
 */
public class Conexion {
    private static final String url="jdbc:mariadb://localhost/gp7_cinemacentro";
    private static final String usuario="root";
    private static final String password="";
    
    private static Connection conexion = null;

    private Conexion() {
    }

    
    public static Connection buscarConexion(){
      if (conexion == null) {

            //Requiere de un try catch porque puede lanzar una excepcion. si la clase que le indico en este caso el driver no existe en el classpath o no puede cargarse larga la excepcion
            try {
                Class.forName("org.mariadb.jdbc.Driver"); //cargar la clase y ejecuta su bloque estatico que ya registra el driver en drivermanager

                //Conexion con la bd - tambien requiere de un try catch si ocurre un error al intentar conectarse a la base de datos ej: url incorrecta, usuario o contraseña invalidos, base de datos apagada
                conexion = DriverManager.getConnection(url, usuario, password);
               

            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Error al cargar Driver " + ex.getMessage());
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al conectarse a la bd " + e.getMessage());
            }
            
            
        }
      
      return conexion;
    }

}

