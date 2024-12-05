package com.dulcehogar.managers;

import com.dulcehogar.utils.Pagos;
import java.util.List;
import com.dulcehogar.interfaces.SocioInterface;

public class RegistroSocioManager {
    private final SocioInterface socioDAO;

    public RegistroSocioManager(SocioInterface socioDAO) {
        this.socioDAO = socioDAO;
    }

    public boolean agregarSocio(SocioManager socio) {
        return socioDAO.agregarSocio(socio);
    }

    public SocioManager buscarSocioPorRut(String rut) {
        return socioDAO.buscarPorRut(rut);
    }

    public SocioManager buscarSocioPorNumero(int numDeSocio) {
        return socioDAO.buscarPorNumeroSocio(numDeSocio);
    }

    public boolean actualizarSocio(SocioManager socio) {
        return socioDAO.actualizarSocio(socio);
    }

    public boolean eliminarSocio(int numDeSocio) {
        return socioDAO.eliminarSocio(numDeSocio);
    }

    public List<SocioManager> obtenerTodos() {
        return socioDAO.obtenerTodos();
    }
    
    /**
     * Registra un pago realizado por un socio.
     *
     * @param numDeSocio Número de socio
     * @param monto Monto a registrar
     * @return `true` si el pago se registró correctamente, de lo contrario, `false`
     */
    public boolean registrarPago(int numDeSocio, int monto) {
        return socioDAO.registrarPago(numDeSocio, monto);
    }

    /**
     * Obtiene la lista de pagos realizados por un socio.
     *
     * @param numDeSocio Número de socio
     * @return Lista de pagos realizados
     */
    public List<Pagos> obtenerPagos(int numDeSocio) {
        return socioDAO.obtenerPagos(numDeSocio);
    }
    
}