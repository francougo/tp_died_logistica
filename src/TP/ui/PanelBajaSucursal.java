package TP.ui;


import javax.swing.*;

import TP.datos.GestorSucursales;
import TP.datos.Sucursal;

public class PanelBajaSucursal extends JPanel{
	
	private JLabel etiquetaBorrado;
	private JComboBox menuBorrado;
	private JButton botonBorrado;
	
	public void inicializar() {
		
		etiquetaBorrado = new JLabel("Seleccione la sucursal a borrar");
		menuBorrado = new JComboBox();
		botonBorrado = new JButton("ELIMINAR");
		
		menuBorrado.addItem("-----");
		for (Sucursal s : GestorSucursales.getInstancia().getSucursales()) {
			menuBorrado.addItem(s);
		}
		
		//prueba de evento. para la version final tendria que borrar el elemento seleccionado y retornar un msj de exito.
		botonBorrado.addActionListener(e -> {
			try {
				Sucursal seleccionado = (Sucursal)menuBorrado.getSelectedItem();
				GestorSucursales.getInstancia().bajaSucursal(((Sucursal) menuBorrado.getSelectedItem()));
				JOptionPane.showMessageDialog(this, "Sucursal borrada!");
				//this.actualizarLista();	REVISAR ACTUALIZACION DE LA LISTA
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(this, "Sucursal no borrada. Asegurarse de seleccionar una.");
			}
		});
		
		this.add(etiquetaBorrado);
		this.add(menuBorrado);
		this.add(botonBorrado);
		
	}

}
