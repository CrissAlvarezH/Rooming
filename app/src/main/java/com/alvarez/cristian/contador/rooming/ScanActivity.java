package com.alvarez.cristian.contador.rooming;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{
    private ZXingScannerView scannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        scannerView = new ZXingScannerView(this);// creamos una istancia
        setContentView(scannerView);// lo definimos como la vista de la activity

        setActionBar();

        scannerView.setResultHandler(this);// establecemos el listener para el resultado del scanner
        scannerView.startCamera();

    }

    private void setActionBar() {
        ActionBar actionBar;
        if((actionBar = getSupportActionBar()) != null) {
            actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#050505")));
            actionBar.setTitle("Escaner");
            actionBar.setDisplayHomeAsUpEnabled(true);// mostramos el boton de ir atras
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            default:
                return  onOptionsItemSelected(item);
        }

    }

    @Override
    public void handleResult(Result result) {// CallBack del escaner con el resultado
        Toast.makeText(this, "Resultado: "+result.getText(), Toast.LENGTH_SHORT).show();
    }
}
