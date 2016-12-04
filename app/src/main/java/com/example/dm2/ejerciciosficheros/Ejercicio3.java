package com.example.dm2.ejerciciosficheros;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Ejercicio3 extends AppCompatActivity {

    private ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //llenar arraylist
        ArrayList<MisWebs> lista=new ArrayList<MisWebs>();
        ArrayList<Integer> img=new ArrayList<Integer>();
        try {
            InputStream fraw=getResources().openRawResource(R.raw.ejercicio3);
            BufferedReader brin=new BufferedReader(new InputStreamReader(fraw));
            String linea=brin.readLine();
            int cont=0;
            String imagen1="bing";
            while(linea!=null)
            {
                String[] datos=linea.split(";");
                lista.add(new MisWebs(datos[0],datos[1],datos[3]));
                if(datos[2].equals(imagen1))
                {
                    img.add(R.drawable.bing);
                }
                else
                {
                    img.add(R.drawable.yahoo);
                }
                linea=brin.readLine();
                cont++;
            }
            fraw.close();
            brin.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        AdaptadorWebs adaptador=new AdaptadorWebs(this,lista,img);

        listview=(ListView)findViewById(R.id.lstwebs);
        listview.setAdapter(adaptador);
    }

}
