package com.example.datosentreactividades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ArraysActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrays);

        String productos[] = getIntent().getStringArrayExtra("productos");
        float costos[]= getIntent().getFloatArrayExtra("costos");

        TextView producto1 = findViewById(R.id.producto1);
        String product1 = productos[0]+" $"+costos[0];
        producto1.setText(product1);

        TextView producto2 = findViewById(R.id.producto2);
        String product2 = productos[1]+" $"+costos[1];
        producto2.setText(product2);

        TextView producto3 = findViewById(R.id.producto3);
        String product3 = productos[2]+" $"+costos[2];
        producto3.setText(product3);


    }
}