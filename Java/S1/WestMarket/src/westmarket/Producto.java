package westmarket;

/**
 * @author GRUPO 1
 * 
 * Constructor para almacenar los datos y valores para el producto.
 */
public class Producto {
    int codigo;
    String desc;
    int precio;
    
    Producto(){
    }
    
    public Producto(int codigo, String desc, int precio) {
        this.codigo = codigo;
        this.desc = desc;
        this.precio = precio;
    }
}
