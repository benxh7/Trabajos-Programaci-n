package dulcehogar;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * La Clase RegistroPago representa un registro individual del pago realizado por un socio.
 * Almacena la fecha y hora exacta del pago, el n�mero de socio y el monto pagado por el usuario.
 *
 * @author GRUPO 1
 */
public class RegistroPago {
    private LocalDateTime fechaHora;
    private int numDeSocio;
    private int monto;

    // Constructor de la clase que recibe el n�mero de socio y el monto del pago
    public RegistroPago(int numDeSocio, int monto) {
        this.fechaHora = LocalDateTime.now();
        this.numDeSocio = numDeSocio;
        this.monto = monto;
    }
    // M�todo getter para obtener el monto del pago.
    public int getMonto() {
        return monto;
    }

    // M�todo para mostrar la fecha y detalles del pago en un formato amigable para el usuario.
    public String mostrarFechaDePagos() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        // Retorna una cadena con la fecha, n�mero de socio y monto del pago, con un formato amigable a la vista para el usuario.
        return String.format("[%s] Numero de Socio: %d Monto: $%,d", fechaHora.format(formatter), numDeSocio, monto);
    }
}