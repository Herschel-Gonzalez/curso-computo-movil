package com.practica.examenbd19651164;

public class Licencia {
    private String numeroLicencia;
    private String curp;
    private String fechaEmision;
    private String fechaExpiracion;
    private String estado;

    public Licencia(String numeroLicencia, String curp, String fechaEmision, String fechaExpiracion, String estado) {
        this.numeroLicencia = numeroLicencia;
        this.curp = curp;
        this.fechaEmision = fechaEmision;
        this.fechaExpiracion = fechaExpiracion;
        this.estado = estado;
    }


    public String getNumeroLicencia() {
        return numeroLicencia;
    }

    public void setNumeroLicencia(String numeroLicencia) {
        this.numeroLicencia = numeroLicencia;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(String fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


}
