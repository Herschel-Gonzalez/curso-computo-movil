package com.practica.examenu2herschelgonzalez;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Utileria {

    public static List<String> getCountryLines(Context context){

        try{
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.getAssets().open("paises.txt")));
            List<String>lineas = new ArrayList<>();
            for (String linea=bufferedReader.readLine();linea!=null;linea=bufferedReader.readLine()) {
                lineas.add(linea);
            }
            return lineas;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static List<Pais>getCountries(List<String> lineas,Context context){
        List<Pais> paises = new ArrayList<>();
        for (int i = 0; i < lineas.size(); i++) {
            String linea = lineas.get(i);
            String lineaArray[] = linea.split(",");
            String codigo = lineaArray[0];
            String nombre = lineaArray[1];
            String capital = lineaArray[2];
            String continente = lineaArray[3];

            Pais pais = new Pais(Integer.parseInt(codigo),nombre,capital,continente);
            paises.add(pais);

        }
        return  paises;
    }
}
