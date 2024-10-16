package westsalud;

import java.util.Scanner;

public class WestSalud {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /** Le pedimos al usuario que ingrese su contrase�a
         * REQUERIMIENTOS:
         * Debe tener un largo m�nimo de 6 caracteres.
         * Debe tener al menos una letra. Se permiten may�sculas y min�sculas.
         * Debe tener al menos un n�mero.
         * Debe tener al menos un car�cter que no sea letra ni n�mero.
         */
        Scanner scan = new Scanner(System.in);
        System.out.println("Ingrese la clave de seguridad:");
        String password = scan.next();

        // Creamos una instancia de PasswordManager
        PasswordManager passwordManager = new PasswordManager(password);

        // Validar la contrase�a e imprimir el resultado
        if (passwordManager.isValid()) {
            System.out.println("La clave cumple con los requisitos");
        } else {
            System.out.println("La clave no cumple con los requisitos");
        }
    }
}
