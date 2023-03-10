package com.example.datosentreactividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.io.Serializable;
import java.math.BigInteger;

public class MainActivity extends AppCompatActivity {

    Intent intent = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //PASO DE OBJETO DE OBJETOS PARCELABLES

        findViewById(R.id.cadenas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nombreCompleto = "Herschel Gonzalez Posadas";
                intent = new Intent(getApplicationContext(), CadenasActivity.class);
                intent.putExtra("nombreCompleto", nombreCompleto);
                startActivity(intent);

            }
        });

        findViewById(R.id.numeros).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int idArticulo = 22;
                double costo = 999.23;
                float peso = 23.5f;
                intent = new Intent(getApplicationContext(), NumerosActivity.class);
                intent.putExtra("idArticulo", idArticulo);
                intent.putExtra("costo", costo);
                intent.putExtra("peso", peso);
                startActivity(intent);

            }
        });

        findViewById(R.id.arrays).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] productos = {"Iphone 12 pro","Xiaomi Mi 11","Alcatel Pixi 5"};
                float [] costos = {999.2f,9822.4f,7777.5f};
                intent = new Intent(getApplicationContext(), ArraysActivity.class);
                intent.putExtra("productos", productos);
                intent.putExtra("costos", costos);
                startActivity(intent);
            }
        });

        findViewById(R.id.serializables).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Producto iphone = new Producto("iPhone 13 pro max",29999.3f,0.33f);
                intent = new Intent(getApplicationContext(), CadenasActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("product", (Serializable) iphone);
                intent.putExtra("product", bundle);
                startActivity(intent);
            }
        });

        findViewById(R.id.object_array).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Producto iphone = new Producto("iPhone 13 pro max",29999.3f,0.33f);
                Producto alcatel = new Producto("Alcatel Pixi 2",999.3f,0.13f);
                Producto productos [] = {iphone,alcatel};
                intent = new Intent(getApplicationContext(), CadenasActivity.class);
                intent.putExtra("productos", productos);
                startActivity(intent);
            }
        });

        findViewById(R.id.libros).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(),BooksActivity.class);
                startActivity(intent);
            }
        });


    }
}

