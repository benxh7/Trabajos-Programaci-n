package com.dulcehogar.managers;

public class SocioManager {
    // Valor Cuota Mensual es un valor fijo por defecto
    private static final int VALOR_CUOTA_MENSUAL = 1650000;
    private int numDeSocio;
    private String run;
    private String nombre;
    private String apPaterno;
    private String apMaterno;
    private String domicilio;
    private String comuna;
    private String telefono;
    private String correo;
    private String region;
    private String ciudad;
    private int cantidadAportada;
    private int valorDeCuota;

    // Constructor principal con todos los parámetros
    public SocioManager(int numDeSocio, String run, String nombre,
            String apPaterno, String apMaterno, String domicilio, String comuna,
            String telefono, String correo, String region, String ciudad,
            int cantidadAportada, int valorDeCuota) {
        this.numDeSocio = numDeSocio;
        this.run = run;
        this.nombre = nombre;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
        this.domicilio = domicilio;
        this.comuna = comuna;
        this.telefono = telefono;
        this.correo = correo;
        this.region = region;
        this.ciudad = ciudad;
        this.cantidadAportada = cantidadAportada;
        this.valorDeCuota = valorDeCuota;
    }

    // Constructor alternativo que usa el valor de cuota por defecto
    public SocioManager(int numDeSocio, String run, String nombre, String apPaterno,
            String apMaterno, String domicilio, String comuna, String telefono,
            String correo, String region, String ciudad) {
        this(numDeSocio, run, nombre, apPaterno, apMaterno, domicilio, comuna, telefono, correo, region, ciudad, 0, VALOR_CUOTA_MENSUAL);
    }

    // Getters y setters
    public int getValorCuotaMensual() { return VALOR_CUOTA_MENSUAL; }
    public int getNumDeSocio() { return numDeSocio;}
    public void setNumDeSocio(int numDeSocio) { this.numDeSocio = numDeSocio; }
    public String getRun() { return run; }
    public void setRun(String run) { this.run = run; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApPaterno() { return apPaterno; }
    public void setApPaterno(String apPaterno) { this.apPaterno = apPaterno; }
    public String getApMaterno() { return apMaterno; }
    public void setApMaterno(String apMaterno) { this.apMaterno = apMaterno; }
    public String getDomicilio() {  return domicilio; }
    public void setDomicilio(String domicilio) { this.domicilio = domicilio; }
    public String getComuna() { return comuna; }
    public void setComuna(String comuna) { this.comuna = comuna; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getRegion() { return region;  }
    public void setRegion(String region) { this.region = region; }
    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }
    public int getCantidadAportada() { return cantidadAportada; }
    public void setCantidadAportada(int cantidadAportada) { this.cantidadAportada = cantidadAportada; }
    public void aumentarCantidadAportada(int monto) { this.cantidadAportada += monto; }
    public int getValorDeCuota() { return valorDeCuota; }
    public void setValorDeCuota(int valorDeCuota) { this.valorDeCuota = valorDeCuota; }
}