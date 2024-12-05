package com.dulcehogar.utils;

import java.text.DecimalFormat;

public class Utils {
    
    /**
     * Metodo para dar formato a un monto por ejemplo: $1.000.000
     * @param monto Monto en formato entero.
     * @return Cadena con el monto formateado.
     */
    public static String formatearDinero(int monto) {
        DecimalFormat formato = new DecimalFormat("$###,###,###");
        return formato.format(monto).replace(",",".");
    }
    
    /**
    * Valida si un numero de un socio es un entero de 9 dígitos.
    * @param numSocio
    * @return
    */
    public static boolean validarNumSocio(int numSocio) {
        String numSocioStr = String.valueOf(numSocio);
        return numSocioStr.matches("^\\d{9}$");
    }
    
    /**
     * Normaliza el RUT eliminando puntos y guiones.
     * Ej: 21.480.278-3 -> 214802783 o 214802783 -> 214802783
     * @param rut
     * @return
     */
    public static String normalizarRut(String rut) {
        return rut.replace(".", "").replace("-", "").trim();
    }

    /**
     * Da formato al RUT para que quede como 21.480.278-3
     * Ej: 214802783 -> 21.480.278-3
     * @param rut
     * @return
     */
    public static String formatearRut(String rut) {
        if (rut == null || rut.isEmpty()) {
            return null; // RUT nulo o vacío no es válido
        }

        // Validar formatos permitidos:
        // - 123456789
        // - 12345678-9
        // - 12.345.678-9
        if (!rut.matches("^\\d{7,8}[\\dkK]$|^\\d{7,8}-[\\dkK]$|^\\d{1,3}(\\.\\d{3}){2}-[\\dkK]$")) {
            return null; // Rechazar si no cumple con los formatos válidos
        }

        // Normalizar el RUT eliminando puntos y guion
        String rutNormalizado = rut.replaceAll("[^\\dkK]", "");

        // Separar numeros y dígito verificador
        String cuerpo = rutNormalizado.substring(0, rutNormalizado.length() - 1);
        String dv = rutNormalizado.substring(rutNormalizado.length() - 1).toUpperCase();

        // Aplicar formato, agrega puntos y guion
        StringBuilder rutConFormato = new StringBuilder();
        int contador = 0;
        for (int i = cuerpo.length() - 1; i >= 0; i--) {
            rutConFormato.insert(0, cuerpo.charAt(i));
            contador++;
            if (contador % 3 == 0 && i != 0) {
                rutConFormato.insert(0, ".");
            }
        }

        return rutConFormato.toString() + "-" + dv;
    }
    
}
