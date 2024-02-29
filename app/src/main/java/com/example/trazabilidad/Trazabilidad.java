package com.example.trazabilidad;

import static com.google.android.material.tabs.TabLayout.*;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

public class Trazabilidad extends AppCompatActivity {
    Button btregresar, Bt3, btnuevo, btGuardar;
    EditText TextFactura, TexCodigoQR;
    TextView TextFacturas, TexCodigoQRs, Texinfo,TexResultado, TextCont, TexXml, TexSinxml;
    private int counter1 = 0;
    private TableLayout tabLayout;
    String Codigo1, codigo2,id_Art;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trazabilidad);
        TexCodigoQR = findViewById(R.id.codigoQR);
        TextFactura = findViewById(R.id.Factura);

        TexCodigoQRs = findViewById(R.id.textView7);
        TextFacturas = findViewById(R.id.textView2);
        Texinfo = findViewById(R.id.TextInfo);
        TextCont = findViewById(R.id.cont);
        TexXml = findViewById(R.id.xml);
        TexSinxml = findViewById(R.id.sinxml);

        Bt3 = findViewById(R.id.button5);
        btnuevo = findViewById(R.id.button3);
        btregresar = findViewById(R.id.button4);
        btGuardar = findViewById(R.id.button5);

        TabLayout tabLayout = findViewById(R.id.tab);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();

                // Iniciar la actividad correspondiente a esa pestaña
                switch (position) {
                    case 0:
                        if (!Trazabilidad.class.equals(Trazabilidad.this.getClass())) {
                            startActivity(new Intent(Trazabilidad.this, Trazabilidad.class));
                        }
                        break;
                    case 1:
                        if (!Factura.class.equals(Trazabilidad.this.getClass())) {
                            startActivity(new Intent(Trazabilidad.this, Factura.class));
                        }
                        break;
                    // Agrega más casos según sea necesario
                    default:
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        TexCodigoQR.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() != 0) {
                    // Incrementar contador para todos los escaneos
                    counter1++;
                    TextCont.setText(String.valueOf(counter1));

                    // Asignar el texto escaneado a Texinfo
                    Texinfo.setText(s.toString());

                    if (s.toString().contains("90MX013")) {
                        try {
                            String Codigo1 = s.toString().trim();
                            int index90MX013 = Codigo1.indexOf("90MX013");
                            if (index90MX013 != -1) {
                                String Proveedor = "Detonadores Estrella";
                                String codigo2 = Codigo1.substring(index90MX013 + 10, Math.min(index90MX013 + 10 + 56, Codigo1.length()));
                                String codigo3 = codigo2.substring(Math.max(0, codigo2.length() - 44));

                                int Ind_21 = codigo3.indexOf("21240");
                                int Ind_22 = codigo3.indexOf("22240");
                                int Ind_23 = codigo3.indexOf("23240");
                                int Ind_24 = codigo3.indexOf("24240");
                                int Ind_25 = codigo3.indexOf("25240");
                                String Codigo_Final = "";
                                if (Ind_21 > 0) {
                                    Codigo_Final = codigo2.substring(0, codigo2.indexOf("21240") - 6);
                                } else if (Ind_22 > 0) {
                                    Codigo_Final = codigo2.substring(0, codigo2.indexOf("22240") - 6);
                                } else if (Ind_23 > 0) {
                                    Codigo_Final = codigo2.substring(0, codigo2.indexOf("23240") - 6);
                                } else if (Ind_24 > 0) {
                                    Codigo_Final = codigo2.substring(0, codigo2.indexOf("24240") - 6);
                                } else if (Ind_25 > 0) {
                                    Codigo_Final = codigo2.substring(0, codigo2.indexOf("25240") - 6);
                                }

                                String validar_long_art = codigo2.replace(Codigo_Final, "");
                                String id_Art;
                                if (validar_long_art.length() > 30) {
                                    id_Art = validar_long_art.substring(11, 11 + 24);
                                } else {
                                    id_Art = validar_long_art.substring(11, 11 + 5);
                                }

                                String resultado = "Proveedor: " + Proveedor +  "\nCódigo final:" + Codigo_Final + "\nValidar artículo: " + validar_long_art + "\nArtículo: " + id_Art;
                                Texinfo.setText(resultado);

                                // Limpiar el EditText después de escanear
                                TexCodigoQR.setText("");
                            }
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    } else if (s.toString().contains("codigo:775000")) {
                        // Si contiene "codigo:775000", mostrar el mensaje para "Famesa"
                        String Proveedor = "FAMESA";
                        String Codigo = s.toString();
                        Toast.makeText(getApplicationContext(), "Famesa", Toast.LENGTH_SHORT).show();
                        String Resultado = "Proveedor: " + Proveedor + "\nCódigo: " + Codigo; // Combinar el nombre del proveedor y el código
                        Texinfo.setText(Resultado);
                        TexCodigoQR.setText("");
                    } else if (s.toString().contains("90PE") && s.toString().contains("775000")) {
                        // Si contiene "90PE" y "775000", mostrar el mensaje para "Famesa"
                        String Proveedor = "FAMESA";
                        String Codigo = s.toString(); // Obtener el código escaneado
                        Toast.makeText(getApplicationContext(), "Famesa", Toast.LENGTH_SHORT).show();
                        String Resultado = "Proveedor: " + Proveedor + "\nCódigo: " + Codigo; // Combinar el nombre del proveedor y el código
                        Texinfo.setText(Resultado);

                        TexCodigoQR.setText("");
                    } else {
                        Toast.makeText(getApplicationContext(), "Proveedor no identificado", Toast.LENGTH_SHORT).show();

                        TexCodigoQR.setText("");
                    }
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

        TextFactura.setEnabled(false);

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
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alerta;
                alerta = new AlertDialog.Builder(Trazabilidad.this);
                alerta.setTitle("Salir");
                alerta.setMessage("¿Desea regresar a la página principal?");
                alerta.setCancelable(false);
                alerta.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo, int button4) {
                        finalizar();
                    }
                });
                alerta.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo, int button4) {
                        // No hacer nada
                    }
                });
                alerta.create().show();
            }

            public void finalizar() {
                finish();
            }
        });
    }
}
