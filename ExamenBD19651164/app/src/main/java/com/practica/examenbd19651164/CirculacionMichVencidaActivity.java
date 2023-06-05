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
import java.util.Date;
import java.util.List;

public class CirculacionMichVencidaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circulacion_mich_vencida);
        DbHelper dbHelper = new DbHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        List<String> docVencidos = new ArrayList<>();
        Cursor cursorCirculacion = db.query("tarjetaCirculacion", null, null, null, null, null, null);
        if (cursorCirculacion.moveToFirst()) {
            do {
                @SuppressLint("Range") String curp = cursorCirculacion.getString(cursorCirculacion.getColumnIndex("curp"));
                @SuppressLint("Range") String fExpiracion = cursorCirculacion.getString(cursorCirculacion.getColumnIndex("fechaExpiracion"));
                @SuppressLint("Range") String estado = cursorCirculacion.getString(cursorCirculacion.getColumnIndex("estado"));

                try {
                    Date myDate = new Date();
                    Date expiracion = formato.parse(fExpiracion);
                    if(expiracion.before(myDate) && estado.equals("Michoacan")){
                        if (!docVencidos.contains(curp)){
                            docVencidos.add(curp);
                        }
                    }

                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

            } while (cursorCirculacion.moveToNext());
        }
        cursorCirculacion.close();

        Cursor cursorLicencia = db.query("licencia", null, null, null, null, null, null);
        if (cursorLicencia.moveToFirst()) {
            do {
                @SuppressLint("Range") String curp = cursorLicencia.getString(cursorLicencia.getColumnIndex("curp"));
                @SuppressLint("Range") String fExpiracion = cursorLicencia.getString(cursorLicencia.getColumnIndex("fechaExpiracion"));

                try {
                    Date myDate = new Date();
                    Date expiracion = formato.parse(fExpiracion);

                    for (int i = 0; i < docVencidos.size(); i++) {
                        if(docVencidos.get(i).equals(curp)){
                            if(expiracion.before(myDate)){
                                docVencidos.remove(curp);
                            }
                        }
                    }

                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

            } while (cursorLicencia.moveToNext());
        }
        cursorLicencia.close();


        ListView listView;
        listView = findViewById(R.id.listMich);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.activity_list_view,docVencidos);
        listView.setAdapter(adapter);
    }
}