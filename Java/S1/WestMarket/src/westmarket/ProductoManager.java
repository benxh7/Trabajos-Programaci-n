package westmarket;

import java.util.Scanner;

/**
 * GRUPO 1
 */

public class ProductoManager {

    private Scanner scan = new Scanner(System.in);

    /**
     * Este metodo permite que creemos un nuevo producto
     * dandole la posibilidad al usuario de poder crear
     * su codigo, descripcion y precio.
     * 
     * Tambien comprobamos si la validacion que se hace
     * es valida, en el caso de que no lo sea esta se
     * toma como invalida.
     * 
     * Si todo es valido retorna el producto al final.
     */
    public Producto crearProducto() {
        int code = capturarInt("Ingrese un código: ");
        if (code == -1) return null;

        String desc = capturarString("Ingrese una descripción ");
        if (desc == null) return null;

        int prize = capturarInt("Ingrese un precio (EJ: 1000): ");
        if (prize == -1) return null;

        return new Producto(code, desc, prize);
    }

    // Captura un número entero con validación
    /**
     * Este metodo nos permite a nosotros obtener si o si un numero entero
     * si el usuario no agrega un numero que no es entero se aplica el sistema
     * de intentos el cual por 3 intentos fallidos el ingreso de datos se cancela.
     */
    private int capturarInt(String message) {
        int attempts = 0;
        while (attempts < 3) {
            System.out.println(message);
            try {
                return Integer.parseInt(scan.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingresa un número válido. Intentos restantes: " + (2 - attempts));
            }
            attempts++;
        }
        return -1;
    }

    /**
     * Este metodo hace algo similar al de arriba pero este en vez de obtener
     * un numero entero obtiene un String o cadena de texto, tambien aplicando
     * el sistema de intentos.
     */
    private String capturarString(String message) {
        int attempts = 0;
        while (attempts < 3) {
            System.out.println(message);
            String input = scan.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            } else {
                System.out.println("Error: Ingresa una descripción. Intentos restantes: " + (2 - attempts));
            }
            attempts++;
        }
        return null;
    }
    
    /**
     * Metodo para validar la cantidad de un producto ingresado
     * este metodo se encarga de poder comprobar y calcular la cantidad y monto
     * de un producto que previamente ya se ingreso en la consola.
     * 
     * Asi tambien aqui se administra el sistema de intentos.
     */
    public int validarCantidad() {
        Scanner scan = new Scanner(System.in);
        int quantity = 0;
        int attempts = 0;
        boolean validInput = false;

        /**
         * Aqui podemos validar la cantidad de un producto
         */
        while (attempts < 3 && !validInput) {
            try {
                System.out.println("Ingrese la cantidad: ");
                quantity = Integer.parseInt(scan.nextLine());
                if (quantity > 0) {
                    validInput = true;
                } else {
                    System.out.println("La cantidad debe ser mayor a 0. Intentos restantes: " + (2 - attempts));
                }
            } catch (NumberFormatException e) {
                System.out.println("Debes ingresar una cantidad válida. Intentos restantes: " + (2 - attempts));
            }
            attempts++;
        }

        if (validInput) {
            return quantity;
        } else {
            return -1;
        }
    }
}
