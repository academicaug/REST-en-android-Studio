package com.example.danielpaguay.consultaacademica;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    //Declaro variables
    private TextView txtCedula;
    private TextView txtPeriodo;
    private TextView txtObtDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Localizar los controles
        txtCedula = (TextView)findViewById(R.id.TxtCedula);
        txtPeriodo= (TextView)findViewById(R.id.TxtPeriodo);
        txtObtDatos=(TextView)findViewById(R.id.txtObtDatos);

        //Recuperamos la información pasada en el intent
        Bundle bundle = this.getIntent().getExtras();

        //Construimos el mensaje a mostrar
        txtCedula.setText(" " + bundle.getString("CED"));
        txtPeriodo.setText(" " + bundle.getString("PER"));
        txtObtDatos.setText("" + bundle.getString("NOT"));

        //Codigo por defaul
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

}
