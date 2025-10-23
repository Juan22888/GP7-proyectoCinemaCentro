/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package Vista;

import Modelo.Pelicula;
import Persistencia.PeliculaData;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author LaMaquina
 */
public class MenuPrincipalProyecto {

    public static void main(String[] args) throws SQLException, Exception {
        Pelicula pelicula = new Pelicula(-1, "Guerra Mundial Z", "Marc Forster", "Brad Pitt, Mireille Enos", "Estados Unidos", "Ciencia Ficcion", LocalDate.of(2023, 03, 03), true);
        PeliculaData peliculaData = new PeliculaData();
        //Pelicula pelicula2=peliculaData.buscarPelicula(1);
        //System.out.println(pelicula2.getTitulo());
        //boolean seAgrego=peliculaData.insertarPelicula(pelicula);
        //System.out.println(seAgrego);
        //boolean seModifico = peliculaData.actualizarPelicula(1, "titulo", "Harry Potter");
        //System.out.println(seModifico);
        //boolean seDioDeBaja=peliculaData.bajaLogicaPelicula(1);
        //System.out.println(seDioDeBaja);
        //boolean seDioDeAlta=peliculaData.altaLogicaPelicula(1);
        //System.out.println(seDioDeAlta);
        //boolean seElimino=peliculaData.eliminarPelicula(2);
        //System.out.println(seElimino);  
    }
}
