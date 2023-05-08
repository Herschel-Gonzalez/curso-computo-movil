package com.practica.sensores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SensorInformation extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_information);
        textView = findViewById(R.id.textView);
        Intent intent = getIntent();
        String datoRecibido = intent.getStringExtra("information");
        textView.setText(datoRecibido);

    }
}