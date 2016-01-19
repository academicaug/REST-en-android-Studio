package com.example.danielpaguay.consultaacademica;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class MainActivity extends AppCompatActivity {

    //DECLARO LAS VARIABLES A UTILIZAR
    private EditText txtCed;
    private Button btnAceptar;
    private RadioButton radio_ci,radio_cii;
    private String periodo;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //obtenemos la referencia a los controles de la interfaz
        txtCed = (EditText)findViewById(R.id.Txtced);
        btnAceptar = (Button)findViewById(R.id.BtnAceptar);
        radio_ci=(RadioButton)findViewById(R.id.radio_ci);
        radio_cii=(RadioButton)findViewById(R.id.radio_cii);

        //Implementamos el evento click del botón
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerDato oDatos = new obtenerDato();

                //Inicio RadioButton
                if (radio_ci.isChecked() == true) {
                    periodo = "15120001";
                    oDatos.execute(txtCed.getText().toString(), periodo);
                } else if (radio_cii.isChecked() == true) {
                    periodo = "15120002";
                    oDatos.execute(txtCed.getText().toString(), periodo);
                }
                //Fin RadioButton


            }
        });



        //Esto es codigo por default
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void PasarDatos(Notas[] notases){
        Notas[] recibeNoras = notases;
        //Creamos el Intent
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);

                //Creamos la información a pasar entre actividades
                Bundle b = new Bundle();
                b.putString("CED", txtCed.getText().toString());
                b.putString("PER", periodo);
                b.putString("NOT",recibeNoras.toString());

                //Añadimos la información al intent
                intent.putExtras(b);

                //Iniciamos la nueva actividad
                startActivity(intent);
    }

    public class obtenerDato extends AsyncTask<String,Void,Notas[]> {
        private ProgressDialog progressDialog;

        @Override
        protected Notas[] doInBackground(String... params) {
            String cedu = params[0];
            String perio = params[1];
            String erroer=" ";

            try {
                final String url = "http://lisrestful.azurewebsites.net/api/notas?cod_estudiante="+cedu+"&cod_lectivo="+perio;
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                Notas[] resultado = restTemplate.getForObject(url,Notas[].class);
                //Greeting greeting = restTemplate.getForObject(url, Greeting.class);
                //return greeting;
                return resultado;
            } catch (Exception e) {
                //Log.e("MainActivity", e.getMessage(), e);
                erroer = e.getMessage();
                e.printStackTrace();
            }

            return null;
        }


        @Override
        protected void onPostExecute(Notas[] notases) {
            super.onPostExecute(notases);
            PasarDatos(notases);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(MainActivity.this, "POR FAVOR ESPERE", "PROCESANDO....");
        }


    }
}
