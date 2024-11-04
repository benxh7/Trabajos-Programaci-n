package dulcehogar;

/**
 * @author GRUPO 1
 * Constructor para almacenar los datos y valores para el producto.
 */
public class Socio {
    private int numDeSocio;
    private String run;
    private String nombre;
    private String apPaterno;
    private String apMaterno;
    private String correo;
    private String domicilio;
    private String region;
    private String ciudad;
    private String comuna;
    private String telefono;
    private int cantidadAportada;
    private int valorDeCuota;

    public Socio(int numDeSocio, String run, String nombre, String apPaterno,
                 String apMaterno, String correo, String domicilio,
                 String region, String ciudad, String comuna,
                 String telefono, int cantidadAportada, int valorDeCuota)
    {
        this.numDeSocio = numDeSocio;
        this.run = run;
        this.nombre = nombre;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
        this.correo = correo;
        this.domicilio = domicilio;
        this.region = region;
        this.ciudad = ciudad;
        this.comuna = comuna;
        this.telefono = telefono;
        this.cantidadAportada = cantidadAportada;
        this.valorDeCuota = valorDeCuota;
    }

    // Getters y Setter para obtener atributos de socio.
    public int getNumDeSocio() {
        return numDeSocio;
    }

    public void setNumDeSocio(int numDeSocio) {
        this.numDeSocio = numDeSocio;
    }

    public String getRun() {
        return run;
    }

    public void setRun(String run) {
        this.run = run;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApPaterno() {
        return apPaterno;
    }

    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    public String getApMaterno() {
        return apMaterno;
    }

    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getCantidadAportada() {
        return cantidadAportada;
    }

    public void setCantidadAportada(int cantidadAportada) {
        this.cantidadAportada = cantidadAportada;
    }

    public void aumentarCantidadAportada(int monto) {
        this.cantidadAportada += monto;
    }

    public int getValorDeCuota() {
        return valorDeCuota;
    }

    public void setValorDeCuota(int valorDeCuota) {
        this.valorDeCuota = valorDeCuota;
    }

}
