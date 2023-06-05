package com.practica.examenbd19651164;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AutosActivity extends AppCompatActivity {
    TextView numeroDeSerie,marca,modelo,anio,curpp,color;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autos);

        List<Auto> autos = Utileria.getAutos(this);
        List<String>nameAutos = new ArrayList<>();
        for (int i = 0; i < autos.size(); i++) {
            nameAutos.add(autos.get(i).getMarca()+" "+autos.get(i).getModelo());
        }

        ListView listView;
        listView = findViewById(R.id.listViewAutos);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.activity_list_view,nameAutos);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AutosActivity.this);
                View alert = getLayoutInflater().inflate(R.layout.dialog_auto,null);

                builder.setView(alert);
                AlertDialog dialog = builder.create();
                dialog.show();
                numeroDeSerie = alert.findViewById(R.id.serie);
                marca = alert.findViewById(R.id.marca);
                modelo = alert.findViewById(R.id.modelo);
                anio = alert.findViewById(R.id.anio);
                curpp = alert.findViewById(R.id.curpp);
                color = alert.findViewById(R.id.color);

                //getting the properties from the current

                    Auto auto = autos.get(i);

                    //setting the properties to the dialog
                    numeroDeSerie.setText(auto.getNumeroDeSerie());
                    marca.setText(auto.getMarca());
                    modelo.setText(auto.getModelo());
                    curpp.setText(auto.getCurp());
                    anio.setText(auto.getAnio());
                    color.setText(auto.getColor());


                Button closeButton = alert.findViewById(R.id.cerrar);
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }
}