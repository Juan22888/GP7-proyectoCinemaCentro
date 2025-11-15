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
import java.sql.Statement;
import Persistencia.CompradorData;
import java.time.LocalDate;

/**
 *
 * @author Fede-
 */
public class TicketData {

    private Connection con = null;

    public TicketData() {
        this.con = Conexion.buscarConexion();
    }

    public TicketData(LugarData lugarData) {
        this.con = Conexion.buscarConexion();
    }
    
    private void validarTicket(TicketCompra t) throws IllegalArgumentException{
        if(t == null){
            throw new NullPointerException("El objeto ThicketCompra no puede ser nulo.");
        }
        if(t.getComprador() == null || t.getComprador().getCodComprador() <= 0){
            throw new IllegalArgumentException("El ticket debe de estar asociado a un comprador");
        }
        if(t.getFechaCompra() == null || t.getFechaCompra().isAfter(LocalDate.now())){
            throw new IllegalArgumentException("La fecha de compra no puede ser nula ni futura.");
        }
        if(t.getMonto() <= 0){
            throw new IllegalArgumentException("El monto total del ticket debe ser mayor a 0");
        }
        try{
            CompradorData cd = new CompradorData();
            if(cd.buscarComprador(t.getComprador().getCodComprador()) == null){
                throw new IllegalArgumentException("El comprador asociado al ticket no existe en la DB");
            }
        } catch(SQLException ex){
            throw new RuntimeException("Error al verificar la existencia del comprador: " + ex.getMessage());
        }
    }

   public boolean insertarTicket(TicketCompra t) throws SQLException {
       try {
            validarTicket(t);
        } catch (IllegalArgumentException ex) {
            throw new SQLException("Datos de funcion inválidos: " + ex.getMessage());
        }

    String sql = "INSERT INTO ticketcompra (FechaCompra, Monto, metodoPago, codComprador) VALUES (?, ?, ?, ?)";

    try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

        ps.setDate(1, Date.valueOf(t.getFechaCompra()));
        ps.setDouble(2, t.getMonto());
        ps.setBoolean(3, t.isMetodoPago());
        ps.setInt(4, t.getComprador().getCodComprador());

        int filasAfectadas = ps.executeUpdate();

        if (filasAfectadas > 0) {
            // Obtener el ID generado automáticamente
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                t.setCodTicket(rs.getInt(1));  // <<< FUNDAMENTAL
            }
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
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ticket = new TicketCompra();
                ticket.setCodTicket(rs.getInt("codTicket"));
                ticket.setFechaCompra(rs.getDate("FechaCompra").toLocalDate());
                ticket.setMonto(rs.getDouble("Monto"));
                ticket.setMetodoPago(rs.getBoolean("metodoPago"));
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
        
        if (!columna.equals("FechaCompra")
                && !columna.equals("Monto")
                && !columna.equals("codComprador")
                && !columna.equals("metodoPago")) {

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
            } else if (dato instanceof Boolean) {
                ps.setBoolean(1, (boolean) dato);
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
