package com.dulcehogar.database;

import com.dulcehogar.managers.SocioManager;
import com.dulcehogar.interfaces.SocioInterface;
import com.dulcehogar.utils.Pagos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SocioDatabase implements SocioInterface {
    
    /**
     * Agrega un nuevo socio a la base de datos si no existe coincidencia
     * con el Número de Socio, el RUT o el Correo.
     *
     * @param socio
     * @return
     */
    @Override
    public boolean agregarSocio(SocioManager socio) {
        DBConnection dbCtx = new DBConnection();
        String verQuery = "SELECT COUNT(*) FROM socios WHERE num_de_socio = ? OR rut = ? OR correo = ?";
        String query = "INSERT INTO socios (num_de_socio, rut, nombre, apellido_paterno, apellido_materno, correo, domicilio, region, ciudad, comuna, telefono, valor_cuota, cantidad_aportada) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = dbCtx.getConnection()) {
            // Verificar si ya existe un socio con num_de_socio, rut o correo
            try (PreparedStatement verificarStmt = conn.prepareStatement(verQuery)) {
                verificarStmt.setInt(1, socio.getNumDeSocio());
                verificarStmt.setString(2, socio.getRun());
                verificarStmt.setString(3, socio.getCorreo());

                try (ResultSet rs = verificarStmt.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        System.err.println("Error: Ya existe un socio con el Número de Socio, RUT o correo proporcionado. Por favor limpie los datos y seleccione unos nuevos.");
                        return false;
                    }
                }
            }

            // Insertar el socio si no existe conflicto
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, socio.getNumDeSocio());
                stmt.setString(2, socio.getRun());
                stmt.setString(3, socio.getNombre());
                stmt.setString(4, socio.getApPaterno());
                stmt.setString(5, socio.getApMaterno());
                stmt.setString(6, socio.getCorreo());
                stmt.setString(7, socio.getDomicilio());
                stmt.setString(8, socio.getRegion());
                stmt.setString(9, socio.getCiudad());
                stmt.setString(10, socio.getComuna());
                stmt.setString(11, socio.getTelefono());
                stmt.setInt(12, socio.getValorCuotaMensual());
                stmt.setInt(13, socio.getCantidadAportada());

                return stmt.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error al registrar el socio: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Busca un socio en la base de datos utilizando su RUT.
     *
     * @param rut
     * @return
     */
    @Override
    public SocioManager buscarPorRut(String rut) {
        DBConnection dbCtx = new DBConnection();
        String query = "SELECT * FROM socios WHERE rut = ?";
        try (Connection conn = dbCtx.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, rut);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return resultSetToSocio(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Busca un socio en la base de datos utilizando su número de socio.
     *
     * @param numDeSocio
     * @return
     */
    @Override
    public SocioManager buscarPorNumeroSocio(int numDeSocio) {
        DBConnection dbCtx = new DBConnection();
        String query = "SELECT * FROM socios WHERE num_de_socio = ?";
        try (Connection conn = dbCtx.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, numDeSocio);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return resultSetToSocio(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Actualiza los datos de un socio existente en la base de datos.
     *
     * @param socio
     * @return
     */
    @Override
    public boolean actualizarSocio(SocioManager socio) {
        DBConnection dbCtx = new DBConnection();
        String query = "UPDATE socios SET nombre = ?, apellido_paterno = ?, apellido_materno = ?, correo = ?, domicilio = ?, region = ?, ciudad = ?, comuna = ?, telefono = ?, valor_cuota = ?, cantidad_aportada = ? " +
                       "WHERE num_de_socio = ?";
        try (Connection conn = dbCtx.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, socio.getNombre());
            stmt.setString(2, socio.getApPaterno());
            stmt.setString(3, socio.getApMaterno());
            stmt.setString(4, socio.getCorreo());
            stmt.setString(5, socio.getDomicilio());
            stmt.setString(6, socio.getRegion());
            stmt.setString(7, socio.getCiudad());
            stmt.setString(8, socio.getComuna());
            stmt.setString(9, socio.getTelefono());
            stmt.setInt(10, socio.getValorCuotaMensual());
            stmt.setInt(11, socio.getCantidadAportada());
            stmt.setInt(12, socio.getNumDeSocio());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Elimina un socio de la base de datos utilizando su número de socio.
     *
     * @param numDeSocio
     * @return
     */
    @Override
    public boolean eliminarSocio(int numDeSocio) {
        DBConnection dbCtx = new DBConnection();
        String query = "DELETE FROM socios WHERE num_de_socio = ?";
        try (Connection conn = dbCtx.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, numDeSocio);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Obtiene una lista con todos los socios registrados en la base de datos.
     *
     * @return
     */
    @Override
    public List<SocioManager> obtenerTodos() {
        DBConnection dbCtx = new DBConnection();
        String query = "SELECT * FROM socios";
        List<SocioManager> socios = new ArrayList<>();
        try (Connection conn = dbCtx.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                socios.add(resultSetToSocio(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return socios;
    }

    /**
     * Registra un pago en la base de datos y actualiza la cantidad aportada por el socio.
     *
     * @param numDeSocio
     * @param monto
     * @return
     */
    @Override
    public boolean registrarPago(int numDeSocio, int monto) {
        DBConnection dbCtx = new DBConnection();
        String insertPagoQuery = "INSERT INTO pagos (num_de_socio, monto) VALUES (?, ?)";
        String updateCantidadAportadaQuery = "UPDATE socios SET cantidad_aportada = cantidad_aportada + ? WHERE num_de_socio = ?";

        try (Connection conn = dbCtx.getConnection()) {
            conn.setAutoCommit(false); // Iniciar una transacción

            try (PreparedStatement insertPagoStmt = conn.prepareStatement(insertPagoQuery);
                 PreparedStatement updateCantidadStmt = conn.prepareStatement(updateCantidadAportadaQuery)) {

                // Inserta el registro en la tabla de pagos
                insertPagoStmt.setInt(1, numDeSocio);
                insertPagoStmt.setInt(2, monto);
                insertPagoStmt.executeUpdate();

                // Actualiza la cantidad aportada en la tabla de socios
                updateCantidadStmt.setInt(1, monto);
                updateCantidadStmt.setInt(2, numDeSocio);
                updateCantidadStmt.executeUpdate();

                conn.commit(); // Confirma la transacción
                return true;
            } catch (SQLException e) {
                conn.rollback(); // Revertir la transacción en caso de error
                System.err.println("Error al registrar el pago: " + e.getMessage());
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Obtiene una lista de pagos realizados por un socio.
     *
     * @param numDeSocio
     * @return
     */
    @Override
    public List<Pagos> obtenerPagos(int numDeSocio) {
        DBConnection dbCtx = new DBConnection();
        String query = "SELECT * FROM pagos WHERE num_de_socio = ?";
        List<Pagos> pagos = new ArrayList<>();
        try (Connection conn = dbCtx.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, numDeSocio);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Pagos pago = new Pagos(rs.getInt("monto"), rs.getTimestamp("fecha").toLocalDateTime());
                    pagos.add(pago);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pagos;
    }

    /**
     * Mapea los datos de un ResultSet a el objeto SocioManager.
     *
     * @param rs
     * @return
     * @throws
     */
    private SocioManager resultSetToSocio(ResultSet rs) throws SQLException {
        return new SocioManager(
            rs.getInt("num_de_socio"),
            rs.getString("rut"),
            rs.getString("nombre"),
            rs.getString("apellido_paterno"),
            rs.getString("apellido_materno"),
            rs.getString("correo"),
            rs.getString("domicilio"),
            rs.getString("region"),
            rs.getString("ciudad"),
            rs.getString("comuna"),
            rs.getString("telefono"),
            rs.getInt("cantidad_aportada"),
            rs.getInt("valor_cuota")
        );
    }
}
