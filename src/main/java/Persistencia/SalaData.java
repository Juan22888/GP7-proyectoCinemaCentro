
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

//Para crear las salas del cine
public class SalaData {

    private Connection con = null;

    public SalaData() {
        this.con = Conexion.buscarConexion();
    }
    
    
    //Validador para mantener coherencia con lo que pide el integrador
    private void validarCapacidad(int capacidad) throws IllegalArgumentException {

        if (capacidad < 170 || capacidad > 230) {
            throw new IllegalArgumentException("La capacidad de la sala debe estar entre 170 y 230 butacas.");
        }
    }
    //otro validador, para uqe no rompa toda la base de datos
    private void validarSala(Sala sala) throws IllegalArgumentException {
        if (sala == null) {
            throw new NullPointerException("El objeto Sala no puede ser nulo");

        }

        if (sala.getNroSala() <= 0) {
            throw new IllegalArgumentException("El numero de sala debe de ser un valor positivo");
        }
        validarCapacidad(sala.getCapacidad());
    }
    //Usado por la vista VistaNuevaSala
    public boolean insertarSala(Sala sala) throws SQLException {

        String sql = "INSERT INTO sala (nroSala, apta3d, capacidad, estado) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            validarSala(sala);
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
          
                return true;
            }
            if (buscarSalaPorNroSala(sala.getNroSala()) != null) { 
             JOptionPane.showMessageDialog(null, "Error: Ya existe una sala con el número " + sala.getNroSala(), "Error", JOptionPane.ERROR_MESSAGE);
             return false;
        }
            return false;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar sala: " + ex.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
// FuncionData lo usa para saber en que sala se da una funcion
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
    //se usa en VistaSala para modifcar los datos de la tabla
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
    //Usado para alta y baja logica en VistaSala
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
    //Busca la sala pero por su numero, no por su codigo, porque es el que el usuario ve. 
    //Usado en VistaSala
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
    //Trae todas las salas. Usado en VistaSala para llenar la tabla cuando se abre
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
    //Duplicado de buscarSalaPorNroSala, no toco porque como dice el dicho: Si no esta roto, no toques..
    public Sala buscarSalaPorNro(int nroSala) {
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
            JOptionPane.showMessageDialog(null, "Error al buscar sala por nroSala: " + ex.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
        }
        return sala;
    }

}
