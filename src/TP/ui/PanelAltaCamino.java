package TP.ui;


import javax.swing.*;

import TP.datos.GestorRutas;
import TP.datos.GestorSucursales;
import TP.datos.Sucursal;

public class PanelAltaCamino extends JPanel {
	
	//String nombre, Double hs_apertura, Double hs_cierre, boolean estado
	
		private JLabel etiquetaOrigen;
		private JComboBox inputOrigen;
		private JLabel etiquetaDestino;
		private JComboBox inputDestino;
		private JLabel etiquetaCapacidad;
		private JTextField inputCapacidad;
		private JLabel etiquetaDuracion;
		private JTextField inputDuracion;
		private JLabel etiquetaEstado;
		private JRadioButton habilitada, deshabilitada;
		private ButtonGroup bg;
		private JButton botonGuardar;
		
		public void inicializar() {
			
			etiquetaOrigen = new JLabel ("Origen: ");
			etiquetaDestino = new JLabel ("Destino: ");
			etiquetaCapacidad = new JLabel ("Capacidad maxima: ");
			etiquetaDuracion = new JLabel ("Duracion (en min): ");
			etiquetaEstado = new JLabel ("ruta habilitada? ");
			inputOrigen = new JComboBox<Sucursal>();
			inputDestino = new JComboBox<Sucursal>();
			inputCapacidad = new JTextField (5);
			inputDuracion = new JTextField (10);
			habilitada = new JRadioButton ("Si");
			deshabilitada = new JRadioButton ("No");
			bg = new ButtonGroup();
			botonGuardar = new JButton("GUARDAR");
			bg.add(habilitada); bg.add(deshabilitada);
			
			inputOrigen.addItem("-----");
			for (Sucursal s : GestorSucursales.getInstancia().getSucursales()) {
				inputOrigen.addItem(s);
			}
			
			
			inputDestino.addItem("-----");
			for (Sucursal s : GestorSucursales.getInstancia().getSucursales()) {
				inputDestino.addItem(s);
			}
			
			
			
			//COMPLETAR LA ACCION CUANDO SE QUIEREN GUARDAR LOS DATOS
			botonGuardar.addActionListener(e -> {
				try {
					Sucursal origen = (Sucursal)inputOrigen.getSelectedItem();
					Sucursal destino = (Sucursal)inputDestino.getSelectedItem();
					String capacidad = inputCapacidad.getText();
					String duracion = inputDuracion.getText();
					boolean hab = habilitada.isSelected();
					boolean deshab = deshabilitada.isSelected();
					
					if (this.validar(origen, destino, capacidad, duracion, hab, deshab)) {
						GestorRutas.getInstancia().agregarRuta(origen, destino, Double.valueOf(capacidad), Double.valueOf(duracion), hab);
						JOptionPane.showMessageDialog(this, "Ruta agregada!");
					}else {
						JOptionPane.showMessageDialog(this, "Ruta no agregada. Asegurarse de que la capacidad y la duracion sean decimales, tildar una opcion de habilitacion y seleccionar una sucursal de destino y origen");
					}
				}catch(Exception ex) {
					//ex.printStackTrace();
					JOptionPane.showMessageDialog(this, "Ruta no agregada. Asegurarse de que la capacidad y la duracion sean decimales, tildar una opcion de habilitacion y seleccionar una sucursal de destino y origen");
				}
			});
			
			this.add(etiquetaOrigen);
			this.add(inputOrigen);
			this.add(etiquetaDestino);
			this.add(inputDestino);
			this.add(etiquetaCapacidad);
			this.add(inputCapacidad);
			this.add(etiquetaDuracion);
			this.add(inputDuracion);
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
