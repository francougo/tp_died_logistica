package TP.ui;

import javax.swing.*;

import TP.datos.GestorProductos;
import TP.datos.GestorRutas;
import TP.datos.GestorSucursales;
import TP.datos.Producto;
import TP.datos.Ruta;
import TP.datos.Stock;
import TP.datos.Sucursal;

public class PanelEdicionStockSucursal extends JPanel{
	
	private JLabel etiquetaSucursal;
	private JComboBox menuSucursal;
	private JLabel etiquetaProducto;
	private JComboBox menuProducto;
	private JLabel etiquetaCantidad;
	private JTextField inputCantidad;
	private JLabel etiquetaTipo;
	private JRadioButton ingreso, egreso;
	private ButtonGroup bg;
	private JButton botonGuardar;
	
	public void inicializar() {
		
		etiquetaSucursal = new JLabel("Seleccione una sucursal");
		etiquetaProducto = new JLabel("Seleccione un producto");
		etiquetaCantidad = new JLabel("Cantidad a modificar");
		etiquetaTipo = new JLabel("ingreso o egreso?");
		menuSucursal = new JComboBox();
		menuProducto = new JComboBox();
		inputCantidad = new JTextField(5);
		ingreso = new JRadioButton("I");
		egreso = new JRadioButton("E");
		botonGuardar = new JButton("GUARDAR");
		bg = new ButtonGroup();
		bg.add(ingreso); bg.add(egreso);
		
		menuSucursal.addItem("-----");
		for (Sucursal s : GestorSucursales.getInstancia().getSucursales()) {
			menuSucursal.addItem(s);
		}
		
		menuProducto.addItem("-----");
		for (Producto p : GestorProductos.getInstancia().getProductos()) {
			menuProducto.addItem(p);
		}
		
		botonGuardar.addActionListener(e -> {
			try {
        		String cant = inputCantidad.getText();
        		Sucursal auxsucursal = (Sucursal) menuSucursal.getSelectedItem();
        		Producto auxproducto = (Producto) menuProducto.getSelectedItem();
        		boolean ing = ingreso.isSelected();
        		boolean egr = egreso.isSelected();
            	if (this.validar(cant, auxsucursal, auxproducto, ing, egr)) {
            		if(egr) {
            			auxsucursal.agregarStock(auxproducto, -1*Integer.valueOf(cant));
            			//habria que poner algo que borre el stock de la sucursal cuando la cantidad de un stock es cero.
            		}else {
            			auxsucursal.agregarStock(auxproducto, Integer.valueOf(cant));
            		}
            		JOptionPane.showMessageDialog(this, "Stock modificado!");
            	}else {
            		JOptionPane.showMessageDialog(this, "Stock no modificado. Asegurarse de seleccionar un producto, sucursal y si es egreso o ingreso. Ingresar la cantidad entera. \n Ademas, no puede eliminar mas cantidad de la cantidad existente de un producto.");
            	}
        	}catch(Exception ex) {
				//ex.printStackTrace();
				JOptionPane.showMessageDialog(this, "Producto no agregado. Asegurarse de seleccionar un producto, sucursal y si es egreso o ingreso. Ingresar la cantidad entera");
        	}
		});

		this.add(etiquetaSucursal);
		this.add(menuSucursal);
		this.add(etiquetaProducto);
		this.add(menuProducto);
		this.add(etiquetaCantidad);
		this.add(inputCantidad);
		this.add(etiquetaTipo);
		this.add(ingreso);
		this.add(egreso);
		this.add(botonGuardar);
		
		
	}
	
    private boolean validar(String cantidad, Sucursal suc, Producto prod, boolean ing, boolean egr) {
		try {
			int restante;
			if (cantidad.isEmpty()) return false;
			if (!ing && !egr) return false;
			if(egr) {
				Stock auxiliar = suc.busqueda(s -> s.getProd().equals(prod)).stream().findFirst().orElse(null);
				restante = auxiliar.getCantidad() - Integer.valueOf(cantidad);
				if (auxiliar == null || restante < 0) return false;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
