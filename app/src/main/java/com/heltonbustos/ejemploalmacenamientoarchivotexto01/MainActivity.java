package com.heltonbustos.ejemploalmacenamientoarchivotexto01;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText texto, texto2;
    Button btnGuardar, btnGuardar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        texto = findViewById(R.id.txtTexto);
        btnGuardar = findViewById(R.id.btnGuardar);

        texto2 = findViewById(R.id.txtTexto2);
        btnGuardar2 = findViewById(R.id.btnGuardar2);

        String archivos[] = fileList();

        if(ArchivoExiste(archivos, "tareas.txt")){
            try {
                InputStreamReader archivo = new InputStreamReader(openFileInput("tareas.txt"));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();
                String cadenaCompleta = "";

                while (linea != null) {
                    cadenaCompleta = cadenaCompleta + linea + "\n";
                    linea = br.readLine();
                }

                br.close();
                archivo.close();
                texto.setText(cadenaCompleta);
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        if(ArchivoExiste(archivos, "tareas2.txt")){
            try {
                InputStreamReader archivo = new InputStreamReader(openFileInput("tareas2.txt"));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();
                String cadenaCompleta = "";

                while (linea != null) {
                    cadenaCompleta = cadenaCompleta + linea + "\n";
                    linea = br.readLine();
                }

                br.close();
                archivo.close();
                texto2.setText(cadenaCompleta);
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String textoMulti = texto.getText().toString();

                    OutputStreamWriter archivo =
                            new OutputStreamWriter(openFileOutput("tareas.txt", Activity.MODE_PRIVATE));

                    archivo.write(textoMulti);
                    archivo.flush();
                    archivo.close();
                } catch (IOException e){
                    e.printStackTrace();
                }

                Toast.makeText(MainActivity.this, "Texto almacenado correctamente!", Toast.LENGTH_SHORT).show();
            }
        });

        btnGuardar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String textoMulti = texto2.getText().toString();

                    OutputStreamWriter archivo =
                            new OutputStreamWriter(openFileOutput("tareas2.txt", Activity.MODE_PRIVATE));

                    archivo.write(textoMulti);
                    archivo.flush();
                    archivo.close();
                } catch (IOException e){
                    e.printStackTrace();
                }

                Toast.makeText(MainActivity.this, "Texto almacenado correctamente!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private boolean ArchivoExiste(String[] archivos, String s) {
        for(int i=0; i<archivos.length; i++){
            if (s.equals(archivos[i])){
                return true;
            }
        }
        return false;
    }


}