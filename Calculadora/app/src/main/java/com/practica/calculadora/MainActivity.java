package com.practica.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button cero,uno,dos,tres,cuatro,cinco,seis,siete,ocho,nueve,punto;
    Button suma,resta,multiplicacion,division;
    Button limpiarTodo,limpiar,porcentaje,igual;
    static TextView pantalla,pantallaOperacion;
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
        pantallaOperacion = findViewById(R.id.operaciones);

        



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
                        boolean tieneOperaciones = false;
                        for (int i = screen.length()-1; i > 0 ; i--) {
                            if (screen.charAt(i)=='+'||screen.charAt(i)=='-'||screen.charAt(i)=='X'||screen.charAt(i)=='/'){
                                tieneOperaciones=true;
                                String cantidad = screen.substring(i,screen.length()-1);
                                if (!cantidad.contains(".")){


                                    int longitudPantalla = screen.length();
                                   if (screen.charAt(longitudPantalla-1)=='+'||screen.charAt(longitudPantalla-1)=='-'||screen.charAt(longitudPantalla-1)=='X'||screen.charAt(longitudPantalla-1)=='/'){
                                       String nuevaPantalla = screen+"0.";
                                       pantalla.setText(nuevaPantalla);
                                   }else{
                                       String nuevaPantalla = screen+".";
                                       pantalla.setText(nuevaPantalla);
                                   }
                                }
                                break;
                            }
                        }
                        if (!tieneOperaciones){
                            if (!screen.contains(".")){
                                String nuevaPantalla = screen+".";
                                pantalla.setText(nuevaPantalla);
                            }

                        }
                    }
                }else{
                    pantalla.setText("0.");
                }
            }
        });

        limpiarTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pantalla.setText("");
                pantallaOperacion.setText("");
            }
        });

        limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String screen = pantalla.getText().toString();
                pantallaOperacion.setText("");
                if (screen.length()>0){
                    pantalla.setText(screen.substring(0,screen.length()-1));
                }

            }
        });

        igual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String screen = pantalla.getText().toString();
                pantallaOperacion.setText(screen);



                String[] operaciones = screen.split("[+\\-\\\\/X]");
                List<String> numeros = new LinkedList<String>(Arrays.asList(operaciones));
                //System.out.println(Arrays.toString(operaciones));
                ArrayList<String>signos = new ArrayList<>();
                for (int i = 0; i < screen.length(); i++) {
                    if (esOperacion(screen.charAt(i))){
                        signos.add(String.valueOf(screen.charAt(i)));
                    }

                }
                if(screen.charAt(0)=='-'){
                    String newNumber = "-"+numeros.get(1);
                    System.out.println(newNumber);
                    numeros.set(1,newNumber);
                    signos.remove(0);
                    numeros.remove(0);
                }

                System.out.println(numeros);
                System.out.println(signos);
                //efectuamos las multiplicaciones
                for (int i = 0; i < signos.size(); i++) {
                    if (signos.get(i).equals("X")){
                        float numero1 = Float.parseFloat(numeros.get(i));
                        float numero2 = Float.parseFloat(numeros.get(i+1));
                        float resultado = numero1*numero2;
                        numeros.remove(i+1);
                        numeros.set(i, String.valueOf(resultado));
                        signos.remove(i);

                    }
                }
                System.out.println(numeros);
                System.out.println(signos);

                //efectuamos las divisiones
                for (int i = 0; i < signos.size(); i++) {
                    if (signos.get(i).equals("/")){
                        float numero1 = Float.parseFloat(numeros.get(i));
                        float numero2 = Float.parseFloat(numeros.get(i+1));
                        float resultado = numero1/numero2;
                        numeros.remove(i+1);
                        numeros.set(i, String.valueOf(resultado));
                        signos.remove(i);

                    }
                }
                System.out.println(numeros);
                System.out.println(signos);


                //efectuamos las sumas
                for (int i = 0; i < signos.size(); i++) {
                    if (signos.get(i).equals("+")){
                        float numero1 = Float.parseFloat(numeros.get(i));
                        float numero2 = Float.parseFloat(numeros.get(i+1));
                        float resultado = numero1+numero2;
                        numeros.remove(i+1);
                        numeros.set(i, String.valueOf(resultado));
                        signos.remove(i);

                    }
                }
                System.out.println(numeros);
                System.out.println(signos);

                //efectuamos las restas
                for (int i = 0; i < signos.size(); i++) {
                    if (signos.get(i).equals("-")){
                        float numero1 = Float.parseFloat(numeros.get(i));
                        float numero2 = Float.parseFloat(numeros.get(i+1));
                        float resultado = numero1-numero2;
                        numeros.remove(i+1);
                        numeros.set(i, String.valueOf(resultado));
                        signos.remove(i);

                    }
                }
                System.out.println(numeros);
                System.out.println(signos);

                if (numeros.size()>0){
                    pantalla.setText(numeros.get(0));
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

    public static boolean esOperacion(char caracter){

            if(caracter!='+'&&caracter!='-'&&caracter!='X'&&caracter!='/'){
                return false;
            }else{
                return true;
            }

    }


}