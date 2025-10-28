/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Modelo.Conexion;
import Modelo.Funcion;
import Modelo.Pelicula;
import Modelo.Sala;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author FRANCO
 */
public class FuncionData {

    private Connection con = null;
    PeliculaData peliculaData;
    SalaData salaData;
    LugarData lugarData;

    public FuncionData() {
        this.con = Conexion.buscarConexion();
        this.peliculaData = new PeliculaData();
        this.salaData = new SalaData();
        this.lugarData = new LugarData();
    }

    public boolean insertarFuncion(Funcion f) throws SQLException, NullPointerException, RuntimeException {

        String sql = "INSERT INTO funcion (codPelicula, idioma, es3d, subtitulada, horaInicio, horaFin,codSala,precioLugar) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        
        
        try {
            
             Sala sala = salaData.buscarSala(f.getSalaFuncion().getCodSala());
             Pelicula pelicula = peliculaData.buscarPelicula(f.getPelicula().getCodPelicula());
             
            if (pelicula==null) {
                throw new NullPointerException("No se encontró la pelicula!");
            }

            if (sala == null) {
                throw new NullPointerException("No se encontró la sala!");
            }

            if (sala.isEstado() == false) {
                throw new RuntimeException("Error: La sala " + f.getSalaFuncion().getNroSala() + " esta inactiva");
            }

            if (pelicula.isEnCartelera()==false) {
                throw new RuntimeException("Error: La pelicula " + f.getPelicula().getTitulo() + " no esta en cartelera");
            }

        } catch (SQLException e) {
            throw new SQLException("Error de Base de Datos al validar los metodos " + e);
        }

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, f.getPelicula().getCodPelicula());
            ps.setString(2, f.getIdioma());
            ps.setBoolean(3, f.isEs3d());
            ps.setBoolean(4, f.isSubtitulada());
            ps.setTime(5, java.sql.Time.valueOf(f.getHoraInicio()));
            ps.setTime(6, java.sql.Time.valueOf(f.getHoraFin()));
            ps.setInt(7,f.getSalaFuncion().getCodSala());
            ps.setDouble(8, f.getPrecioLugar());
            
            int filasAfectadas = ps.executeUpdate();
            
            Funcion funcion = this.buscarFuncion(f.getCodFuncion());
            lugarData.crearLugaresParaFuncion(funcion.getCodFuncion(), f.getSalaFuncion().getCapacidad());
            if (filasAfectadas > 0) {      
                return true;
            }

        } catch (SQLException ex) {
            throw new SQLException("Error al guardar la funcion! " + ex);
        }
        return false;
    }

    public Funcion buscarFuncion(int id) throws SQLException {
        String sql = "SELECT * FROM funcion WHERE codFuncion=?";
        Funcion funcion = null;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                funcion = new Funcion();
                Pelicula pelicula = null;
                Sala sala = null;
                funcion.setCodFuncion(rs.getInt("codFuncion"));
                pelicula = peliculaData.buscarPelicula(rs.getInt("codPelicula"));
                funcion.setPelicula(pelicula);
                funcion.setIdioma(rs.getString("idioma"));
                funcion.setEs3d(rs.getBoolean("es3d"));
                funcion.setSubtitulada(rs.getBoolean("subtitulada"));
                funcion.setHoraInicio(rs.getTime("horaInicio").toLocalTime());
                funcion.setHoraFin(rs.getTime("horaFin").toLocalTime());
                sala = salaData.buscarSala(funcion.getSalaFuncion().getCodSala());
                funcion.setSalaFuncion(sala);
                funcion.setPrecioLugar(rs.getDouble("precioLugar"));
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            throw new SQLException("Error al buscar la funcion! + " + ex);
        }
        return funcion;
    }

    public boolean actualizarFuncion(int id, String columna, Object dato) throws Exception {
        if (!columna.equals("idioma") && !columna.equals("es3d")
                && !columna.equals("subtitulada") && !columna.equals("horaInicio")
                && !columna.equals("horaFin") && !columna.equals("codSala")
                && !columna.equals("precioLugar") && !columna.equals("codPelicula")) {

            throw new IllegalArgumentException("Columna de actualización no permitida: " + columna);
        }

        String sql = "UPDATE funcion SET " + columna + "=? WHERE codFuncion = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            // Determina el tipo de dato para establecer el parámetro
            if (dato instanceof String) {
                ps.setString(1, (String) dato);
            } else if (dato instanceof Boolean) {
                ps.setBoolean(1, (boolean) dato);
            } else if (dato instanceof java.sql.Time) {
                // Conversión de LocalDate a java.sql.Date
                ps.setTime(1, (java.sql.Time) dato);
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
            throw new SQLException("Error al modificar la funcion! " + ex);
        }

    }

    public boolean eliminarFuncion(int id) throws SQLException {
        String sql = "DELETE FROM funcion WHERE codFuncion = ?";
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
            throw new SQLException("Error al eliminar la funcion" + ex);
        }
    }

}
