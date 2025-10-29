/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Modelo.Conexion;
import Modelo.Funcion;
import Modelo.Pelicula;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author FRANCO
 */
public class FuncionData {
    private Connection con=null;

    public FuncionData() {
        this.con = Conexion.buscarConexion();
    }
    
      public boolean insertarFuncion(Funcion f) throws SQLException {

        String sql = "INSERT INTO funcion (codPelicula, idioma, es3d, subtitulada, horaInicio, horaFin, lugaresDisponibles,codSala,precioLugar) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, f.getPelicula().getCodPelicula());
            ps.setString(2, f.getIdioma());
            ps.setBoolean(3, f.isEs3d());
            ps.setBoolean(4, f.isSubtitulada());
            ps.setTime(5,java.sql.Time.valueOf(f.getHoraInicio()));
            ps.setTime(6, java.sql.Time.valueOf(f.getHoraFin()));
 //           ps.

//            ps.setBoolean(7, p.isEnCartelera());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                return true;
            }

        } catch (SQLException ex) {
            throw new SQLException("No se pudo guardar la pelicula! " + ex);
        }
        return false;
    }

    public Pelicula buscarPelicula(int id) throws SQLException {
        String sql = "SELECT * FROM pelicula WHERE codPelicula=?";
        Pelicula pelicula = null;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                pelicula = new Pelicula();
                pelicula.setCodPelicula(rs.getInt("codPelicula"));
                pelicula.setTitulo(rs.getString("titulo"));
                pelicula.setDirector(rs.getString("director"));
                pelicula.setActores(rs.getString("actores"));
                pelicula.setOrigen(rs.getString("origen"));
                pelicula.setGenero(rs.getString("genero"));
                pelicula.setEstreno(rs.getDate("estreno").toLocalDate());
                pelicula.setEnCartelera(rs.getBoolean("enCartelera"));
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            throw new SQLException("No se encontro la pelicula! + " + ex);
        }
        return pelicula;
    }

    public boolean actualizarPelicula(int id, String columna, Object dato) throws Exception {
        if (!columna.equals("titulo") && !columna.equals("director")
                && !columna.equals("actores") && !columna.equals("origen")
                && !columna.equals("genero") && !columna.equals("estreno")
                && !columna.equals("enCartelera")) {

            throw new IllegalArgumentException("Columna de actualización no permitida: " + columna);
        }

        String sql = "UPDATE pelicula SET " + columna + "=? WHERE codPelicula = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            // Determina el tipo de dato para establecer el parámetro
            if (dato instanceof String) {
                ps.setString(1, (String) dato);
            } else if (dato instanceof Boolean) {
                ps.setBoolean(1, (boolean) dato);
            } else if (dato instanceof java.sql.Date) {
                // Conversión de LocalDate a java.sql.Date
                ps.setDate(1, (java.sql.Date) dato);
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
            throw new SQLException("No se pudo modificar la pelicula! " + ex);
        }

    }

    public boolean bajaLogicaPelicula(int id) throws SQLException {
        String sql = "UPDATE pelicula SET enCartelera = 0 WHERE codPelicula = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            throw new SQLException("No se pudo dar de baja la pelicula " + ex);
        }
    }

    public boolean altaLogicaPelicula(int id) throws SQLException {
        String sql = "UPDATE pelicula SET enCartelera = 1 WHERE codPelicula = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            throw new SQLException("No se pudo dar dee alta la pelicula " + ex);
        }
    }

    public boolean eliminarPelicula(int id) throws SQLException {
        String sql = "DELETE FROM pelicula WHERE codPelicula = ?";
        try{
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
            throw new SQLException("No se pudo eliminar la pelicula " + ex);
        }
    }
    
    
    
}
