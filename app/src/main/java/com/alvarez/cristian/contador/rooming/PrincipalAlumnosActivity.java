package com.alvarez.cristian.contador.rooming;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.alvarez.cristian.contador.rooming.fragments_tabs.*;

import java.util.ArrayList;
import java.util.List;

public class PrincipalAlumnosActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabs;

    // variable capaz de controla la visibildiad del item buscar
    private boolean mostrarItemBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_alumnos);
        setToolbar();

        mostrarItemBuscar = false;// se inicializa false, porque con el primer fragment no se va a mostrar

        // enlazamos el view pager con el XML
        viewPager = (ViewPager) findViewById(R.id.pager);
        prepararViewPager(viewPager);

        tabs = (TabLayout) findViewById(R.id.tabs);
        // ponemos al viewPager a administrar los eventos y el manejo de fragments
        tabs.setupWithViewPager(viewPager);
        // añadimos un escuchador del evento para seleccionar un tab
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // como la posicion 0 del item corresponde al scan, no se va a buscar nada en ese fragment
                // entonces oculramos la opcion del menu que permite buscar
                if(tab.getPosition() == 0){
                    // si es verdadero, entonces ponemos en false para quitar el item
                    if(mostrarItemBuscar) {
                        mostrarItemBuscar = false;
                        invalidateOptionsMenu();// para lanzar de nuevo el onCreateOptionMenu()
                    }
                }else {
                    // si no se esta mostrando el item, ponemos en true para mostrarlo
                    if(!mostrarItemBuscar) {
                        mostrarItemBuscar = true;
                        invalidateOptionsMenu();// para lanzar de nuevo el onCreateOptionMenu()
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

        establecerIconosTabs(tabs);
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle("Rooming");
            setSupportActionBar(toolbar);// ponemos la toolbar en la appbar
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_principal, menu);

        MenuItem itemBuscar = menu.findItem(R.id.item_buscar);
        itemBuscar.setVisible(mostrarItemBuscar);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.item_buscar));
        searchView.setQueryHint("Buscar...");
        searchView.setOnQueryTextListener(new QueryListener());// establecemos el escuchador de las consultas

        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Clase interna para manejar el evento del item para search
     */
    private class QueryListener implements SearchView.OnQueryTextListener{

        @Override
        public boolean onQueryTextSubmit(String query) {
            buscar(query);

            return true;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            buscar(newText);

            return true;
        }

        private void buscar(String peticion){
            // obtenemos el fragment actual
            Fragment fragment = getCurrentFragment(viewPager, tabs);
            BuscadorListener fragmentBuscable = (BuscadorListener) fragment;// casteamos con la interfas

            fragmentBuscable.buscar(peticion);// ejecutamos la busqueda
        }
    }

    /**
     * Obtenemos una instacia del fragment que esta seleccionado en los tabs
     * @param vPager para obtener el adapter del cual se sacará el fragment
     * @param tLayout para obtener la posicion del tab seleccionado actualmente
     * @return retorna el fragment actual
     */
    private Fragment getCurrentFragment(ViewPager vPager, TabLayout tLayout){
        // Obetemos el adaptador y hacemos el casting respectivo
        FragmentPagerAdapter pagerAdapter = (FragmentPagerAdapter) vPager.getAdapter();
        // Usamos getItem para obtener un Fragment de los que estan en el adaptador
        // y la posicion la sacamos de el indice del tab seleccionado con la instancia de TabLayout
        return pagerAdapter.getItem(tLayout.getSelectedTabPosition());
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
