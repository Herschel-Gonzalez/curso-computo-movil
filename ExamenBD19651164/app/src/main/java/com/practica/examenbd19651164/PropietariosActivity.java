package com.practica.examenbd19651164;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PropietariosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propietarios);

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


        ListView listView;
        listView = findViewById(R.id.listPropietarios);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.activity_list_view,nombres);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PropietariosActivity.this);
                View alert = getLayoutInflater().inflate(R.layout.dialog_propietario,null);

                builder.setView(alert);
                AlertDialog dialog = builder.create();
                dialog.show();
                TextView curpet = alert.findViewById(R.id.curp);
                TextView nombreet = alert.findViewById(R.id.nombre);
                TextView paternoet = alert.findViewById(R.id.paterno);
                TextView maternoet = alert.findViewById(R.id.materno);


                    Propietario propietario = propietarios.get(i);

                        //setting the properties to the dialog
                        curpet.setText(propietario.getCurp());
                        nombreet.setText(propietario.getNombre());
                        paternoet.setText(propietario.getPaterno());
                        maternoet.setText(propietario.getMaterno());

                Button closeButton = alert.findViewById(R.id.cerrar);
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                Button documentos = alert.findViewById(R.id.documentos);
                documentos.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(PropietariosActivity.this, DocumentosPropietarioActivity.class);
                        intent.putExtra("curp", propietario.getCurp());
                        startActivity(intent);

                    }
                });
            }
        });

    }
}