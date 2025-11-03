/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package Vista;

import Modelo.Comprador;
import Modelo.DetalleTicket;
import Modelo.Funcion;
import Modelo.Lugar;
import Modelo.Pelicula;
import Modelo.Sala;
import Modelo.TicketCompra;
import Persistencia.CompradorData;
import Persistencia.DetalleTicketData;
import Persistencia.FuncionData;
import Persistencia.LugarData;
import Persistencia.PeliculaData;
import Persistencia.SalaData;
import Persistencia.TicketData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LaMaquina
 */
public class MenuPrincipalProyecto {

    public static void main(String[] args) throws SQLException, Exception {

        Comprador comprador = new Comprador(1, 45801071, "Franco Coria", LocalDate.of(2004, 06, 07), "123Franco", true);

        Pelicula pelicula = new Pelicula();
        pelicula.setCodPelicula(23);
        Sala sala=new Sala();
        sala.setCodSala(10);

     
        
        Funcion funcion1 = new Funcion(1, pelicula, "Ingles", true, true, LocalTime.of(23, 10), LocalTime.of(00, 30), sala, 2500);
    //DetalleTicket detalleTicket = new DetalleTicket(1, .get(0), true);
        //TicketCompra ticketCompra = new TicketCompra(1, LocalDate.of(2025, 10, 29), LocalDate.of(2025, 10, 30), 2500, comprador, detalleTicket);

        CompradorData compradorData = new CompradorData();

        // 1. Creación de todos los objetos
        //SalaData salaData = new SalaData();
        //PeliculaData peliculaData = new PeliculaData();
        //FuncionData funcionData = new FuncionData(); 
        //LugarData lugarData = new LugarData();      
        //TicketData ticketData = new TicketData(lugarData);
        //DetalleTicketData detalleTicketData = new DetalleTicketData(lugarData);

       
// 2. Inyección de las dependencias circulares (¡Rompiendo el ciclo!)
        //funcionData.setLugarData(lugarData);
        //lugarData.setFuncionData(funcionData);
        
        //salaData.insertarSala(sala);
        //peliculaData.insertarPelicula(pelicula);
        //funcionData.insertarFuncion(funcion1);
    }
}
