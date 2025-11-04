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

    public boolean insertarSala(Sala s) throws SQLException {

        String sql = "INSERT INTO sala (nroSala, apta3d, capacidad, estado) "
                + "VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, s.getNroSala());
            ps.setBoolean(2, s.isApta3d());
            ps.setInt(3, s.getCapacidad());
            ps.setBoolean(4, s.isEstado());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                return true;
            }

        } catch (SQLException ex) {
            throw new SQLException("Error al guardar la sala! " + ex);
        }
        return false;
    }

    public Sala buscarSala(int id) throws SQLException {
        String sql = "SELECT * FROM sala WHERE codSala=?";
        Sala sala = null;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                sala = new Sala();
                sala.setCodSala(rs.getInt("codSala"));
                sala.setNroSala(rs.getInt("nroSala"));
                sala.setApta3d(rs.getBoolean("apta3d"));
                sala.setCapacidad(rs.getInt("capacidad"));
                sala.setEstado(rs.getBoolean("estado"));
              
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            throw new SQLException("Error al buscar la funcion! + " + ex);
        }
        return sala;
    }

    public boolean actualizarSala(int id, String columna, Object dato) throws Exception {
        if (!columna.equals("nroSala") && !columna.equals("apta3d")
                && !columna.equals("capacidad") && !columna.equals("estado")) {

            throw new IllegalArgumentException("Columna de actualización no permitida: " + columna);
        }

        String sql = "UPDATE sala SET " + columna + "=? WHERE codSala = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            // Determina el tipo de dato para establecer el parámetro
            if (dato instanceof String) {
                ps.setString(1, (String) dato);
            } else if (dato instanceof Boolean) {
                ps.setBoolean(1, (boolean) dato);
            } else if (dato instanceof Integer) {
                
                ps.setInt(1, (Integer) dato);
            } else {
                // Asume que otros tipos, como int o float, se pueden manejar aquí si son necesarios
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
            throw new SQLException("Error al modificar la sala! " + ex);
        }

    }
    
    
     public boolean bajaLogicaSala(int id) throws SQLException {
        String sql = "UPDATE sala SET estado = 0 WHERE codSala = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            throw new SQLException("Error al dar de baja la sala" + ex);
        }
    }

    public boolean altaLogicaSala(int id) throws SQLException {
        String sql = "UPDATE sala SET estado = 1 WHERE codSala = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            throw new SQLException("Error al dar de alta la sala " + ex);
        }
    }

    public boolean eliminarSala(int id) throws SQLException {
        String sql = "DELETE FROM sala WHERE codSala = ?";
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
            throw new SQLException("Error al eliminar la sala" + ex);
        }
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


