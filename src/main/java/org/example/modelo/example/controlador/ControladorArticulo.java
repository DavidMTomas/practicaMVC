package org.example.modelo.example.controlador;

import com.sun.jdi.ArrayReference;
import org.example.modelo.example.modelo.Articulo;
import org.example.modelo.example.modelo.ArticuloDAO;
import org.example.modelo.example.vista.JFArticulos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class ControladorArticulo {
    private JFArticulos vista;
    private ArticuloDAO modelo;


    public ControladorArticulo(JFArticulos vista, ArticuloDAO modelo) {
            this.vista = vista;
            this.modelo = modelo;

            inicializarVista();
    }

    private void inicializarVista() {
        limpiarElementos();
        llenarTabla(vista.table1);
    }

    private void llenarTabla(JTable tableT) {
        //creamos un tipo de vista
        DefaultTableModel modeloT=new DefaultTableModel();
        // le asignamos el modelo a al tabla
        tableT.setModel(modeloT);
        // le añadimos las columnas
        modeloT.addColumn("ID");
        modeloT.addColumn("Nombre");
        modeloT.addColumn("Precio");
        modeloT.addColumn("Stock");
        modeloT.addColumn("Imagen");
        modeloT.addColumn("Descripcion");

        // creamos un objeto para poder guardar cada columna por tipo
        Object[] columna = new Object[6];

        // obtenemos le listado de articulos de ArticulosDAO
        List articulos= modelo.obtenerTodos();

        // pintamos los articulos en la columna
        for (int i = 0; i < articulos.size(); i++) {
            Articulo articulo = (Articulo) articulos.get(i);
            columna[0] = articulo.getId();
            columna[1] = articulo.getNombre();
            columna[2] = articulo.getPrecio();
            columna[3] = articulo.getStock();
            columna[4] = articulo.getImagen();
            columna[5] = articulo.getDescripcion();
            modeloT.addRow(columna);
        }
    }

    private void limpiarElementos() {

    }
}
