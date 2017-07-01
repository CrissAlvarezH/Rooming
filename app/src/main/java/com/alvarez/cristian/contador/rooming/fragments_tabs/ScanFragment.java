package com.alvarez.cristian.contador.rooming.fragments_tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alvarez.cristian.contador.rooming.R;

public class ScanFragment extends Fragment {
    public ScanFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_scan, container, false);

        return vista;
    }

}
