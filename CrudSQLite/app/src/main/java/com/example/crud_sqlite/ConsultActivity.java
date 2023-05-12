package com.example.crud_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ConsultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult);
        DbHelper dbHelper = new DbHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("libro", null, null, null, null, null, null);
        List<String> titulos = new ArrayList<>();
        List<Libro> libros = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int isbn = cursor.getInt(cursor.getColumnIndex("isbn"));
                @SuppressLint("Range") String titulo = cursor.getString(cursor.getColumnIndex("titulo"));
                @SuppressLint("Range") String editorial = cursor.getString(cursor.getColumnIndex("editorial"));
                @SuppressLint("Range") String edicion = cursor.getString(cursor.getColumnIndex("edicion"));
                //Toast.makeText(this, titulo, Toast.LENGTH_SHORT).show();
                titulos.add(titulo);
                Libro libro = new Libro(isbn,titulo,editorial,edicion);
                libros.add(libro);
            } while (cursor.moveToNext());
        }
        cursor.close();

        ListView listView;
        listView = findViewById(R.id.listBooks);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.activity_list_view,titulos);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ConsultActivity.this);
                View alert = getLayoutInflater().inflate(R.layout.activity_libro,null);

                builder.setView(alert);
                AlertDialog dialog = builder.create();
                dialog.show();
                EditText titulo = alert.findViewById(R.id.consultTitulo);
                EditText editorial = alert.findViewById(R.id.consultEditorial);
                EditText edicion = alert.findViewById(R.id.consultEdicion);

                Button actualizarLibro = alert.findViewById(R.id.actualizarLibro);
                Button eliminarLibro = alert.findViewById(R.id.eliminar);
                Button cancelar = alert.findViewById(R.id.cerrar);

                Libro libro = libros.get(i);

                titulo.setText(libro.getTitulo());
                editorial.setText(libro.getEditorial());
                edicion.setText(libro.getEdicion());

                cancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                actualizarLibro.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ContentValues values = new ContentValues();
                        values.put("titulo",titulo.getText().toString());
                        values.put("editorial",editorial.getText().toString());
                        values.put("edicion",edicion.getText().toString());
                        boolean updated = updateBook(db,libro.getIsbn(),values);
                        System.out.println(values);
                        if (updated){
                            Toast.makeText(ConsultActivity.this, "Se actualizo con exito", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            recreate();
                        }else{
                            Toast.makeText(ConsultActivity.this, "Ocurrio un problema al actualizar el libro", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                eliminarLibro.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean deleted = deleteBook(db,String.valueOf(libro.getIsbn()));
                        if (deleted){
                            Toast.makeText(ConsultActivity.this, "Se elimino con exito", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            recreate();
                        }else{
                            Toast.makeText(ConsultActivity.this, "Ocurrio un erro al eliminar el registro", Toast.LENGTH_SHORT).show();
                        }
                    }
                });





            }
        });

    }

    public boolean deleteBook(SQLiteDatabase db,String isbn){
        String whereClause = "isbn = ?";
        String[] whereArgs = { isbn };
        int rowsDeleted = db.delete("libro", whereClause, whereArgs);
        if (rowsDeleted > 0) {
            // El registro se eliminó correctamente
            return true;
        } else {
            // No se eliminó ningún registro
            return  false;
        }

    }

    public boolean updateBook(SQLiteDatabase db, int isbn, ContentValues values){
        int filasActualizadas = db.update("libro", values, "isbn = ?", new String[]{String.valueOf(isbn)});
        db.close();
        return filasActualizadas>0;
    }

}