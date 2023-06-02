package com.practica.examenbd19651164;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class DocPorVencerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_por_vencer);
        List<String> propietarios = Utileria.getDocumentosPorVencer(this);
        ListView listView;
        listView = findViewById(R.id.listDocPorVencer);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.activity_list_view,propietarios);
        listView.setAdapter(adapter);
    }
}