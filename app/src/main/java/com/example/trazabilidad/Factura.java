package com.example.trazabilidad;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;

import kotlin.io.FileAlreadyExistsException;

public class Factura extends AppCompatActivity {
    private TableLayout tabLayout;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factura);

        TabLayout tabLayout = findViewById(R.id.tabs);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();

                // Obtén la clase de la actividad correspondiente a la pestaña seleccionada
                Class<? extends Activity> selectedActivity = null;
                switch (position) {
                    case 0:
                        selectedActivity = (Class<? extends Activity>) Trazabilidad.class;
                        break;
                    case 1:
                        selectedActivity = Factura.class;
                        break;
                    case 2:
                        selectedActivity = Trazabilidad.class;
                        break;
                    default:
                        break;
                }

                // Comprueba si la actividad seleccionada es diferente de la actividad actual antes de iniciarla
                if (selectedActivity != null && !selectedActivity.equals(Factura.this.getClass())) {
                    startActivity(new Intent(Factura.this, selectedActivity));
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });


    }
}