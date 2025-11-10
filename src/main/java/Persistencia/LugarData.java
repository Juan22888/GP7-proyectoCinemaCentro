/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Modelo.Conexion;
import Modelo.Funcion;
import Modelo.Lugar;
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
 * @author feerl
 */
public class LugarData {

    private Connection con = null;
    private FuncionData funData;

    public LugarData() {
        this.con = Conexion.buscarConexion();

    }

    // Setter para inyectar la dependencia
    public void setFuncionData(FuncionData funcionData) {
        this.funData = funcionData;
    }
    // ...

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
                    lugar.setFila(rs.getString("fila").charAt(0));
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

    public boolean actualizarLugar(int codLugar, boolean nuevoEstado) throws SQLException {

        String sql = "UPDATE lugar SET estado = ? WHERE codLugar = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setBoolean(1, nuevoEstado);
            ps.setInt(2, codLugar);

            int filasAfectadas = ps.executeUpdate();

            return filasAfectadas > 0;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Lugar para actualizar estado: " + ex.getMessage());

        }
        return false;

    }

    public boolean eliminarLugar(int codLugar) {
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

    /*public List<Lugar> obtenerLugaresDisponiblesPorFuncion(int codFuncion) {
        String sql = "SELECT codLugar, fila, numero FROM lugar WHERE codFuncion = ? AND estado = 0";
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
                        lugar.setFila(rs.getString("fila").charAt(0));
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
    }*/
    public List<Lugar> obtenerLugaresPorFuncion(int codFuncion) {
        String sql = "SELECT codLugar, fila, numero FROM lugar WHERE codFuncion = ?";
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
                        lugar.setFila(rs.getString("fila").charAt(0));
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

    public void crearLugaresParaFuncion(int codFuncion, int cantidad) throws SQLException {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad de asientos debe ser mayor que cero.");
        }

        // 🔹 Buscar la función y su sala
        FuncionData funcionData = new FuncionData();
        Funcion funcion = funcionData.buscarFuncion(codFuncion);

        if (funcion == null) {
            throw new IllegalArgumentException("No se encontró la función con el código especificado.");
        }

        Sala sala = funcion.getSalaFuncion();
        
        if (sala == null) {
            throw new IllegalArgumentException("No se encontró la sala asociada a la función.");
        }

        int capacidadMaxima = sala.getCapacidad();

        // 🔹 Contar lugares ya existentes
        List<Lugar> lugaresExistentes = obtenerLugaresPorFuncion(codFuncion);
        int cantidadActual = lugaresExistentes.size();

        if (cantidadActual >= capacidadMaxima) {
            throw new IllegalArgumentException("La función ya tiene todos los lugares creados.");
        }

        if (cantidadActual + cantidad > capacidadMaxima) {
            int disponibles = capacidadMaxima - cantidadActual;
            throw new IllegalArgumentException("No se pueden crear " + cantidad
                    + " lugares. Solo quedan disponibles " + disponibles + ".");
        }

        // 🔹 Generar lugares (respetando estructura por filas)
        String sql = "INSERT INTO lugar (fila, numero, estado, codFuncion) VALUES (?,?,?,?)";
        final int asientosPorFila = 20;
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(sql);
            char filaActual = 'A';

            // Avanzar las filas en función de los lugares ya existentes
            int filasExistentes = cantidadActual / asientosPorFila;
            filaActual += filasExistentes;

            int filasNuevas = (int) Math.ceil((double) cantidad / asientosPorFila);
            int lugaresRestantes = cantidad;

            for (int i = 0; i < filasNuevas; i++) {
                for (int j = 1; j <= asientosPorFila && lugaresRestantes > 0; j++) {
                    ps.setString(1, String.valueOf(filaActual));
                    ps.setInt(2, j);
                    ps.setInt(3, 0); // 0 = libre
                    ps.setInt(4, codFuncion);
                    ps.addBatch();
                    lugaresRestantes--;
                }
                filaActual++;
            }

            ps.executeBatch();
            ps.close();

        } catch (SQLException e) {
            throw new SQLException("Error al generar asientos en lote (batch): " + e.getMessage());
        }
    }

    public List listarLugares() throws SQLException {
        List<Lugar> lugares = new ArrayList<>();
        String sql = "SELECT * FROM lugar";

        try (PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Funcion f = new Funcion();
                Lugar l = new Lugar();
                f = funData.buscarFuncion(rs.getInt("codFuncion"));
                l.setCodLugar(rs.getInt("codLugar"));
                l.setFila(rs.getString("fila").charAt(0));
                l.setNumero(rs.getInt("numero"));
                l.setEstado(rs.getBoolean("estado"));
                l.setFuncion(f);
                lugares.add(l);
            }

        } catch (SQLException ex) {
            throw new SQLException("Error al listar peliculas " + ex);
        }

        return lugares;
    }

}
