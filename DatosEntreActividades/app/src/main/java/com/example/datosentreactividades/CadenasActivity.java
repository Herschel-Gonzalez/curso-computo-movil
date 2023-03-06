package com.example.datosentreactividades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class CadenasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadenas);
        String nombre = getIntent().getStringExtra("nombreCompleto");

        TextView name = findViewById(R.id.nombre);
        name.setText(nombre);

    }
}