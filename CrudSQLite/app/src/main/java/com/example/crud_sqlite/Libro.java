package com.example.crud_sqlite;

public class Libro {
    int isbn;
    String titulo;
    String editorial;
    String edicion;

    public Libro(int isbn,String titulo,String editorial, String edicion){
        this.isbn=isbn;
        this.titulo = titulo;
        this.editorial=editorial;
        this.edicion=edicion;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getEdicion() {
        return edicion;
    }

    public void setEdicion(String edicion) {
        this.edicion = edicion;
    }



}
