package com.practica.examenu2herschelgonzalez;

public class Pais {
    private int codigo;
    private String nombre;
    private String capital;
    private String continente;

    public Pais(int codigo,String nombre,String capital,String continente){
        this.codigo = codigo;
        this.nombre = nombre;
        this.capital = capital;
        this.continente = continente;
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

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getContinente() {
        return continente;
    }

    public void setContinente(String continente) {
        this.continente = continente;
    }
}
