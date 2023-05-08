package com.practica.examenu2herschelgonzalez;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView codigo,nombre,capital,continente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<String>lines =  Utileria.getCountryLines(this);
        List<Pais>paises = Utileria.getCountries(lines,this);

        List<String> nombrePaises = new ArrayList<>();
        for (int i = 0; i < paises.size(); i++) {
            nombrePaises.add(paises.get(i).getNombre());
        }

        //generating the ListView
        ListView listView;
        listView = findViewById(R.id.list_countries);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.activity_list_view,nombrePaises);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                View alert = getLayoutInflater().inflate(R.layout.dialog_country,null);

                builder.setView(alert);
                AlertDialog dialog = builder.create();
                dialog.show();
                codigo = alert.findViewById(R.id.codigo);
                nombre = alert.findViewById(R.id.nombre);
                capital = alert.findViewById(R.id.capital);
                continente = alert.findViewById(R.id.continente);

                //getting the properties from the current book
                String countrieSelected = nombrePaises.get(i);
                for (int j = 0; j < paises.size(); j++) {
                    Pais pais = paises.get(j);
                    if (pais.getNombre().equals(countrieSelected)){
                        //setting the properties to the dialog
                        codigo.setText(String.valueOf(pais.getCodigo()));
                        nombre.setText(pais.getNombre());
                        capital.setText(pais.getCapital());
                        continente.setText(pais.getContinente());
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent=null;

        switch (item.getItemId()){
            case R.id.america:
                intent = new Intent(this,ContinenteActivity.class);
                intent.putExtra("continente","america");
                break;
            case R.id.africa:
                intent = new Intent(getApplicationContext(),ContinenteActivity.class);
                intent.putExtra("continente","africa");
                break;
            case R.id.asia:
                intent = new Intent(getApplicationContext(),ContinenteActivity.class);
                intent.putExtra("continente","asia");
                break;
            case R.id.oceania:
                intent = new Intent(getApplicationContext(),ContinenteActivity.class);
                intent.putExtra("continente","oceania");
                break;
            case R.id.europa:
                intent = new Intent(getApplicationContext(),ContinenteActivity.class);
                intent.putExtra("continente","europa");
                break;

        }
        startActivity(intent);
        return true;
    }
}