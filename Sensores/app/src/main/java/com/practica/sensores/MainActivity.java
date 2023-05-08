package com.practica.sensores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String listSensor = "";
        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
        for (Sensor sensor : sensorList) {
            String sensorName = sensor.getName();
            listSensor+=sensorName+"\n";
        }


        String[] datos = new String[sensorList.size()];

        for (int i = 0; i < sensorList.size(); i++) {
            datos[i]=sensorList.get(i).getName();
        }

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                datos
        );

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adaptador);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                boolean otro = true;
                if(datos[i].contains("Magnetometer")){
                    otro = false;
                    Intent intent = new Intent(MainActivity.this, Brujula.class);
                    startActivity(intent);
                }

                if(datos[i].contains("Accelerometer")){
                    otro = false;
                    Intent intent = new Intent(MainActivity.this, Accelerometer.class);
                    startActivity(intent);
                }

                if(datos[i].contains("Proximity")){
                    otro = false;
                    Intent intent = new Intent(MainActivity.this, Proximity.class);
                    startActivity(intent);
                }

                if(datos[i].contains("Ambient Light")){
                    otro = false;
                    Intent intent = new Intent(MainActivity.this, AmbientLight.class);
                    startActivity(intent);
                }

                if (otro){
                    Intent intent = new Intent(MainActivity.this, SensorInformation.class);
                    String information = "";
                    information+=sensorList.get(i).getName()+"\n";
                    information+=sensorList.get(i).getVendor()+"\n";
                    information+=sensorList.get(i).getId()+"\n";
                    information+=sensorList.get(i).getType()+"\n";
                    information+=sensorList.get(i).getVersion()+"\n";
                    intent.putExtra("information", information);
                    startActivity(intent);
                }

            }
        });




    }
}