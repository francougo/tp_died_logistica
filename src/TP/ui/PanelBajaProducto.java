package TP.ui;


import javax.swing.*;

import TP.datos.GestorProductos;
import TP.datos.OrdenProvision;
import TP.datos.Producto;

public class PanelBajaProducto extends JPanel {
	
	// Atributos
    private JLabel etiquetaBorrado;
    private JComboBox menuBorrado;
    private JButton botonBorrado;

    public void inicializar() {
        // Componentes para seleccionar el producto a borrar
        etiquetaBorrado = new JLabel("Seleccione el producto a borrar");
        menuBorrado = new JComboBox();
        botonBorrado = new JButton("ELIMINAR");

        menuBorrado.addItem("-----");
		for (Producto p : GestorProductos.getInstancia().getProductos()) {
			menuBorrado.addItem(p);
		}

		botonBorrado.addActionListener(e -> {
			try {
				Producto seleccionado = (Producto)menuBorrado.getSelectedItem();
				GestorProductos.getInstancia().bajaProducto(((Producto) menuBorrado.getSelectedItem()));
				JOptionPane.showMessageDialog(this, "Producto borrado!");
				//this.actualizarLista();	REVISAR ACTUALIZACION DE LA LISTA
			}catch(Exception ex) {
				//ex.printStackTrace();
				JOptionPane.showMessageDialog(this, "Producto no borrado. Asegurarse de seleccionar uno.");
			}
		});

        // Agregar componentes al panel
        this.add(etiquetaBorrado);
        this.add(menuBorrado);
        this.add(botonBorrado);
	

    }
    
}
