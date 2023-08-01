package TP.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.swing.*;

import TP.datos.GestorRutas;
import TP.datos.GestorSucursales;
import TP.datos.Ruta;
import TP.datos.Sucursal;

public class PanelBusquedaCamino extends JPanel {

	//int id, String nombre, Double hs_apertura, Double hs_cierre, boolean estado
	
		private JLabel etiquetaID;
		private JTextField inputID;
		private JLabel etiquetaIDOrigen;
		private JTextField inputIDOrigen;
		private JLabel etiquetaIDDestino;
		private JTextField inputIDDestino;
		private JLabel etiquetaDuracion;
		private JTextField inputDuracion;
		private JLabel etiquetaCapacidad;
		private JTextField inputCapacidad;
		private JLabel etiquetaEstado;
		private JRadioButton habilitada, deshabilitada;
		private JRadioButton porID, porIDO, porIDD, porDuracion, porCapacidad, porEstado;
		private ButtonGroup grupoBusqueda;
		private JButton botonBusqueda;
		private ButtonGroup bg;
		private JList<String> resultadosList;
	    private DefaultListModel<String> listModel;
		
		public void inicializar() {
			
			etiquetaID = new JLabel("ID: ");
			etiquetaEstado = new JLabel ("ruta operativa? ");
			etiquetaIDOrigen = new JLabel ("ID sucursal origen: ");
			etiquetaIDDestino = new JLabel ("ID sucursal destino: ");
			etiquetaDuracion = new JLabel ("Duracion del trayecto: ");
			etiquetaCapacidad = new JLabel ("Capacidad: ");
			
			inputCapacidad = new JTextField (10);
			inputDuracion = new JTextField (10);
			inputIDOrigen = new JTextField (2);
			inputIDDestino = new JTextField (2);
			inputID = new JTextField(2);
			
			habilitada = new JRadioButton ("Si");
			deshabilitada = new JRadioButton ("No");
			bg = new ButtonGroup();
			bg.add(habilitada); bg.add(deshabilitada);
			porID= new JRadioButton();
			porIDO= new JRadioButton();
			porIDD= new JRadioButton();
			porDuracion= new JRadioButton();
			porCapacidad= new JRadioButton();
			porEstado= new JRadioButton();
			grupoBusqueda = new ButtonGroup();
			grupoBusqueda.add(porID);
			grupoBusqueda.add(porIDO);
			grupoBusqueda.add(porIDD);
			grupoBusqueda.add(porDuracion);
			grupoBusqueda.add(porCapacidad);
			grupoBusqueda.add(porEstado);
			
			botonBusqueda = new JButton("BUSCAR");
			listModel = new DefaultListModel<>();
		    resultadosList = new JList<>(listModel);
		    JScrollPane resultadosScrollPane = new JScrollPane(resultadosList);
			
		    this.add(porID);
			this.add(etiquetaID);
			this.add(inputID);
			this.add(porIDO);
			this.add(etiquetaIDOrigen);
			this.add(inputIDOrigen); 
			this.add(porIDD);
			this.add(etiquetaIDDestino);
			this.add(inputIDDestino);
			this.add(porDuracion);
			this.add(etiquetaDuracion);
			this.add(inputDuracion);
			this.add(porCapacidad);
			this.add(etiquetaCapacidad);
			this.add(inputCapacidad);
			this.add(porEstado);
			this.add(etiquetaEstado);
			this.add(habilitada);
			this.add(deshabilitada);
			this.add(botonBusqueda);
		    this.add(resultadosScrollPane);
			
		    botonBusqueda.addActionListener(e -> {
				
				Predicate<Ruta> predicado;
				try {
					predicado = this.crearPredicado();
					ArrayList<Ruta> rdo = GestorRutas.getInstancia().busqueda(predicado);
					List<String> aux = rdo.stream()
											.map(s -> " ID: " + s.getId() + " ID Sucursal origen: " + s.getOrigen().getId() + " ID Sucursal destino: " + s.getDestino().getId() + " CAPACIDAD: " + s.getCapacidad() + " DURACION: " + s.getDuracion_min() + " OPERATIVA: " + s.isEstado())
											.collect(Collectors.toList());
					mostrarResultados(aux);
				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(this, "No se puede realizar la busqueda. Asegurarse de seleccionar una opcion de busqueda y llenar el campo correspondiente");
				}
				
			});
			
		}
		
		private Predicate<Ruta> crearPredicado() throws Exception{
			
			if(!porEstado.isSelected() && !porID.isSelected() && !porCapacidad.isSelected() && !porDuracion.isSelected() && !porIDO.isSelected() && !porIDD.isSelected()) {
				throw new Exception();
			}else if(porID.isSelected()) {
				Integer id = Integer.valueOf(inputID.getText());
				return (s -> s.getId() == id);
			}else if(porIDO.isSelected()) {
				Integer id = Integer.valueOf(inputIDOrigen.getText());
				return (s -> s.getOrigen().getId() == id);
			}else if(porIDD.isSelected()) {
				Integer id = Integer.valueOf(inputIDDestino.getText());
				return (s -> s.getDestino().getId()  == id);
			}else if(porEstado.isSelected()) {
				if (!habilitada.isSelected() && !deshabilitada.isSelected()) {
					throw new Exception();
				}
				return (s -> s.isEstado() == habilitada.isSelected());
			}else if(porCapacidad.isSelected()) {
				Double apertura = Double.valueOf(inputCapacidad.getText());
				return (s -> s.getCapacidad() == apertura);
			}else if(porDuracion.isSelected()) {
				Double cierre = Double.valueOf(inputDuracion.getText());
				return (s -> s.getDuracion_min() == cierre);
			}
			return null;
			
		}
		
		private void mostrarResultados(List<String> aux) {
	        listModel.clear();

	        for (String sucursal : aux) {
	            listModel.addElement(sucursal);
	        }
	    }
	
}
