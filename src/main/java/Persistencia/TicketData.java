package Persistencia;

import Modelo.Conexion;
import Modelo.TicketCompra;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Modelo.Comprador;
import Modelo.DetalleTicket;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


//Esta clase guarda info general de la venta como: quien compro, cuando, el monto total, como pago, etc.
public class TicketData {

    private Connection con = null;
    
   
    private DetalleTicketData detalleData;
    private CompradorData compradorData;

    public TicketData() {
        this.con = Conexion.buscarConexion();
        this.detalleData = new DetalleTicketData(new LugarData());
        this.compradorData = new CompradorData();
    }
        //Validador por aqui, validador por alla, todo sea por validar.
    private void validarTicket(TicketCompra t) {
        if (t == null) {
            throw new IllegalArgumentException("El ticket no puede ser nulo.");
        }

        if (t.getFechaCompra() == null) {
            throw new IllegalArgumentException("La fecha de compra no puede ser nula.");
        }
        //Para que no creemos tickets en el "futuro"
        if (t.getFechaCompra().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de compra no puede ser futura.");
        }

        if (t.getMonto() <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a 0.");
        }

        if (t.getComprador() == null) {
            throw new IllegalArgumentException("Debe tener un comprador asociado.");
        }

        if (!t.getComprador().isEstado()) {
            //No le vendemos a un comprador que esta dado de baja
            throw new IllegalArgumentException("El comprador está dado de baja.");
        }
    }

    public boolean insertarTicket(TicketCompra t) throws SQLException {

        validarTicket(t);

        String sql = "INSERT INTO ticketcompra (FechaCompra, Monto, metodoPago, codComprador)"
                + " VALUES (?, ?, ?, ?)";
        //Invoco a la DB para que me devuelva el codTicket que genera
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setDate(1, Date.valueOf(t.getFechaCompra())); //Convertimos LocalDate
            ps.setDouble(2, t.getMonto());
            ps.setBoolean(3, t.isMetodoPago());//true para tarjeta, false para efectivo
            ps.setInt(4, t.getComprador().getCodComprador());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        t.setCodTicket(rs.getInt(1));
                    }
                }
                return true;
            }
            return false;

        } catch (SQLException ex) {

            throw new SQLException("Error al insertar el ticket: " + ex.getMessage(), ex);
        }
    }

    public TicketCompra buscarTicket(int id) throws SQLException {

        String sql = "SELECT * FROM ticketcompra WHERE codTicket = ?";
        TicketCompra ticket = null;

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                ticket = new TicketCompra();
                ticket.setCodTicket(rs.getInt("codTicket"));
                ticket.setFechaCompra(rs.getDate("FechaCompra").toLocalDate());
                ticket.setMonto(rs.getDouble("Monto"));
                ticket.setMetodoPago(rs.getBoolean("metodoPago"));
                // Para que el ticket esté "completo", buscamos su objeto Comprador
                Comprador comprador = compradorData.buscarComprador(rs.getInt("codComprador"));
                ticket.setComprador(comprador);// Y se lo asignamos
            }
            rs.close();

        } catch (SQLException ex) {
            throw new SQLException("No se encontró el ticket! " + ex);
        }
        return ticket;
    }
    
    
    //actualiza una columna especifica de un ticket. Usado en VistaTicket para cambiar el monto o la fecha
    public boolean actualizarTicket(int id, String columna, Object dato) throws Exception {
    // Validación de seguridad para evitar que modifiquen columnas no permitidas
        if (!columna.equals("FechaCompra")
                && !columna.equals("Monto")
                && !columna.equals("codComprador")
                && !columna.equals("metodoPago")) {

            throw new IllegalArgumentException("Columna de actualización no permitida: " + columna);
        }

        String sql = "UPDATE ticketcompra SET " + columna + " = ? WHERE codTicket = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
    // Detectamos qué tipo de dato nos pasaron
            if (dato instanceof String) {
                ps.setString(1, (String) dato);
            } else if (dato instanceof Double) {
                ps.setDouble(1, (Double) dato);
            } else if (dato instanceof java.sql.Date) {
                ps.setDate(1, (java.sql.Date) dato);
            } else if (dato instanceof Integer) {
                ps.setInt(1, (Integer) dato);
            } else if (dato instanceof Boolean) {
                ps.setBoolean(1, (Boolean) dato);
            } else {
                throw new IllegalArgumentException("Tipo de dato no soportado para actualizar.");
            }

            ps.setInt(2, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            throw new SQLException("No se pudo modificar el ticket! " + ex);
        }
    }
    //UN SUPER METODO, UN METODO PARA GOBERNARLOS A TODOS. 
    //Habia un error al borrar un ticket porque estaba relacionado a otras clases y metodos
    //Este metodo borra en detalleTicket y en ticketCompra a la vez.
    public boolean eliminarTicket(int id) throws SQLException {
        String sqlDetalles = "DELETE FROM detalleticket WHERE codTicket = ?";
        String sqlTicket = "DELETE FROM ticketcompra WHERE codTicket = ?";

        PreparedStatement psDetalles = null;
        PreparedStatement psTicket = null;
        
        try {
            //Para que la db no guarde cuando quiera, si no cuando le digamos
            con.setAutoCommit(false);

            // Elimina a los hijos primero, osea todo lo de detalleTicket
            psDetalles = con.prepareStatement(sqlDetalles);
            psDetalles.setInt(1, id);
            psDetalles.executeUpdate(); // Elimina los detalles

            // Borra ticketCompra
            psTicket = con.prepareStatement(sqlTicket);
            psTicket.setInt(1, id);
            int filasAfectadas = psTicket.executeUpdate(); // Elimina el ticket principal

            // Ahora si db, borra todo, como te gusta
            con.commit();
            
            return filasAfectadas > 0; // Devuelve true si el ticket principal se borró

        } catch (SQLException ex) {
            // Si algo sale mal, revierte los cambios, cortita y al pie.
            if (con != null) {
                try {
                    con.rollback();
                    JOptionPane.showMessageDialog(null, "Error al eliminar ticket. Transacción revertida: " + ex.getMessage());
                } catch (SQLException e) {
                     JOptionPane.showMessageDialog(null, "Error grave durante el rollback: " + e.getMessage());
                }
            }
            throw new SQLException("No se pudo eliminar el ticket (transacción revertida): " + ex.getMessage());
        
        } finally {
            //Si sale bien o mal o mal y bien, limpia todo igual
            if (psDetalles != null) {
                psDetalles.close();
            }
            if (psTicket != null) {
                psTicket.close();
            }
            
            //IMPORTANTE, bueno no se si tanto, pero ponemos la DB en auto guardado nuevamente. (Si es importante jeje)
            if (con != null) {
                try {
                    con.setAutoCommit(true);
                } catch (SQLException e) {
                     System.err.println("Error al restaurar auto-commit: " + e.getMessage());
                }
            }
        }
    }
        
    //Trae todos los tickets de la db. Usado en VistaTicket para llenar la tabla
    public List<TicketCompra> listarTickets() {

        List<TicketCompra> tickets = new ArrayList<>();
        CompradorData cData = new CompradorData();


        String sql = "SELECT * FROM ticketcompra";

        try (PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                TicketCompra t = new TicketCompra();
                t.setCodTicket(rs.getInt("codTicket"));
                t.setFechaCompra(rs.getDate("FechaCompra").toLocalDate());
                t.setMonto(rs.getDouble("Monto"));
                t.setMetodoPago(rs.getBoolean("metodoPago"));

                Comprador comprador = cData.buscarComprador(rs.getInt("codComprador"));
                t.setComprador(comprador);

                tickets.add(t);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al listar tickets: " + ex.getMessage());
        }
        return tickets;
    }
//Trae los ticket por fecha de compra usado en VistaInforme
    public List<TicketCompra> listarTicketsPorFecha(LocalDate fecha) throws SQLException {
        List<TicketCompra> tickets = new ArrayList<>();
        String sql = "SELECT * FROM ticketcompra WHERE FechaCompra = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(fecha));// Seteamos la fecha a buscar

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    TicketCompra t = new TicketCompra();
                    t.setCodTicket(rs.getInt("codTicket"));
                    t.setFechaCompra(rs.getDate("FechaCompra").toLocalDate());
                    t.setMonto(rs.getDouble("Monto"));
                    t.setMetodoPago(rs.getBoolean("metodoPago"));

                    Comprador comprador = compradorData.buscarComprador(rs.getInt("codComprador"));
                    t.setComprador(comprador);

                    tickets.add(t);
                }
            }

        } catch (SQLException ex) {
            throw new SQLException("Error al listar tickets por fecha: " + ex.getMessage());
        }
        return tickets;
    }
    //Trae los tickets filtrados por pelicula. Una super consulta usando join por todos lados. Usado en VistaInformes
    public List<TicketCompra> listarTicketsPorPelicula(int codPelicula) throws SQLException {
        List<TicketCompra> tickets = new ArrayList<>();
        String sql = "SELECT DISTINCT t.* FROM ticketcompra t "
                + "JOIN detalleticket dt ON t.codTicket = dt.codTicket "
                + "JOIN lugar l ON dt.codLugar = l.codLugar "
                + "JOIN funcion f ON l.codFuncion = f.codFuncion "
                + "WHERE f.codPelicula = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, codPelicula);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    TicketCompra t = new TicketCompra();
                    t.setCodTicket(rs.getInt("codTicket"));
                    t.setFechaCompra(rs.getDate("FechaCompra").toLocalDate());
                    t.setMonto(rs.getDouble("Monto"));
                    t.setMetodoPago(rs.getBoolean("metodoPago"));

                    Comprador comprador = compradorData.buscarComprador(rs.getInt("codComprador"));
                    t.setComprador(comprador);

                    tickets.add(t);
                }
            }

        } catch (SQLException ex) {
            throw new SQLException("Error al listar tickets por película: " + ex.getMessage());
        }
        return tickets;
    }
}
