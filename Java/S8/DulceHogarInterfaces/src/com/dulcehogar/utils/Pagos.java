package com.dulcehogar.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Pagos {
    private int monto; // Monto pagado por el socio
    private LocalDateTime fecha; // Fecha en la que se hizo el pago
    
    /**
     * Constructor para inicializar un nuevo registro de pago
     * @param monto Monto del pago.
     * @param fecha Fecha en que se realizó el pago.
     */
    public Pagos(int monto, LocalDateTime fecha) {
        this.monto = monto;
        this.fecha = fecha;
    }
    
    public int getMonto() {
        return monto;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }
    
    /**
     * Devuelve la fecha formateada como "dd-MM-yyyy hh:mm a".
     * Esto facilita la visualizacion de las fechas en el sistema
     * @return Fecha formateada como String
     */
    public String getFechaFormateada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a");
        return fecha.format(formatter);
    }
}
