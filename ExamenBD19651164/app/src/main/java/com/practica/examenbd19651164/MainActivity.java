package com.practica.examenbd19651164;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    EditText nombre,curp,paterno,materno,noLicencia,fechaEmision,fechaExpiracion,estadoEmision,folio,noSerie,tipo,estadoCirculacion,expedicionCirculacion,expiracionCirculacion;
    Button registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        DbHelper dbHelper = new DbHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        nombre = findViewById(R.id.nombre);
        curp = findViewById(R.id.curp);
        paterno = findViewById(R.id.paterno);
        materno = findViewById(R.id.materno);
        fechaEmision = findViewById(R.id.fechaEmision);
        fechaExpiracion = findViewById(R.id.fechaExpiracion);
        estadoEmision = findViewById(R.id.estadoEmision);
        noSerie = findViewById(R.id.noSerie);
        folio = findViewById(R.id.folio);
        tipo = findViewById(R.id.tipo);
        estadoCirculacion = findViewById(R.id.estado);
        expedicionCirculacion = findViewById(R.id.fechaExpedcionCirculacion);
        expiracionCirculacion = findViewById(R.id.fechaExpiracionCirculacion);
        noLicencia = findViewById(R.id.noLicencia);

        registrar = findViewById(R.id.registrar);
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //propietario
                String nombreP = nombre.getText().toString();
                String curpP = curp.getText().toString();
                String paternoP = paterno.getText().toString();
                String maternoP = nombre.getText().toString();
                ContentValues valoresPropietario = new ContentValues();
                valoresPropietario.put("curp", curpP);
                valoresPropietario.put("nombre", nombreP);
                valoresPropietario.put("paterno", paternoP);
                valoresPropietario.put("materno", maternoP);
                long idPropietario = db.insert("propietario", null, valoresPropietario);
                //Licencia
                String noLicenciaL = noLicencia.getText().toString();
                String fechaEmisionL = fechaEmision.getText().toString();
                String fechaExpiracionL = fechaExpiracion.getText().toString();
                String estadoEmisionL = estadoEmision.getText().toString();
                ContentValues valoresLicencia = new ContentValues();
                valoresLicencia.put("noLicencia", noLicenciaL);
                valoresLicencia.put("curp", curpP);
                valoresLicencia.put("fechaEmision", fechaEmisionL);
                valoresLicencia.put("fechaExpiracion", fechaExpiracionL);
                valoresLicencia.put("estadoEmision", estadoEmisionL);
                long idLicencia = db.insert("licencia", null, valoresLicencia);
                //Tarjeta Circulacion
                String folioC = folio.getText().toString();
                String noSerieC = noSerie.getText().toString();
                String tipoC = tipo.getText().toString();
                String estadoC = estadoCirculacion.getText().toString();
                String fechaExpedicionC = expedicionCirculacion.getText().toString();
                String fechaExpiracionC = expiracionCirculacion.getText().toString();
                ContentValues valoresCirculacion = new ContentValues();
                valoresCirculacion.put("folio", folioC);
                valoresCirculacion.put("curp", curpP);
                valoresCirculacion.put("noSerie", noSerieC);
                valoresCirculacion.put("tipo", tipoC);
                valoresCirculacion.put("estado", estadoC);
                valoresCirculacion.put("fechaExpedicion", fechaExpedicionC);
                valoresCirculacion.put("fechaExpiracion", fechaExpiracionC);
                long idCirculacion = db.insert("tarjetaCirculacion", null, valoresCirculacion);
                Toast.makeText(MainActivity.this, "Se registro con exito", Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent=null;

        switch (item.getItemId()){
            case R.id.listadoPropietarios:
                intent = new Intent(this,PropietariosActivity.class);
                break;
            case R.id.almenosUnoVencido:
                intent = new Intent(getApplicationContext(),UnDocVencidoActivity.class);
                break;
            case R.id.todosDocVencidos:
                intent = new Intent(getApplicationContext(),TodosDocVencidosActivity.class);
                break;
            case R.id.porVencer:
                intent = new Intent(getApplicationContext(),DocPorVencerActivity.class);
                break;

        }
        startActivity(intent);
        return true;
    }
}