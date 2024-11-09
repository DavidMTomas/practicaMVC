package org.example.modelo.example.main;

import org.example.modelo.example.controlador.ControladorArticulo;
import org.example.modelo.example.modelo.Articulo;
import org.example.modelo.example.modelo.ArticuloDAO;
import org.example.modelo.example.modelo.GestionBD;
import org.example.modelo.example.vista.JFArticulos;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {


        JFArticulos vista = new JFArticulos();
        ArticuloDAO modelo = new ArticuloDAO();

        ControladorArticulo ca = new ControladorArticulo(vista, modelo);


        vista.setLocationRelativeTo(null);
        vista.setVisible(true);


    }
}
