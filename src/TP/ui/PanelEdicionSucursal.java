package TP.ui;


import javax.swing.*;

import TP.datos.GestorSucursales;
import TP.datos.Sucursal;

public class PanelEdicionSucursal extends JPanel{
	
	//String nombre, Double hs_apertura, Double hs_cierre, boolean estado
	
	private JLabel etiquetaSucursal;
	private JComboBox menuSucursal;
	private JLabel etiquetaNombre;
	private JTextField inputNombre;
	private JLabel etiquetaHs_apertura;
	private JTextField inputHs_apertura;
	private JLabel etiquetaHs_cierre;
	private JTextField inputHs_cierre;
	private JLabel etiquetaEstado;
	private JRadioButton habilitada, deshabilitada;
	private JButton botonGuardar;
	private ButtonGroup bg;
	
	public void inicializar() {
		
		etiquetaSucursal = new JLabel ("Seleccione la sucursal a editar: ");
		menuSucursal = new JComboBox();
		etiquetaNombre = new JLabel ("Nombre de la sucursal: ");
		etiquetaHs_apertura = new JLabel ("Horario de apertura (ingresar decimal): ");
		etiquetaHs_cierre = new JLabel ("Nombre de la sucursal (ingresar decimal): ");
		etiquetaEstado = new JLabel ("Sucursal operativa? ");
		inputNombre = new JTextField (30);
		inputHs_apertura = new JTextField (5);
		inputHs_cierre = new JTextField (5);
		habilitada = new JRadioButton ("Si");
		deshabilitada = new JRadioButton ("No");
		bg = new ButtonGroup();
		botonGuardar = new JButton("GUARDAR");
		bg.add(habilitada); bg.add(deshabilitada);
		
		menuSucursal.addItem("-----");
		for (Sucursal s : GestorSucursales.getInstancia().getSucursales()) {
			menuSucursal.addItem(s);
		}
		
		botonGuardar.addActionListener(e -> {
			try {
				String nombre = inputNombre.getText();
				String hs_a = inputHs_apertura.getText();
				String hs_c = inputHs_cierre.getText();
				boolean hab = habilitada.isSelected();
				boolean deshab = deshabilitada.isSelected();
				if (this.validar(nombre, hs_a, hs_c, hab, deshab)) {
					Sucursal aux = (Sucursal) menuSucursal.getSelectedItem();
					GestorSucursales.getInstancia().editarSucursal(aux, nombre, Double.valueOf(hs_a), Double.valueOf(hs_c), hab);
					JOptionPane.showMessageDialog(this, "Sucursal modificada!");
				} else {
					JOptionPane.showMessageDialog(this, "Sucursal no modificada. Asegurarse de que nombre sea una cadena de caracteres (10 caracteres), los horarios decimales, y tildar una opcion de habilitacion");
				}
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(this, "Sucursal no modificada. Asegurarse de seleccionar una y que nombre sea una cadena de caracteres (10 caracteres), los horarios decimales, y tildar una opcion de habilitacion.");
			}
		});
		
		this.add(etiquetaSucursal);
		this.add(menuSucursal);
		this.add(etiquetaNombre);
		this.add(inputNombre);
		this.add(etiquetaHs_apertura);
		this.add(inputHs_apertura);
		this.add(etiquetaHs_cierre);
		this.add(inputHs_cierre);
		this.add(etiquetaEstado);
		this.add(habilitada);
		this.add(deshabilitada);
		this.add(botonGuardar);
		
	}
	
	private boolean validar(String nombre, String hs_a, String hs_c, boolean hab, boolean deshab) {
		if (!hab && !deshab) return false;
		if (nombre.isEmpty() || hs_a.isEmpty() || hs_c.isEmpty()) return false;
		if (nombre.length() > 10) return false;
		try {
			Double.valueOf(hs_a); 
			Double.valueOf(hs_c); 

		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
