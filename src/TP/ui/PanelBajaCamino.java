package TP.ui;

import javax.swing.*;

import TP.datos.GestorRutas;
import TP.datos.GestorSucursales;
import TP.datos.Ruta;
import TP.datos.Sucursal;

public class PanelBajaCamino extends JPanel {
	
	private JLabel etiquetaBorrado;
	private JComboBox menuBorrado;
	private JButton botonBorrado;
	
	public void inicializar() {
		
		etiquetaBorrado = new JLabel("Seleccione el camino a borrar");
		menuBorrado = new JComboBox();
		botonBorrado = new JButton("ELIMINAR");
		
		menuBorrado.addItem("-----");
		for (Ruta r : GestorRutas.getInstancia().getRutas()) {
			menuBorrado.addItem(r);
		}
		
		botonBorrado.addActionListener(e -> {
			try {
				Ruta seleccionado = (Ruta)menuBorrado.getSelectedItem();
				GestorRutas.getInstancia().bajaRuta(((Ruta) menuBorrado.getSelectedItem()));
				JOptionPane.showMessageDialog(this, "Ruta borrada!");
				//this.actualizarLista();	REVISAR ACTUALIZACION DE LA LISTA
			}catch(Exception ex) {
				//ex.printStackTrace();
				JOptionPane.showMessageDialog(this, "Ruta no borrada. Asegurarse de seleccionar una.");
			}
		});
		
		this.add(etiquetaBorrado);
		this.add(menuBorrado);
		this.add(botonBorrado);

	}
	
	public void actualizarLista() {
		
		menuBorrado.removeAll();
		for (Ruta r : GestorRutas.getInstancia().getRutas()) {
			menuBorrado.addItem(r);
		}
		
	}
}
