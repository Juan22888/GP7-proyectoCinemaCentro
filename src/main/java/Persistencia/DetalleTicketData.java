/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;


import Modelo.Conexion;
import Modelo.DetalleTicket;
import Modelo.Lugar;
import Modelo.TicketCompra;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.util.List;

/**
 *
 * @author FRANCO
 */
public class DetalleTicketData {
      private Connection con = null;
      private LugarData lugarData;
      private TicketData ticketData;
    public DetalleTicketData(LugarData lugarData1) {
        this.con = Conexion.buscarConexion();
    }
      
      
      
    public DetalleTicketData(LugarData lugarData,TicketData ticketData){
    this.con = Conexion.buscarConexion();
    this.lugarData=lugarData;
    this.ticketData = ticketData;
    }
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
    
    public void crearDetallesTicket(List<DetalleTicket> listaDetalleTicket) throws SQLException{
        if(listaDetalleTicket==null){
         throw new NullPointerException("No hay detalles ticket.");
        }
        
        
        String sql = "INSERT INTO detalleticket(codLugar,codTicket,estado) VALUES (?, ?, ?)";
        
        
        try{
           PreparedStatement ps = con.prepareStatement(sql);
            
            for(int i = 0; i<listaDetalleTicket.size();i++){
                ps.setInt(1, listaDetalleTicket.get(i).getLugar().getCodLugar());
                ps.setInt(2, listaDetalleTicket.get(i).getTicketCompra().getCodTicket());
                ps.setBoolean(3,listaDetalleTicket.get(i).isEstado());
                ps.addBatch();
            }
            ps.executeBatch();
            ps.close();
        }catch(SQLException ex){
            throw new SQLException("Error al generar detalleticket en lote " + ex);
        }
    
    }

}
        
    
    

