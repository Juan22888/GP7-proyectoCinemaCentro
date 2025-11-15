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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class TicketData {

    private Connection con = null;
    private DetalleTicketData detalleData;
    private CompradorData compradorData;

    public TicketData() {
        this.con = Conexion.buscarConexion();
        this.detalleData = new DetalleTicketData(new LugarData());
        this.compradorData = new CompradorData();
    }

    private void validarTicket(TicketCompra t) {

        if (t == null)
            throw new IllegalArgumentException("El ticket no puede ser nulo.");

        if (t.getFechaCompra() == null)
            throw new IllegalArgumentException("La fecha de compra no puede ser nula.");

        if (t.getFechaCompra().isAfter(LocalDate.now()))
            throw new IllegalArgumentException("La fecha de compra no puede ser futura.");

        if (t.getMonto() <= 0)
            throw new IllegalArgumentException("El monto debe ser mayor a 0.");

        if (t.getComprador() == null)
            throw new IllegalArgumentException("Debe tener un comprador asociado.");

        if (!t.getComprador().isEstado())
            throw new IllegalArgumentException("El comprador está dado de baja.");

        if (t.getDetalleTicket() == null)
            throw new IllegalArgumentException("Debe tener un detalle asociado.");

        // Valida si el asiento está ocupado
        if (detalleData.asientoOcupado(t.getDetalleTicket()))
            throw new IllegalArgumentException("Ese asiento ya está ocupado para esa función.");
    }

   
    public boolean insertarTicket(TicketCompra t) throws SQLException {

        validarTicket(t);

        // Inserta primero el detalle
        detalleData.insertarDetalleTicket(t.getDetalleTicket());

        String sql = "INSERT INTO ticketcompra (FechaCompra, Monto, metodoPago, codComprador, codDetalle)"
                   + " VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setDate(1, Date.valueOf(t.getFechaCompra()));
            ps.setDouble(2, t.getMonto());
            ps.setBoolean(3, t.isMetodoPago());
            ps.setInt(4, t.getComprador().getCodComprador());
            ps.setInt(5, t.getDetalleTicket().getCodDetalle());

        ps.setDate(1, Date.valueOf(t.getFechaCompra()));
        ps.setDouble(2, t.getMonto());
        ps.setBoolean(3, t.isMetodoPago());
        ps.setInt(4, t.getComprador().getCodComprador());

            if (filasAfectadas > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    t.setCodTicket(rs.getInt(1));
                }
                rs.close();
                return true;
            }

        if (filasAfectadas > 0) {
            // Obtener el ID generado automáticamente
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                t.setCodTicket(rs.getInt(1));  // <<< FUNDAMENTAL
            }
            return true;
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

                // cargar detalle
                DetalleTicket detalle = detalleData.buscarDetalleTicket(rs.getInt("codDetalle"));
                ticket.setDetalleTicket(detalle);

                // cargar comprador
                Comprador comprador = compradorData.buscarComprador(rs.getInt("codComprador"));
                ticket.setComprador(comprador);
            }

            rs.close();

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

            if (dato instanceof String)
                ps.setString(1, (String) dato);
            else if (dato instanceof Double)
                ps.setDouble(1, (Double) dato);
            else if (dato instanceof java.sql.Date)
                ps.setDate(1, (java.sql.Date) dato);
            else if (dato instanceof Integer)
                ps.setInt(1, (Integer) dato);
            else if (dato instanceof Boolean)
                ps.setBoolean(1, (Boolean) dato);
            else
                throw new IllegalArgumentException("Tipo de dato no soportado para actualizar.");

            ps.setInt(2, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            throw new SQLException("No se pudo modificar el ticket! " + ex);
        }
    }

   
    public boolean eliminarTicket(int id) throws SQLException {

        String sql = "DELETE FROM ticketcompra WHERE codTicket = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            throw new SQLException("No se pudo eliminar el ticket " + ex);
        }
    }

    
    public List<TicketCompra> listarTickets() {

        List<TicketCompra> tickets = new ArrayList<>();
        CompradorData cData = new CompradorData();
        DetalleTicketData dData = new DetalleTicketData(new LugarData());

        String sql = "SELECT * FROM ticketcompra";

        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                TicketCompra t = new TicketCompra();

                t.setCodTicket(rs.getInt("codTicket"));
                t.setFechaCompra(rs.getDate("FechaCompra").toLocalDate());
                t.setMonto(rs.getDouble("Monto"));

                Comprador comprador = cData.buscarComprador(rs.getInt("codComprador"));
                DetalleTicket detalle = dData.buscarDetalleTicket(rs.getInt("codDetalle"));

                t.setComprador(comprador);
                t.setDetalleTicket(detalle);

                tickets.add(t);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al listar tickets: " + ex.getMessage());
        }

        return tickets;
    }
}
