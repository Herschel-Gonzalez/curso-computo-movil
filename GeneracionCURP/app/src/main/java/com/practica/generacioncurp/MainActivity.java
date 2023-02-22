package com.practica.generacioncurp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;

import android.content.Context;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.time.LocalDate;

public class MainActivity extends AppCompatActivity {


    int dia;
    int mes;
    int ano;
    String fecha;
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button fechaNacimiento = findViewById(R.id.fnacimiento);
        Context context = this;

        fechaNacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                dia = c.get(Calendar.DAY_OF_MONTH);
                mes = c.get(Calendar.MONTH);
                ano = c.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            fecha = String.valueOf(LocalDate.of(year,month+1,day));
                        }
                        fechaNacimiento.setText(fecha.toString());
                    }


                },2022,mes, dia);
                datePickerDialog.show();




            }
        });

    }
}