package com.example.dm2.ejerciciosficheros;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button butAnadirFichInt,butLeerFichInt,butRecurso;
    private EditText textoIntroducido;
    private TextView textViewContenidoFichero;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        butAnadirFichInt=(Button)findViewById(R.id.butAnadirFichInt);
        butLeerFichInt=(Button)findViewById(R.id.butLeerFichInt);
        butRecurso=(Button)findViewById(R.id.butLeerRecurso);

        textoIntroducido=(EditText) findViewById(R.id.txttexto);
        textViewContenidoFichero=(TextView)findViewById(R.id.lbltextoFichero);

        butRecurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputStream fraw=getResources().openRawResource(R.raw.prueba_raw);
                BufferedReader brin=new BufferedReader(new InputStreamReader(fraw));
                String linea= null;
                try {
                    linea = brin.readLine();
                    while(linea!=null)
                    {
                        textViewContenidoFichero.append(linea);
                        linea=brin.readLine();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }
    public void interno(View v)
    {
        if(v.getId()==butAnadirFichInt.getId())
        {
            String texto=textoIntroducido.getText().toString();

            try {
                OutputStreamWriter osw=new OutputStreamWriter(openFileOutput("ficheroInt.txt", Context.MODE_APPEND));
                osw.write(texto);
                osw.close();
                Toast.makeText(getApplicationContext(),"Texto escrito en fichero",Toast.LENGTH_LONG).show();
            }  catch (IOException e) {
                Log.e("Ficheros","Error!! al escribit fichero a memoria interna");
            }
        }
        else
        {
            if(v.getId()==butLeerFichInt.getId())
            {
                try {
                    BufferedReader fin=new BufferedReader(new BufferedReader(new InputStreamReader(openFileInput("ficheroInt.txt"))));
                    String textoLeido=fin.readLine();
                    fin.close();
                    textViewContenidoFichero.setText(textoLeido);


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else
            {
                deleteFile("ficheroInt.txt");
                Toast.makeText(getApplicationContext(),"Fichero Borrado",Toast.LENGTH_LONG).show();
            }
        }
    }

    public void externo(View v)
    {
        int id=v.getId();

        switch (id) {
            case  R.id.butAnadirFichExt:
                try{



                    File ruta_sd = Environment.getExternalStorageDirectory();

                    File f = new File(ruta_sd,"prueba_sd.txt");

                    Toast.makeText(getApplicationContext(),Environment.isExternalStorageRemovable()+"",Toast.LENGTH_LONG).show();
                    OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(f));
                    osw.write(textoIntroducido.getText().toString());
                    osw.close();
                    Toast.makeText(getApplicationContext(),"Exito",Toast.LENGTH_LONG).show();
                }catch(Exception ex) {
                    Log.e("Ficheros","Error al escribir fichero en tarjeta SD");
                }
                break;
                    case  R.id.butLeerFichExt:


                        try {
                            File ruta_sd = Environment.getExternalStorageDirectory();

                            File f = new File(ruta_sd,"prueba_sd.txt");
                            BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(f)));

                            String linea=br.readLine();
                            while(linea!=null) {
                                textViewContenidoFichero.append(linea);
                                linea = br.readLine();
                            }
                            br.close();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        break;
                    case R.id.butBorrarFichExt:
                        File ruta_sd = Environment.getExternalStorageDirectory();

                        File f = new File(ruta_sd,"prueba_sd.txt");
                        f.delete();
                        Log.i("dd","exito");
                        break;
        }
    }

}
