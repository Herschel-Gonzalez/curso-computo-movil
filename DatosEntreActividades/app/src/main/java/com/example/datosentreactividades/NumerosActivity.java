package com.example.datosentreactividades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class NumerosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numeros);
        TextView id = findViewById(R.id.identificador);
        TextView costo = findViewById(R.id.costo);
        TextView peso = findViewById(R.id.peso);

        id.setText(String.valueOf(getIntent().getIntExtra("idArticulo",2)));
        costo.setText(String.valueOf(getIntent().getFloatExtra("costo",22.3f)));
        peso.setText(String.valueOf(getIntent().getFloatExtra("peso",0.2f)));

    }
}