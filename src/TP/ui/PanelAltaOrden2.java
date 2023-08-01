package TP.ui;

import javax.swing.*;

import TP.datos.Estado_Orden;
import TP.datos.GestorProductos;
import TP.datos.GestorSucursales;
import TP.datos.OrdenProvision;
import TP.datos.Producto;
import TP.datos.Sucursal;

public class PanelAltaOrden2 extends JPanel {

    
    //Atributos segundo panel
    private JLabel etiquetaProducto;
    private JComboBox inputProducto; //ID
    private JLabel etiquetaCantidad;
    private JTextField inputCantidad;
    private JButton	botonAgregarProd;
    private JButton botonFinalizar;
    private Sucursal seleccionada;
    private OrdenProvision ordenNueva;
    
    public void inicializar() {
    	
    	JLabel etiquetaProducto = new JLabel("ID de producto a agregar: ");
        JComboBox inputProducto = new JComboBox(); //ID
        JLabel etiquetaCantidad = new JLabel("Cantidad a pedir: ");
        JTextField inputCantidad = new JTextField(5);
        JButton	botonAgregarProd = new JButton("AGREGAR PRODUCTO");
        JButton botonFinalizar = new JButton("FINALIZAR");
        
        //prueba
        for (Producto p : GestorProductos.getInstancia().getProductos()) {
			inputProducto.addItem(p);
		}
        
        botonAgregarProd.addActionListener(e -> {
        	try {
        		String cant = inputCantidad.getText();
            	if (this.validar(cant)) {
            		ordenNueva.agregarStock((Producto)inputProducto.getSelectedItem(), Integer.valueOf(cant));
            		JOptionPane.showMessageDialog(this, "Producto agregado!");
            	}else {
            		JOptionPane.showMessageDialog(this, "Producto no agregado. Asegurarse de seleccionar un producto y ingresar la cantidad en decimal");
            	}
        	}catch(Exception ex) {
				//ex.printStackTrace();
				JOptionPane.showMessageDialog(this, "Producto no agregado. Asegurarse de seleccionar un producto y ingresar la cantidad en decimal");
        	}
        });
        
        botonFinalizar.addActionListener(e -> {
        	if(ordenNueva.getProductos().size() > 0) {
        		seleccionada.editarOrden(ordenNueva, Estado_Orden.PENDIENTE, -1);
            	JOptionPane.showMessageDialog(this, "Orden confirmada!");
        	}else {
        		JOptionPane.showMessageDialog(this, "Agregue al menos un producto para confirmar la orden.");
        	}
        });
        
        this.add(etiquetaProducto);
        this.add(inputProducto);
        this.add(etiquetaCantidad);
        this.add(inputCantidad);
        this.add(botonAgregarProd);
        this.add(botonFinalizar);
   	
    }
    
    public void setSeleccionada(Sucursal s) {
    	this.seleccionada = s;
    }
    
    private boolean validar(String cantidad) {
		try {
			if (cantidad.isEmpty()) return false;
			Integer.valueOf(cantidad);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
    
    public void inicializarOrdenNueva() {
    	
    	//System.out.println("inicializar orden");
    	ordenNueva = seleccionada.crearOrden("69/01/420");
    	
    }
}
