package com.practica.examenbd19651164;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DocumentosPropietarioActivity extends AppCompatActivity {
    TextView numeroLic,fEmisionLic,fExpiracionLic,estadoLic;
    TextView folio,serie,tipo,estadoCirc,expedicionCirc,expiracionCirc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documentos_propietario);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String curp = extras.getString("curp");
            Licencia licencia = Utileria.getLicencia(curp,this);
            numeroLic = findViewById(R.id.licencia);
            fEmisionLic = findViewById(R.id.fEmisionLic);
            fExpiracionLic = findViewById(R.id.fExpiracionLic);
            estadoLic = findViewById(R.id.estadoLic);

            numeroLic.setText(licencia.getNumeroLicencia());
            fEmisionLic.setText(licencia.getFechaEmision());
            fExpiracionLic.setText(licencia.getFechaExpiracion());
            estadoLic.setText(licencia.getEstado());

            TarjetaCirculacion tarjetaCirculacion = Utileria.getTarjetaCirculacion(curp,this);
            folio = findViewById(R.id.folioCic);
            serie = findViewById(R.id.serieCirc);
            tipo = findViewById(R.id.tipoCirc);
            estadoCirc = findViewById(R.id.estadoCirc);
            expedicionCirc = findViewById(R.id.fechaExpedCirc);
            expiracionCirc = findViewById(R.id.fechaExperiCirc);

            folio.setText(tarjetaCirculacion.getFolio());
            serie.setText(tarjetaCirculacion.getNumeroSerie());
            tipo.setText(tarjetaCirculacion.getTipo());
            estadoCirc.setText(tarjetaCirculacion.getEstado());
            expedicionCirc.setText(tarjetaCirculacion.getFechaExpedicion());
            expiracionCirc.setText(tarjetaCirculacion.getFechaExpiracion());


        }

    }
}