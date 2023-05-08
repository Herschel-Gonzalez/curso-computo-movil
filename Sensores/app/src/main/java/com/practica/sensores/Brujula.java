package com.practica.sensores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Brujula extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor magnetometer;
    TextView textView;
    private float[] magnetometerReading = new float[3];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brujula);
        // Obtiene el servicio del sensor
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // Obtiene el sensor magnómetro
        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

    }

    @Override
    protected void onResume() {
        super.onResume();

        // Registra el listener del sensor
        sensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Detiene el listener del sensor
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // Obtiene los valores del sensor
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            magnetometerReading = event.values;
        }

        // Actualiza la dirección en la brújula
        updateCompass();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // No se utiliza en este ejemplo
    }

    private void updateCompass() {
        // Obtiene la dirección en grados
        float degrees = getDegreeFromOrientation(magnetometerReading);
        textView = findViewById(R.id.textView2);
        textView.setText(degrees+"");
        // Actualiza la vista de la brújula
        ImageView compass = findViewById(R.id.imageView);
        compass.setRotation(-degrees);
    }

    private float getDegreeFromOrientation(float[] orientation) {
        float degree = 0;
        if (orientation != null) {
            degree = (float) Math.toDegrees(Math.atan2(orientation[0], orientation[1]));
        }
        return degree;
    }
}