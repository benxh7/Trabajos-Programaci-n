package westmarketv2;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author GRUPO 1
 * Esta clase har� la gestion de los productos, incluyendo
 * el registro, la impresion y la eliminacion de productos.
 */
public class GestionProducto {
    private ArrayList<Producto> productos = new ArrayList<>();
    private ArrayList<Categoria> cat;
    private Scanner scan = new Scanner(System.in);
    
    public GestionProducto() {
        cat = new ArrayList<>();
        cat.add(new Categoria(1, "Bebidas"));
        cat.add(new Categoria(2, "Congelados"));
        cat.add(new Categoria(3, "L�cteos"));
        cat.add(new Categoria(4, "Aseo"));
    }
    
    // Este metodo nos permitir� registrar un nuevo producto.
    public void registrarProducto() {
        // Todos los datos ingresados y mensajes estan similares a los
        // solicitados dentro de el archivo word.
        // Obtenemos el codigo del producto
        int codigo =  capturarInt("Ingresa el c�digo del producto:");
        if (codigo == -1) return;
        
        // Obtenemos la descripci�n del producto
        String descripcion = capturarString("Ingresa la descripci�n del producto:");
        if (descripcion == null) return;

        // Obtenemos el precio del producto
        int precio = capturarInt("Ingresa el precio del producto:");
        if (precio == -1) return;

        // Obtenemos el stock del producto
        int stock = capturarInt("Ingresa el stock del producto:");
        if (stock == -1) return;

        // Mostramos las categor�as y pedimos la seleccion de una
        System.out.println("Categor�as:");
        for (Categoria categoria : cat) {
            System.out.println(" " + categoria.getCodigo() + ". " + categoria.getNombre());
        }
        int catCodigo = capturarInt("Asignale una categor�a al producto:");
        Categoria categoriaSeleccionada = cat.stream()
                .filter(c -> c.getCodigo() == catCodigo)
                .findFirst().orElse(null);
        
        // Aqui nos encargamos de a�adir el producto y almacenar sus datos
        if (categoriaSeleccionada != null) {
            Producto producto = new Producto(codigo, descripcion, precio, stock, categoriaSeleccionada);
            productos.add(producto);
            System.out.println("\n[SISTEMA] El producto ha sido ingresado");
        } else {
            System.out.println("\n[ERROR] La categoria es inv�lida. No se pudo registrar el producto.");
        }
    }
    
    // Este metodo imprime el listado de todos los productos ingresados previamente por el usuario
    public void imprimirListado() {
        if (productos.isEmpty()) {
            System.out.println("\n[SISTEMA] No hay productos registrados.");
        } else {
                System.out.println("\n----------- Listado de Productos -----------");
            for (Producto producto : productos) {
                System.out.println("C�digo: " + producto.getCodigo());
                System.out.println("Descripci�n: " + producto.getDesc());
                System.out.println("Precio: " + formatoMonto(producto.getPrecio()));
                System.out.println("Categor�a: " + producto.getCat().getNombre()+ " (Cod: " + producto.getCat().getCodigo() + ")");
                System.out.println("Stock: " + producto.getStock());
                System.out.println("--------------------------------------------");
            }
        }
    }
    
    // Este metodo hace algo similar al de arriba pero de una forma mas sencilla
    // ademas tambien en este mismo podemos eliminar un producto obteniendo su codigo
    public void imprimirListadoSimple() {
        if (productos.isEmpty()) {
            System.out.println("\n[SISTEMA] No hay productos registrados para elimianr.");
            return;
        }
        
        // Mostrar la lista de los productos en el formato del word
            System.out.println("\n----------- Productos Ingresados -----------");
        for (Producto producto : productos) {
            System.out.println("C�digo: " + producto.getCodigo());
            System.out.println("Descripci�n: " + producto.getDesc());
            System.out.println("--------------------------------------------");
        }
        
        // Le pedimos al usuario que ingrese el codigo del producto para elimianar
        int codigo = capturarInt("Ingresa el c�digo del producto que deseas eliminar: ");
        
        // Buscar y eliminar el producto si existe
        boolean productoEliminado = productos.removeIf(p -> p.getCodigo() == codigo);

        if (productoEliminado) {
            System.out.println("\n[SISTEMA] El producto ha sido eliminado.");
        } else {
            System.out.println("\n[ERROR] No se encontr� un producto con el c�digo especificado.");
        }
    }
    
    /**
     * Este metodo nos permite a nosotros obtener si o si un numero entero
     * si el usuario no agrega un numero que no es entero se aplica el sistema
     * de intentos el cual por 3 intentos fallidos el ingreso de datos se cancela.
     */
    private int capturarInt(String mensaje) {
        int attempts = 0;
        while (attempts < 3) {
            System.out.println(mensaje);
            try {
                return Integer.parseInt(scan.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("\n[ERROR] Ingresa un n�mero v�lido. Intentos restantes: " + (2 - attempts));
            }
            attempts++;
        }
        System.out.println("[SISTEMA] Demasiados intentos fallidos. Volviendo al men�.");
        return -1;
    }

    /**
     * Este metodo permite obtener un String no vac�o con un m�ximo de intentos.
     * Si se ingresan valores vac�os m�s de 3 veces, se cancela el ingreso.
     */
    private String capturarString(String mensaje) {
        int attempts = 0;
        while (attempts < 3) {
            System.out.println(mensaje);
            String input = scan.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            } else {
                System.out.println("\n[ERROR] Ingresa una descripci�n v�lida. Intentos restantes: " + (2 - attempts));
            }
            attempts++;
        }
        System.out.println("[SISTEMA] Demasiados intentos fallidos. Volviendo al men�.");
        return null;
    }
    
    public String formatoMonto(int monto) {
        return String.format("$%,d", monto);
    }
}