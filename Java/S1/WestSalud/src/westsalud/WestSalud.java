package westsalud;

import java.util.Scanner;

public class WestSalud {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Le pedimos al usuario que ingrese su contraseña
        Scanner scan = new Scanner(System.in);
        System.out.println("Ingrese la clave de seguridad:");
        String password = scan.next();

        // Definimos PasswordManager para poder utilizar sus clases
        PasswordManager passwordManager = new PasswordManager(password);

        // Validar la contraseña e imprimir el resultado
        if (passwordManager.isValid()) {
            System.out.println("La clave cumple con los requisitos");
        } else {
            System.out.println("La clave no cumple con los requisitos");
        }
    }
}
