package TP.ui;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import TP.datos.GestorRutas;
import TP.datos.Ruta;

public class PanelHabilitacionCamino extends JPanel {

	//String nombre, Double hs_apertura, Double hs_cierre, boolean estado
	
			private JLabel etiquetaCamino;
			private JComboBox menuCamino;
			private JLabel etiquetaEstado;
			private JRadioButton habilitada, deshabilitada;
			private JButton botonGuardar;
			private ButtonGroup bg;
			
			public void inicializar() {
				
				etiquetaCamino = new JLabel ("Nombre de la ruta: ");
				etiquetaEstado = new JLabel ("Ruta operativa? ");
				menuCamino = new JComboBox();
				habilitada = new JRadioButton ("Si");
				deshabilitada = new JRadioButton ("No");
				bg = new ButtonGroup();
				botonGuardar = new JButton("GUARDAR");
				bg.add(habilitada); bg.add(deshabilitada);
				
				menuCamino.addItem("-----");
				for (Ruta r : GestorRutas.getInstancia().getRutas()) {
					menuCamino.addItem(r);
				}
				
				//COMPLETAR LA ACCION CUANDO SE QUIEREN GUARDAR LOS DATOS
				botonGuardar.addActionListener(e -> {
					try {
						Ruta cam = (Ruta) menuCamino.getSelectedItem();
						boolean hab = habilitada.isSelected();
						boolean deshab = deshabilitada.isSelected();
						if (this.validar(hab, deshab)) {
							GestorRutas.getInstancia().editarRutas(cam.getOrigen(), cam.getDestino(), cam.getCapacidad(), cam.getDuracion_min(), hab, cam);
							JOptionPane.showMessageDialog(this, "Ruta modificada!");
						} else {
							JOptionPane.showMessageDialog(this, "Ruta no modificada. Asegurarse de seleccionar una y tildar una opcion de habilitacion");
						}	
					}
					catch(Exception ex) {
						//ex.printStackTrace();
						JOptionPane.showMessageDialog(this, "Ruta no agregada. Asegurarse de seleccionar una y tildar una opcion de habilitacion");
					}
				} );
				
				
				
				this.add(etiquetaCamino);
				this.add(menuCamino);
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
