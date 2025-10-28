/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Modelo.Comprador;
import Modelo.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Emiliano
 */
public class CompradorData {
    private Connection con=null;

    public CompradorData() {
          this.con = Conexion.buscarConexion();
    }
    
    public boolean insertarComprador (Comprador c) throws SQLException{
        String sql = "INSERT INTO comprador (dni, nombre, fechaNacimiento, possword, metodoPago)"+"VALUES (?, ?, ?, ?, ?)";
    try(PreparedStatement ps= con.prepareStatement(sql)){
        ps.setInt(1, c.getDni());
        ps.setString(2, c.getNombre());
        ps.setDate(3, Date.valueOf(c.getFechaNacimiento()));
        ps.setString(4, c.getPossword());
        ps.setBoolean(5,c.isMetodoPago());
        
        int filasAfectadas = ps.executeUpdate();
        return filasAfectadas > 0;
     }catch (SQLException ex){
         throw new SQLException("No se pudo guardar el comprador: "+ ex.getMessage());
     }
        
    }
    public Comprador buscarComprador(int id) throws SQLException{
        String sql = "SELECT * FROM comprador WHERE codComprador = ?";
        Comprador comprador = null;
        try (PreparedStatement ps = con.prepareStatement(sql)){     
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery ();
            if (rs.next()){
                comprador = new Comprador ();
                comprador.setCodComprador(rs.getInt("codComprador"));
                comprador.setDni(rs.getInt("dni"));
                comprador.setNombre(rs.getString("nombre"));
                comprador.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                comprador.setPossword(rs.getString("possword"));
                comprador.setMetodoPago(rs.getBoolean("metodoPago"));
                          
            }
            rs.close();
        }catch (SQLException ex){
            throw new SQLException ("no se pudo encontrar el comprador: "+ ex.getMessage());          
        }
        return comprador;
    }
    public boolean actualizarComprador(int id, String columna, Object dato) throws Exception{
        if(!columna.equals("dni")&& !columna.equals("nombre")
             && !columna.equals("fechaNacimiento") && !columna.equals("possword") 
              && !columna.equals("metodoPago")) {
            throw new IllegalArgumentException("Columna no valida: " + columna);
        }
        String Sql = "UPDATE comprador SET"+ columna + "= WHERE codComprador=";
           try(PreparedStatement ps = con.prepareStatement(Sql)) {
            if ( dato instanceof String){
                ps.setString(1, (String) dato);
            }else if (dato instanceof Boolean){
               ps.setBoolean(1, (Boolean) dato);
           } else if (dato instanceof java.sql.Date) {
                ps.setDate(1, (java.sql.Date) dato);
            } else if (dato instanceof Integer) {
                ps.setInt(1, (int) dato);
            } else {
                throw new IllegalArgumentException("El tipo de dato no es correcto para actualizar.");
            }
            ps.setInt(2, id);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        }catch (SQLException ex){
            throw new SQLException("no se pudo actualizar el comprador: "+ ex.getMessage());
        }
    }
     public boolean bajaLogicaComprador(int id) throws SQLException{
        String sql = "UPDATE comprador SET metodoPago = 1 WHERE codCOmprado=?";
        try(PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            return filas >0;
        }catch (SQLException ex){
            throw new SQLException ("no se pudo dar de baja el comprador: "+ ex.getMessage());
        }
    }
    public boolean altaLogicaComprador(int id)  throws SQLException{
        String sql = "UPDATE omprador SET metodoPAgo = 1 WHERE codComprador =?";
        try (PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            return filas > 0;
        }catch (SQLException ex){
            throw new SQLException("No se pudo dar de alta al comprador =?");

            }
        }
    public boolean eliminarComprador(int id) throws SQLException{
        String sql = "DELATE FROM comprador WHERE codComprador =?";
             try (PreparedStatement ps = con.prepareStatement(sql)){
                ps.setInt(1, id);
                int filas = ps.executeUpdate();
                return filas > 0;
        }catch (SQLException ex){
            throw new SQLException("Error al eliminar el comprador: "+ ex.getMessage());
         }
     }
}
