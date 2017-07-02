package com.alvarez.cristian.contador.rooming.fragments_tabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.alvarez.cristian.contador.rooming.R;
import com.alvarez.cristian.contador.rooming.ScanActivity;

public class ScanFragment extends Fragment {
    public ScanFragment() {}

    private Button btnScannear;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_scan, container, false);

        btnScannear = (Button) vista.findViewById(R.id.btn_scannear);
        btnScannear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), ScanActivity.class);
                startActivity(i);
            }
        });

        return vista;
    }


}
