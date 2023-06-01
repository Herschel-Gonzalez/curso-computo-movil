package com.practica.examenbd19651164;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "examen";
    private static final int DATABASE_VERSION = 1;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE veiculo (noSerie TEXT PRIMARY KEY, marca TEXT, modelo TEXT,anio TEXT,color TEXT,curp TEXT);");
        db.execSQL("CREATE TABLE propietario (curp TEXT PRIMARY KEY, nombre TEXT, paterno TEXT,materno TEXT);");
        db.execSQL("CREATE TABLE licencia (noLicencia TEXT PRIMARY KEY, curp TEXT, fechaEmision TEXT,fechaExpiracion TEXT,estadoEmision TEXT);");
        db.execSQL("CREATE TABLE tarjetaCirculacion (folio TEXT PRIMARY KEY, curp TEXT, noSerie TEXT,tipo TEXT,estado TEXT,fechaExpedicion TEXT,fechaExpiracion TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS libro;");
        onCreate(db);
    }
}
