package org.example.modelo.example.controlador;

import org.example.modelo.example.modelo.Articulo;
import org.example.modelo.example.modelo.ArticuloDAO;
import org.example.modelo.example.vista.JFArticulos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.List;

public class ControladorArticulo implements ActionListener, KeyListener {
    private JFArticulos vista;
    private ArticuloDAO modelo;


    public ControladorArticulo(JFArticulos vista, ArticuloDAO modelo) {
        this.vista = vista;
        this.modelo = modelo;

        vista.btnNuevo.addActionListener(this);
        vista.btnGuardar.addActionListener(this);
        vista.btnModificar.addActionListener(this);
        vista.btnEliminar.addActionListener(this);
        vista.btnSeleccionar.addActionListener(this);


        vista.txtBusquedaNombreTextField.addKeyListener(this);


        inicializarVista();
    }

    private void inicializarVista() {
        limpiarElementos();
        llenarTabla(vista.table1);
    }


    // si pasamos el metodo del modelo podemos rafactorizar codigo
    private void llenarTabla(JTable tableT) {
        //creamos un tipo de vista
        DefaultTableModel modeloT = new DefaultTableModel();
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
        List articulos = modelo.obtenerTodos(); // TODOS

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


        //Propiedades tabla
        tableT.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tableT.getTableHeader().setReorderingAllowed(false);

        vista.txtTotalArticulosTextField.setText(String.valueOf(articulos.size()));

    }

    private void limpiarElementos() {
        vista.txtIdTextField.setText("");
        vista.txtPrecio.setText("");
        vista.txtStockTextField.setText("");
        vista.txtNombreTextField.setText("");
        vista.txtImagenTextField.setText("");
        vista.txtDescripcionTextField.setText("");
        vista.txtBusquedaNombreTextField.setText("");
        vista.txtTotalArticulosTextField.setText("");

        vista.btnNuevo.setEnabled(true);
        vista.btnGuardar.setEnabled(true);
        vista.btnModificar.setEnabled(true);
        vista.btnEliminar.setEnabled(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == vista.btnNuevo) {
                limpiarElementos();
                llenarTabla(vista.table1);
/////////////
            } else if (e.getSource() == vista.btnGuardar) {
                String id = vista.txtIdTextField.getText();
                Float precio = Float.parseFloat(vista.txtPrecio.getText());
                int stock = Integer.parseInt(vista.txtStockTextField.getText());
                String nombre = vista.txtNombreTextField.getText();
                String imagen = vista.txtImagenTextField.getText();
                String descripcion = vista.txtDescripcionTextField.getText();

                Articulo articulo = new Articulo(id, nombre, precio, stock, imagen, descripcion);
                int numRegistros = modelo.insertar(articulo); // INSERTAR

                if (numRegistros > 0) {
                    JOptionPane.showMessageDialog(null, "Número de registros guardados: " + numRegistros);
                } else {
                    JOptionPane.showMessageDialog(null, "Error al guardar el artículo");
                }

                limpiarElementos();
                llenarTabla(vista.table1);
/////
            } else if (e.getSource() == vista.btnModificar) {
                String id = vista.txtIdTextField.getText();
                Float precio = Float.parseFloat(vista.txtPrecio.getText());
                int stock = Integer.parseInt(vista.txtStockTextField.getText());
                String nombre = vista.txtNombreTextField.getText();
                String imagen = vista.txtImagenTextField.getText();
                String descripcion = vista.txtDescripcionTextField.getText();

                Articulo articulo = new Articulo(id, nombre, precio, stock, imagen, descripcion);
                int numRegistros = modelo.actualizar(articulo); //ACTUALIZAR

                if (numRegistros > 0) {
                    JOptionPane.showMessageDialog(null, "Número de registros actualizados: " + numRegistros);
                } else {
                    JOptionPane.showMessageDialog(null, "Error al actualizar el artículo");
                }

                limpiarElementos();
                llenarTabla(vista.table1);
///
            } else if (e.getSource() == vista.btnEliminar) {
                String id = vista.txtIdTextField.getText();
                Articulo articulo = new Articulo();
                articulo.setId(id);
                int numRegistros = modelo.eliminar(articulo); // ELIMINAR
                mensajeEmergente(numRegistros);
///
            } else if (e.getSource() == vista.btnSeleccionar) {

                String id = vista.txtIdTextField.getText();
                DefaultTableModel modeloT = new DefaultTableModel();
                vista.table1.setModel(modeloT);


                modeloT.addColumn("ID");
                modeloT.addColumn("Nombre");
                modeloT.addColumn("Precio");
                modeloT.addColumn("Stock");
                modeloT.addColumn("Imagen");
                modeloT.addColumn("Descripcion");

                Articulo articulo = modelo.buscarPorId(id); // BUSCAR el artículo por ID

                if (articulo != null) {
                    Object[] registro = new Object[6];
                    registro[0] = articulo.getId();
                    registro[1] = articulo.getNombre();
                    registro[2] = articulo.getPrecio();
                    registro[3] = articulo.getStock();
                    registro[4] = articulo.getImagen();
                    registro[5] = articulo.getDescripcion();
                    modeloT.addRow(registro);

                    //Propiedades tabla
                    vista.table1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                    vista.table1.getTableHeader().setReorderingAllowed(false);
                    vista.txtTotalArticulosTextField.setText((articulo != null) ? "1" : "0");

                } else JOptionPane.showMessageDialog(null, "Registro no encontrado");

            } else {
                JOptionPane.showMessageDialog(null, "Acción no reconocida");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Error en los datos ingresados. Verifique los campos.");
        }
    }

    private void mensajeEmergente(int numRegistros) {
        if (numRegistros > 0) {
            JOptionPane.showMessageDialog(null, "Registros añadidos: " + numRegistros);
        } else JOptionPane.showMessageDialog(null, "Error al eliminar el registro");
        limpiarElementos();
        llenarTabla(vista.table1);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == vista.txtBusquedaNombreTextField) {
            String texto = vista.txtBusquedaNombreTextField.getText();
            DefaultTableModel modeloT = new DefaultTableModel();
            vista.table1.setModel(modeloT);

            modeloT.addColumn("ID");
            modeloT.addColumn("Nombre");
            modeloT.addColumn("Precio");
            modeloT.addColumn("Stock");
            modeloT.addColumn("Imagen");
            modeloT.addColumn("Descripcion");

            Object[] registros = new Object[6];

            List numRegistros = modelo.obtenerPorNombre(texto);

            for (int i = 0; i < numRegistros.size(); i++) {
                Articulo articulo = (Articulo) numRegistros.get(i);
                registros[0] = articulo.getId();
                registros[1] = articulo.getNombre();
                registros[2] = articulo.getPrecio();
                registros[3] = articulo.getStock();
                registros[4] = articulo.getImagen();
                registros[5] = articulo.getDescripcion();
                modeloT.addRow(registros);

            }
            //Propiedades abla
            vista.table1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            vista.table1.getTableHeader().setReorderingAllowed(false);

            vista.txtTotalArticulosTextField.setText(String.valueOf(numRegistros.size()));


        }

    }
}
