package com.example.dm2.ejerciciosficheros;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Ejercicio2 extends AppCompatActivity {

    private Spinner spinnerProvincias;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio2);

        //hacer spinner
        spinnerProvincias=(Spinner)findViewById(R.id.spinner);

        ArrayAdapter<String> adaptador=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);

        //leer fichero recurso

        try {
            InputStream fraw=getResources().openRawResource(R.raw.ejercicio2);
            BufferedReader brin=new BufferedReader(new InputStreamReader(fraw));
            String linea=brin.readLine();
            int cont=0;
            while(linea!=null)
            {
                adaptador.insert(linea,cont);
                linea=brin.readLine();
                cont++;
            }
            fraw.close();
            brin.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProvincias.setAdapter(adaptador);
    }
}
