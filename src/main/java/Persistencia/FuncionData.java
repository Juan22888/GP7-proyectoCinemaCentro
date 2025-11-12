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
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author FRANCO
 */
public class FuncionData {

    private Connection con = null;
    PeliculaData peliculaData;
    SalaData salaData;

    public FuncionData() {
        this.con = Conexion.buscarConexion();
        this.peliculaData = new PeliculaData();
        this.salaData = new SalaData();

    }

    public boolean validarFuncion(Funcion f) throws SQLException {
        if (f == null) {
            throw new NullPointerException("La función no puede ser nula.");
        }

        if (f.getPelicula() == null) {
            throw new NullPointerException("Debe seleccionar una película.");
        }

        if (f.getSalaFuncion() == null) {
            throw new NullPointerException("Debe seleccionar una sala.");
        }

        if (f.getIdioma() == null || f.getIdioma().trim().isEmpty()) {
            throw new IllegalArgumentException("Debe ingresar un idioma.");
        }

        if (f.getHoraInicio() == null || f.getHoraFin() == null) {
            throw new IllegalArgumentException("Debe ingresar hora de inicio y hora de fin.");
        }

        if (!f.getHoraFin().isAfter(f.getHoraInicio())) {
            throw new IllegalArgumentException("La hora de fin debe ser posterior a la de inicio.");
        }

        if (f.getFecha() == null || f.getFecha().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Debe ingresar una fecha valida.");
        }

        if (f.getPrecioLugar() <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor que 0.");
        }

        Sala sala = salaData.buscarSala(f.getSalaFuncion().getCodSala());
        Pelicula pelicula = peliculaData.buscarPelicula(f.getPelicula().getCodPelicula());

        if (pelicula == null) {
            throw new NullPointerException("No se encontró la película en la base de datos.");
        }

        if (sala == null) {
            throw new NullPointerException("No se encontró la sala en la base de datos.");
        }

        if (!sala.isEstado()) {
            throw new RuntimeException("La sala " + sala.getNroSala() + " está inactiva.");
        }

        if (!pelicula.isEnCartelera()) {
            throw new RuntimeException("La película " + pelicula.getTitulo() + " no está en cartelera.");
        }

        if (f.isEs3d() && !sala.isApta3d()) {
            throw new RuntimeException("La sala " + sala.getNroSala() + " no es apta para proyecciones 3D.");
        }

        List<Funcion> listaFunciones = this.listarFunciones();

        for (Funcion existente : listaFunciones) {
           
            if (existente.isEstado()
                    && existente.getSalaFuncion().getCodSala() == f.getSalaFuncion().getCodSala()
                    && existente.getCodFuncion() != f.getCodFuncion()) {

                if (existente.getFecha().isEqual(f.getFecha())) {

                    LocalTime inicioExistente = existente.getHoraInicio();
                    LocalTime finExistente = existente.getHoraFin();
                    LocalTime inicioNueva = f.getHoraInicio();
                    LocalTime finNueva = f.getHoraFin();

                    boolean seSolapa
                            = (inicioNueva.isBefore(finExistente) && finNueva.isAfter(inicioExistente));

                    if (seSolapa) {
                        throw new RuntimeException(
                                "Ya existe una función activa en ese horario para la sala "
                                + existente.getSalaFuncion().getNroSala() + " en la fecha "
                                + existente.getFecha() + ".");
                    }
                }
            }
        }

        return true;
    }

    public boolean insertarFuncion(Funcion f) throws SQLException, NullPointerException, RuntimeException {

        try {
            validarFuncion(f);
        } catch (IllegalArgumentException ex) {
            throw new SQLException("Datos de funcion inválidos: " + ex.getMessage());
        }

        String sql = "INSERT INTO funcion (codPelicula, idioma, es3d, subtitulada, horaInicio, horaFin,fechaFuncion,codSala,precioLugar,estado) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {

            Sala sala = salaData.buscarSala(f.getSalaFuncion().getCodSala());
            Pelicula pelicula = peliculaData.buscarPelicula(f.getPelicula().getCodPelicula());

            if (pelicula == null) {
                throw new NullPointerException("No se encontró la pelicula!");
            }

            if (sala == null) {
                throw new NullPointerException("No se encontró la sala!");
            }

            if (sala.isEstado() == false) {
                throw new RuntimeException("Error: La sala " + f.getSalaFuncion().getNroSala() + " esta inactiva");
            }

            if (pelicula.isEnCartelera() == false) {
                throw new RuntimeException("Error: La pelicula " + f.getPelicula().getTitulo() + " no esta en cartelera");
            }

            try (PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setInt(1, f.getPelicula().getCodPelicula());
                ps.setString(2, f.getIdioma());
                ps.setBoolean(3, f.isEs3d());
                ps.setBoolean(4, f.isSubtitulada());
                ps.setTime(5, java.sql.Time.valueOf(f.getHoraInicio()));
                ps.setTime(6, java.sql.Time.valueOf(f.getHoraFin()));
                ps.setDate(7, java.sql.Date.valueOf(f.getFecha()));
                ps.setInt(8, sala.getCodSala());
                ps.setDouble(9, f.getPrecioLugar());
                ps.setBoolean(10, f.isEstado());

                int filasAfectadas = ps.executeUpdate();

                if (filasAfectadas > 0) {
                    return true;
                }

            } catch (SQLException ex) {
                throw new SQLException("Error al guardar la funcion! " + ex);
            }
        } catch (SQLException e) {
            throw new SQLException("Error de Base de Datos al validar los metodos " + e);
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
                funcion.setFecha(rs.getDate("fechaFuncion").toLocalDate());
                sala = salaData.buscarSala(rs.getInt("codSala"));
                funcion.setSalaFuncion(sala);
                funcion.setPrecioLugar(rs.getDouble("precioLugar"));
                funcion.setEstado(rs.getBoolean("estado"));
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            throw new SQLException("Error al buscar la funcion! + " + ex);
        }
        return funcion;
    }

    public boolean actualizarFuncion(Funcion f) throws Exception {
        try {
            validarFuncion(f);
        } catch (IllegalArgumentException ex) {
            throw new SQLException("Datos de funcion inválidos: " + ex.getMessage());
        }

        String sql = "UPDATE funcion SET codPelicula = ?, idioma = ?, es3d = ?, subtitulada = ?, horaInicio = ?, horaFin = ?, fechaFuncion = ?, codSala = ?, precioLugar = ?, estado = ?  WHERE codFuncion = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, f.getPelicula().getCodPelicula());
            ps.setString(2, f.getIdioma());
            ps.setBoolean(3, f.isEs3d());
            ps.setBoolean(4, f.isSubtitulada());
            ps.setTime(5, java.sql.Time.valueOf(f.getHoraInicio()));
            ps.setTime(6, java.sql.Time.valueOf(f.getHoraFin()));
            ps.setDate(7, java.sql.Date.valueOf(f.getFecha()));
            ps.setInt(8, f.getSalaFuncion().getCodSala());
            ps.setDouble(9, f.getPrecioLugar());
            ps.setBoolean(10, f.isEstado());

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

    public List listarFunciones() throws SQLException {
        List<Funcion> funciones = new ArrayList<>();
        String sql = "SELECT * FROM funcion";

        try (PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Funcion f = new Funcion();
                Pelicula p = new Pelicula();
                Sala s = new Sala();
                f.setCodFuncion(rs.getInt("codFuncion"));
                p = peliculaData.buscarPelicula(rs.getInt("CodPelicula"));
                f.setPelicula(p);
                f.setIdioma(rs.getString("idioma"));
                f.setEs3d(rs.getBoolean("es3d"));
                f.setSubtitulada(rs.getBoolean("subtitulada"));
                f.setHoraInicio(rs.getTime("horaInicio").toLocalTime());
                f.setHoraFin(rs.getTime("horaFin").toLocalTime());
                f.setFecha(rs.getDate("fechaFuncion").toLocalDate());
                s = salaData.buscarSala(rs.getInt("codSala"));
                f.setSalaFuncion(s);
                f.setPrecioLugar(rs.getDouble("precioLugar"));
                f.setEstado(rs.getBoolean("estado"));
                funciones.add(f);
            }

        } catch (SQLException ex) {
            throw new SQLException("Error al listar funciones " + ex);
        }

        return funciones;
    }

    public List listarFuncionesPorPelicula(int codPelicula) throws SQLException {
        List<Funcion> funciones = new ArrayList<>();
        String sql = "SELECT * FROM funcion WHERE codPelicula = ? AND estado = true";

        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, codPelicula);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Funcion f = new Funcion();
                Pelicula p = new Pelicula();
                Sala s = new Sala();
                f.setCodFuncion(rs.getInt("codFuncion"));
                p = peliculaData.buscarPelicula(rs.getInt("CodPelicula"));
                f.setPelicula(p);
                f.setIdioma(rs.getString("idioma"));
                f.setEs3d(rs.getBoolean("es3d"));
                f.setSubtitulada(rs.getBoolean("subtitulada"));
                f.setHoraInicio(rs.getTime("horaInicio").toLocalTime());
                f.setHoraFin(rs.getTime("horaFin").toLocalTime());
                f.setFecha(rs.getDate("fechaFuncion").toLocalDate());
                s = salaData.buscarSala(rs.getInt("codSala"));
                f.setSalaFuncion(s);
                f.setPrecioLugar(rs.getDouble("precioLugar"));
                f.setEstado(rs.getBoolean("estado"));
                funciones.add(f);
            }

        } catch (SQLException ex) {
            throw new SQLException("Error al listar funciones " + ex);
        }

        return funciones;
    }

}
