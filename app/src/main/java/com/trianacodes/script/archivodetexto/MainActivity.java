package com.trianacodes.script.archivodetexto;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText txt_bitacora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_bitacora = findViewById(R.id.et_Bitacora);
        String archivos [] = fileList();

        if (Archivo_Existe(archivos,"bitacora.txt")){

            try{

                InputStreamReader lectura = new InputStreamReader(openFileInput("bitacora.txt"));
                BufferedReader br = new BufferedReader(lectura);
                String linea = br.readLine();
                String bitacoraCompleta = "";

                while (linea != null){

                    bitacoraCompleta = bitacoraCompleta + linea + "\n";
                    linea = br.readLine();

                }

                br.close();
                lectura.close();
                txt_bitacora.setText(bitacoraCompleta);

            } catch (IOException e){



            }

        }

    }

    private boolean Archivo_Existe(String[] archivos, String NombreArchivo) {

        for (int i = 0; i < archivos.length; i++)
            if (NombreArchivo.equals(archivos[i]))
                return true;
            return false;

    }

    public void Guardar(View view){

        try{

            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("bitacora.txt", Activity.MODE_PRIVATE));
            archivo.write(txt_bitacora.getText().toString());
            archivo.flush();
            archivo.close();

        } catch (IOException e){

        }

        Toast.makeText(this,"Información guardada coreectamente. Gracias", Toast.LENGTH_LONG).show();
        finish();

    }

}
