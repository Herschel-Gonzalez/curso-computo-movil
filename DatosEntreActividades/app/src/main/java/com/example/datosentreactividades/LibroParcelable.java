package com.example.datosentreactividades;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.sql.SQLInput;

public class LibroParcelable implements Parcelable {
    private String titulo;
    private String isbn;

    private String autor;
    private String editorial;
    private String edicion;
    private String idioma;


    protected LibroParcelable() {
        titulo = in.readString();
        isbn = in.readString();
    }
    public LibroParcelable(String isbn,String titulo,String autor,String editorial,String edicion,String idioma){
        this.isbn = isbn;
        this.titulo=titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.edicion = edicion;
        this.idioma = idioma;
    }

    public static final Creator<LibroParcelable> CREATOR = new Creator<LibroParcelable>() {
        @Override
        public LibroParcelable createFromParcel(Parcel in) {
            return new LibroParcelable();
        }

        @Override
        public LibroParcelable[] newArray(int size) {
            return new LibroParcelable[size];
        }
    };

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
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

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }



    @NonNull
    @Override
    public String toString() {
        return titulo;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        LibroParcelable libro = (LibroParcelable) obj;
        return libro.getIsbn().equals(isbn);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(titulo);
        parcel.writeString(isbn);
    }
}
