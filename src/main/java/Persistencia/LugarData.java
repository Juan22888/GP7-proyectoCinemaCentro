/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Modelo.Conexion;
import Modelo.Funcion;
import Modelo.Lugar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author feerl
 */
public class LugarData {
    
    private Connection con = null;
    private FuncionData funData;
    
    public LugarData(){
        this.con = Conexion.buscarConexion();
        this.funData = new FuncionData();
    }
    
     public boolean insertarLugar(Lugar lugar) {
    
        String sql = "INSERT INTO lugar (fila, numero, estado, codFuncion) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setInt(1, lugar.getFila());
            ps.setInt(2, lugar.getNumero());
            ps.setBoolean(3, lugar.isEstado());
            ps.setInt(4, lugar.getFuncion().getCodFuncion()); 

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        lugar.setCodLugar(rs.getInt(1));
                    }
                }
                return true;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Lugar para insertar: " + ex.getMessage());
        }
        return false;
    }
     
     public Lugar buscarLugar(int codLugar) {
        String sql = "SELECT codLugar, fila, numero, estado, codFuncion FROM lugar WHERE codLugar = ?";
        Lugar lugar = null;

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, codLugar);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    lugar = new Lugar();
                    lugar.setCodLugar(rs.getInt("codLugar"));
                    lugar.setFila(rs.getInt("fila"));
                    lugar.setNumero(rs.getInt("numero"));
                    lugar.setEstado(rs.getBoolean("estado"));
                    
                 
                    int codFuncion = rs.getInt("codFuncion");
                    
               
                    Funcion funcion = funData.buscarFuncion(codFuncion); 
                    
                    lugar.setFuncion(funcion);

                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Lugar para buscar: " + ex.getMessage());
        }
    
        return lugar;
    }
    
     
     public boolean actualizarLugar (int codLugar, boolean nuevoEstado) throws SQLException{
         
         String sql = "UPDATE lugar SET estado = ? WHERE codLugar = ?";
         
         try(PreparedStatement ps = con.prepareCall(sql)){
             
             ps.setBoolean(1, nuevoEstado);
             ps.setInt(2, codLugar);
             
             int filasAfectadas = ps.executeUpdate();
             
             return filasAfectadas > 0;
         } catch (SQLException ex){
                         JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Lugar para actualizar estado: " + ex.getMessage());

         }
         return false;
         
     }
     
     public boolean eliminarLugar(int codLugar){
         String sql = "DELETE FROM lugar WHERE codLugar = ?";
         
          try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, codLugar);

            int filasAfectadas = ps.executeUpdate();

            return filasAfectadas > 0;
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Lugar para eliminar: " + ex.getMessage());
        }
        return false;
     }
     
     
      public List<Lugar> obtenerLugaresDisponiblesPorFuncion(int codFuncion) {
        String sql = "SELECT codLugar, fila, numero FROM lugar WHERE idFuncion = ? AND estado = 0";
        List<Lugar> lugaresDisponibles = new ArrayList<>();
        
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, codFuncion);
            
            // Solo necesitamos el objeto Funcion completo una vez.
            Funcion funcion = funData.buscarFuncion(codFuncion); 
            
            if (funcion != null) {
                 try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Lugar lugar = new Lugar();
                        lugar.setCodLugar(rs.getInt("codLugar"));
                        lugar.setFila(rs.getInt("fila"));
                        lugar.setNumero(rs.getInt("numero"));
                        lugar.setEstado(false); 
                        lugar.setFuncion(funcion);
                        
                        lugaresDisponibles.add(lugar);
                    }
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al listar los lugares disponibles: " + ex.getMessage());
        } 
        
        return lugaresDisponibles;
    }
    
 
}
