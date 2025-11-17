
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


//Lugar hace referencia a un asiento, como fila 1 nro 5, etc
//No existe por si mismo, siempre pertenece a una funcion, depende mayormente a funcionData, para que lo tengan en cuenta
public class LugarData {

    private Connection con = null;
    private FuncionData funData;

    public LugarData() {
        this.con = Conexion.buscarConexion();
        this.funData = new FuncionData();

    }

    //
   public void setFuncionData(FuncionData funcionData) {
        this.funData = funcionData;
    }


    public boolean validarLugar(Lugar lugar) throws IllegalArgumentException, SQLException {
        if (lugar == null) {
            throw new IllegalArgumentException("El lugar no puede ser nulo.");
        }
        // Para que la fila sea una letra
        if (!Character.isLetter(lugar.getFila())) {
            throw new IllegalArgumentException("La fila debe ser una letra (A, B, C...).");
        }

        if (lugar.getNumero() <= 0 || lugar.getNumero() > 20) {
            throw new IllegalArgumentException("El número de asiento debe estar entre 1 y 20.");
        }

        if (lugar.isEstado() != true && lugar.isEstado() != false) {
            throw new IllegalArgumentException("El estado del asiento debe ser válido (ocupado o libre).");
        }

        if (lugar.getFuncion() == null || lugar.getFuncion().getCodFuncion() <= 0) {
            throw new IllegalArgumentException("El lugar debe estar asociado a una función válida.");
        }

        FuncionData funcionData = new FuncionData();
        Funcion funcion = funcionData.buscarFuncion(lugar.getFuncion().getCodFuncion());
        if (funcion == null) {
            throw new IllegalArgumentException("La función asociada al lugar no existe en la base de datos.");
        }
        // para que no repita asientos
        String sql = "SELECT COUNT(*) FROM lugar WHERE fila = ? AND numero = ? AND codFuncion = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, String.valueOf(lugar.getFila()));
            ps.setInt(2, lugar.getNumero());
            ps.setInt(3, lugar.getFuncion().getCodFuncion());
            ResultSet rs = ps.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                throw new IllegalArgumentException("Ya existe un asiento con esa fila y número para la función seleccionada.");
            }
        }

        return true;
    }
    //para insertar un solo asiento, despreciado..
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
    // para cambiar el estado de un asiento a ocupado y para liberarlo tambien
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
//Para buscar todos los asientos libres de una funcion, usado en DialogLugaresDisponibles
    public List<Lugar> obtenerLugaresDisponiblesPorFuncion(int codFuncion) {
        String sql = "SELECT codLugar, fila, numero FROM lugar WHERE codFuncion = ? AND estado = 0";
        List<Lugar> lugaresDisponibles = new ArrayList<>();

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, codFuncion);

           
            Funcion funcion = funData.buscarFuncion(codFuncion);

            if (funcion != null) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Lugar lugar = new Lugar();
                        lugar.setCodLugar(rs.getInt("codLugar"));
                        lugar.setFila(rs.getString("fila").charAt(0));
                        lugar.setNumero(rs.getInt("numero"));
                        lugar.setEstado(false);// Sabemos que es 'false' (libre) por el SQL
                        lugar.setFuncion(funcion);// Le asignamos la función (así todos apuntan al mismo objeto)

                        lugaresDisponibles.add(lugar);
                    }
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al listar los lugares disponibles: " + ex.getMessage());
        }

        return lugaresDisponibles;
    }
//Es parecido al anterior, pero trae todos los asientos de una funcion, sean libres u ocupados, es para saber cuantos asientos existen.
    public List<Lugar> obtenerLugaresPorFuncion(int codFuncion) {
        String sql = "SELECT codLugar, fila, numero FROM lugar WHERE codFuncion = ?";
        List<Lugar> lugaresDisponibles = new ArrayList<>();

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, codFuncion);

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
//usado en la vista NuevoLugar, para generar automaticamente los asientos, mucho drama hacerlo uno por uno
    public void crearLugaresParaFuncion(int codFuncion, int cantidad) throws SQLException {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad de asientos debe ser mayor que cero.");
        }

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

        List<Lugar> lugaresExistentes = obtenerLugaresPorFuncion(codFuncion);
        int cantidadActual = lugaresExistentes.size();
          if (cantidadActual == capacidadMaxima) {
            throw new IllegalArgumentException("La función ya tiene todos los lugares creados.");
        }

        if (cantidadActual + cantidad > capacidadMaxima) {
            int disponibles = capacidadMaxima - cantidadActual;
            throw new IllegalArgumentException("No se pueden crear " + cantidad
                    + " lugares. Solo quedan disponibles " + disponibles + ".");
        }

        String sql = "INSERT INTO lugar (fila, numero, estado, codFuncion) VALUES (?,?,?,?)";
        final int asientosPorFila = 20;
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(sql);
            

            char filaActual = (char) ('A' + (cantidadActual / asientosPorFila));
            int numeroActual = (cantidadActual % asientosPorFila) + 1;

            for (int i = 0; i < cantidad; i++) {

                // Si el número supera los asientos por fila, pasamos a la siguiente fila
                if (numeroActual > asientosPorFila) { 
                    filaActual++;
                    numeroActual = 1;
                }

                ps.setString(1, String.valueOf(filaActual));
                ps.setInt(2, numeroActual);
                ps.setInt(3, 0);
                ps.setInt(4, codFuncion);
                ps.addBatch();

                numeroActual++;
            }

            ps.executeBatch();
            ps.close();

        } catch (SQLException e) {
            throw new SQLException("Error al generar asientos en lote (batch): " + e.getMessage());
        }
    }
    //Trae todos los lugares de todas las funciones
    public List listarLugares() throws SQLException {
        List<Lugar> lugares = new ArrayList<>();
        String sql = "SELECT * FROM lugar";

        try (PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Funcion f = new Funcion();
                Lugar l = new Lugar();
                // Por cada asiento, busca su función
                f = funData.buscarFuncion(rs.getInt("codFuncion"));
                l.setCodLugar(rs.getInt("codLugar"));
                l.setFila(rs.getString("fila").charAt(0));
                l.setNumero(rs.getInt("numero"));
                l.setEstado(rs.getBoolean("estado"));
                l.setFuncion(f);// Asigna el objeto Función
                lugares.add(l);
            }

        } catch (SQLException ex) {
            throw new SQLException("Error al listar peliculas " + ex);
        }

        return lugares;
    }
    
    
      public boolean bajaLogicaLugar(int codLugar) throws SQLException {
        String sql = "UPDATE lugar SET estado = 0 WHERE codLugar=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, codLugar);
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException ex) {
            throw new SQLException("no se pudo dar de baja el lugar: " + ex.getMessage());
        }
    }

    public boolean altaLogicaLugar(int codLugar) throws SQLException {
        String sql = "UPDATE lugar SET estado = 1 WHERE codLugar =?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, codLugar);
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException ex) {
            throw new SQLException("No se pudo dar de alta al lugar =?");

        }
    }


}
