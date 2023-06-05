package com.practica.examenbd19651164;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AgregarAutoActivity extends AppCompatActivity {
    EditText noSerie,marca,modelo,anio,color;
    Button registrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_auto);
        DbHelper dbHelper = new DbHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        noSerie = findViewById(R.id.numeroSerie);
        marca = findViewById(R.id.marca);
        modelo = findViewById(R.id.modelo);
        anio = findViewById(R.id.anio);
        color = findViewById(R.id.color);
        registrar = findViewById(R.id.btnRegistrarAuto);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues valoresPropietario = new ContentValues();
                valoresPropietario.put("noSerie", noSerie.getText().toString());
                valoresPropietario.put("marca", marca.getText().toString());
                valoresPropietario.put("modelo", modelo.getText().toString());
                valoresPropietario.put("anio", anio.getText().toString());
                valoresPropietario.put("color", color.getText().toString());
                valoresPropietario.put("curp", "sin propietario");
                long idVeiculo = db.insert("veiculo", null, valoresPropietario);
                Toast.makeText(AgregarAutoActivity.this, "Se agrego con exito el auto", Toast.LENGTH_SHORT).show();
            }
        });




    }
}