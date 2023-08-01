package TP.ui;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import TP.datos.GestorSucursales;
import TP.datos.Sucursal;

public class PanelHabilitacionSucursal extends JPanel {

	//String nombre, Double hs_apertura, Double hs_cierre, boolean estado
	
		private JLabel etiquetaSucursal;
		private JComboBox menuSucursal;
		private JLabel etiquetaEstado;
		private JRadioButton habilitada, deshabilitada;
		private JButton botonGuardar;
		private ButtonGroup bg;
		
		public void inicializar() {
			
			etiquetaSucursal = new JLabel ("Nombre de la sucursal: ");
			etiquetaEstado = new JLabel ("Sucursal operativa? ");
			menuSucursal = new JComboBox();
			habilitada = new JRadioButton ("Si");
			deshabilitada = new JRadioButton ("No");
			bg = new ButtonGroup();
			botonGuardar = new JButton("GUARDAR");
			bg.add(habilitada); bg.add(deshabilitada);
			
			menuSucursal.addItem("-----");
			for (Sucursal s : GestorSucursales.getInstancia().getSucursales()) {
				menuSucursal.addItem(s);
			}
			
			//COMPLETAR LA ACCION CUANDO SE QUIEREN GUARDAR LOS DATOS
			botonGuardar.addActionListener(e -> {
				try {
					Sucursal suc = (Sucursal) menuSucursal.getSelectedItem();
					boolean hab = habilitada.isSelected();
					boolean deshab = deshabilitada.isSelected();
					if (this.validar(hab, deshab)) {
						GestorSucursales.getInstancia().editarSucursal(suc, suc.getNombre(), suc.getHs_apertura(), suc.getHs_cierre(), hab);
						JOptionPane.showMessageDialog(this, "Sucursal modificada!");
					} else {
						JOptionPane.showMessageDialog(this, "Sucursal no modificada. Asegurarse de seleccionar una y tildar una opcion de habilitacion");
					}	
				}
				catch(Exception ex) {
					//ex.printStackTrace();
					JOptionPane.showMessageDialog(this, "Sucursal no agregada. Asegurarse de seleccionar una y tildar una opcion de habilitacion");
				}
			} );
			
			
			
			this.add(etiquetaSucursal);
			this.add(menuSucursal);
			this.add(etiquetaEstado);
			this.add(habilitada);
			this.add(deshabilitada);
			this.add(botonGuardar);
			
		}
		
		private boolean validar(boolean hab, boolean deshab) {
			if (!hab && !deshab) return false;
			return true;
		}
	
}
