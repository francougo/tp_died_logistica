package TP.ui;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import TP.datos.GestorProductos;
import TP.datos.GestorSucursales;

public class PanelAltaProducto extends JPanel {
	
	 // Atributos
    private JLabel etiquetaNombre;
    private JTextField inputNombre;
    private JLabel etiquetaDescripcion;
    private JTextField inputDescripcion;
    private JLabel etiquetaPrecioUnitario;
    private JTextField inputPrecioUnitario;
    private JLabel etiquetaPesoKilos;
    private JTextField inputPesoKilos;
    private JButton botonGuardar;

    public void inicializar() {
        // Componentes para ingresar datos del producto
        etiquetaNombre = new JLabel("Nombre del producto:");
        inputNombre = new JTextField(30);
        etiquetaDescripcion = new JLabel("Descripcion:");
        inputDescripcion = new JTextField(30);
        etiquetaPrecioUnitario = new JLabel("Precio Unitario (ingresar decimal):");
        inputPrecioUnitario = new JTextField(10);
        etiquetaPesoKilos = new JLabel("Peso en kilos (ingresar decimal):");
        inputPesoKilos = new JTextField(10);
        botonGuardar = new JButton("GUARDAR");

        // Acción cuando se quiere guardar los datos
        botonGuardar.addActionListener(e -> {
        	try {
        		String nombre = inputNombre.getText();
    			String desc = inputDescripcion.getText();
    			String precio = inputPrecioUnitario.getText();
    			String peso = inputPesoKilos.getText();
    			if (this.validar(nombre, desc, precio, peso)) {
    				GestorProductos.getInstancia().agregarProducto(nombre, desc, Double.valueOf(precio), Double.valueOf(peso));
    				JOptionPane.showMessageDialog(this, "Producto agregado!");
    			}else {
    				JOptionPane.showMessageDialog(this, "Producto no agregado. Asegurarse de que nombre y descripcion sean cadenas de caracteres, y precio y peso decimales");
    			}
        	}catch(Exception ex) {
				//ex.printStackTrace();
				JOptionPane.showMessageDialog(this, "Producto no agregado. Asegurarse de que nombre y descripcion sean cadenas de caracteres, y precio y peso decimales");
			}
        });

        // Agregar componentes al panel
        this.add(etiquetaNombre);
        this.add(inputNombre);
        this.add(etiquetaDescripcion);
        this.add(inputDescripcion);
        this.add(etiquetaPrecioUnitario);
        this.add(inputPrecioUnitario);
        this.add(etiquetaPesoKilos);
        this.add(inputPesoKilos);
        this.add(botonGuardar);
    }

    private boolean validar(String nombre, String desc, String precio, String peso) {
		if (nombre.isEmpty() || desc.isEmpty() || precio.isEmpty() || peso.isEmpty()) return false;
		try {
			Double.valueOf(precio);
			Double.valueOf(peso);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
