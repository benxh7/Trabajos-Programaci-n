package westmarket;

/**
 * @author GRUPO 1
 */
public class WestMarket {

    public static void main(String[] args) {
        /**
         * Aqui llamamos a ProductoManager que es quien se encarga de
         * ciertas acciones al momento de ingresar y verificar datos.
         * 
         * Asi mismo hacemos lo mismo con Producto para asi poder
         * obtener los datos previamente ingresados y plasmarlos
         * en la consola.
         */
        ProductoManager manager = new ProductoManager();
        Producto producto = manager.crearProducto();
        
        /**
         * Si se ingresan correctamente los datos del producto el algoritmo
         * imprimira los datos previamente recolectados en la consola.
         */
        if (producto != null) {
            int quantity = manager.validarCantidad();

            if (quantity > 0) {
                System.out.println("--- Datos del Producto ---");
                System.out.println("Código: " + producto.codigo);
                System.out.println("Descripción: " + producto.desc);
                System.out.println("Precio (Unidad): $" + producto.precio);
                System.out.println("Cantidad: " + quantity);
                System.out.println("Total a pagar: $" + (producto.precio * quantity));
            } else {
                System.out.println("No se ingresó una cantidad válida. ¡Adiós!");
            }
        } else {
            System.out.println("Ha sobrepasado la cantidad máxima de intentos. ¡Adiós!");
        }
    }
}

