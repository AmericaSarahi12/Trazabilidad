package com.example.trazabilidad;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity {
    Button bt, btsalida;
    public LogRecord alerta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt = (Button) findViewById(R.id.button);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Trazabilidad.class));
            }

        });




        btsalida = (Button) findViewById(R.id.button2);
        btsalida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alerta;
                alerta = new AlertDialog.Builder(MainActivity.this);
                alerta.setTitle("salir");
                alerta.setMessage("Desea Salir de la app?");
                alerta.setCancelable(false);
                alerta.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo, int button5) {
                        finalizar();
                    }
                });
                alerta.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo, int button5) {
                        continuar();
                    }

                    private void continuar() {
                    }
                });
                alerta.create();
                alerta.show();
            }

            public void finalizar() {
                finish();
            }

        });

    }
}