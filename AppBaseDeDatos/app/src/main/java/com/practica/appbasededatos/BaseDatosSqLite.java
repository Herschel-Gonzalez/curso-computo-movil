package com.practica.appbasededatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDatosSqLite extends SQLiteOpenHelper {
    public BaseDatosSqLite(Context contexto, String nombre, SQLiteDatabase.CursorFactory factoria, int version ){
        super(contexto,nombre,factoria,version);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // TODO Auto-generated method stub
        sqLiteDatabase.execSQL("create table libro(id text primary key, titulo text,autor text, precio text,disponible text)");
        sqLiteDatabase.execSQL("create table miembro(id text primary key, nombre text,direccion text, fechaNacimiento text,fechaRegistro text, telefono text, latitud real, longitud real)");
        sqLiteDatabase.execSQL("create table editor(id text primary key, nombre text, direccion text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // TODO Auto-generated method stub
        sqLiteDatabase.execSQL("drop table if exists libro");
        sqLiteDatabase.execSQL("drop table if exists miembro");
        sqLiteDatabase.execSQL("drop table if exists editor");
        onCreate(sqLiteDatabase);
        //db.execSQL("create table libro(id text primary key, titulo text,autor text, precio text,disponible text)");
    }
}
