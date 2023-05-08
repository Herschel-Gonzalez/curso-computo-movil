package com.practica.examenu2herschelgonzalez;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ContinenteActivity extends AppCompatActivity {

    TextView codigo,nombre,capital,cont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continente);
        String continente = getIntent().getStringExtra("continente");

        List<String> lines =  Utileria.getCountryLines(this);
        List<Pais>paises = Utileria.getCountries(lines,this);

        Toast.makeText(this, continente, Toast.LENGTH_SHORT).show();


        List<Pais>paisesFiltrados = new ArrayList<>();


        switch (continente){
            case "america":
                for (int i = 0; i < paises.size(); i++) {
                    if (paises.get(i).getContinente().equals("America")){
                        paisesFiltrados.add(paises.get(i));
                    }
                }
                break;
            case "asia":
                for (int i = 0; i < paises.size(); i++) {
                    if (paises.get(i).getContinente().equals("Asia")){
                        paisesFiltrados.add(paises.get(i));
                    }
                }
                break;
            case "oceania":
                for (int i = 0; i < paises.size(); i++) {
                    if (paises.get(i).getContinente().equals("Oceania")){
                        paisesFiltrados.add(paises.get(i));
                    }
                }
                break;
            case "europa":
                for (int i = 0; i < paises.size(); i++) {
                    if (paises.get(i).getContinente().equals("Europa")){
                        paisesFiltrados.add(paises.get(i));
                    }
                }
                break;
            case "africa":
                for (int i = 0; i < paises.size(); i++) {
                    if (paises.get(i).getContinente().equals("Africa")){
                        paisesFiltrados.add(paises.get(i));
                    }
                }
                break;
        }

        List<String> nombrePaises = new ArrayList<>();
        for (int i = 0; i < paisesFiltrados.size(); i++) {
            nombrePaises.add(paisesFiltrados.get(i).getNombre());
        }

        //generating the ListView
        ListView listView;
        listView = findViewById(R.id.listCountriesContinent);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.activity_list_view,nombrePaises);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ContinenteActivity.this);
                View alert = getLayoutInflater().inflate(R.layout.dialog_country,null);

                builder.setView(alert);
                AlertDialog dialog = builder.create();
                dialog.show();
                codigo = alert.findViewById(R.id.codigo);
                nombre = alert.findViewById(R.id.nombre);
                capital = alert.findViewById(R.id.capital);
                cont = alert.findViewById(R.id.continente);

                //getting the properties from the current country
                String countrieSelected = nombrePaises.get(i);
                for (int j = 0; j < paisesFiltrados.size(); j++) {
                    Pais pais = paisesFiltrados.get(j);
                    if (pais.getNombre().equals(countrieSelected)){
                        //setting the properties to the dialog
                        codigo.setText(String.valueOf(pais.getCodigo()));
                        nombre.setText(pais.getNombre());
                        capital.setText(pais.getCapital());
                        cont.setText(pais.getContinente());
                    }
                }

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