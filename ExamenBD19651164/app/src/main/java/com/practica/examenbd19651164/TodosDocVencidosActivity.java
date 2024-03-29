package com.practica.examenbd19651164;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class TodosDocVencidosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todos_doc_vencidos);
        DbHelper dbHelper = new DbHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("propietario", null, null, null, null, null, null);
        List<String> nombres = new ArrayList<>();

        List<Propietario> propietarios = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String curp = cursor.getString(cursor.getColumnIndex("curp"));
                @SuppressLint("Range") String nombre = cursor.getString(cursor.getColumnIndex("nombre"));
                @SuppressLint("Range") String paterno = cursor.getString(cursor.getColumnIndex("paterno"));
                @SuppressLint("Range") String materno = cursor.getString(cursor.getColumnIndex("materno"));
                //Toast.makeText(this, titulo, Toast.LENGTH_SHORT).show();
                nombres.add(nombre);
                Propietario propietario = new Propietario(curp,nombre,paterno,materno);
                propietarios.add(propietario);
            } while (cursor.moveToNext());
        }
        cursor.close();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        List<String> docVencidos = new ArrayList<>();
        Cursor cursorLicencia = db.query("licencia", null, null, null, null, null, null);
        if (cursorLicencia.moveToFirst()) {
            do {
                @SuppressLint("Range") String curp = cursorLicencia.getString(cursorLicencia.getColumnIndex("curp"));
                @SuppressLint("Range") String fExpiracion = cursorLicencia.getString(cursorLicencia.getColumnIndex("fechaExpiracion"));

                try {
                    Date myDate = new Date();
                    Date expiracion = formato.parse(fExpiracion);
                    if(expiracion.before(myDate)){
                        docVencidos.add(curp);
                    }

                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

            } while (cursorLicencia.moveToNext());
        }
        cursorLicencia.close();


        Cursor cursorCirculacion = db.query("tarjetaCirculacion", null, null, null, null, null, null);
        if (cursorCirculacion.moveToFirst()) {
            do {
                @SuppressLint("Range") String curp = cursorCirculacion.getString(cursorCirculacion.getColumnIndex("curp"));
                @SuppressLint("Range") String fExpiracion = cursorCirculacion.getString(cursorCirculacion.getColumnIndex("fechaExpiracion"));

                try {
                    Date myDate = new Date();
                    Date expiracion = formato.parse(fExpiracion);
                    if(expiracion.before(myDate)){
                        docVencidos.add(curp);
                    }

                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

            } while (cursorCirculacion.moveToNext());
        }
        cursorCirculacion.close();

        List<Propietario> propietariosVencidos = new ArrayList<>();
        List<String> nombresVencidos = new ArrayList<>();
        for (int i = 0; i < propietarios.size(); i++) {
            Propietario propietario = propietarios.get(i);
            int frecuencia = Collections.frequency(docVencidos, propietario.getCurp());
            if(frecuencia==2){
                propietariosVencidos.add(propietario);
                nombresVencidos.add(propietario.getNombre());
            }
        }





        ListView listView;
        listView = findViewById(R.id.listAllVencidos);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.activity_list_view,nombresVencidos);
        listView.setAdapter(adapter);
    }
}