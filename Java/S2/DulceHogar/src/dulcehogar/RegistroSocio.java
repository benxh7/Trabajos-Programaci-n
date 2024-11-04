package dulcehogar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author GRUPO 1
 */
public class RegistroSocio {
    /**
     * Constructor en donde encuentras métodos con todo lo relacionado a
     * registros de socios, formato para valores, sistema de intentos
     * en caso de fallar al ingresar un dato y un sistema de guardado en memoria.
     */
    private Scanner scan = new Scanner(System.in);
    private Map<Integer, Socio> socios = new HashMap<>(); // Almacenamos los datos en la memoria
    private Map<Integer, List<RegistroPago>> pagosRealizados = new HashMap<>(); // Almacenamos los pagos realizados de igual manera en la memoria

    public Socio registrarUnSocio() {
        int numDeSocio = capturarInt("Ingrese el número de socio (9 dígitos): ");
        // Valida que el número de socio sea un número de 9 dígitos
        if (numDeSocio == -1 || String.valueOf(numDeSocio).length() != 9) {
            System.out.println("\nError: El número de socio debe tener exactamente 9 dígitos.");
            return null;
        }

        String run = capturarRun("Ingrese el RUN (Ej: 22.342.753-0 o 223427530): ");
        if (run == null) return null;

        String nombre = capturarString("Ingrese el nombre: ");
        if (nombre == null) return null;

        String apPaterno = capturarString("Ingrese el apellido paterno: ");
        if (apPaterno == null) return null;

        String apMaterno = capturarString("Ingrese el apellido materno: ");
        if (apMaterno == null) return null;

        String correo = capturarString("Ingrese el correo: ");
        if (correo == null) return null;

        String domicilio = capturarString("Ingrese el domicilio: ");
        if (domicilio == null) return null;

        String region = capturarString("Ingrese la región: ");
        if (region == null) return null;

        String ciudad = capturarString("Ingrese la ciudad: ");
        if (ciudad == null) return null;

        String comuna = capturarString("Ingrese la comuna: ");
        if (comuna == null) return null;

        String telefono = capturarString("Ingrese el teléfono: ");
        if (telefono == null) return null;

        // Inicializar cantidad aportada por nuevos clientes o usuarios en $0 pesos
        int cantidadAportada = 0;

        // Se define el valor de la cuota en $1.000.000 basado en precio de mercado actual en Chile.
        int valorCuota = 1000000;

        Socio socio = new Socio(numDeSocio, run, nombre, apPaterno, apMaterno, correo, domicilio, region, ciudad, comuna, telefono, cantidadAportada, valorCuota);
        socios.put(numDeSocio, socio); // Agregamos el socio al mapa.
        System.out.println("");
        System.out.println("¡Socio fue registrado exitosamente!");
        return socio;
    }

    /**
     * Se obtienen los datos de un socio mediante el número de socio.
     */
    public void verDatosDelSocio() {
        Socio socio = obtenerSocioPorNumero();
        if (socio == null) {
            return;
        }

        if (socio != null) {
            // Recupera los datos usando los getters de la clase Socio y los imprime en el mensaje
            System.out.println("------ Datos del Socio ------");
            System.out.println(" Número de Socio: " + socio.getNumDeSocio());
            System.out.println(" RUN: " + socio.getRun());
            System.out.println(" Nombre: " + socio.getNombre());
            System.out.println(" Apellido Paterno: " + socio.getApPaterno());
            System.out.println(" Apellido Materno: " + socio.getApMaterno());
            System.out.println(" Correo: " + socio.getCorreo());
            System.out.println(" Domicilio: " + socio.getDomicilio());
            System.out.println(" Región: " + socio.getRegion());
            System.out.println(" Ciudad: " + socio.getCiudad());
            System.out.println(" Comuna: " + socio.getComuna());
            System.out.println(" Teléfono: " + socio.getTelefono());
            System.out.println(" Cantidad Aportada: " + formatoMonto(socio.getCantidadAportada()));
            System.out.println(" Valor de Cuota: " + formatoMonto(socio.getValorDeCuota()));
            System.out.println("-----------------------------");
        } else {
            System.out.println("El número de socio ingresado es incorrecto o no ha sido registrado.");
        }
    }

    /**
     * Este método permite al usuario realizar el pago o abono de una cuota de un socio mediante el ingreso del número asociado al socio.
     */
    public void pagoDeCuota() {
        Socio socio = obtenerSocioPorNumero();
        if (socio == null) {
            return;
        }

        // Verificamos si el socio está registrado en el mapa de socios.
        if (socio != null) {
            // Se imprime el valor de la cuota a pagar y luego la cantidad aportada haste ese momento por el socio.
            System.out.println("El valor de tu cuota actual es: " + formatoMonto(socio.getValorDeCuota()));
            System.out.println("Cantidad abonada: " + formatoMonto(socio.getCantidadAportada()));

            // Solicitar el monto a cancelar.
            int monto = capturarInt("Ingresa monto a pagar: ");
            if (monto > 0) {
                // Actualizar la cantidad aportada del socio.
                socio.aumentarCantidadAportada(monto);

                // Agregar un nuevo registro de pago al Map.
                pagosRealizados.putIfAbsent(socio.getNumDeSocio(), new ArrayList<>());
                pagosRealizados.get(socio.getNumDeSocio()).add(new RegistroPago(socio.getNumDeSocio(), monto));

                System.out.println("¡Tu pago fue realizado con éxito!");
                System.out.println("Tu nuevo saldo abonado es: " + formatoMonto(socio.getCantidadAportada()));

                // Verificar si la cuota está completamente pagada.
                if (socio.getCantidadAportada() >= socio.getValorDeCuota()) {
                    System.out.println("¡Tu cuota ha sido pagada en su totalidad con éxito!");
                } else {
                    System.out.println("Saldo pendiente por pagar.");
                }
            } else {
                System.out.println("Error: Por favor, ingresa un monto mayor a cero.");
            }
        } else {
            System.out.println("Error: El número ingresado no corresponde a ningún socio registrado.");
        }
    }

    public void consultarPagoDeCuotas() {
        Socio socio = obtenerSocioPorNumero();
        if (socio == null) {
            return;
        }

        // Mostramos los pagos realizados, para trazabilidad.
        List<RegistroPago> pagos = pagosRealizados.get(socio.getNumDeSocio());
        if (pagos == null || pagos.isEmpty()) {
            System.out.println("Registro de Transferencia (0):");
            System.out.println("- Sus cuotas pendientes no han sido pagadas.");
        } else {
            System.out.println("Registro de Transferencia (" + pagos.size() + "):");
            int contador = 1;
            for (RegistroPago pago : pagos) {
                System.out.println(" " + contador + ". " + pago.mostrarFechaDePagos());
                contador++;
            }
        }
    }

    public void consultarTotalCuotas() {
        Socio socio = obtenerSocioPorNumero();
        if (socio == null) {
            return;
        }

        // Mostramos los pagos de cuota del usuario
        List<RegistroPago> pagos = pagosRealizados.get(socio.getNumDeSocio());
        if (pagos == null || pagos.isEmpty()) {
            System.out.println("Cantidad de Cuotas Pagadas (0):");
            System.out.println("- Sus cuotas pendientes no han sido pagadas.");
        } else {
            // Calcular el total de las cuotas pagadas
            int totalCuotasPagadas = 0;
            for (RegistroPago pago : pagos) {
                totalCuotasPagadas += pago.getMonto();
            }

            // Obtenemos el valor de la cuota
            int valorCuota = socio.getValorDeCuota();

            // Calcular cuántas cuotas se han pagado
            int cantidadCuotasPagadas = totalCuotasPagadas / valorCuota;

            // Mostramos la cantidad de cuotas pagadas y el total
            System.out.println("\nCuotas pagadas hasta la fecha (" + cantidadCuotasPagadas + "):");
            System.out.println("Cantidad total de dinero aportado en cuotas: " + formatoMonto(totalCuotasPagadas) + " pesos");
        }
    }

    public void editarEliminarDatosSocio() {
        Socio socio = obtenerSocioPorNumero();
        if (socio == null) {
            return;
        }

        // Mostrar submenu de edicion y eliminacion de datos de un usuario
        System.out.println("\nSeleccione una opción:");
        System.out.println("1. Editar Datos del Socio");
        System.out.println("2. Eliminar Datos del Socio");
        System.out.println("3. Cancelar y Volver al Menu Inicial");
        int opcion = capturarInt("Ingrese su opción: ");

        switch (opcion) {
            case 1:
                // Editar Datos del Socio
                editarDatosSocio(socio);
                break;
            case 2:
                // Eliminar Datos del Socio
                socios.remove(socio.getNumDeSocio());
                pagosRealizados.remove(socio.getNumDeSocio());
                System.out.println("El socio y sus registros de pago han sido eliminados exitosamente.");
                break;
            case 3:
                // Cancelar y Volver al Menu Inicial
                System.out.println("Operación cancelada.");
                break;
            default:
                System.out.println("La opción no es válida.");
                break;
        }
    }

    /**
     * Metodo para poder editar los datos de un socio.
     */
    private void editarDatosSocio(Socio socio) {
        boolean continuarEdicion = true;

        while (continuarEdicion) {
            // Mostrar los atributos editables - El Numero de Socio y RUN son inmodificables
            System.out.println("\nSeleccione el atributo a editar:");
            System.out.println(" 1. Nombre");
            System.out.println(" 2. Apellido Paterno");
            System.out.println(" 3. Apellido Materno");
            System.out.println(" 4. Correo");
            System.out.println(" 5. Domicilio");
            System.out.println(" 6. Región");
            System.out.println(" 7. Ciudad");
            System.out.println(" 8. Comuna");
            System.out.println(" 9. Teléfono");
            System.out.println(" 10. Cancelar");
            int opcion = capturarInt("Ingrese su opción: ");

            // Realizar la edición basada en la opción seleccionada
            switch (opcion) {
                case 1:
                    String nuevoNombre = capturarString("Ingrese el nuevo nombre: ");
                    if (nuevoNombre != null) {
                        socio.setNombre(nuevoNombre);
                        System.out.println("Nombre actualizado exitosamente.");
                    }
                    break;
                case 2:
                    String nuevoApPaterno = capturarString("Ingrese el nuevo apellido paterno: ");
                    if (nuevoApPaterno != null) {
                        socio.setApPaterno(nuevoApPaterno);
                        System.out.println("Apellido paterno actualizado exitosamente.");
                    }
                    break;
                case 3:
                    String nuevoApMaterno = capturarString("Ingrese el nuevo apellido materno: ");
                    if (nuevoApMaterno != null) {
                        socio.setApMaterno(nuevoApMaterno);
                        System.out.println("Apellido materno actualizado exitosamente.");
                    }
                    break;
                case 4:
                    String nuevoCorreo = capturarString("Ingrese el nuevo correo: ");
                    if (nuevoCorreo != null) {
                        socio.setCorreo(nuevoCorreo);
                        System.out.println("Correo actualizado exitosamente.");
                    }
                    break;
                case 5:
                    String nuevoDomicilio = capturarString("Ingrese el nuevo domicilio: ");
                    if (nuevoDomicilio != null) {
                        socio.setDomicilio(nuevoDomicilio);
                        System.out.println("Domicilio actualizado exitosamente.");
                    }
                    break;
                case 6:
                    String nuevaRegion = capturarString("Ingrese la nueva región: ");
                    if (nuevaRegion != null) {
                        socio.setRegion(nuevaRegion);
                        System.out.println("Región actualizada exitosamente.");
                    }
                    break;
                case 7:
                    String nuevaCiudad = capturarString("Ingrese la nueva ciudad: ");
                    if (nuevaCiudad != null) {
                        socio.setCiudad(nuevaCiudad);
                        System.out.println("Ciudad actualizada exitosamente.");
                    }
                    break;
                case 8:
                    String nuevaComuna = capturarString("Ingrese la nueva comuna: ");
                    if (nuevaComuna != null) {
                        socio.setComuna(nuevaComuna);
                        System.out.println("Comuna actualizada exitosamente.");
                    }
                    break;
                case 9:
                    String nuevoTelefono = capturarString("Ingrese el nuevo teléfono: ");
                    if (nuevoTelefono != null) {
                        socio.setTelefono(nuevoTelefono);
                        System.out.println("Teléfono actualizado exitosamente.");
                    }
                    break;
                case 10:
                    System.out.println("Edición cancelada.");
                    continuarEdicion = false; // Sale del bucle
                    break;
                default:
                    System.out.println("Esa opcion no es valida. Intentalo otra vez.");
                    break;
            }
            // Mostrar un mensaje opcional para continuar editando
            if (continuarEdicion) {
                System.out.println("\nPuedes seleccionar otro dato para editar o elegir '10' para salir.");
            }
        }
    }

    /**
     * Este metodo nos permite consultar antes de proceder con una opcion
     * pidiendole al usuario el numero de socio, esto nos permite seleccionar un
     * socio mediante su Numero de Socio.
     */
    private Socio obtenerSocioPorNumero() {
        // Solicitar el número de socio al usuario
        int numDeSocio = capturarInt("Ingrese el número de socio para continuar: ");
        if (numDeSocio == -1) {
            System.out.println("Número de socio no válido.");
            return null; // Retorna nulo si el número es inválido
        }

        // Verifica si el socio está registrado en el mapa de socios
        Socio socio = socios.get(numDeSocio);
        if (socio == null) {
            System.out.println("Error: El número ingresado no corresponde a ningún socio registrado.");
            return null; // Retorna nulo si no se encuentra el socio
        }

        return socio; // Retorna el socio si es encontrado
    }

    /**
     * Captura y valida el RUN, aplicando el formato correcto.
     */
    private String capturarRun(String message) {
        int attempts = 0;
        while (attempts < 3) {
            System.out.println(message);
            String input = scan.nextLine().trim().replace(".", "").replace("-", "");

            // Verificar si tiene 9 o 10 caracteres numéricos (RUN sin formato)
            if (input.matches("\\d{9,10}")) {
                // Formatear el RUN: 99G999G999-9 G = .
                String formattedRun = input.replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d{1})", "$1.$2.$3-$4");
                return formattedRun;
            } else {
                System.out.println("Error: Debes ingresar un RUN válido con 11 a 12 caracteres. Intentos restantes: " + (2 - attempts));
            }
            attempts++;
        }
        return null;
    }

    /**
     * Este método nos permite a nosotros obtener si o si un número entero
     * si el usuario agrega un número que no es entero se aplica el sistema
     * de intentos, el cual después de 3 intentos fallidos cancela el ingreso de datos.
     */
    private int capturarInt(String message) {
        int attempts = 0;
        while (attempts < 3) {
            System.out.println(message);
            try {
                return Integer.parseInt(scan.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor solo ingresa números. Intentos restantes: " + (2 - attempts));
                System.out.println("\nRegresando al menu principal...");
            }
            attempts++;
        }
        return -1;
    }
    
    /**
     * Este método hace algo similar al de arriba pero este en vez de obtener
     * un número entero se obtiene un String o cadena de texto, también aplicando
     * el sistema de intentos.
     */
    private String capturarString(String message) {
        int attempts = 0; // Inicializa el contador de intentos en 0.
        while (attempts < 3) { // Bucle que permite hasta 3 intentos para capturar una entrada válida.
            System.out.println(message);
            String input = scan.nextLine().trim();
            // Verificamos si el usuario ingresó un texto válido (no vacío).
            if (!input.isEmpty()) {
                return input;
            } else {
                // Muestra mensaje de error y el número de intentos restantes.
                System.out.println("Error: Por favor ingresa una descripción. Intentos restantes: " + (2 - attempts));
                System.out.println("\nRegresando al menú principal...");
            }
            attempts++;
        }
        return null;
    }
    
    /**
     * Este metodo nos da la opcion de darle formato a el dinero mostrado
     * en cada menu. Como ejemplo este metodo convierte 1000000 a $1.000.000
     */
    public String formatoMonto(int monto) {
        return String.format("$%,d", monto);
    }
}
