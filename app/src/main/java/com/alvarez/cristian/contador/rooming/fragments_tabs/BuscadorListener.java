package com.alvarez.cristian.contador.rooming.fragments_tabs;

/**
 * Created by CristianAlvarez on 01/07/2017.
 */

/**
 * Debido a que cada fragment tendrá una lista (bloques, docentes, cursos), estos realizaran
 * una accion que será buscar, por tanto este comportamiento será reclutado en esta interfaz
 */
public interface BuscadorListener {
    void buscar(String consulta);
}
