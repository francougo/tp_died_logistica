package TP.ui;

import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.swing.*;

import TP.datos.Camino;
import TP.datos.Estado_Orden;
import TP.datos.GestorRutas;
import TP.datos.GestorSucursales;
import TP.datos.OrdenProvision;
import TP.datos.Ruta;
import TP.datos.Sucursal;

public class PanelRecorridoOrden2 extends JPanel { 

	private JLabel etiquetaCamino;
	private JComboBox menuCamino;
	private JButton botonAsignar;
	private JLabel duracionCamino;
	private OrdenProvision seleccionada;
	
	public void inicializar() {
		
		etiquetaCamino = new JLabel("Seleccione el camino: ");
		menuCamino = new JComboBox();
		botonAsignar = new JButton("ASIGNAR");
		duracionCamino = new JLabel("Duracion del camino seleccionado");
		
		botonAsignar.addActionListener(e -> {
			//pasar la orden a estado "en proceso"
			//escribir en la orden el tiempo_maximo asignado
			try {
				Camino auxCamino = ((Camino) menuCamino.getSelectedItem());
				seleccionada.getDestino().editarOrden(seleccionada, Estado_Orden.EN_PROCESO, auxCamino.getTiempoMax());
				JOptionPane.showMessageDialog(this, "Camino asignado la orden!");
			}catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Seleccione un camino!");
			}
		});
		
		this.add(etiquetaCamino);
		this.add(menuCamino);
		this.add(duracionCamino);
		this.add(botonAsignar);
	
	}
	
	public void setSeleccionada(OrdenProvision o) {
		seleccionada = o;
	}
	
	public void inicializarMenuCaminos(Sucursal origenSeleccionado) {
		//esta funcion se llama cuando efectivamente se ha elegido algo en el menu anterior.
		
		menuCamino.removeAllItems();
		for (ArrayList<Sucursal> sucursales_del_camino: GestorRutas.getInstancia().caminosPosibles(seleccionada.getDestino(), origenSeleccionado)) {
			//lo pasamos a objeto camino para almacenarlo y que haga el display bien en pantalla
			Camino auxCamino = new Camino(sucursales_del_camino);
			menuCamino.addItem(auxCamino);
		}
		
	}
	
}
