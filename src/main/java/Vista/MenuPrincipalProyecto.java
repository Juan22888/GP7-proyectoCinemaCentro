/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package Vista;

import Modelo.Pelicula;
import Persistencia.PeliculaData;
import java.time.LocalDate;

/**
 *
 * @author LaMaquina
 */
public class MenuPrincipalProyecto {

    public static void main(String[] args) {
       //Pelicula pelicula= new Pelicula("Guerra Mundial Z","Marc Forster","Brad Pitt, Mireille Enos","Estados Unidos","Ciencia Ficcion",LocalDate.of(2023,03,03),true);
       PeliculaData peliculaData= new PeliculaData(); 
       Pelicula pelicula=peliculaData.buscarPelicula(1);
        System.out.println(pelicula);
    }
}
