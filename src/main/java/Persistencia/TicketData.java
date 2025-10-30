/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Modelo.Conexion;
import Modelo.TicketCompra;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Modelo.Comprador;
import Modelo.DetalleTicket;

/**
 *
 * @author Fede-
 */
public class TicketData {

    private Connection con = null;
    private DetalleTicketData detalleTicketData;

    public TicketData() {
    }
    
    
    public TicketData(LugarData lugarData) {
        this.con = Conexion.buscarConexion();
        this.detalleTicketData = new DetalleTicketData(lugarData);
    }

    public boolean insertarTicket(TicketCompra t) throws SQLException {

        String sql = "INSERT INTO ticketcompra (FechaCompra, FechaFuncion, Monto, codComprador,codDetalle)" + " VALUES (?, ?, ?, ?,?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(t.getFechaCompra()));
            ps.setDate(2, Date.valueOf(t.getFechaFuncion()));
            ps.setDouble(3, t.getMonto());
            ps.setInt(4, t.getComprador().getCodComprador()); // Asume que la clase Comprador tiene codigoComprador
            ps.setInt(5, t.getDetalleTicket().getCodDetalle());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                return true;
            }

        } catch (SQLException ex) {
            throw new SQLException("No se pudo guardar el ticket! " + ex);
        }
        return false;
    }

    public TicketCompra buscarTicket(int id) throws SQLException {
        String sql = "SELECT * FROM ticketcompra WHERE codTicket = ?";
        TicketCompra ticket = null;
        DetalleTicket detalleTicket = null;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ticket = new TicketCompra();
                detalleTicket = new DetalleTicket();
                ticket.setCodTicket(rs.getInt("codTicket"));
                ticket.setFechaCompra(rs.getDate("FechaCompra").toLocalDate());
                ticket.setFechaFuncion(rs.getDate("FechaFuncion").toLocalDate());
                ticket.setMonto(rs.getDouble("Monto"));
                detalleTicket = detalleTicketData.buscarDetalleTicket(rs.getInt("codDetalle"));
                ticket.setDetalleTicket(detalleTicket);
                // Cargar comprador
                CompradorData compradorData = new CompradorData();
                Comprador comprador = compradorData.buscarComprador(rs.getInt("codComprador"));
                ticket.setComprador(comprador);
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            throw new SQLException("No se encontró el ticket! " + ex);
        }
        return ticket;
    }

    public boolean actualizarTicket(int id, String columna, Object dato) throws Exception {
        if (!columna.equals("FechaCompra") && !columna.equals("FechaFuncion")
                && !columna.equals("Monto")
                && !columna.equals("codComprador")
                && !columna.equals("codDetalle")) {

            throw new IllegalArgumentException("Columna de actualización no permitida: " + columna);
        }

        String sql = "UPDATE ticketcompra SET " + columna + " = ? WHERE codTicket = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            if (dato instanceof String) {
                ps.setString(1, (String) dato);
            } else if (dato instanceof Double) {
                ps.setDouble(1, (Double) dato);
            } else if (dato instanceof java.sql.Date) {
                ps.setDate(1, (java.sql.Date) dato);
            } else if (dato instanceof Integer) {
                ps.setInt(1, (Integer) dato);
            } else {
                throw new IllegalArgumentException("Tipo de dato no soportado para actualizar.");
            }
            ps.setInt(2, id);

            int filasAfectadas = ps.executeUpdate();
            ps.close();

            if (filasAfectadas > 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            throw new SQLException("No se pudo modificar el ticket! " + ex);
        }

    }

    public boolean eliminarTicket(int id) throws SQLException {
        String sql = "DELETE FROM ticketcompra WHERE codTicket = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int filasAfectadas = ps.executeUpdate();
            ps.close();

            if (filasAfectadas > 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            throw new SQLException("No se pudo eliminar el ticket " + ex);
        }
    }

}
