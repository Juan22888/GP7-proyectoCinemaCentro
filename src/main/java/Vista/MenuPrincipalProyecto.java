/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package Vista;

import Persistencia.CompradorData;
import Persistencia.DetalleTicketData;
import Persistencia.FuncionData;
import Persistencia.LugarData;
import Persistencia.PeliculaData;
import Persistencia.SalaData;
import Persistencia.TicketData;
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
                CompradorData compradorData = new CompradorData();
                DetalleTicketData detalleTicketData = new DetalleTicketData();
                FuncionData funcionData = new FuncionData();
                LugarData lugarData = new LugarData();
                PeliculaData peliculaData = new PeliculaData();
                SalaData salaData = new SalaData();
                TicketData ticketData = new TicketData();
                new VistaPrincipal(compradorData, detalleTicketData, funcionData,lugarData,peliculaData, salaData, ticketData).setVisible(true);
            }
        });

    }
}
