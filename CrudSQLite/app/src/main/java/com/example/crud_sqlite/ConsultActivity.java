package com.example.crud_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
        List<String> libros = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int isbn = cursor.getInt(cursor.getColumnIndex("isbn"));
                @SuppressLint("Range") String titulo = cursor.getString(cursor.getColumnIndex("titulo"));
                @SuppressLint("Range") String editorial = cursor.getString(cursor.getColumnIndex("editorial"));
                @SuppressLint("Range") String edicion = cursor.getString(cursor.getColumnIndex("edicion"));
                //Toast.makeText(this, titulo, Toast.LENGTH_SHORT).show();
                titulos.add(titulo);
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

    public boolean updateBook(SQLiteDatabase db, String isbn, ContentValues values){
        int filasActualizadas = db.update("libro", values, "isbn = ?", new String[]{String.valueOf(isbn)});
        return filasActualizadas>0;
    }

}