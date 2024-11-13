package westmarketv2;

/**
 * @author GRUPO 1 
 * Constructor para almacenar y obtener los datos
 * y los valores de los Productos.
 */
public class Producto {
    private int codigo;
    private String desc;
    private int precio;
    private int stock;
    private Categoria cat;
    
    public Producto(int codigo, String desc, int precio, int stock, Categoria cat) {
        this.codigo = codigo;
        this.desc = desc;
        this.precio = precio;
        this.stock = stock;
        this.cat = cat;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Categoria getCat() {
        return cat;
    }

    public void setCat(Categoria cat) {
        this.cat = cat;
    }
}
