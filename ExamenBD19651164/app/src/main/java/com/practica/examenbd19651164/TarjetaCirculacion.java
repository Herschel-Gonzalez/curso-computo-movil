package com.practica.examenbd19651164;

public class TarjetaCirculacion {
    private String folio;
    private String numeroSerie;
    private String tipo;
    private String estado;
    private String fechaExpedicion;
    private String fechaExpiracion;
    private String curp;

    public TarjetaCirculacion(String folio, String numeroSerie, String tipo, String estado, String fechaExpedicion, String fechaExpiracion,String curp) {
        this.folio = folio;
        this.numeroSerie = numeroSerie;
        this.tipo = tipo;
        this.estado = estado;
        this.fechaExpedicion = fechaExpedicion;
        this.fechaExpiracion = fechaExpiracion;
        this.curp=curp;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaExpedicion() {
        return fechaExpedicion;
    }

    public void setFechaExpedicion(String fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
    }

    public String getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(String fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }
}
