package TP.ui;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import TP.datos.Estado_Orden;
import TP.datos.GestorSucursales;
import TP.datos.Stock;
import TP.datos.Sucursal;

public class PanelAltaOrden extends JPanel {

	/*
	 * private int id;
	private String fecha;
	private Sucursal destino;
	private int tiempo_maximo;
	private ArrayList<Stock> productos;
	private Estado_Orden estado;
	 */
	
	// Atributos primer panel
    private JLabel etiquetaIdSucursal;
    private JComboBox inputIdSucursal;
    private JButton botonSiguiente;

    public void inicializar(JFrame frame, JPanel panel) {
    	
        etiquetaIdSucursal = new JLabel("ID de la sucursal destino: ");
        inputIdSucursal = new JComboBox();
        botonSiguiente = new JButton("SIGUIENTE");

        for (Sucursal s : GestorSucursales.getInstancia().getSucursales()) {
			inputIdSucursal.addItem(s);
		}
        
        botonSiguiente.addActionListener( e -> {
			try {
				frame.setContentPane(panel);
				((PanelAltaOrden2) panel).setSeleccionada(this.getSeleccion());
				((PanelAltaOrden2) panel).inicializarOrdenNueva();
				frame.pack();
				frame.setSize(500,500);
			}catch (Exception ex) {
				JOptionPane.showMessageDialog(frame, "Seleccione una sucursal!");
				ex.printStackTrace();
			}
		});
        
        this.add(etiquetaIdSucursal);
        this.add(inputIdSucursal);
        this.add(botonSiguiente);
        

    }
    
    public JButton getBotonSig() {
    	
    	return botonSiguiente;
    	
    }
    
    public Sucursal getSeleccion() throws Exception{
    	if ((Sucursal)inputIdSucursal.getSelectedItem() == null) {
    		throw new Exception();
    	}else {
    		return (Sucursal)inputIdSucursal.getSelectedItem();
    	}
    }
    
	
}
