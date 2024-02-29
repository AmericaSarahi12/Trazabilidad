package com.example.trazabilidad;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import com.example.trazabilidad.db.DbHelper;

public class Trazabilidad extends AppCompatActivity {
    Button btregresar, Bt3, btnuevo, btGuardar;
    String TextFactura;
    String TexCodigoQR;
    TextView TextFacturas, TexCodigoQRs, Texinfo, TexCaja, TexResumen, TextCont, TexXml, TexSinxml;
    private int counter1 = 0;
    private TableLayout tabLayout;
    private android.widget.TableLayout TableLayout;
    //7TableLayout pestañas;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trazabilidad);
        TexCodigoQR = String.valueOf((EditText) findViewById(R.id.codigoQR));
        TextFactura = String.valueOf((EditText) findViewById(R.id.Factura));


        TexCodigoQRs = (TextView) findViewById(R.id.textView7);
        TextFacturas = (TextView) findViewById(R.id.textView2);
        Texinfo = (TextView) findViewById(R.id.TextInfo);

        TextCont = (TextView) findViewById(R.id.cont);
        TexXml = (TextView) findViewById(R.id.xml);
        TexSinxml = (TextView) findViewById(R.id.sinxml);


        Bt3 = (Button) findViewById(R.id.button5);
        btnuevo = (Button) findViewById(R.id.button3);
        btregresar = (Button) findViewById(R.id.button4);
        btGuardar = (Button) findViewById(R.id.button5);
        // pestañas=(TableLayout)findViewById(R.id.tab);



        TableLayout=(TableLayout) findViewById(R.id.tabLayout);
        TexCodigoQR.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                boolean codigo = true;
                if ( TexCodigoQR.contains("90MX013")) Log.d("Detonadores Etrella", "Detonadores Estrella");
                else if (TexCodigoQR.contains("90PE")){
                    Log.d("Famesa", "famesa");
                }
                else {
                    Toast.makeText(this"Error:Provdor no identificado",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    Texinfo.setText(s.toString());
                }


            }


            @Override
            public void afterTextChanged(Editable s) {

            }

        });
        Texinfo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0){
                    counter1++;
                    TextCont.setText(String.valueOf(counter1));
                }


            }
        });
        TextFactura.setEnabled(false);
        btnuevo.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                btnuevo.setEnabled(false);
                TextFactura.setEnabled(true);
                TextFactura.requestFocus();


            }

        });





        TexCodigoQR.setEnabled(false);
        TextFactura.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    TextFactura.setEnabled(false);
                    TexCodigoQR.setEnabled(true);
                    TexCodigoQR.requestFocus();


                    return true;


                }


                return false;

            }
        });
        btregresar.setOnClickListener(new View.OnClickListener() {
// PARA MENSAJE DE ALERTA

            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(Trazabilidad.this, MainActivity.class);
                //startActivity(intent);
                AlertDialog.Builder alerta;
                alerta = new AlertDialog.Builder(Trazabilidad.this);
                alerta.setTitle("salir");
                alerta.setMessage("Desea regresar a la pagina principal?");
                alerta.setCancelable(false);
                alerta.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo, int button4) {
                        finalizar();
                    }
                });
                alerta.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo, int button4) {
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
      //  btGuardar.setOnClickListener(new View.OnClickListener() {
        //    @Override
          //  public void onClick(View v) {
            //    DbHelper dbHelper= new DbHelper(Trazabilidad.this);
              //  SQLiteDatabase db = dbHelper.getReadableDatabase();
                //if (db != null){
                  //  Toast.makeText(Trazabilidad.this,"base de datos creada",Toast.LENGTH_LONG).show();
                //}
                //else {
                  //  if (db != null){
                    //    Toast.makeText(Trazabilidad.this,"base de datos no creada",Toast.LENGTH_LONG).show();
                    //}
                //}
            //}
        //});



    }

}