package TP.ui;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import TP.datos.GestorRutas;
import TP.datos.GestorSucursales;
import TP.datos.OrdenProvision;
import TP.datos.Ruta;
import TP.datos.Sucursal;

public class PanelBajaOrden extends JPanel {
	
	private JLabel etiquetaBorrado;
	private JComboBox menuBorrado;
	private JButton botonBorrado;
	
	public void inicializar() {
		
		etiquetaBorrado = new JLabel("Seleccione la orden a borrar: ");
		menuBorrado = new JComboBox();
		botonBorrado = new JButton("ELIMINAR");
		
		menuBorrado.addItem("-----");
		for (Sucursal s : GestorSucursales.getInstancia().getSucursales()) {
			for (OrdenProvision o: s.getOrdenes()) {
				menuBorrado.addItem(o);
			}
		}
	
		botonBorrado.addActionListener(e -> {
			try {
				OrdenProvision seleccionado = (OrdenProvision)menuBorrado.getSelectedItem();
				seleccionado.getDestino().bajaOrden(((OrdenProvision) menuBorrado.getSelectedItem()));
				JOptionPane.showMessageDialog(this, "Orden borrada!");
				//this.actualizarLista();	REVISAR ACTUALIZACION DE LA LISTA
			}catch(Exception ex) {
				//ex.printStackTrace();
				JOptionPane.showMessageDialog(this, "Orden no borrada. Asegurarse de seleccionar una.");
			}
		});
		
		this.add(etiquetaBorrado);
		this.add(menuBorrado);
		this.add(botonBorrado);
		
		
	}
	

}
