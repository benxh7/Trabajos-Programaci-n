package com.dulcehogar.utils;

import javax.swing.JOptionPane;

public class Mensajes {
    
    /**
     * Mensajes para mostrar en cada accion que sea necesario
     * este metodo nos ayuda a manejar de forma mas comoda los mensajes
     * ingresados en el programa y asi mismo a no estar cambiando linea por
     * linea cada mensaje que sea requerido, una forma sencilla y optima.
     */
    public static final String ERROR_RUT = "El RUT ingresado no es válido. Por favor, inténtelo nuevamente.";
    public static final String SOCIO_REGISTRADO = "Socio registrado exitosamente.";
    public static final String CORREO_YA_EXISTE = "El Correo ingresado ya está registrado en otra cuenta de socio. Por favor, elija otro.";
    public static final String VERIFICAR_DATOS_INGRESADOS = "Por favor, verifica los datos ingresados.";
    public static final String DATOS_ELIMINADOS = "Los datos han sido limpiados correctamente.";
    public static final String ERROR_FORMATO_RUT = "El RUT ingresado no es válido.\nFormatos aceptados: 12.345.678-9, 12345678-9 y 123456789";
    public static final String SOCIO_NO_ENCONTRADO = "No se encontró un socio con ese RUT ingresado.";
    public static final String MONTO_INVALIDO = "Por favor, ingresa un monto válido.";
    public static final String MONTO_CUOTA_MENOR_CERO = "El monto debe ser mayor a cero.";
    public static final String CUOTA_INVALIDO = "Por favor, ingresa un monto de cuota válido.";
    public static final String MONTO_MENOR_CERO = "El monto debe ser mayor a cero.";
    public static final String PAGO_EXITOSO = "¡Pago realizado con éxito!\nNuevo monto abonado: $";
    public static final String CUOTA_COMPLETA = "¡La cuota ha sido pagada en su totalidad!";
    public static final String CORREO_REGION_CIUDAD_OBLIGATORIO = "Correo, Región y Ciudad son campos obligatorios.";
    
    /**
     * Muestra un mensaje de informacion con un titulo predeterminado
     * cuando se requere mostrar un subpantalla con informacion importante
     * o simplemente si se requiere enviar un mensaje.
     * 
     * En este metodo de aqui se muestra un mensaje con informacion.
     *
     * @param parent
     * @param mensaje
     */
    public static void mostrarInfo(java.awt.Component parent, String mensaje) {
        JOptionPane.showMessageDialog(parent, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Muestra un mensaje de exito con un titulo predeterminado
     * cuando se requere mostrar un subpantalla con informacion importante
     * o simplemente si se requiere enviar un mensaje.
     * 
     * En este metodo aqui muestra un mensaje para acciones con ejecuciones exitosas
     *
     * @param parent
     * @param mensaje
     */
    public static void mostrarExito(java.awt.Component parent, String mensaje) {
        JOptionPane.showMessageDialog(parent, mensaje, "Exito", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Muestra un mensaje de error con un titulo predeterminado
     * cuando se requere mostrar un subpantalla con informacion importante
     * o simplemente si se requiere enviar un mensaje.
     * 
     * En este otro metodo se muestra un mensaje para acciones con errores o problemas
     *
     * @param parent
     * @param mensaje
     */
    public static void mostrarError(java.awt.Component parent, String mensaje) {
        JOptionPane.showMessageDialog(parent, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
}
