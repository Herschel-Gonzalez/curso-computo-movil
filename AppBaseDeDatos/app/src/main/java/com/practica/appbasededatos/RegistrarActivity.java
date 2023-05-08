package com.practica.appbasededatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class RegistrarActivity extends AppCompatActivity {
    private Spinner selectorDisponible;
    private EditText editId;
    private EditText editTitulo;
    private EditText editAutor;
    private EditText editPrecio;
    private EditText editDisponible;
    private String disponible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        editId=(EditText) findViewById(R.id.editTextId);
        editTitulo=(EditText) findViewById(R.id.editTextTitulo);
        editAutor=(EditText) findViewById(R.id.editTextAutor);
        editPrecio=(EditText) findViewById(R.id.editTextPrecio);
        selectorDisponible=(Spinner) findViewById(R.id.spinnerDisponible);
        Resources res = getResources();
        final String opcionesDisponible[] = res.getStringArray(R.array.disponible);
        //ArrayAdapter adaptador=ArrayAdapter.createFromResource(this, R.array.ediciones, android.R.layout.simple_spinner_item);
        ArrayAdapter adaptador=new ArrayAdapter(this,android.R.layout.simple_spinner_item,opcionesDisponible);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectorDisponible.setAdapter(adaptador);
        selectorDisponible.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> lista, View v, int posicion, long id) {
                // TODO Auto-generated method stub
                disponible=opcionesDisponible[posicion];

            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }

        });

    }
    private boolean hayCajasVacias(){
        boolean b1=editId.getText().toString().equals("");
        boolean b2=editTitulo.getText().toString().equals("");
        boolean b3=editAutor.getText().toString().equals("");
        boolean b4=editPrecio.getText().toString().equals("");
        // boolean b5=editDisponible.getText().toString().equals("");
        if(b1||b2||b3||b4)
            return true;
        return false;

    }
    public void limpiarCajas(){
        editId.setText("");
        editTitulo.setText("");
        editAutor.setText("");
        editPrecio.setText("");
        selectorDisponible.setSelection(0);
        editId.requestFocus();

    }
    public void guardar(View v){
        if(!hayCajasVacias()){
            TablaLibro tablaLibro=new TablaLibro(this);
            String clave=editId.getText().toString();
            if(tablaLibro.existe(clave)==null){
                Libro libro=new Libro();
                libro.setId(clave);
                libro.setTitulo(editTitulo.getText().toString());
                libro.setAutor(editAutor.getText().toString());
                libro.setPrecio(editPrecio.getText().toString());
                libro.setDisponible(disponible);
                tablaLibro.guardar(libro);
                Toast.makeText(this, "Libro almacenado con exito",Toast.LENGTH_SHORT).show();
                limpiarCajas();
            }else
                Toast.makeText(this, "El libro ya se encuentra registrado",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Favor de ingresar todos los datos que se piden",Toast.LENGTH_LONG).show();
        }
    }
}