package TP.ui;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.swing.*;
import TP.datos.GestorProductos;
import TP.datos.GestorSucursales;
import TP.datos.Producto;
import TP.datos.Sucursal;

public class PanelBusquedaProducto extends JPanel {

	 // Atributos
	private JLabel etiquetaID;
	private JTextField inputID;
    private JLabel etiquetaNombre;
    private JTextField inputNombre;
    private JLabel etiquetaDescripcion;
    private JTextField inputDescripcion;
    private JLabel etiquetaPrecio;
    private JTextField inputPrecio;
    private JLabel etiquetaPeso;
    private JTextField inputPeso;
    
    private JRadioButton porNombre;
    private JRadioButton porDesc;
    private JRadioButton porPrecio;
    private JRadioButton porPeso;
    private JRadioButton porID;
    private ButtonGroup grupoBusqueda;
    
    private JButton botonBusqueda;
    private JList<String> resultadosList;
    private DefaultListModel<String> listModel;

    public void inicializar() {
  
        etiquetaNombre = new JLabel("Nombre del producto: ");
        etiquetaDescripcion = new JLabel("Descripción del producto: ");
        etiquetaPrecio = new JLabel("Precio unitario (ingresar decimal): ");
        etiquetaPeso = new JLabel("Peso en kilogramos (ingresar decimal): ");
        etiquetaID =  new JLabel("ID de producto:");
        inputNombre = new JTextField(30);
        inputDescripcion = new JTextField(30);
        inputPrecio = new JTextField(5);
        inputPeso = new JTextField(5);
        inputID = new JTextField(3);
        botonBusqueda = new JButton("BUSCAR");
        porNombre = new JRadioButton();
        porDesc = new JRadioButton();
        porPrecio = new JRadioButton();
        porPeso = new JRadioButton();
        porID = new JRadioButton();
        grupoBusqueda = new ButtonGroup();
        listModel = new DefaultListModel<>();
        resultadosList = new JList<>(listModel);
        JScrollPane resultadosScrollPane = new JScrollPane(resultadosList);
        
        grupoBusqueda.add(porNombre);
        grupoBusqueda.add(porDesc);
        grupoBusqueda.add(porPrecio);
        grupoBusqueda.add(porPeso);
        grupoBusqueda.add(porID);

        this.add(porID);
        this.add(etiquetaID);
        this.add(inputID);
        this.add(porNombre);
        this.add(etiquetaNombre);
        this.add(inputNombre);
        this.add(porDesc);
        this.add(etiquetaDescripcion);
        this.add(inputDescripcion);
        this.add(porPrecio);
        this.add(etiquetaPrecio);
        this.add(inputPrecio);
        this.add(porPeso);
        this.add(etiquetaPeso);
        this.add(inputPeso);
        this.add(botonBusqueda);
        this.add(resultadosScrollPane);

        botonBusqueda.addActionListener(e -> {
			
			Predicate<Producto> predicado;
			try {
				predicado = this.crearPredicado();
				ArrayList<Producto> rdo = GestorProductos.getInstancia().busqueda(predicado);
				List<String> aux = rdo.stream()
										.map(s -> "NOMBRE " + s.getNombre() + " DESCRIPCION: " + s.getDesc() + " ID: " + s.getId() + " PESO (KG): " + s.getPeso_kg() + " PRECIO UNITARIO: " + s.getPrecio_unitario())
										.collect(Collectors.toList());
				mostrarResultados(aux);
			} catch (Exception e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(this, "No se puede realizar la busqueda. Asegurarse de seleccionar una opcion de busqueda y llenar el campo correspondiente");
			}
			
		});
    }

    private Predicate<Producto> crearPredicado() throws Exception {
    	
    	if(!porPrecio.isSelected() && !porPeso.isSelected() && !porDesc.isSelected() && !porID.isSelected() && !porNombre.isSelected()) {
			throw new Exception();
		}else if(porID.isSelected()) {
			Integer id = Integer.valueOf(inputID.getText());
			return (s -> s.getId() == id);
		}else if(porNombre.isSelected()) {
			String nombre = inputNombre.getText();
			return (s -> s.getNombre().contains(nombre));
		}else if(porDesc.isSelected()) {
			String desc = inputDescripcion.getText();
			return (s -> s.getDesc().contains(desc));
		}else if(porPrecio.isSelected()) {
			Double apertura = Double.valueOf(inputPrecio.getText());
			return (s -> s.getPrecio_unitario() == apertura);
		}else if(porPeso.isSelected()) {
			Double cierre = Double.valueOf(inputPeso.getText());
			return (s -> s.getPeso_kg() == cierre);
		}
		return null;
    }

    private void mostrarResultados(List<String> aux) {
        listModel.clear();

        for (String producto : aux) {
            listModel.addElement(producto);
        }
    }
	
}
