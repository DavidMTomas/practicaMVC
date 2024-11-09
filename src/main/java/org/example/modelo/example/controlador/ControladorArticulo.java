package org.example.modelo.example.controlador;

import org.example.modelo.example.modelo.ArticuloDAO;
import org.example.modelo.example.vista.JFArticulos;

public class ControladorArticulo {
    private JFArticulos vista;
    private ArticuloDAO modelo;


    public ControladorArticulo(JFArticulos vista, ArticuloDAO modelo) {
            this.vista = vista;
            this.modelo = modelo;
    }
}
