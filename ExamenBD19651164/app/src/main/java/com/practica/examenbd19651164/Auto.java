package com.practica.examenbd19651164;

public class Auto {

    private String numeroDeSerie;
    private String marca;
    private String modelo;
    private String anio;
    private String curp;
    private String color;

    public Auto(String numeroDeSerie, String marca, String modelo, String anio, String curp, String color) {
        this.numeroDeSerie = numeroDeSerie;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.curp = curp;
        this.color = color;
    }

    public String getNumeroDeSerie() {
        return numeroDeSerie;
    }

    public void setNumeroDeSerie(String numeroDeSerie) {
        this.numeroDeSerie = numeroDeSerie;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
