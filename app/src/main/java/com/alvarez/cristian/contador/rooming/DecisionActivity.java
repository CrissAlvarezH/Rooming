package com.alvarez.cristian.contador.rooming;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DecisionActivity extends AppCompatActivity {
    private Button btnSoyEstudiante, btnSoyDocente;
    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decision);

        btnSoyEstudiante = (Button) findViewById(R.id.btn_soy_estudiante);
        btnSoyDocente = (Button) findViewById(R.id.btn_soy_docente);
    }

    // Metodo para cuando se haga click en el boton 'btnSoyDocente'
    public void soyDocenteClick(View v){

    }

    // Metodo para cuando se haga click en el boton 'btnSoyEstudiante'
    public void soyEstudianteClick(View v){
        i = new Intent(this, LoginAlumnosActivity.class);
        startActivity(i);
    }
}
