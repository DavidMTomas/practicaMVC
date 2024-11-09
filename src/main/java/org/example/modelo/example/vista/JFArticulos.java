package org.example.modelo.example.vista;

import javax.swing.*;

public class JFArticulos extends JFrame {
    private JPanel pnPrincipal;
    public JTextField txtIdTextField;
    public JTextField txtPrecio;
    public JTextField txtStrockTextField;
    public JTextField txtNombreTextField;
    public JTextField txtImagenTextField;
    public JTextField txtDescripcionTextField;
    public JButton btnNuevo;
    public JButton btnGuardar;
    public JButton btnModificar;
    public JButton btnEliminar;
    public JButton seleccionarButton;
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
      pack();
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }


}
