package westmarketv2;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author GRUPO 1
 * Esta clase hará la gestion de los productos, incluyendo
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
        cat.add(new Categoria(3, "Lácteos"));
        cat.add(new Categoria(4, "Aseo"));
    }
    
    // Este metodo nos permitirá registrar un nuevo producto.
    public void registrarProducto() {
        // Todos los datos ingresados y mensajes estan similares a los
        // solicitados dentro de el archivo word.
        // Obtenemos el codigo del producto
        int codigo =  capturarInt("Ingresa el código del producto:");
        if (codigo == -1) return;
        
        // Obtenemos la descripción del producto
        String descripcion = capturarString("Ingresa la descripción del producto:");
        if (descripcion == null) return;

        // Obtenemos el precio del producto
        int precio = capturarInt("Ingresa el precio del producto:");
        if (precio == -1) return;

        // Obtenemos el stock del producto
        int stock = capturarInt("Ingresa el stock del producto:");
        if (stock == -1) return;

        // Mostramos las categorías y pedimos la seleccion de una
        System.out.println("Categorías:");
        for (Categoria categoria : cat) {
            System.out.println(" " + categoria.getCodigo() + ". " + categoria.getNombre());
        }
        int catCodigo = capturarInt("Asignale una categoría al producto:");
        Categoria categoriaSeleccionada = cat.stream()
                .filter(c -> c.getCodigo() == catCodigo)
                .findFirst().orElse(null);
        
        // Aqui nos encargamos de añadir el producto y almacenar sus datos
        if (categoriaSeleccionada != null) {
            Producto producto = new Producto(codigo, descripcion, precio, stock, categoriaSeleccionada);
            productos.add(producto);
            System.out.println("\n[SISTEMA] El producto ha sido ingresado");
        } else {
            System.out.println("\n[ERROR] La categoria es inválida. No se pudo registrar el producto.");
        }
    }
    
    // Este metodo imprime el listado de todos los productos ingresados previamente por el usuario
    public void imprimirListado() {
        if (productos.isEmpty()) {
            System.out.println("\n[SISTEMA] No hay productos registrados.");
        } else {
                System.out.println("\n----------- Listado de Productos -----------");
            for (Producto producto : productos) {
                System.out.println("Código: " + producto.getCodigo());
                System.out.println("Descripción: " + producto.getDesc());
                System.out.println("Precio: " + formatoMonto(producto.getPrecio()));
                System.out.println("Categoría: " + producto.getCat().getNombre()+ " (Cod: " + producto.getCat().getCodigo() + ")");
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
            System.out.println("Código: " + producto.getCodigo());
            System.out.println("Descripción: " + producto.getDesc());
            System.out.println("--------------------------------------------");
        }
        
        // Le pedimos al usuario que ingrese el codigo del producto para elimianar
        int codigo = capturarInt("Ingresa el código del producto que deseas eliminar: ");
        
        // Buscar y eliminar el producto si existe
        boolean productoEliminado = productos.removeIf(p -> p.getCodigo() == codigo);

        if (productoEliminado) {
            System.out.println("\n[SISTEMA] El producto ha sido eliminado.");
        } else {
            System.out.println("\n[ERROR] No se encontró un producto con el código especificado.");
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
                System.out.println("\n[ERROR] Ingresa un número válido. Intentos restantes: " + (2 - attempts));
            }
            attempts++;
        }
        System.out.println("[SISTEMA] Demasiados intentos fallidos. Volviendo al menú.");
        return -1;
    }

    /**
     * Este metodo permite obtener un String no vacío con un máximo de intentos.
     * Si se ingresan valores vacíos más de 3 veces, se cancela el ingreso.
     */
    private String capturarString(String mensaje) {
        int attempts = 0;
        while (attempts < 3) {
            System.out.println(mensaje);
            String input = scan.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            } else {
                System.out.println("\n[ERROR] Ingresa una descripción válida. Intentos restantes: " + (2 - attempts));
            }
            attempts++;
        }
        System.out.println("[SISTEMA] Demasiados intentos fallidos. Volviendo al menú.");
        return null;
    }
    
    public String formatoMonto(int monto) {
        return String.format("$%,d", monto);
    }
}