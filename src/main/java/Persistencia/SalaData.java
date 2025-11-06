/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Modelo.Conexion;
import Modelo.Sala;
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
 * @author FRANCO
 */
public class SalaData {

    private Connection con = null;

    public SalaData() {
        this.con = Conexion.buscarConexion();
    }

    public boolean insertarSala(Sala sala) throws SQLException {

        String sql = "INSERT INTO sala (nroSala, apta3d, capacidad, estado) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, sala.getNroSala());
            ps.setBoolean(2, sala.isApta3d());
            ps.setInt(3, sala.getCapacidad());
            ps.setBoolean(4, sala.isEstado());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {

                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        sala.setCodSala(rs.getInt(1));
                    }
                }
                JOptionPane.showMessageDialog(null, "Sala guardada exitosamente. CodSala: " + sala.getCodSala(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
            return false;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar sala: " + ex.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public Sala buscarSala(int codSala) {
        String sql = "SELECT codSala, nroSala, apta3d, capacidad, estado FROM sala WHERE codSala = ?";
        Sala sala = null;
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, codSala);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    sala = new Sala();
                    sala.setCodSala(rs.getInt("codSala"));
                    sala.setNroSala(rs.getInt("nroSala"));
                    sala.setApta3d(rs.getBoolean("apta3d"));
                    sala.setCapacidad(rs.getInt("capacidad"));
                    sala.setEstado(rs.getBoolean("estado"));
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar sala por CodSala: " + ex.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
        }
        return sala;
    }

    public boolean actualizarSala(int codSala, int nroSala, boolean apta3D, int capacidad, boolean estado) {
     
        String sql = "UPDATE sala SET nroSala = ?, apta3d = ?, capacidad = ?, estado = ? WHERE codSala = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, nroSala);
            ps.setBoolean(2, apta3D);
            ps.setInt(3, capacidad);
            ps.setBoolean(4, estado);
            ps.setInt(5, codSala); 

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar sala (CodSala: " + codSala + "): " + ex.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean cambiarEstadoSala(int codSala, boolean nuevoEstado) { 
        String sql = "UPDATE sala SET estado = ? WHERE codSala = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setBoolean(1, nuevoEstado);
            ps.setInt(2, codSala);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cambiar estado de sala (CodSala: " + codSala + "): " + ex.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean eliminarSala(int codSala) {
        String sql = "DELETE FROM sala WHERE codSala = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, codSala);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Error al eliminar sala (CodSala: " + codSala + "): " + ex.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public Sala buscarSalaPorNroSala(int nroSala) {
        String sql = "SELECT codSala, nroSala, apta3d, capacidad, estado FROM sala WHERE nroSala = ?";
        Sala sala = null;
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, nroSala);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    sala = new Sala();
                    sala.setCodSala(rs.getInt("codSala"));
                    sala.setNroSala(rs.getInt("nroSala"));
                    sala.setApta3d(rs.getBoolean("apta3d"));
                    sala.setCapacidad(rs.getInt("capacidad"));
                    sala.setEstado(rs.getBoolean("estado"));
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar sala por NroSala: " + ex.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
        }
        return sala;
    }

    public List<Sala> listarSalas() {
        String sql = "SELECT codSala, nroSala, apta3d, capacidad, estado FROM sala";
        List<Sala> salas = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Sala sala = new Sala();
                sala.setCodSala(rs.getInt("codSala"));
                sala.setNroSala(rs.getInt("nroSala"));
                sala.setApta3d(rs.getBoolean("apta3d"));
                sala.setCapacidad(rs.getInt("capacidad"));
                sala.setEstado(rs.getBoolean("estado"));
                salas.add(sala);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al listar salas: " + ex.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
        }
        return salas;
    }
    public List<Sala> listarSalas() {
        List<Sala> salas = new ArrayList<>();
        String sql = "SELECT * FROM sala";
    
    try (PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            Sala s = new Sala();
            s.setCodSala(rs.getInt("codSala"));
            s.setNroSala(rs.getInt("nroSala"));
            s.setCapacidad(rs.getInt("capacidad"));
            s.setEstado(rs.getBoolean("estado"));
            salas.add(s);
        }

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error al listar salas: " + ex.getMessage());
    }

    return salas;
}
}
