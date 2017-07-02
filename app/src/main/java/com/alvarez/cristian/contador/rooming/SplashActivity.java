package com.alvarez.cristian.contador.rooming;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPreferences preferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        Intent i = null;

        // si hay datos de usuario guardados quiere decir que ya se logueó
        if ((preferences.getInt("id", -1) != -1) && (!preferences.getString("pass", "").equals(""))){

        }else{// no se ha logueado
            //i = new Intent(this, DecisionActivity.class);
            i = new Intent(this, PrincipalAlumnosActivity.class);// por pruebas vamos a mandar a la principal
        }

        if(i != null) {
            // mandamos el intent con vandera para que no vuelva a esta activity
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(i);
            finish();
        }else{
            Toast.makeText(this, "Ah ocurrido un problema", Toast.LENGTH_LONG).show();
        }
    }
}
