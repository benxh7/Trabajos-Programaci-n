package westmarketv2;

import java.util.Scanner;

/**
 * @author GRPO 1
 */
public class WestMarketV2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scan = new Scanner(System.in);
        GestionProducto gestorProductos = new GestionProducto();
        int opcion;
        boolean salir = false;
        
        while (!salir) {
            System.out.println("\nBienvenido al Menú de West Market: ");
            System.out.println(" 1. Registrar un producto");
            System.out.println(" 2. Imprimir productos");
            System.out.println(" 3. Eliminar un producto");
            System.out.println(" 4. Salir del Programa");
            System.out.print("Por favor, selecciona una opción: ");
            opcion = scan.nextInt();

            switch (opcion) {
                case 1:
                    gestorProductos.registrarProducto();
                    break;
                case 2:
                    gestorProductos.imprimirListado();
                    break;
                case 3:
                    gestorProductos.imprimirListadoSimple();
                    break;
                case 4:
                    salir = true;
                    System.out.println("\nSaliendo del Menú. ¡Muchas gracias! ¡Que tengas un buen día!");
                    break;
                default:
                    System.out.println("[ERROR] Opción no válida. Intenta nuevamente.");
                    break;
            }
        }
    }
}
