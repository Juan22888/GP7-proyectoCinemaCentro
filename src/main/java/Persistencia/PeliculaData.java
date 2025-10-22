/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Modelo.Conexion;
import Modelo.Pelicula;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author feerl
 */
public class PeliculaData {
     private Connection con = null;
     
     public PeliculaData(){
         this.con = Conexion.buscarConexion();
     }
     
     
     
     public void guardarPelicula(Pelicula p) {
      
        String sql = "INSERT INTO pelicula (titulo, director, actores, origen, genero, estreno, enCartelera) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
      
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, p.getTitulo());
            ps.setString(2, p.getDirector());
            ps.setString(3, p.getActores());
            ps.setString(4, p.getOrigen());
            ps.setString(5, p.getGenero());
            
         
            ps.setDate(6, Date.valueOf(p.getEstreno())); 
            
            ps.setBoolean(7, p.isEnCartelera()); 
            
            int filasAfectadas = ps.executeUpdate();
          
            if (filasAfectadas > 0) {
                 JOptionPane.showMessageDialog(null, "Se agregó la película '" + p.getTitulo() + "' exitosamente!", 
                         "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
            
        } catch (SQLException ex) {
        
            JOptionPane.showMessageDialog(null, "Error al guardar la película: " + ex.getMessage(), 
                    "Error de SQL", JOptionPane.ERROR_MESSAGE);
        }
    }
     
     public Pelicula buscarPelicula(int id){
         String sql = "SELECT * FROM pelicula WHERE idPelicula = ?";
         Pelicula pelicula = null;
         try (PreparedStatement ps = con.prepareStatement(sql)){
             ps.setInt(1, id);
             try(ResultSet rs = ps.executeQuery()){
                 pelicula = new Pelicula();            
                 pelicula.setTitulo(rs.getString("titulo"));
                 pelicula.setDirector(rs.getString("director"));
                 pelicula.setActores(rs.getString("actores"));
                 pelicula.setGenero(rs.getString("genero"));
                 pelicula.setEstreno(rs.getDate("estreno").toLocalDate());
                pelicula.setEnCartelera(rs.getBoolean("enCartelera"));
             }
             
         }
         catch(SQLException ex) {
             JOptionPane.showMessageDialog(null, "Error al buscar la película: " + ex.getMessage());
         }
         return pelicula;
     }
     
     public void actualizarPelicula (int id, String columna, Object dato){
         if (!columna.equals("titulo") && !columna.equals("director") && 
            !columna.equals("actores") && !columna.equals("origen") && 
            !columna.equals("genero") && !columna.equals("estreno") && 
            !columna.equals("enCartelera")) { 
            
            throw new IllegalArgumentException("Columna de actualización no permitida: " + columna);
        }
         
         String sql = "UPDATE pelicula SET" + columna + " = ? WHERE idPelicula = ?";
         
         try (PreparedStatement ps = con.prepareStatement(sql)) {
            
            // Determina el tipo de dato para establecer el parámetro
            if (dato instanceof String string) {
                ps.setString(1, string);
            } else if (dato instanceof Boolean) {
                ps.setBoolean(1, (boolean) dato);
            } else if (dato instanceof java.time.LocalDate localDate) {
                 // Conversión de LocalDate a java.sql.Date
                ps.setDate(1, Date.valueOf(localDate)); 
            } else {
                // Asume que otros tipos, como int o float, se pueden manejar aquí si son necesarios
                throw new IllegalArgumentException("Tipo de dato no soportado para actualizar.");
            }
            
            ps.setInt(2, id); 

            int filasAfectadas = ps.executeUpdate();
            
            if (filasAfectadas > 0) {
                 JOptionPane.showMessageDialog(null, "Película actualizada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                 JOptionPane.showMessageDialog(null, "No se encontró la película para actualizar (ID: " + id + ")", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar la película: " + ex.getMessage());
        }
         
     }
     
     
     public void bajaLogicaPelicula (int id) {
         String sql = "UPDATE pelicula SET enCartelera = 0 WHERE idPelicula = ?";
         try (PreparedStatement ps = con.prepareStatement(sql)){
             ps.setInt(1, id);
             int filasAfectadas = ps.executeUpdate();
             
             if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "La película ya NO está en cartelera", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                 JOptionPane.showMessageDialog(null, "No se encontró la película con ID: " + id, "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
             
         }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar la película: " + ex.getMessage());
        }
     }
     public void AltaLogicaPelicula (int id) {
         String sql = "UPDATE pelicula SET enCartelera = 1 WHERE idPelicula = ?";
         try (PreparedStatement ps = con.prepareStatement(sql)){
             ps.setInt(1, id);
             int filasAfectadas = ps.executeUpdate();
             
             if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "La película ya NO está en cartelera", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                 JOptionPane.showMessageDialog(null, "No se encontró la película con ID: " + id, "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
             
         }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar la película: " + ex.getMessage());
        }
     }
     
     
     public void eliminarPelicula (int id) {
         String sql = "DELETE FROM pelicula WHERE idPelicula = ?";
         
          try (PreparedStatement ps = con.prepareStatement(sql)){
              ps.setInt(1, id);
              int filasAfectadas = ps.executeUpdate();
              if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "La película se eliminó exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                 JOptionPane.showMessageDialog(null, "No se encontró la película para eliminar (ID: " + id + ")", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
          } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar la película: " + ex.getMessage());
        }
     }
     
     
}
