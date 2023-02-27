package com.practica.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button cero,uno,dos,tres,cuatro,cinco,seis,siete,ocho,nueve,punto;
    Button suma,resta,multiplicacion,division;
    Button limpiarTodo,limpiar,porcentaje,igual;
    static TextView pantalla;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        punto=findViewById(R.id.decimal);
        suma=findViewById(R.id.suma);
        resta=findViewById(R.id.resta);
        multiplicacion=findViewById(R.id.multiplicacion);
        division=findViewById(R.id.division);
        limpiarTodo=findViewById(R.id.ca);
        limpiar=findViewById(R.id.c);
        porcentaje=findViewById(R.id.percent);
        igual=findViewById(R.id.resultado);

        cero = findViewById(R.id.cero);
        uno = findViewById(R.id.uno);
        dos = findViewById(R.id.dos);
        tres = findViewById(R.id.tres);
        cuatro = findViewById(R.id.cuatro);
        cinco = findViewById(R.id.cinco);
        seis = findViewById(R.id.seis);
        siete = findViewById(R.id.siete);
        ocho = findViewById(R.id.ocho);
        nueve = findViewById(R.id.nueve);
        pantalla = findViewById(R.id.pantalla);



        cero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTextOnScreen('0');
            }
        });

        uno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTextOnScreen('1');
            }
        });

        dos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTextOnScreen('2');
            }
        });

        tres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTextOnScreen('3');
            }
        });

        cuatro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTextOnScreen('4');
            }
        });

        cinco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTextOnScreen('5');
            }
        });

        seis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTextOnScreen('6');
            }
        });

        siete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTextOnScreen('7');
            }
        });

        ocho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTextOnScreen('8');
            }
        });

        nueve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTextOnScreen('9');
            }
        });

        division.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String screen = pantalla.getText().toString();
                if(!esOperacion()){
                    String nuevaPantalla = screen+"/";
                    pantalla.setText(nuevaPantalla);
                }
            }
        });

        multiplicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String screen = pantalla.getText().toString();
                if(!esOperacion()){
                    String nuevaPantalla = screen+"X";
                    pantalla.setText(nuevaPantalla);
                }
            }
        });

        resta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String screen = pantalla.getText().toString();
                if(!esOperacion()){
                    String nuevaPantalla = screen+"-";
                    pantalla.setText(nuevaPantalla);
                }
            }
        });

        suma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String screen = pantalla.getText().toString();
                    if(!esOperacion()){
                        String nuevaPantalla = screen+"+";
                        pantalla.setText(nuevaPantalla);
                    }
            }
        });

        punto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String screen = pantalla.getText().toString();
                if (screen.length()>0){
                    if(screen.charAt(screen.length()-1)!='.'){

                        for (int i = screen.length()-1; i > 0 ; i--) {
                            if (screen.charAt(i)=='+'||screen.charAt(i)=='-'||screen.charAt(i)=='X'||screen.charAt(i)=='/'){
                                String cantidad = screen.substring(i,screen.length()-1);
                                if (!cantidad.contains(".")){
                                    String nuevaPantalla = screen+".";
                                    pantalla.setText(nuevaPantalla);
                                }
                                break;
                            }
                        }
                    }
                }
            }
        });

        limpiarTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pantalla.setText("");
            }
        });

        limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String screen = pantalla.getText().toString();
                if (screen.length()>0){
                    pantalla.setText(screen.substring(0,screen.length()-1));
                }

            }
        });


    }

    public static void setTextOnScreen(char text){
        String screen = pantalla.getText().toString();
        pantalla.setText(screen+text);
    }

    public static boolean esOperacion(){
        String screen = pantalla.getText().toString();
        if (screen.length()>0){
            char ultimoCaracter = screen.charAt(screen.length()-1);
            if(ultimoCaracter!='+'&&ultimoCaracter!='-'&&ultimoCaracter!='X'&&ultimoCaracter!='/'){
                return false;
            }else{
                return true;
            }
        }else{
            return false;
        }

    }


}