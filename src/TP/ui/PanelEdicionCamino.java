package TP.ui;

import javax.swing.*;

import TP.datos.GestorRutas;
import TP.datos.GestorSucursales;
import TP.datos.Ruta;
import TP.datos.Sucursal;

public class PanelEdicionCamino extends JPanel {

	//String nombre, Double hs_apertura, Double hs_cierre, boolean estado
	
		private JLabel etiquetaRuta;
		private JComboBox menuRuta;
		private JLabel etiquetaOrigen;
		private JComboBox menuOrigen;
		private JLabel etiquetaDestino;
		private JComboBox menuDestino;
		private JLabel etiquetaDuracion;
		private JTextField inputDuracion;
		private JLabel etiquetaCapacidad;
		private JTextField inputCapacidad;
		private JLabel etiquetaEstado;
		private JRadioButton habilitada, deshabilitada;
		private JButton botonGuardar;
		private ButtonGroup bg;
		
		
		public void inicializar() {
			
			etiquetaRuta = new JLabel ("Seleccione la ruta a editar: ");
			menuRuta = new JComboBox();
			etiquetaOrigen = new JLabel ("Sucursal de origen: ");
			menuOrigen = new JComboBox();
			etiquetaDestino = new JLabel ("Sucursal de destino: ");
			menuDestino = new JComboBox();
			etiquetaDuracion = new JLabel ("duracion (en min): ");
			inputDuracion = new JTextField (2);
			etiquetaCapacidad = new JLabel ("capacidad (en kg): ");
			inputCapacidad = new JTextField (2);
			etiquetaEstado = new JLabel("ruta operativa? ");
			habilitada = new JRadioButton ("Si");
			deshabilitada = new JRadioButton ("No");
			bg = new ButtonGroup();
			botonGuardar = new JButton("GUARDAR");
			bg.add(habilitada); bg.add(deshabilitada);
			
			menuRuta.addItem("-----");
			for (Ruta r : GestorRutas.getInstancia().getRutas()) {
				menuRuta.addItem(r);
			}
			
			menuOrigen.addItem("-----");
			for (Sucursal s : GestorSucursales.getInstancia().getSucursales()) {
				menuOrigen.addItem(s);
			}
			
			menuDestino.addItem("-----");
			for (Sucursal s : GestorSucursales.getInstancia().getSucursales()) {
				menuDestino.addItem(s);
			}
			
			botonGuardar.addActionListener(e -> {
				try {
					Sucursal origen = (Sucursal)menuOrigen.getSelectedItem();
					Sucursal destino = (Sucursal)menuDestino.getSelectedItem();
					String capacidad = inputCapacidad.getText();
					String duracion = inputDuracion.getText();
					boolean hab = habilitada.isSelected();
					boolean deshab = deshabilitada.isSelected();
					
					if (this.validar(origen, destino, capacidad, duracion, hab, deshab)) {
						Ruta auxruta = (Ruta) menuRuta.getSelectedItem();
						Sucursal auxorigen  = (Sucursal) menuOrigen.getSelectedItem();
						Sucursal auxdestino = (Sucursal) menuDestino.getSelectedItem();
						GestorRutas.getInstancia().editarRutas(auxorigen, auxdestino, Double.valueOf(capacidad), Double.valueOf(duracion), hab, auxruta);
						JOptionPane.showMessageDialog(this, "Ruta modificada!");
					}else {
						JOptionPane.showMessageDialog(this, "Ruta no modificada. Asegurarse de que la capacidad y la duracion sean decimales, tildar una opcion de habilitacion.");
					}
				}catch(Exception ex) {
					//ex.printStackTrace();
					JOptionPane.showMessageDialog(this, "Ruta no modificada. Asegurarse de que la capacidad y la duracion sean decimales, tildar una opcion de habilitacion y seleccionar una sucursal de destino y origen");
				}
			});
			
			this.add(etiquetaRuta);
			this.add(menuRuta);
			this.add(etiquetaOrigen);
			this.add(menuOrigen);
			this.add(etiquetaDestino);
			this.add(menuDestino);
			this.add(etiquetaDuracion);
			this.add(inputDuracion);
			this.add(etiquetaCapacidad);
			this.add(inputCapacidad);
			this.add(etiquetaEstado);
			this.add(habilitada);
			this.add(deshabilitada);
			this.add(botonGuardar);
			
		}
		
		private boolean validar(Sucursal origen, Sucursal destino, String capacidad, String dur, boolean hab, boolean deshab) {
			if (!hab && !deshab) return false;
			if (origen.equals(destino)) return false;
			if (capacidad.isEmpty() || dur.isEmpty()) return false;
			try {
				Double.valueOf(capacidad);
				Double.valueOf(dur);
			} catch (Exception e) {
				return false;
			}
			return true;
		}
}


		
