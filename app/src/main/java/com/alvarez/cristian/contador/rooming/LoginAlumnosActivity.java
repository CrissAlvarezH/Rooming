package com.alvarez.cristian.contador.rooming;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class LoginAlumnosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_alumnos);
        setToolbar();

    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle("Login Alumnos");// agregamos el nombre de la app en la toolbar
            setSupportActionBar(toolbar);// ponemos la toolbar en la appbar

            if(getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);// mostramos el boton de ir atras
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                // Navega a la actividad que en el manifes esta como parentActivity de esta
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return onOptionsItemSelected(item);
        }
    }

    public void irRegistroActivity(View v){
        Intent i = new Intent(this, RegistroAlumnosActivity.class);
        startActivity(i);
    }
}