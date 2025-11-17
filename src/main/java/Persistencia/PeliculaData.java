
package Persistencia;

import Modelo.Conexion;
import Modelo.Pelicula;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PeliculaData {

    private Connection con = null;

    public PeliculaData() {
        this.con = Conexion.buscarConexion();
    }

    private void validarPelicula(Pelicula p) throws IllegalArgumentException {
        if (p == null) {
            throw new IllegalArgumentException("El objeto Pelicula no puede ser nulo.");
        }

        String tituloLimpio = (p.getTitulo() == null) ? "" : p.getTitulo().trim();
        if (tituloLimpio.isEmpty()) {
            throw new IllegalArgumentException("El título no puede estar vacío.");
        }
        if (tituloLimpio.length() < 5) {
            throw new IllegalArgumentException("El título debe tener al menos 5 caracteres.");
        }

        String directorLimpio = (p.getDirector() == null) ? "" : p.getDirector().trim();
        if (directorLimpio.isEmpty()) {
            throw new IllegalArgumentException("El director no puede estar vacío.");
        }

        if (p.getActores() == null || p.getActores().trim().isEmpty()) {
            throw new IllegalArgumentException("Los actores no pueden estar vacíos.");
        }
        if (p.getGenero() == null || p.getGenero().trim().isEmpty()) {
            throw new IllegalArgumentException("El género no puede estar vacío.");
        }
        if (p.getEstreno() == null) {
            throw new IllegalArgumentException("La fecha de estreno no puede ser nula.");
        }
        if (p.isEnCartelera() && p.getEstreno().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La pelicula no puede estar en cartelera si su fecha de estreno es futura");
        }
        
        //Estas validaciones estan hechas para la base de datos, para que se inyecten de forma organica y no la rompan
        if (p.getDirector().length() > 20) {
            throw new IllegalArgumentException("El director no puede tener más de 20 caracteres.");
        }
        if (p.getActores().length() > 100) {
            throw new IllegalArgumentException("Los actores no pueden tener más de 100 caracteres.");
        }
        if (p.getOrigen().length() > 30) {
            throw new IllegalArgumentException("El origen no puede tener más de 30 caracteres.");
        }
        if (p.getGenero().length() > 25) {
            throw new IllegalArgumentException("El género no puede tener más de 25 caracteres.");
        }

    }
    //obviamente para crear una nueva pelicula, usada en la vista NuevaPelicula
    public boolean insertarPelicula(Pelicula p) throws SQLException {

        try {
            validarPelicula(p);
        } catch (IllegalArgumentException ex) {
            throw new SQLException("Datos de pelicula inválidos: " + ex.getMessage());
        }

        String sql = "INSERT INTO pelicula (titulo, director, actores, origen, genero, estreno, enCartelera) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        // Usamos "try-with-resources" para que el PreparedStatement se cierre solo
        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getTitulo());
            ps.setString(2, p.getDirector());
            ps.setString(3, p.getActores());
            ps.setString(4, p.getOrigen());
            ps.setString(5, p.getGenero());

            ps.setDate(6, Date.valueOf(p.getEstreno())); // Convertimos LocalDate a sql.Date

            ps.setBoolean(7, p.isEnCartelera());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                return true;
            }

        } catch (SQLException ex) {
            throw new SQLException("Error al guardar la pelicula! " + ex);
        }
        return false;
    }
    //Bue, para buscar peliculas, obvio pa. Usada en la vista FuncionData para saber que pelicula va en una funcion
    public Pelicula buscarPelicula(int id) throws SQLException {
        String sql = "SELECT * FROM pelicula WHERE codPelicula=?";
        Pelicula pelicula = null;
        
       //No usamos try-with-resources porque somos conos y nos dimos cuenta despues que era mas util,
       //igual funciona, y como dice el dicho, si funciona no se toca.
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
            throw new SQLException(" Error al buscar la pelicula! + " + ex);
        }
        return pelicula;
    }
        //... me canso de explicar, ya lo dice su nombre. Usado en VistaPelicula para cuando modificamos tabla
    public boolean actualizarPelicula(Pelicula pelicula) throws Exception {
        // Primero, validamos el ID para el update
        if (pelicula.getCodPelicula() <= 0) { // Asumiendo que tienes un getIdPelicula()
            throw new IllegalArgumentException("El ID de la película no es válido para actualizar.");
        }
        try {
            //super validacion, que no falten las validaciones. VIVAN LAS VALIDACIONES (por favor que termine este infierno de validar todo)
            validarPelicula(pelicula);
        } catch (IllegalArgumentException ex) {
            throw new SQLException("Datos de pelicula inválidos: " + ex.getMessage());
        }

        String sql = "UPDATE pelicula SET titulo=?, director=?, actores=?, origen=?, genero=?, estreno=?, enCartelera=? WHERE codPelicula = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
               //Comento porque estoy re cebado, seteamos los datos...por centesima vez :)
            ps.setString(1, pelicula.getTitulo());
            ps.setString(2, pelicula.getDirector());
            ps.setString(3, pelicula.getActores());
            ps.setString(4, pelicula.getOrigen());
            ps.setString(5, pelicula.getGenero());
            ps.setDate(6, Date.valueOf(pelicula.getEstreno()));
            ps.setBoolean(7, pelicula.isEnCartelera());
            //Para que lo tengas en cuenta, el ID va al final para el WHERE ok?
            ps.setInt(8, pelicula.getCodPelicula());

            int filasAfectadas = ps.executeUpdate();
            return (filasAfectadas > 0);

        } catch (SQLException ex) {
            throw new SQLException("Error al actualizar la pelicula: " + ex.getMessage(), ex);
        }

    }
        //para "apagar" pero no borrar, como mi cerebro que ya no da mas
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
            throw new SQLException("Error al dar de baja la pelicula " + ex);
        }
    }
        // Lo mismo que el anterior pero para "prender" como mi cerebro pero al reves (re que no pegaba) 
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
            throw new SQLException("Error al dar de alta la pelicula " + ex);
        }
    }
    // No voy a comentar nada, osea si, estoy comentando, pero.. tu me entiendes
    public boolean eliminarPelicula(int id) throws SQLException {
        String sql = "DELETE FROM pelicula WHERE codPelicula = ?";
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
            throw new SQLException("Error al eliminar la pelicula " + ex);
        }
    }
   //Trae todas las peliculas de la BD. Usado en VistaPelicula para rellenar la tabla. (No puedo hacer chistes en todos lados, me quedo sin ideas)
    public List listarPeliculas() throws SQLException {
        List<Pelicula> peliculas = new ArrayList<>();
        String sql = "SELECT * FROM pelicula";

        try (PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Pelicula p = new Pelicula();
                p.setCodPelicula(rs.getInt("codPelicula"));
                p.setTitulo(rs.getString("titulo"));
                p.setDirector(rs.getString("director"));
                p.setActores(rs.getString("actores"));
                p.setOrigen(rs.getString("origen"));
                p.setGenero(rs.getString("genero"));
                p.setEstreno(rs.getDate("estreno").toLocalDate());
                p.setEnCartelera(rs.getBoolean("enCartelera"));
                peliculas.add(p);
            }

        } catch (SQLException ex) {
            throw new SQLException("Error al listar peliculas " + ex);
        }

        return peliculas;
    }
    //Trae toda las peliculas activas, osea si isCartelera = 1 osea true osea...
    //Usado en VistaTaquilla para mostrar las pelis que se pueden poner en cartelera
    public List<Pelicula> listarPeliculasEnCartelera() throws SQLException {
        List<Pelicula> peliculas = new ArrayList<>();
        String sql = "SELECT * FROM pelicula WHERE enCartelera=1"; //<--- Es clave es 1

        try (PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Pelicula p = new Pelicula();
                p.setCodPelicula(rs.getInt("codPelicula"));
                p.setTitulo(rs.getString("titulo"));
                p.setDirector(rs.getString("director"));
                p.setActores(rs.getString("actores"));
                p.setOrigen(rs.getString("origen"));
                p.setGenero(rs.getString("genero"));
                p.setEstreno(rs.getDate("estreno").toLocalDate());
                p.setEnCartelera(rs.getBoolean("enCartelera"));
                peliculas.add(p);
            }

        } catch (SQLException ex) {
            throw new SQLException("Error al listar peliculas " + ex);
        }

        return peliculas;
    }
    
    //Trae las peliculas que no se estrenaron y no estan en cartelera, obviamente.
    //Usado en VistaInformes para..informes
    public List<Pelicula> listarProximosEstrenos() throws SQLException {
        List<Pelicula> peliculas = new ArrayList<>();
        
        String sql = "SELECT * FROM pelicula WHERE estreno > CURDATE() AND enCartelera = 0";

        try (PreparedStatement ps = con.prepareStatement(sql); 
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Pelicula p = new Pelicula();
                p.setCodPelicula(rs.getInt("codPelicula"));
                p.setTitulo(rs.getString("titulo"));
                p.setDirector(rs.getString("director"));
                p.setActores(rs.getString("actores"));
                p.setOrigen(rs.getString("origen"));
                p.setGenero(rs.getString("genero"));
                p.setEstreno(rs.getDate("estreno").toLocalDate());
                p.setEnCartelera(rs.getBoolean("enCartelera"));
                peliculas.add(p);
            }

        } catch (SQLException ex) {
            throw new SQLException("Error al listar próximos estrenos: " + ex);
        }

        return peliculas;
    }

}
