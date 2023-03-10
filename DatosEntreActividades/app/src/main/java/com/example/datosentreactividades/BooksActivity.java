package com.example.datosentreactividades;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.*;

import java.util.ArrayList;
import java.util.List;

public class BooksActivity extends AppCompatActivity {

    TextView title,author,editorial,edition,language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        List<String> lines =  Utilities.getBookLines(this);
        List<LibroParcelable> parcelableBooks = Utilities.getParcelableBooks(lines,this);

        //creating an array of title books
        List<String> titleBooks = new ArrayList<>();
        for (int i = 0; i < parcelableBooks.size(); i++) {
            titleBooks.add(parcelableBooks.get(i).getTitulo());
        }

        //generating the ListView
        ListView listView;
        listView = findViewById(R.id.list_books);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(this,R.layout.activity_list_view,titleBooks);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(BooksActivity.this, titleBooks.get(i), Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(BooksActivity.this);
                View alert = getLayoutInflater().inflate(R.layout.dialog_book,null);

                builder.setView(alert);
                AlertDialog dialog = builder.create();
                dialog.show();
                title = alert.findViewById(R.id.titulo);
                author = alert.findViewById(R.id.autor);
                editorial = alert.findViewById(R.id.editorial);
                edition = alert.findViewById(R.id.edicion);
                language = alert.findViewById(R.id.idioma);

                //getting the properties from the current book
                String bookSelected = titleBooks.get(i);
                for (int j = 0; j < parcelableBooks.size(); j++) {
                    LibroParcelable book = parcelableBooks.get(j);
                    if (book.getTitulo().equals(bookSelected)){
                        //setting the properties to the dialog
                        title.setText(book.getTitulo());
                        author.setText(book.getAutor());
                        editorial.setText(book.getEditorial());
                        edition.setText(book.getEdicion());
                        language.setText(book.getIdioma());
                    }
                }


                Button closeButton = alert.findViewById(R.id.close);
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