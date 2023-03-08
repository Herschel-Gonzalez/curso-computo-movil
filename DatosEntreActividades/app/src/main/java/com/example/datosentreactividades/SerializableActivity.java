package com.example.datosentreactividades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class SerializableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serializable);
        Bundle objetoRecibido = getIntent().getBundleExtra("product");
        Producto producto = (Producto) objetoRecibido.getSerializable("product");

        System.out.println(producto.getName());


    }
}