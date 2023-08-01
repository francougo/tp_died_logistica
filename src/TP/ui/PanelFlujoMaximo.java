package TP.ui;

import javax.swing.*;

import TP.datos.GestorRutas;
import TP.datos.GestorSucursales;


public class PanelFlujoMaximo extends JPanel {
	
	private JLabel etiquetaFlujo;
	
	public void inicializar() {
		
		GestorSucursales gestorSuc = GestorSucursales.getInstancia();
		GestorRutas gestorRut = GestorRutas.getInstancia();
		Double flujomax = gestorRut.obtenerFlujoMaximo(gestorRut.caminosPosibles(gestorSuc.getCentro(), gestorSuc.getPuerto()));
		String aux = new String("Flujo maximo de la red: " + flujomax.toString() + " kgs");
		etiquetaFlujo = new JLabel(aux);
		
		this.add(etiquetaFlujo);
	
	}
	

}
