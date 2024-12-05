package com.dulcehogar.interfaces;

import com.dulcehogar.managers.SocioManager;
import com.dulcehogar.utils.Pagos;
import java.util.List;

public interface SocioInterface {
    boolean agregarSocio(SocioManager socio);
    SocioManager buscarPorRut(String rut);
    SocioManager buscarPorNumeroSocio(int numDeSocio);
    boolean actualizarSocio(SocioManager socio);
    boolean eliminarSocio(int numDeSocio);
    List<SocioManager> obtenerTodos();
    boolean registrarPago(int numDeSocio, int monto);
    List<Pagos> obtenerPagos(int numDeSocio);
}