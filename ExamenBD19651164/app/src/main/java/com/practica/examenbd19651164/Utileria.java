package com.practica.examenbd19651164;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Utileria {
    public static Licencia getLicencia(String curp, Context context){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Licencia licencia = null;
        Cursor cursorLicencia = db.query("licencia", null, null, null, null, null, null);
        if (cursorLicencia.moveToFirst()) {
            do {
                @SuppressLint("Range") String curp2 = cursorLicencia.getString(cursorLicencia.getColumnIndex("curp"));
                @SuppressLint("Range") String noLicencia = cursorLicencia.getString(cursorLicencia.getColumnIndex("noLicencia"));
                @SuppressLint("Range") String fExpiracion = cursorLicencia.getString(cursorLicencia.getColumnIndex("fechaExpiracion"));
                @SuppressLint("Range") String fEmision = cursorLicencia.getString(cursorLicencia.getColumnIndex("fechaEmision"));
                @SuppressLint("Range") String estadoEmision = cursorLicencia.getString(cursorLicencia.getColumnIndex("estadoEmision"));
                if (curp.equals(curp2)){
                    licencia = new Licencia(noLicencia,curp,fEmision,fExpiracion,estadoEmision);
                }

            } while (cursorLicencia.moveToNext());
        }
        cursorLicencia.close();
        return licencia;
    }

    public static TarjetaCirculacion getTarjetaCirculacion(String curp,Context context){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        TarjetaCirculacion tarjetaCirculacion = null;
        Cursor cursor = db.query("tarjetaCirculacion", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String curp2 = cursor.getString(cursor.getColumnIndex("curp"));
                @SuppressLint("Range") String folio = cursor.getString(cursor.getColumnIndex("folio"));
                @SuppressLint("Range") String noSerie = cursor.getString(cursor.getColumnIndex("noSerie"));
                @SuppressLint("Range") String tipo = cursor.getString(cursor.getColumnIndex("tipo"));
                @SuppressLint("Range") String estado = cursor.getString(cursor.getColumnIndex("estado"));
                @SuppressLint("Range") String fechaExpedicion = cursor.getString(cursor.getColumnIndex("fechaExpedicion"));
                @SuppressLint("Range") String fechaExpiracion = cursor.getString(cursor.getColumnIndex("fechaExpiracion"));
                if (curp.equals(curp2)){
                    tarjetaCirculacion = new TarjetaCirculacion(folio,noSerie,tipo,estado,fechaExpedicion,fechaExpiracion,curp);
                }

            } while (cursor.moveToNext());
        }
        cursor.close();
        return tarjetaCirculacion;
    }

    public static List<String> getDocumentosPorVencer(Context context){
        List<String>docPorVencer = new ArrayList<>();
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Cursor cursor = db.query("tarjetaCirculacion", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String curp2 = cursor.getString(cursor.getColumnIndex("curp"));
                @SuppressLint("Range") String folio = cursor.getString(cursor.getColumnIndex("folio"));
                @SuppressLint("Range") String noSerie = cursor.getString(cursor.getColumnIndex("noSerie"));
                @SuppressLint("Range") String tipo = cursor.getString(cursor.getColumnIndex("tipo"));
                @SuppressLint("Range") String estado = cursor.getString(cursor.getColumnIndex("estado"));
                @SuppressLint("Range") String fechaExpedicion = cursor.getString(cursor.getColumnIndex("fechaExpedicion"));
                @SuppressLint("Range") String fechaExpiracion = cursor.getString(cursor.getColumnIndex("fechaExpiracion"));

                try {
                    Date myDate = new Date();
                    Date expiracion = formato.parse(fechaExpiracion);
                    if(expiracion.getYear()==myDate.getYear()){
                        if(!docPorVencer.contains(curp2)){
                            docPorVencer.add(curp2);
                        }
                    }

                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }


            } while (cursor.moveToNext());
        }
        cursor.close();

        Cursor cursorLicencia = db.query("licencia", null, null, null, null, null, null);
        if (cursorLicencia.moveToFirst()) {
            do {
                @SuppressLint("Range") String curp2 = cursorLicencia.getString(cursorLicencia.getColumnIndex("curp"));
                @SuppressLint("Range") String noLicencia = cursorLicencia.getString(cursorLicencia.getColumnIndex("noLicencia"));
                @SuppressLint("Range") String fExpiracion = cursorLicencia.getString(cursorLicencia.getColumnIndex("fechaExpiracion"));
                @SuppressLint("Range") String fEmision = cursorLicencia.getString(cursorLicencia.getColumnIndex("fechaEmision"));
                @SuppressLint("Range") String estadoEmision = cursorLicencia.getString(cursorLicencia.getColumnIndex("estadoEmision"));

                try {
                    Date myDate = new Date();
                    Date expiracion = formato.parse(fExpiracion);
                    if(expiracion.getYear()==myDate.getYear()){
                        if(!docPorVencer.contains(curp2)){
                            docPorVencer.add(curp2);
                        }
                    }

                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

            } while (cursorLicencia.moveToNext());
        }
        cursorLicencia.close();
        return docPorVencer;
    }

    public static List<Auto> getAutos(Context context){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //List<String>autosName = new ArrayList<>();
        List<Auto>autos = new ArrayList<>();
        Cursor cursor = db.query("veiculo", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String marca = cursor.getString(cursor.getColumnIndex("marca"));
                @SuppressLint("Range") String modelo = cursor.getString(cursor.getColumnIndex("modelo"));
                @SuppressLint("Range") String noSerie = cursor.getString(cursor.getColumnIndex("noSerie"));
                @SuppressLint("Range") String anio = cursor.getString(cursor.getColumnIndex("anio"));
                @SuppressLint("Range") String color = cursor.getString(cursor.getColumnIndex("color"));
                @SuppressLint("Range") String curp = cursor.getString(cursor.getColumnIndex("curp"));


                //autosName.add(marca+" "+modelo);
                Auto auto = new Auto(noSerie,marca,modelo,anio,curp,color);
                autos.add(auto);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return autos;
    }


}
