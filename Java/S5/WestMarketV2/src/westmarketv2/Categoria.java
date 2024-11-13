package westmarketv2;

/**
 * @author GRUPO 1
 * Esta clase representa las categorias de productos
 * su codigo y nombre similar a lo que vimos en la semana.
 */
public class Categoria {
    private int codigo;
    private String nombre;
    
    public Categoria(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
