package utilidades;

import android.content.Context;

import com.example.datosentreactividades.LibroParcelable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Utileria {
    public static List<String> getBusLines(Context context){

        try{
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.getAssets().open("libros.txt")));
            List<String>lineas = new ArrayList<>();
            for (String linea=bufferedReader.readLine();linea!=null;linea=bufferedReader.readLine()) {
                lineas.add(linea);
            }
            return lineas;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static List<LibroParcelable>getParcelableBugs(List<String> lineas,Context context){
        List<LibroParcelable> libros = new ArrayList<>();
        for (int i = 0; i < lineas.size(); i++) {
            String linea = lineas.get(i);
            String lineaArray[] = linea.split(",");
            String isbn = lineaArray[0];
            String titulo = lineaArray[1];
            String autor = lineaArray[2];
            String editorial = lineaArray[3];
            String edicion = lineaArray[4];
            String idioma = lineaArray[5];
            LibroParcelable newLibro = new LibroParcelable(isbn,titulo,autor,editorial,edicion,idioma);
            libros.add(newLibro);
        }
        return  libros;
    }
}
