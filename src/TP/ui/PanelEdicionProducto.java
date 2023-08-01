package TP.ui;


import javax.swing.*;

import TP.datos.GestorProductos;
import TP.datos.GestorSucursales;
import TP.datos.Producto;
import TP.datos.Sucursal;

public class PanelEdicionProducto extends JPanel {

	 // Atributos
    private JLabel etiquetaProducto;
    private JComboBox menuProducto;
    private JLabel etiquetaNombre;
    private JTextField inputNombre;
    private JLabel etiquetaDescripcion;
    private JTextField inputDescripcion;
    private JLabel etiquetaPrecio;
    private JTextField inputPrecio;
    private JLabel etiquetaPeso;
    private JTextField inputPeso;
    private JButton botonGuardar;

    public void inicializar() {
        etiquetaProducto = new JLabel("Seleccione el producto a editar: ");
        menuProducto = new JComboBox();
        etiquetaNombre = new JLabel("Nombre del producto: ");
        etiquetaDescripcion = new JLabel("Descripción del producto: ");
        etiquetaPrecio = new JLabel("Precio unitario (ingresar decimal): ");
        etiquetaPeso = new JLabel("Peso en kilogramos (ingresar decimal): ");
        inputNombre = new JTextField(30);
        inputDescripcion = new JTextField(30);
        inputPrecio = new JTextField(5);
        inputPeso = new JTextField(5);
        botonGuardar = new JButton("GUARDAR");

        menuProducto.addItem("-----");
		for (Producto p : GestorProductos.getInstancia().getProductos()) {
			menuProducto.addItem(p);
		}

        botonGuardar.addActionListener(e -> {
        	try {
        		String nombre = inputNombre.getText();
    			String desc = inputDescripcion.getText();
    			String precio = inputPrecio.getText();
    			String peso = inputPeso.getText();
    			if (this.validar(nombre, desc, precio, peso)) {
    				Producto aux = (Producto) menuProducto.getSelectedItem();
    				GestorProductos.getInstancia().editarProductos(nombre, desc, Double.valueOf(precio), Double.valueOf(peso),aux);
    				JOptionPane.showMessageDialog(this, "Producto modificado!");
    			}else {
    				JOptionPane.showMessageDialog(this, "Producto no modificado. Asegurarse de que nombre y descripcion sean cadenas de caracteres (de 10 y 20 caracteres), y precio y peso decimales");
    			}
        	}catch(Exception ex) {
				//ex.printStackTrace();
				JOptionPane.showMessageDialog(this, "Producto no modificado. Asegurarse de seleccionar uno y que nombre y descripcion sean cadenas de caracteres (de 10 y 20 caracteres), y precio y peso decimales");
			}
		});

        this.add(etiquetaProducto);
        this.add(menuProducto);
        this.add(etiquetaNombre);
        this.add(inputNombre);
        this.add(etiquetaDescripcion);
        this.add(inputDescripcion);
        this.add(etiquetaPrecio);
        this.add(inputPrecio);
        this.add(etiquetaPeso);
        this.add(inputPeso);
        this.add(botonGuardar);
    }
	
    private boolean validar(String nombre, String desc, String precio, String peso) {
		if (nombre.isEmpty() || desc.isEmpty() || precio.isEmpty() || peso.isEmpty()) return false;
		if (nombre.length() > 10 || desc.length() > 20) return false;
		try {
			Double.valueOf(precio);
			Double.valueOf(peso);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
    
}


