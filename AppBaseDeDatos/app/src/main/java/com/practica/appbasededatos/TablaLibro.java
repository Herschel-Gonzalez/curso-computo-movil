package com.practica.appbasededatos;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class TablaLibro {
    private BaseDatosSqLite bdSQLite;
    private SQLiteDatabase bd;
    private Cursor fila;
    private Activity contexto;
    public TablaLibro(Activity contexto){
        this.contexto=contexto;
        bdSQLite= new BaseDatosSqLite(contexto,"libreria", null, 1);
        bd = bdSQLite.getWritableDatabase();

    }
    public Libro generarLibro(Cursor fila){
        Libro libro=new Libro();
        libro.setId(fila.getString(0));
        libro.setTitulo(fila.getString(1));
        libro.setAutor(fila.getString(2));
        libro.setPrecio(fila.getString(3));
        libro.setDisponible(fila.getString(4));
        return libro;

    }
    public Libro existe(String clave){
        fila = bd.rawQuery("select * from libro where Id=" + clave, null);
        if (fila.moveToFirst()) {
            return generarLibro(fila);
        }
        return null;
    }
    public void guardar(Libro libro){
        ContentValues registro = new ContentValues();
        registro.put("id", libro.getId());
        registro.put("titulo", libro.getTitulo());
        registro.put("autor", libro.getAutor());
        registro.put("precio", libro.getPrecio());
        registro.put("disponible", libro.getDisponible());
        bd.insert("libro", null, registro);
    }
    public void actualizarCursor(){
        fila.close();
        fila = bd.rawQuery("select * from libro", null);
    }
    public int eliminar(String clave) {
        int cant = bd.delete("libro", "Id=" + clave, null);
        actualizarCursor();
        return cant;
    }
    public int modificacion(Libro libro) {
        ContentValues registro = new ContentValues();
        registro.put("id", libro.getId());
        registro.put("titulo", libro.getTitulo());
        registro.put("autor", libro.getAutor());
        registro.put("precio", libro.getPrecio());
        registro.put("disponible", libro.getDisponible());
        return  bd.update("libro", registro, "Id="+libro.getId(), null);

    }
    public Libro getPrimero(){
        //System.out.print("en getPrimero");
        fila = bd.rawQuery("select * from libro", null);

        if (fila.moveToFirst()) {
            return generarLibro(fila);
        }
        return null;

    }
    public Libro getUltimo(){
        fila = bd.rawQuery("select * from libro", null);
        if (fila.getCount()>0) {
            int posicion=fila.getCount();
            fila.moveToPosition(posicion-1);
            return generarLibro(fila);
        }
        return null;

    }
    public Libro getSiguiente(){
        if (fila.moveToNext()) {
            return generarLibro(fila);
        }else{
            return getPrimero();
        }
    }
    public Libro getAnterior(){
        if (fila.moveToPrevious()) {
            return generarLibro(fila);
        }
        else{
            return getUltimo();
        }

    }
    public void cerrar(){
        bd.close();
    }
}
