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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Emiliano
 */
public class CompradorData {

    private Connection con = null;

    public CompradorData() {
        this.con = Conexion.buscarConexion();
    }

    private void validarComprador(Comprador c) throws IllegalArgumentException {
        if (c == null) {
            throw new IllegalArgumentException("El objeto Comprador no puede ser nulo.");
        }
        if (c.getDni() <= 0) {

            throw new IllegalArgumentException("El DNI debe ser un número positivo.");
        }
        if (c.getNombre() == null || c.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (c.getNombre().length() > 20) {
            throw new IllegalArgumentException("El nombre no puede tener más de 20 caracteres.");
        }
        if (c.getFechaNacimiento() == null) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser nula.");
        }
        if (c.getFechaNacimiento().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser futura.");
        }
        if (c.getPassword() == null || c.getPassword().length() < 8) {
          
            throw new IllegalArgumentException("La contraseña no puede ser nula y debe tener al menos 8 caracteres.");
        }
    }

    public boolean insertarComprador(Comprador c) throws SQLException {

        try {
            validarComprador(c);
        } catch (IllegalArgumentException ex) {
            throw new SQLException("Datos de comprador inválidos: " + ex.getMessage());
        }

        String sql = "INSERT INTO comprador (dni, nombre, fechaNac, password, estado)" + "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, c.getDni());
            ps.setString(2, c.getNombre());
            ps.setDate(3, Date.valueOf(c.getFechaNacimiento()));
            ps.setString(4, c.getPassword());
            ps.setBoolean(5, c.isEstado());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException ex) {
            throw new SQLException("No se pudo guardar el comprador: " + ex.getMessage());
        }

    }

    public Comprador buscarComprador(int id) throws SQLException {
        String sql = "SELECT * FROM comprador WHERE codComprador = ?";
        Comprador comprador = null;
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                comprador = new Comprador();
                comprador.setCodComprador(rs.getInt("codComprador"));
                comprador.setDni(rs.getInt("dni"));
                comprador.setNombre(rs.getString("nombre"));
                comprador.setFechaNacimiento(rs.getDate("fechaNac").toLocalDate());
                comprador.setPassword(rs.getString("password"));
                comprador.setEstado(rs.getBoolean("estado"));
            }
            rs.close();
        } catch (SQLException ex) {
            throw new SQLException("no se pudo encontrar el comprador: " + ex.getMessage());
        }
        return comprador;
    }

    public boolean actualizarComprador(Comprador c) throws Exception {

        try {
            validarComprador(c); // Reutilizamos la validación
        } catch (IllegalArgumentException ex) {
            throw new SQLException("Datos de comprador inválidos: " + ex.getMessage());
        }

        String sql = "UPDATE comprador SET dni = ?, nombre = ?, fechaNac = ?, password = ?, estado = ? WHERE codComprador = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, c.getDni());
            ps.setString(2, c.getNombre());
            ps.setDate(3, Date.valueOf(c.getFechaNacimiento()));
            ps.setString(4, c.getPassword());
            ps.setBoolean(5, c.isEstado());
            ps.setInt(6, c.getCodComprador());
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException ex) {
            throw new SQLException("no se pudo actualizar el comprador: " + ex.getMessage());
        }
    }

    public boolean bajaLogicaComprador(int id) throws SQLException {
        String sql = "UPDATE comprador SET estado = 0 WHERE codComprador=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException ex) {
            throw new SQLException("no se pudo dar de baja el comprador: " + ex.getMessage());
        }
    }

    public boolean altaLogicaComprador(int id) throws SQLException {
        String sql = "UPDATE comprador SET estado = 1 WHERE codComprador =?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException ex) {
            throw new SQLException("No se pudo dar de alta al comprador =?");

        }
    }

    public boolean eliminarComprador(int id) throws SQLException {
        String sql = "DELETE FROM comprador WHERE codComprador =?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException ex) {
            throw new SQLException("Error al eliminar el comprador: " + ex.getMessage());
        }
    }

    public List<Comprador> listarCompradores() {
        String sql = "SELECT * FROM comprador";
        List<Comprador> compradores = new ArrayList<>();

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Comprador comprador = new Comprador();
                comprador.setCodComprador(rs.getInt("codComprador"));
                comprador.setDni(rs.getInt("dni"));
                comprador.setNombre(rs.getString("nombre"));
                comprador.setFechaNacimiento(rs.getDate("fechaNac").toLocalDate()); // Corregido de 'fechaNacimiento'
                comprador.setPassword(rs.getString("password")); // (Tu modelo usa getPassword() y setPassword()?
                comprador.setEstado(rs.getBoolean("estado"));
                compradores.add(comprador);
            }
            rs.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al listar compradores: " + ex.getMessage());
        }
        return compradores;
    }

    public Comprador buscarCompradorPorDni(int dni) {
        String sql = "SELECT * FROM comprador WHERE dni = ?";
        Comprador comprador = null;
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, dni);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                comprador = new Comprador();
                comprador.setCodComprador(rs.getInt("codComprador"));
                comprador.setDni(rs.getInt("dni"));
                comprador.setNombre(rs.getString("nombre"));
                comprador.setFechaNacimiento(rs.getDate("fechaNac").toLocalDate()); // Corregido de 'fechaNacimiento'
                comprador.setPassword(rs.getString("password"));
                comprador.setEstado(rs.getBoolean("estado"));
            }
            rs.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo encontrar el comprador por DNI: " + ex.getMessage());
        }
        return comprador;
    }

    public boolean eliminarCompradorPorDni(int dni) throws SQLException {
        String sql = "DELETE FROM comprador WHERE dni = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, dni);
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException ex) {
            throw new SQLException("Error al eliminar el comprador por DNI: " + ex.getMessage());
        }
    }

    public boolean actualizarCompradorPorDni(Comprador c) throws SQLException {

        try {
            validarComprador(c); // Reutilizamos la validación
        } catch (IllegalArgumentException ex) {
            throw new SQLException("Datos de comprador inválidos: " + ex.getMessage());
        }

        String sql = "UPDATE comprador SET nombre = ?, fechaNac = ?, password = ?, estado = ? WHERE dni = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getNombre());
            ps.setDate(2, Date.valueOf(c.getFechaNacimiento()));
            ps.setString(3, c.getPassword());
            ps.setBoolean(4, c.isEstado());
            ps.setInt(5, c.getDni());
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException ex) {
            throw new SQLException("no se pudo actualizar el comprador: " + ex.getMessage());
        }
    }
}
