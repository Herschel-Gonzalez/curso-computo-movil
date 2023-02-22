package com.practica.generacioncurp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;

import android.content.Context;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    int dia;
    EditText etNombre,etMaterno,etPaterno;
    int mes;
    int ano;
    String fecha;

    static ArrayList<String>vocals = new ArrayList<>();

    AwesomeValidation awesomeValidation;


    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button fechaNacimiento = findViewById(R.id.fnacimiento);
        Button generarCurp = findViewById(R.id.btn_generar_curp);
        Context context = this;

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(this,R.id.nombre, ".{2,}",R.string.invalid_name);
        awesomeValidation.addValidation(this,R.id.paterno, ".{2,}",R.string.invalid_apellido);
        awesomeValidation.addValidation(this,R.id.materno, ".{2,}",R.string.invalid_apellido);

        vocals.add("A");
        vocals.add("E");
        vocals.add("I");
        vocals.add("O");
        vocals.add("U");

        //definimos las entidades de nacimiento

        String [] entidades = {"AGUASCALIENTES",
                "BAJA CALIFORNIA NTE.",
                "BAJA CALIFORNIA SUR",
                "CAMPECHE",
                "COAHUILA",
                "COLIMA",
                "CHIAPAS",
                "CHIHUAHUA",
                "DISTRITO FEDERAL",
                "DURANGO",
                "GUANAJUATO",
                "GUERRERO",
                "HIDALGO",
                "JALISCO",
                "MEXICO",
                "MICHOACAN",
                "MORELOS",
                "NAYARIT",
                "NUEVO LEON",
                "OAXACA",
                "PUEBLA",
                "QUERETARO",
                "QUINTANA ROO",
                "SAN LUIS POTOSI",
                "SINALOA",
                "SONORA",
                "TABASCO",
                "TAMAULIPAS",
                "TLAXCALA",
                "VERACRUZ",
                "YUCATAN",
                "ZACATECAS",
                "SERV. EXTERIOR MEXICANO",
                "NACIDO EN EL EXTRANJERO"};

        String[] codigoEntidad = {"AS",
                "BC",
                "BS",
                "CC",
                "CL",
                "CM",
                "CS",
                "CH",
                "DF",
                "DG",
                "GT",
                "GR",
                "HG",
                "JC",
                "MC",
                "MN",
                "MS",
                "NT",
                "NL",
                "OC",
                "PL",
                "QT",
                "QR",
                "SP",
                "SL",
                "SR",
                "TC",
                "TS",
                "TL",
                "VZ",
                "YN",
                "ZS",
                "SM",
                "NE"};


        ArrayAdapter<String>adapter = new ArrayAdapter<>(context, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,entidades);

        Spinner spinner = findViewById(R.id.entidad_nacimiento);

        spinner.setAdapter(adapter);

        //definimos el sexo

        String []sexos = {"Hombre","Mujer"};
        ArrayAdapter<String>adapterSexos = new ArrayAdapter<>(context, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,sexos);
        Spinner spinnerSexos = findViewById(R.id.sexo);
        spinnerSexos.setAdapter(adapterSexos);



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
                            dia = day;
                            mes = month+1;
                            ano = year;
                            fecha = String.valueOf(LocalDate.of(year,month+1,day));
                        }
                        fechaNacimiento.setText(fecha.toString());
                    }


                },2022,mes, dia);
                datePickerDialog.show();
                Toast.makeText(context, dia+" "+mes+" "+ano, Toast.LENGTH_SHORT).show();



            }
        });

        generarCurp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(awesomeValidation.validate()){
                    System.out.println(fechaNacimiento.getText());
                    if(!fechaNacimiento.getText().toString().equals("fecha de nacimiento")){
                        etNombre = findViewById(R.id.nombre);
                        etMaterno = findViewById(R.id.materno);
                        etPaterno = findViewById(R.id.paterno);

                        String nacimiento = fechaNacimiento.getText().toString();
                        String entidad = spinner.getSelectedItem().toString();
                        String sexo = spinnerSexos.getSelectedItem().toString();
                        String nombre = etNombre.getText().toString();
                        String paterno = etPaterno.getText().toString();
                        String materno = etMaterno.getText().toString();

                        String[] date = nacimiento.split("-");

                        String year = date[0].substring(2,4);
                        String month = date[1];
                        String day = date[2];
                        String sex = String.valueOf(sexo.charAt(0)).toUpperCase();

                        System.out.println(year);

                        String curp = "";
                        int posicion = 0;
                        for (int i = 0; i < entidades.length; i++) {
                            if(entidad.equals(entidades[i])){
                                posicion = i;
                            }
                        }

                        String codEntidad = codigoEntidad[posicion];


                        //GOPH011211HMNNSRA7



                        curp+=paterno.charAt(0);
                        curp+=getFirstVocal(paterno);
                        curp+=materno.charAt(0);
                        curp+=nombre.charAt(0);
                        curp+=year;
                        curp+=month;
                        curp+=day;
                        curp+=sex;
                        curp+=codEntidad;
                        curp+=getFirstNotVocal(paterno.substring(1));
                        curp+=getFirstNotVocal(materno.substring(1));
                        curp+=getFirstNotVocal(nombre.substring(1));


                        TextView tvCurp = findViewById(R.id.curp);

                        tvCurp.setText(curp);

                        Toast.makeText(context, "CURP generada con exito!", Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(context, "Por favor selecciona una fecha", Toast.LENGTH_SHORT).show();
                    }


                }


            }
        });





    }

    public static String getFirstVocal(String paterno){
        String vocal = "";
        for (int i = 0; i <= paterno.length(); i++) {
            if (vocals.contains(String.valueOf(paterno.charAt(i)).toUpperCase())){
                vocal = String.valueOf(paterno.charAt(i));
                return vocal.toUpperCase();
            }
        }
        return vocal;
    }

    public static String getFirstNotVocal(String entrada){
        String vocal = "";
        for (int i = 0; i <= entrada.length(); i++) {
            vocal = String.valueOf(entrada.charAt(i)).toUpperCase();
            if(!vocals.contains(vocal)){
                return vocal;
            }
        }
        return vocal;
    }
}