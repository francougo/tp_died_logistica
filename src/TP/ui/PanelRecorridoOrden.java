package TP.ui;

import java.util.stream.Collectors;

import javax.swing.*;

import TP.datos.Estado_Orden;
import TP.datos.GestorRutas;
import TP.datos.GestorSucursales;
import TP.datos.OrdenProvision;
import TP.datos.Sucursal;

public class PanelRecorridoOrden extends JPanel {
	
	
	private JLabel etiquetaOrden;
	private JComboBox menuOrden;
	private JButton botonSiguiente;
	private JComboBox menuSucursal;
	private JLabel etiquetaSucursal;
	private JButton botonBuscarSuc;
	
	public void inicializar(JFrame frame, JPanel panel) {
		
		etiquetaOrden = new JLabel("Seleccione la orden a asignar recorrido: ");
		etiquetaSucursal = new JLabel("Asigne una sucursal de origen");
		menuOrden = new JComboBox();
		menuSucursal = new JComboBox();
		botonSiguiente = new JButton("SIGUIENTE");
		botonBuscarSuc = new JButton("BUSCAR SUCURSALES POSIBLES");
		
		for (Sucursal s : GestorSucursales.getInstancia().getSucursales()) {
			for (OrdenProvision o: s.getOrdenes().stream().filter(o -> o.getEstadoTipo() == Estado_Orden.PENDIENTE).collect(Collectors.toList())) {
				menuOrden.addItem(o);
			}
		}
		
		botonBuscarSuc.addActionListener(e -> {
			try {
				Sucursal puerto = GestorSucursales.getInstancia().getPuerto();
				System.out.println(puerto);
				OrdenProvision orden = ((OrdenProvision) menuOrden.getSelectedItem());
				menuSucursal.removeAllItems();
				for(Sucursal s: GestorRutas.getInstancia().sucursalesPosibles(orden, puerto)) {
					if (!orden.getDestino().equals(s)) {
						menuSucursal.addItem(s);
					}
				}
			}catch (Exception ex) {
				JOptionPane.showMessageDialog(frame, "Seleccione una orden!");
			}
		});
		
		botonSiguiente.addActionListener( e -> {
			try {
				frame.setContentPane(panel);
				((PanelRecorridoOrden2) panel).setSeleccionada(((PanelRecorridoOrden) this).getSeleccion());
				Sucursal auxSuc = (Sucursal) menuSucursal.getSelectedItem();
				System.out.println(auxSuc);

				((PanelRecorridoOrden2) panel).inicializarMenuCaminos(auxSuc);
				frame.pack();
				frame.setSize(500,500);
			}catch (Exception ex) {
				JOptionPane.showMessageDialog(frame, "Seleccione una sucursal!");
			}
		});
		
		this.add(etiquetaOrden);
		this.add(menuOrden);
		this.add(botonBuscarSuc);
		this.add(etiquetaSucursal);
		this.add(menuSucursal);
		this.add(botonSiguiente);
	
	}
	
	public JButton getBotonSig() {
		
		return botonSiguiente;
		
	}
	
	public OrdenProvision getSeleccion() throws Exception {
		if ((OrdenProvision) menuOrden.getSelectedItem() == null) {
    		throw new Exception();
    	}else {
    		return (OrdenProvision) menuOrden.getSelectedItem();
    	}
	}
}