package TP.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.swing.*;
import TP.datos.GestorSucursales;
import TP.datos.Sucursal;

public class PanelBusquedaSucursal extends JPanel {
	
	//int id, String nombre, Double hs_apertura, Double hs_cierre, boolean estado
	
	private JLabel etiquetaBusqueda;
	private JLabel etiquetaID;
	private JTextField inputID;
	private JLabel etiquetaNombre;
	private JTextField inputNombre;
	private JLabel etiquetaHs_apertura;
	private JTextField inputHs_apertura;
	private JLabel etiquetaHs_cierre;
	private JTextField inputHs_cierre;
	private JLabel etiquetaEstado;
	private JRadioButton habilitada, deshabilitada;
	private JButton botonBusqueda;
	private ButtonGroup bg;
	private JRadioButton porNombre, porID, porHsA, porHsC, porEstado;
	private ButtonGroup grupoBusqueda;
	
	private JList<String> resultadosList;
    private DefaultListModel<String> listModel;
	
	public void inicializar() {
		
		etiquetaNombre = new JLabel ("Nombre de la sucursal: ");
		etiquetaHs_apertura = new JLabel ("Horario de apertura (ingresar decimal): ");
		etiquetaHs_cierre = new JLabel ("Nombre de la sucursal (ingresar decimal): ");
		etiquetaEstado = new JLabel ("Sucursal operativa? ");
		etiquetaID = new JLabel("ID: ");
		
		inputNombre = new JTextField (30);
		inputHs_apertura = new JTextField (5);
		inputHs_cierre = new JTextField (5);
		inputID = new JTextField(2);
		habilitada = new JRadioButton ("Si");
		deshabilitada = new JRadioButton ("No");
		porNombre = new JRadioButton();
		porID = new JRadioButton();
		porHsA = new JRadioButton();
		porHsC = new JRadioButton();
		porEstado = new JRadioButton();
		bg = new ButtonGroup();
		grupoBusqueda = new ButtonGroup();
		
		botonBusqueda = new JButton("BUSCAR");
		bg.add(habilitada); bg.add(deshabilitada);
		grupoBusqueda.add(porNombre);
		grupoBusqueda.add(porID);
		grupoBusqueda.add(porHsA);
		grupoBusqueda.add(porHsC);
		grupoBusqueda.add(porEstado);
		
		listModel = new DefaultListModel<>();
	    resultadosList = new JList<>(listModel);
	    JScrollPane resultadosScrollPane = new JScrollPane(resultadosList);
		
		//this.add(etiquetaBusqueda);
	    this.add(porID);
		this.add(etiquetaID);
		this.add(inputID);
		this.add(porNombre);
		this.add(etiquetaNombre);
		this.add(inputNombre); 
		this.add(porHsA);
		this.add(etiquetaHs_apertura);
		this.add(inputHs_apertura);
		this.add(porHsC);
		this.add(etiquetaHs_cierre);
		this.add(inputHs_cierre);
		this.add(porEstado);
		this.add(etiquetaEstado);
		this.add(habilitada);
		this.add(deshabilitada);
		this.add(botonBusqueda);
	    this.add(resultadosScrollPane);
		
		botonBusqueda.addActionListener(e -> {
			
			Predicate<Sucursal> predicado;
			try {
				predicado = this.crearPredicado();
				ArrayList<Sucursal> rdo = GestorSucursales.getInstancia().busqueda(predicado);
				List<String> aux = rdo.stream()
										.map(s -> "NOMBRE " + s.getNombre() + " ID: " + s.getId() + " APERTURA: " + s.getHs_apertura() + " CIERRE: " + s.getHs_cierre() + " OPERATIVA: " + s.getEstado())
										.collect(Collectors.toList());
				mostrarResultados(aux);
			} catch (Exception e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(this, "No se puede realizar la busqueda. Asegurarse de seleccionar una opcion de busqueda y llenar el campo correspondiente");
			}
			
		});
		
	}
	
	private Predicate<Sucursal> crearPredicado() throws Exception{
		
		if(!porEstado.isSelected() && !porID.isSelected() && !porHsA.isSelected() && !porHsC.isSelected() && !porNombre.isSelected()) {
			throw new Exception();
		}else if(porID.isSelected()) {
			Integer id = Integer.valueOf(inputID.getText());
			return (s -> s.getId() == id);
		}else if(porNombre.isSelected()) {
			String nombre = inputNombre.getText();
			return (s -> s.getNombre().contains(nombre));
		}else if(porEstado.isSelected()) {
			if (!habilitada.isSelected() && !deshabilitada.isSelected()) {
				throw new Exception();
			}
			return (s -> s.getEstado() == habilitada.isSelected());
		}else if(porHsA.isSelected()) {
			Double apertura = Double.valueOf(inputHs_apertura.getText());
			return (s -> s.getHs_apertura() == apertura);
		}else if(porHsC.isSelected()) {
			Double cierre = Double.valueOf(inputHs_cierre.getText());
			return (s -> s.getHs_apertura() == cierre);
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
