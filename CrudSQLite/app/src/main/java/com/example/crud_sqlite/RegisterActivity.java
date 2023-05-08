package com.example.crud_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText isbn,titulo,editorial,edicion;
    Button register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        DbHelper dbHelper = new DbHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        isbn = findViewById(R.id.isbn);
        titulo = findViewById(R.id.titulo);
        editorial = findViewById(R.id.editorial);
        edicion = findViewById(R.id.edicion);
        register = findViewById(R.id.registerBook);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String isbnText =  isbn.getText().toString();
               String tituloText = titulo.getText().toString();
               String editorialText = editorial.getText().toString();
               String edicionText = edicion.getText().toString();
                ContentValues valores = new ContentValues();
                valores.put("isbn", isbnText);
                valores.put("titulo", tituloText);
                valores.put("editorial", editorialText);
                valores.put("edicion", edicionText);
                long id = db.insert("libro", null, valores);
                Toast.makeText(RegisterActivity.this, "Se registro con exito", Toast.LENGTH_SHORT).show();

            }
        });
    }
}