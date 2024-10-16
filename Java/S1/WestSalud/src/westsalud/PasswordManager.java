package westsalud;

/**
 * Esta clase se encarga de hacer comprobaciones para estar seguros
 * de que la contraseña cumple los parametros requeridos.
 * 
 * REQUERIMIENTOS:
 * Debe tener un largo mínimo de 6 caracteres.
 * Debe tener al menos una letra. Se permiten mayúsculas y minúsculas.
 * Debe tener al menos un número.
 * Debe tener al menos un carácter que no sea letra ni número.
 */

public class PasswordManager {
    private String password;

    // Constructor que recibe la contraseña ingresada por el usuario
    public PasswordManager(String password) {
        this.password = password;
    }

    // Metodo para validar si la contraseña ingresada es válida
    public boolean isValid() {
        return hasMinimumLength() && hasLetter() && hasDigit() && hasSpecialChar();
    }

    // Verifica si la contraseña tiene al menos 6 caracteres
    private boolean hasMinimumLength() {
        return password.length() >= 6;
    }

    // Verifica si la contraseña contiene al menos una letra
    private boolean hasLetter() {
        for (int i = 0; i < password.length(); i++) {
            if (Character.isLetter(password.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    // Verifica si la contraseña contiene al menos un dígito
    private boolean hasDigit() {
        for (int i = 0; i < password.length(); i++) {
            if (Character.isDigit(password.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    // Verifica si la contraseña contiene al menos un carácter especial
    private boolean hasSpecialChar() {
        for (int i = 0; i < password.length(); i++) {
            char x = password.charAt(i);
            if (!Character.isLetterOrDigit(x)) {
                return true;
            }
        }
        return false;
    }
}
