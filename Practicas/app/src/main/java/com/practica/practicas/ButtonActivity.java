package com.practica.practicas;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ButtonActivity extends AppCompatActivity {
    private Button boton,boton1;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);
        boton = findViewById(R.id.botonEjemplo);
        boton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ExampleButtonsActivity.class);
                startActivity(intent);
            }
        });

        boton1 = findViewById(R.id.botonEnviar);

        boton1.setOnClickListener(new OyenteBotonEnviar());

    }
    private class OyenteBotonEnviar implements OnClickListener{

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(),ExampleButtonsActivity.class);
            startActivity(intent);
        }
    }
}