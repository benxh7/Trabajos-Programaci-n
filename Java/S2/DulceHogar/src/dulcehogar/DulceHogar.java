package dulcehogar;

import java.util.Scanner;

/**
 * @author GRUPO 1
 */
public class DulceHogar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        RegistroSocio registroSocio = new RegistroSocio();
        Scanner scan = new Scanner(System.in);
        int opcion;
        boolean salir = false;

        while (!salir) {
            System.out.println("\nBienvenido al Menú de Dulce Hogar: ");
            System.out.println(" 1. Selecciona Para Registrar Socio");
            System.out.println(" 2. Selecciona Para Ver datos del Socio");
            System.out.println(" 3. Editar y Eliminar datos del Socio");
            System.out.println(" 4. Selecciona Para Pagar Cuota");
            System.out.println(" 5. Selecciona Para Consultar Cuotas Pagadas");
            System.out.println(" 6. Selecciona Para Consultar Total de Cuotas Pagadas");
            System.out.println(" 7. Selecciona Para Salir");
            System.out.println("-----------------------------");
            System.out.print("Por favor, selecciona una opción: ");
            opcion = scan.nextInt();

            switch (opcion) {
                case 1:
                    registroSocio.registrarUnSocio();
                    break;
                case 2:
                    registroSocio.verDatosDelSocio();
                    break;
                case 3:
                    registroSocio.editarEliminarDatosSocio();
                    break;
                case 4:
                    registroSocio.pagoDeCuota();
                    break;
                case 5:
                    registroSocio.consultarPagoDeCuotas();
                    break;
                case 6:
                    registroSocio.consultarTotalCuotas();
                    break;
                case 7:
                    salir = true;
                    System.out.println("\nSaliendo del Menú. ¡Muchas gracias! ¡Que tengas un buen día!");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta nuevamente.");
                    break;
            }
        }
    }
}
