package com.practica.appbasededatos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ConsultarActivity extends AppCompatActivity {
    private String Id;
    private EditText editId;
    private EditText editTitulo;
    private EditText editAutor;
    private EditText editPrecio;
    private EditText editDisponible;
    private TablaLibro tablaLibro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);
        tablaLibro=new TablaLibro(this);
        editId=(EditText) findViewById(R.id.editId);
        editTitulo=(EditText) findViewById(R.id.editTitulo);
        editAutor=(EditText) findViewById(R.id.editAutor);
        editPrecio=(EditText) findViewById(R.id.editPrecio);
        editDisponible=(EditText) findViewById(R.id.editDisponible);
        Libro libro=tablaLibro.getPrimero();
        if(libro!=null){
            editId.setText(libro.getId());
            editTitulo.setText(libro.getTitulo());
            editAutor.setText(libro.getAutor());
            editPrecio.setText(libro.getPrecio());
            editDisponible.setText(libro.getDisponible());
            editId.setEnabled(false);
        }else
            Toast.makeText(this, "Este libro no esta registrado", Toast.LENGTH_SHORT).show();
    }
    public void buscarPrimero(View v){

        Libro libro=tablaLibro.getPrimero();
        desplegarLibro(libro);
    }
    public void buscarUltimo(View v){
        Libro libro=tablaLibro.getUltimo();
        desplegarLibro(libro);
    }
    public void buscarSiguiente(View v){
        Libro libro=tablaLibro.getSiguiente();
        desplegarLibro(libro);
    }
    public void buscarAnterior(View v){
        Libro libro=tablaLibro.getAnterior();
        desplegarLibro(libro);

    }
    public void eliminar(View v){
        String id=editId.getText().toString();
        int n=tablaLibro.eliminar(id);
        Toast.makeText(this, "Libro eliminado exitosamente", Toast.LENGTH_LONG).show();
    }
    public void desplegarLibro(Libro libro){
        if(libro!=null){
            editId.setText(libro.getId());
            editTitulo.setText(libro.getTitulo());
            editAutor.setText(libro.getAutor());
            editPrecio.setText(libro.getPrecio());
            editDisponible.setText(libro.getDisponible());
            editId.setEnabled(false);
        }else
            Toast.makeText(this, "Este libro no esta registrado", Toast.LENGTH_SHORT).show();
    }
    public void modificar(View v){
        String id=editId.getText().toString();
        Libro libro=new Libro();
        libro.setId(id);
        libro.setTitulo(editTitulo.getText().toString());
        libro.setAutor(editAutor.getText().toString());
        libro.setPrecio(editPrecio.getText().toString());
        libro.setDisponible(editDisponible.getText().toString());
        tablaLibro.modificacion(libro);
        tablaLibro.actualizarCursor();
    }
}