package org.example.modelo.example.vista;

import javax.swing.*;

public class JFArticulos extends JFrame {
    private JPanel pnPrincipal;
    public JTextField txtIdTextField;
    public JTextField txtPrecio;
    public JTextField txtStockTextField;
    public JTextField txtNombreTextField;
    public JTextField txtImagenTextField;
    public JTextField txtDescripcionTextField;
    public JButton btnNuevo;
    public JButton btnGuardar;
    public JButton btnModificar;
    public JButton btnEliminar;
    public JButton btnSeleccionar;
    public JTextField txtBusquedaNombreTextField;
    public JTextField txtTotalArticulosTextField;
    public JTable table1;
    private JPanel panelInferior;
    private JPanel panelSuperior;
    private JPanel panelIzquierdo;
    private JPanel panelDerecho;

  public JFArticulos() {
      setContentPane(pnPrincipal);
      setTitle("Articulos");
      //setSize(1000,800);
      pack();
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }


}
