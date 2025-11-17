
package Persistencia;


import Modelo.Comprador;
import Modelo.Conexion;
import Modelo.DetalleTicket;
import Modelo.Lugar;
import Modelo.TicketCompra;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.util.List;


public class DetalleTicketData {
      private Connection con = null;
      private LugarData lugarData;
      private TicketData ticketData;
       //primer constructor crea su propia instancia de LugarData
    public DetalleTicketData() {
        this.con = Conexion.buscarConexion();
        this.lugarData = new LugarData();
    }
      
      //Segundo constructor recibe el LugarData
    public DetalleTicketData(LugarData lugarData){
        this.con = Conexion.buscarConexion();
        this.lugarData=lugarData;
        this.ticketData = null;
    }
    //Constructor mas completo, como funciona dejamos los 3 jajaj
    public DetalleTicketData(LugarData lugarData,TicketData ticketData){
        this.con = Conexion.buscarConexion();
        this.lugarData=lugarData;
        this.ticketData = ticketData;
    }
    
    //para validar un poco, para que no se rompa todo
    private void validarDetalleTicket(DetalleTicket dt) throws IllegalArgumentException {
    if (dt == null) {
        throw new NullPointerException("El objeto DetalleTicket no puede ser nulo.");
    }
    if (dt.getTicketCompra() == null || dt.getTicketCompra().getCodTicket() <= 0) {
        throw new IllegalArgumentException("El detalle debe estar asociado a un ticket de compra válido.");
    }
    if (dt.getLugar() == null || dt.getLugar().getCodLugar() <= 0) {
        throw new IllegalArgumentException("El detalle debe estar asociado a un lugar (asiento) válido.");
    }
    

    try {
        TicketData td = new TicketData();
        if (td.buscarTicket(dt.getTicketCompra().getCodTicket()) == null) {
             throw new IllegalArgumentException("El ticket asociado al detalle no existe en la base de datos.");
        }
    } catch (SQLException ex) {
         throw new RuntimeException("Error al verificar la existencia del ticket: " + ex.getMessage());
    }
}
    
    public boolean insertarDetalleTicket(DetalleTicket dt) throws SQLException{
        validarDetalleTicket(dt);
    
    String sql = "INSERT INTO detalleticket (codDetalle, codLugar,codTicket, estado)" + " VALUES (?, ?, ?, ?)";
    
    try (PreparedStatement ps = con.prepareStatement(sql)){
        ps.setInt(1, dt.getCodDetalle());
        ps.setInt(2, dt.getLugar().getCodLugar());
        ps.setInt(3,dt.getTicketCompra().getCodTicket());
        ps.setBoolean(4, dt.isEstado());
            

            int filasAfectadas = ps.executeUpdate();
           

            if (filasAfectadas > 0) {
                return true;
            }

        } catch (SQLException ex) {
            throw new SQLException("No se pudo guardar el detalle del ticket! " + ex);
        }
        return false;
    }
    //Este lista todos los detalles de un mismo ticket
    public List<DetalleTicket> listarDetallesPorTicket(int codTicket) throws SQLException {
    List<DetalleTicket> detalles = new ArrayList<>();
    
    String sql = "SELECT * FROM detalleticket WHERE codTicket = ?"; 

    try (PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, codTicket);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            DetalleTicket dt = new DetalleTicket();
            dt.setCodDetalle(rs.getInt("codDetalle"));
            
            
            Lugar lugar = lugarData.buscarLugar(rs.getInt("codLugar"));
            
            dt.setLugar(lugar);
            dt.setEstado(rs.getBoolean("estado"));
         
            
            detalles.add(dt);
        }
        rs.close();
        
    } catch (SQLException ex) {
        throw new SQLException("Error al listar detalles del ticket por CodTicket: " + ex.getMessage());
    }
    return detalles;
}
//este busca un detalle con codDetalle
    public DetalleTicket buscarDetalleTicket(int id) throws SQLException {
        String sql = "SELECT * FROM detalleticket WHERE codDetalle = ?";
        DetalleTicket dt = null;
        Lugar lugar = null;
        TicketCompra ticketCompra = null;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                dt = new DetalleTicket();
                dt.setCodDetalle(rs.getInt("codDetalle"));
                lugar=lugarData.buscarLugar(rs.getInt("codLugar"));
                dt.setLugar(lugar);
                ticketCompra = ticketData.buscarTicket(rs.getInt("codTicket"));
                dt.setTicketCompra(ticketCompra);
                dt.setEstado(rs.getBoolean("estado"));
                
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            throw new SQLException("No se encontró el detalle del ticket! " + ex);
        }
        return dt;
    }
    //Para comprobar si el lugar esta ocupado
      public boolean asientoOcupado(DetalleTicket dt) {

        String sql = "SELECT * FROM detalleticket WHERE codLugar = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, dt.getLugar().getCodLugar());

            ResultSet rs = ps.executeQuery();
            boolean ocupado = rs.next();
            rs.close();

            return ocupado;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "Error al verificar asiento ocupado: " + ex.getMessage());
            return true; // si hay error lo consideramos ocupado para seguridad
        }
    }

    public boolean actualizarDetalleTicket(int id, String columna, Object dato) throws Exception {
        if (!columna.equals("codDetalle") && !columna.equals("codLugar")
                && !columna.equals("estado")  && !columna.equals("codTicket")) {

            throw new IllegalArgumentException("Columna de actualización no permitida: " + columna);
        }

        String sql = "UPDATE detalleTicket SET " + columna + " = ? WHERE codDetalle = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            //Para saber que tipo de datos se pasan
            if (dato instanceof Boolean) {
                ps.setBoolean(1, (Boolean) dato);
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
            throw new SQLException("No se pudo modificar el detalle del ticket! " + ex);
        }

    }

    public boolean eliminarDetalleTicket(int id) throws SQLException {
        String sql = "DELETE FROM detalleTicket WHERE codDetalle = ?";
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
            throw new SQLException("No se pudo eliminar el detalle del ticket " + ex);
        } 
   }
    //Refactorizado para que haga mas insert a la vez, hace "batchs" osea por lotes
    public void crearDetallesTicket(List<DetalleTicket> listaDetalleTicket) throws SQLException{
        if(listaDetalleTicket==null){
         throw new NullPointerException("No hay detalles ticket.");
        }
        
        
        String sql = "INSERT INTO detalleticket(codLugar,codTicket,estado) VALUES (?, ?, ?)";
        
        
        try{
           PreparedStatement ps = con.prepareStatement(sql);
            //recorriendo la lista de asientos..
            for(int i = 0; i<listaDetalleTicket.size();i++){
                ps.setInt(1, listaDetalleTicket.get(i).getLugar().getCodLugar());
                ps.setInt(2, listaDetalleTicket.get(i).getTicketCompra().getCodTicket());
                ps.setBoolean(3,listaDetalleTicket.get(i).isEstado());
                //mandamos todo de una a la consulta
                ps.addBatch();
            }
            
            //se ejecutan todas las consultas a la vez
            ps.executeBatch();
            ps.close();
        }catch(SQLException ex){
            throw new SQLException("Error al generar detalleticket en lote " + ex);
        }
    
    }
 
    //Uff, una consulta que usa JOIN, como nos enseñaron en base de datos, consulta 4 tablas a la vez
    public List<Comprador> listarCompradoresPorFechaAsistencia(LocalDate fechaFuncion) throws SQLException {
        List<Comprador> compradores = new ArrayList<>();
        
        //DISTINCT es para poner una sola vez la seleccion por si un comprador, por ejemplo, fue a 2 funciones el mismo dia.
        String sql = "SELECT DISTINCT c.* FROM comprador c "
                   + "JOIN ticketcompra t ON c.codComprador = t.codComprador "
                   + "JOIN detalleticket dt ON t.codTicket = dt.codTicket "
                   + "JOIN lugar l ON dt.codLugar = l.codLugar "
                   + "JOIN funcion f ON l.codFuncion = f.codFuncion "
                   + "WHERE f.fechaFuncion = ?";// Filtramos por la fecha de la FUNCIÓN (no la de compra)

       

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(fechaFuncion));
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Comprador c = new Comprador();
                    c.setCodComprador(rs.getInt("codComprador"));
                    c.setDni(rs.getInt("dni"));
                    c.setNombre(rs.getString("nombre"));
                    c.setFechaNacimiento(rs.getDate("fechaNac").toLocalDate());
                    c.setPassword(rs.getString("password"));
                    c.setEstado(rs.getBoolean("estado"));
                    compradores.add(c);
                }
            }
        } catch (SQLException ex) {
            throw new SQLException("Error al listar compradores por fecha de asistencia: " + ex.getMessage());
        }
        return compradores;
    }

}
        
    
    

