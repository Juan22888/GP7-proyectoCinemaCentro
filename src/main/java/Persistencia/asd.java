package Persistencia;

import Modelo.Conexion;
import Modelo.Funcion;
import Modelo.Pelicula;
import Modelo.Sala; // Se necesita la clase Sala
import Modelo.Lugar; // Se necesita la clase Lugar
import java.sql.Connection;
import java.sql.Time;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author FRANCO
 */
public class FuncionData {
    private Connection con = null;
    private PeliculaData peliData; // Dependencia para buscar la Pelicula
    private SalaData salaData; // Dependencia para buscar la Sala

    public FuncionData() {
        this.con = Conexion.buscarConexion();
        this.peliData = new PeliculaData(); // Asume que existe y está inicializada
        this.salaData = new SalaData(); // Asume que existe y está inicializada
    }
    
    /**
     * Busca una Funcion por su código (clave primaria).
     * @param id El código de la función a buscar.
     * @return El objeto Funcion encontrado o null si no existe.
     */
    public Funcion buscarFuncion(int id) {
        // Se asume que en la tabla 'funcion' las FKs son 'codPelicula' y 'codSala'
        String sql = "SELECT codFuncion, codPelicula, idioma, es3d, subtitulada, horaInicio, horaFin, codSala, precioLugar "
                   + "FROM funcion WHERE codFuncion = ?";
        Funcion funcion = null;

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    funcion = new Funcion();
                    funcion.setCodFuncion(rs.getInt("codFuncion"));
                    funcion.setIdioma(rs.getString("idioma"));
                    funcion.setEs3d(rs.getBoolean("es3d"));
                    funcion.setSubtitulada(rs.getBoolean("subtitulada"));
                    
                    // Conversión de java.sql.Time a LocalTime
                    funcion.setHoraInicio(rs.getTime("horaInicio").toLocalTime());
                    funcion.setHoraFin(rs.getTime("horaFin").toLocalTime());
                    
                    funcion.setPrecioLugar(rs.getDouble("precioLugar"));
                    
                    // Recuperar objetos dependientes (Pelicula y Sala)
                    int codPelicula = rs.getInt("codPelicula");
                    Pelicula pelicula = peliData.buscarPelicula(codPelicula); // Asume que PeliculaData.buscarPelicula(id) existe
                    funcion.setPelicula(pelicula);
                    
                    int codSala = rs.getInt("codSala");
                    Sala sala = salaData.buscarSala(codSala); // Asume que SalaData.buscarSala(id) existe
                    funcion.setSalaFuncion(sala);
                    
                    // NOTA: La lista de Lugares Disponibles NO se carga aquí. 
                    // Esto se hace típicamente en LugarData o con un método específico.
                    funcion.setLugaresDisponibles(null); // O new ArrayList<>() si prefieres.

                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar la Función: " + ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar las dependencias (Pelicula/Sala) de la Función: " + ex.getMessage());
        }
        return funcion;
    }


    // --- El resto de los métodos de FuncionData (insertarFuncion, actualizar, etc.) ---

    // El método insertarFuncion, y los de Pelicula que estaban mal ubicados, deberían
    // permanecer en su ubicación original o ser ajustados según las dependencias.

    // A continuación, se deja el método insertarFuncion para mantener la estructura,
    // asumiendo que ya se corrigieron los errores de las sentencias SQL.
    
    public boolean insertarFuncion(Funcion f) throws SQLException {
        // Se asume que 'lugaresDisponibles' no se guarda directamente como un campo en la tabla,
        // ya que es una lista de objetos Lugar. Si el campo en la BD guarda un número,
        // la sentencia SQL debe reflejar eso (por ejemplo, f.getLugaresDisponibles().size()).
        // Si no se usa el campo, se elimina de la sentencia SQL. Se quita 'lugaresDisponibles' de la sentencia.
        
        String sql = "INSERT INTO funcion (codPelicula, idioma, es3d, subtitulada, horaInicio, horaFin, codSala, precioLugar) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, f.getPelicula().getCodPelicula());
            ps.setString(2, f.getIdioma());
            ps.setBoolean(3, f.isEs3d());
            ps.setBoolean(4, f.isSubtitulada());
            ps.setTime(5, java.sql.Time.valueOf(f.getHoraInicio()));
            ps.setTime(6, java.sql.Time.valueOf(f.getHoraFin()));
            // ps.setObject(7, f.getLugaresDisponibles()); <-- Este campo se eliminó de la sentencia SQL de la tabla 'funcion'
            ps.setInt(7, f.getSalaFuncion().getNroSala()); // Asume que codSala es el NroSala
            ps.setDouble(8, f.getPrecioLugar());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                return true;
            }

        } catch (SQLException ex) {
            // Se debe usar la excepción real y no una genérica de Pelicula
            throw new SQLException("No se pudo guardar la función! " + ex);
        }
        return false;
    }
    
    // NOTA IMPORTANTE: Los métodos 'buscarPelicula', 'actualizarPelicula', 
    // 'bajaLogicaPelicula', 'altaLogicaPelicula', y 'eliminarPelicula' deben 
    // estar en la clase PeliculaData, NO en FuncionData. Han sido eliminados 
    // de esta respuesta para no duplicar código y corregir la estructura.
}