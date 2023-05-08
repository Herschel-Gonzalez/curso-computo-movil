package com.practica.appbasededatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class OpcionesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones);
    }
    public void registrar(View v) {
        Intent intento = new Intent(this, RegistrarActivity.class);
        startActivity(intento);
    }

    public void consultar(View v) {
        Intent intento = new Intent(this, ConsultarActivity.class);
        startActivity(intento);
    }
}