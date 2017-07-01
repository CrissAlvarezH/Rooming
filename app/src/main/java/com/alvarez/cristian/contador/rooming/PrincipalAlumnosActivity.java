package com.alvarez.cristian.contador.rooming;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.alvarez.cristian.contador.rooming.fragments_tabs.*;

import java.util.ArrayList;
import java.util.List;

public class PrincipalAlumnosActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_alumnos);
        setToolbar();

        // enlazamos el view pager con el XML
        viewPager = (ViewPager) findViewById(R.id.pager);
        prepararViewPager(viewPager);

        tabs = (TabLayout) findViewById(R.id.tabs);
        // ponemos al viewPager a administrar los eventos y el manejo de fragments
        tabs.setupWithViewPager(viewPager);

        establecerIconosTabs(tabs);
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle("Rooming");
            setSupportActionBar(toolbar);// ponemos la toolbar en la appbar
        }
    }

    private void establecerIconosTabs(TabLayout tabs){
        // establecemos unos iconos de prueba
        try {
            tabs.getTabAt(0).setIcon(android.R.drawable.sym_def_app_icon);
            tabs.getTabAt(1).setIcon(android.R.drawable.alert_dark_frame);
            tabs.getTabAt(2).setIcon(android.R.drawable.btn_star_big_on);
            tabs.getTabAt(3).setIcon(android.R.drawable.button_onoff_indicator_on);
        }catch (NullPointerException e){// setIcon puede producir un NullPointerException
            Log.e("ICONOS DEL TAB", "Error a la hora de colocar iconos " + e.getMessage());
        }
    }

    /**
     * Creamos un adapter y añadimos los fragments a este para luego establecerlo en el viewpager
     * que se pasa por parametros.
     * @param viewPager para pasarle el adapter que contendrá la configuracion de los fragments y titulos, etc..
     */
    private void prepararViewPager(ViewPager viewPager){
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        adapter.agregarFragment(new ScanFragment());
        adapter.agregarFragment(new BloquesFragment());
        adapter.agregarFragment(new DocentesFragment());
        adapter.agregarFragment(new FavoritosFragment());
        viewPager.setAdapter(adapter);// establecemos el adaptador al viewPager
    }

    private class PagerAdapter extends FragmentPagerAdapter {
        // array de fragments que irán en las tabs
        private List<Fragment> fragments = new ArrayList<>();

        /**
         * @param fm puesto que se estan manipulando fragments, se necesita el fragmentManager
         */
        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);// retornamos el fragment de esa posicion del tab
        }

        @Override
        public int getCount() {
            return fragments.size();// numero de tabs (fragments)
        }

        public void agregarFragment(Fragment fragment){
            // agregamos al array de fragments, los cuales por medio de getItem serán añadidos a las tabs
            fragments.add(fragment);
        }
    }
}
