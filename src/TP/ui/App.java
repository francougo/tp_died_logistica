package TP.ui;

import TP.dao.*;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import TP.datos.*;

public class App{
		
	PanelAltaSucursal panelAltaSucursal;
	PanelBajaSucursal panelBajaSucursal;
	PanelEdicionSucursal panelEdicionSucursal;
	PanelBusquedaSucursal panelBusquedaSucursal;
	PanelEdicionStockSucursal panelEdicionStockSucursal;
	PanelAltaCamino panelAltaCamino;
	PanelBajaCamino panelBajaCamino;
	PanelBusquedaCamino panelBusquedaCamino;
	PanelEdicionCamino panelEdicionCamino;
	PanelAltaProducto panelAltaProducto;
	PanelBajaProducto panelBajaProducto;
	PanelBusquedaProducto panelBusquedaProducto;
	PanelEdicionProducto panelEdicionProducto;
	PanelAltaOrden panelAltaOrden;
	PanelAltaOrden2 panelAltaOrden2;
	PanelBajaOrden panelBajaOrden;
	PanelRecorridoOrden panelRecorridoOrden;
	PanelRecorridoOrden2 panelRecorridoOrden2;
	PanelPageRank panelPageRank;
	PanelFlujoMaximo panelFlujoMaximo;
	PanelHabilitacionSucursal panelHabilitacionSucursal;
	PanelHabilitacionCamino panelHabilitacionCamino;
	PanelGrafo panelGrafo;
	
	/*
	 * FUNCIONALIDADES QUE REQUIERE EL SISTEMA:
	 * -SUCURSALES (alta, edicion, baja, busqueda por atributos y visualizacion)
	 * -CAMINOS (alta, edicion, baja, busqueda por atributos y visualizacion)
	 * -PRODUCTOS (alta, edicion, baja, busqueda y visualizacion)
	 * -STOCK EN SUCURSAL (agregar stock, disminuir, listar todos y buscar)
	 * -ORDEN DE PROVISION (SUCURSAL) (crear, agregar productos a la misma, eliminarla)
	 * -CONSULTAR LAS ORDENES DE PROVISION (seleccionar una y hacer las funciones)
	 * -HABILITAR/DESHABILITAR SUCURSALES Y RUTAS
	 * -PAGE RANK (DE UNA SUCURSAL)
	 * -CALCULAR FLUJO MAXIMO
	 */
	
	public void armarMenu() {
		
		JFrame miFrame = new JFrame("Aplicacion TP DIED");
		miFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		miFrame.setLayout(new FlowLayout());
		
		JMenuBar menuBar;
		JMenu menuSucursal;
		JMenu menuCamino;
		JMenu menuProducto;
		JMenu menuOrden;
		JMenu menuCalculo;
		
		JMenuItem menuItemCrearSucursal;
		JMenuItem menuItemEliminarSucursal;
		JMenuItem menuItemEditarSucursal;
		JMenuItem menuItemBuscarSucursal;
		JMenuItem menuItemHabilitarSucursal;
		JMenuItem menuItemStockSucursal;
		
		JMenuItem menuItemCrearCamino;
		JMenuItem menuItemEliminarCamino;
		JMenuItem menuItemEditarCamino;
		JMenuItem menuItemBuscarCamino;
		JMenuItem menuItemHabilitarCamino;
		
		JMenuItem menuItemCrearProducto;
		JMenuItem menuItemEliminarProducto;
		JMenuItem menuItemEditarProducto;
		JMenuItem menuItemBuscarProducto;
		
		JMenuItem menuItemCrearOrden;
		JMenuItem menuItemEliminarOrden;
		//JMenuItem menuItemEditarOrden;
		//JMenuItem menuItemBuscarOrden;
		JMenuItem menuItemRecorridoOrden;
		
		JMenuItem menuItemPageRankCalculo;
		JMenuItem menuItemFlujomaxCalculo;
		
		JMenuItem menuItemGrafo;
		
		menuBar = new JMenuBar();

		menuSucursal = new JMenu("Sucursal");
		menuSucursal.setMnemonic(KeyEvent.VK_S);
		menuCamino = new JMenu("Ruta");
		menuCamino.setMnemonic(KeyEvent.VK_R);
		menuProducto = new JMenu("Producto");
		menuProducto.setMnemonic(KeyEvent.VK_P);
		menuOrden = new JMenu("Orden");
		menuOrden.setMnemonic(KeyEvent.VK_O);
		menuCalculo = new JMenu("Calculo");
		menuCalculo.setMnemonic(KeyEvent.VK_C);
		menuBar.add(menuSucursal);
		menuBar.add(menuCamino);
		menuBar.add(menuProducto);
		menuBar.add(menuOrden);
		menuBar.add(menuCalculo);

		//Items para sucursal
		menuItemCrearSucursal = new JMenuItem ("Crear");
		menuSucursal.add(menuItemCrearSucursal);
		menuItemEliminarSucursal = new JMenuItem ("Eliminar");
		menuSucursal.add(menuItemEliminarSucursal);
		menuItemEditarSucursal = new JMenuItem ("Editar datos");
		menuSucursal.add(menuItemEditarSucursal);
		menuItemStockSucursal = new JMenuItem ("Editar Stock");
		menuSucursal.add(menuItemStockSucursal);
		menuItemBuscarSucursal = new JMenuItem ("Buscar");
		menuSucursal.add(menuItemBuscarSucursal);
		menuItemHabilitarSucursal = new JMenuItem ("Habilitar/Deshabilitar");	//capaz es redundante? podemos hacerlo con el de editar.
		menuSucursal.add(menuItemHabilitarSucursal);

		//Items para camino
		menuItemCrearCamino = new JMenuItem ("Crear");
		menuCamino.add(menuItemCrearCamino);
		menuItemEliminarCamino = new JMenuItem ("Eliminar");
		menuCamino.add(menuItemEliminarCamino);
		menuItemEditarCamino = new JMenuItem ("Editar");
		menuCamino.add(menuItemEditarCamino);
		menuItemBuscarCamino = new JMenuItem ("Buscar");
		menuCamino.add(menuItemBuscarCamino);
		menuItemHabilitarCamino = new JMenuItem ("Habilitar/Deshabilitar");		//tambien
		menuCamino.add(menuItemHabilitarCamino);
		
		//Items para producto
		menuItemCrearProducto = new JMenuItem ("Crear");
		menuProducto.add(menuItemCrearProducto);
		menuItemEliminarProducto = new JMenuItem ("Eliminar");
		menuProducto.add(menuItemEliminarProducto);
		menuItemEditarProducto = new JMenuItem ("Editar");
		menuProducto.add(menuItemEditarProducto);		
		menuItemBuscarProducto = new JMenuItem ("Buscar");
		menuProducto.add(menuItemBuscarProducto);
		
		//Items para orden de provision
		menuItemCrearOrden = new JMenuItem ("Crear");
		menuOrden.add(menuItemCrearOrden);
		menuItemEliminarOrden = new JMenuItem ("Eliminar");
		menuOrden.add(menuItemEliminarOrden);
		menuItemRecorridoOrden = new JMenuItem ("Asignar recorrido");
		menuOrden.add(menuItemRecorridoOrden);
		
		//Items para los algoritmos (calculos)
		menuItemPageRankCalculo = new JMenuItem ("PageRank");
		menuCalculo.add(menuItemPageRankCalculo);
		menuItemFlujomaxCalculo = new JMenuItem ("Flujo maximo");
		menuCalculo.add(menuItemFlujomaxCalculo);
		menuItemGrafo = new JMenuItem("Ver grafo");
		menuCalculo.add(menuItemGrafo);
		
		menuItemCrearSucursal.addActionListener(e -> {
			miFrame.setContentPane(this.getPanelAltaSucursal());
			miFrame.pack();
			miFrame.setSize(500,500);
		});
		menuItemEliminarSucursal.addActionListener(e -> {
			miFrame.setContentPane(this.getPanelBajaSucursal());
			miFrame.pack();
			miFrame.setSize(500,500);
		});
		menuItemEditarSucursal.addActionListener(e -> {
			miFrame.setContentPane(this.getPanelEdicionSucursal());
			miFrame.pack();
			miFrame.setSize(500,500);
		});
		menuItemBuscarSucursal.addActionListener(e -> {
			miFrame.setContentPane(this.getPanelBusquedaSucursal());
			miFrame.pack();
			miFrame.setSize(500,500);
		});
		menuItemStockSucursal.addActionListener(e -> {
			miFrame.setContentPane(this.getPanelStockSucursal());
			miFrame.pack();
			miFrame.setSize(500,500);
		});
		menuItemCrearCamino.addActionListener(e -> {
			miFrame.setContentPane(this.getPanelAltaCamino());
			miFrame.pack();
			miFrame.setSize(500,500);
		});
		menuItemEliminarCamino.addActionListener(e -> {
			miFrame.setContentPane(this.getPanelBajaCamino());
			miFrame.pack();
			miFrame.setSize(500,500);
		});
		menuItemBuscarCamino.addActionListener(e -> {
			miFrame.setContentPane(this.getPanelBusquedaCamino());
			miFrame.pack();
			miFrame.setSize(500,500);
		});
		menuItemEditarCamino.addActionListener(e -> {
			miFrame.setContentPane(this.getPanelEdicionCamino());
			miFrame.pack();
			miFrame.setSize(500,500);
		});
		menuItemCrearProducto.addActionListener(e -> {
			miFrame.setContentPane(this.getPanelAltaProducto());
			miFrame.pack();
			miFrame.setSize(500,500);
		});
		menuItemEliminarProducto.addActionListener(e -> {
			miFrame.setContentPane(this.getPanelBajaProducto());
			miFrame.pack();
			miFrame.setSize(500,500);
		});
		menuItemBuscarProducto.addActionListener(e -> {
			miFrame.setContentPane(this.getPanelBusquedaProducto());
			miFrame.pack();
			miFrame.setSize(500,500);
		});
		menuItemEditarProducto.addActionListener(e -> {
			miFrame.setContentPane(this.getPanelEdicionProducto());
			miFrame.pack();
			miFrame.setSize(500,500);
		});
		menuItemCrearOrden.addActionListener(e -> {
			miFrame.setContentPane(this.getPanelAltaOrden(miFrame, this.getPanelAltaOrden2()));
			miFrame.pack();
			miFrame.setSize(500,500);
		});
		menuItemEliminarOrden.addActionListener(e -> {
			miFrame.setContentPane(this.getPanelBajaOrden());
			miFrame.pack();
			miFrame.setSize(500,500);
		});
		menuItemRecorridoOrden.addActionListener(e -> {
			miFrame.setContentPane(this.getPanelRecorridoOrden(miFrame, this.getPanelRecorridoOrden2()));
			miFrame.pack();
			miFrame.setSize(500,500);
		});
		menuItemPageRankCalculo.addActionListener(e -> {
			miFrame.setContentPane(this.getPanelPageRank());
			miFrame.pack();
			miFrame.setSize(500,500);
		});
		menuItemFlujomaxCalculo.addActionListener(e -> {
			miFrame.setContentPane(this.getPanelFlujoMaximo());
			miFrame.pack();
			miFrame.setSize(500,500);
		});
		menuItemHabilitarSucursal.addActionListener(e -> {
			miFrame.setContentPane(this.getPanelHabilitacionSucursal());
			miFrame.pack();
			miFrame.setSize(500,500);
		});
		menuItemHabilitarCamino.addActionListener(e -> {
			miFrame.setContentPane(this.getPanelHabilitacionCamino());
			miFrame.pack();
			miFrame.setSize(500,500);
		});
		menuItemGrafo.addActionListener(e -> {
			this.iniciarFrameGrafo();
		});
		
		miFrame.setJMenuBar(menuBar);
		miFrame.setVisible(true);
		miFrame.pack();
		miFrame.setSize(500,500);
		
		
	}
	
	public JPanel getPanelAltaSucursal() {
		
		if(this.panelAltaSucursal == null) {
			this.panelAltaSucursal = new PanelAltaSucursal();
			panelAltaSucursal.inicializar();
		}
		return panelAltaSucursal;
		
	}
	
	public JPanel getPanelBajaSucursal() {
		
		if(this.panelBajaSucursal == null) {
			this.panelBajaSucursal = new PanelBajaSucursal();
			panelBajaSucursal.inicializar();
		}
		return panelBajaSucursal;
		
	}
	
	public JPanel getPanelEdicionSucursal() {
		
		if(this.panelEdicionSucursal == null) {
			this.panelEdicionSucursal = new PanelEdicionSucursal();
			panelEdicionSucursal.inicializar();
		}
		return panelEdicionSucursal;
		
	}
	
	public JPanel getPanelBusquedaSucursal() {
		
		if(this.panelBusquedaSucursal == null) {
			this.panelBusquedaSucursal = new PanelBusquedaSucursal();
			panelBusquedaSucursal.inicializar();
		}
		return panelBusquedaSucursal;
		
	}
	
	public JPanel getPanelStockSucursal() {
		
		if(this.panelEdicionStockSucursal == null) {
			this.panelEdicionStockSucursal = new PanelEdicionStockSucursal();
			panelEdicionStockSucursal.inicializar();
		}
		return panelEdicionStockSucursal;
		
	}
	
	public JPanel getPanelAltaCamino() {
		
		if(this.panelAltaCamino == null) {
			this.panelAltaCamino = new PanelAltaCamino();
			panelAltaCamino.inicializar();
		}
		return panelAltaCamino;
		
	}
	
	public JPanel getPanelBajaCamino() {
		
		if(this.panelBajaCamino == null) {
			this.panelBajaCamino = new PanelBajaCamino();
			panelBajaCamino.inicializar();
		}
		return panelBajaCamino;
		
	}
	
	public JPanel getPanelBusquedaCamino() {
		
		if(this.panelBusquedaCamino == null) {
			this.panelBusquedaCamino = new PanelBusquedaCamino();
			panelBusquedaCamino.inicializar();
		}
		return panelBusquedaCamino;
		
	}
	
	public JPanel getPanelEdicionCamino() {
		
		if(this.panelEdicionCamino == null) {
			this.panelEdicionCamino = new PanelEdicionCamino();
			panelEdicionCamino.inicializar();
		}
		return panelEdicionCamino;
		
	}
	
	public JPanel getPanelAltaProducto() {
		
		if(this.panelAltaProducto == null) {
			this.panelAltaProducto = new PanelAltaProducto();
			panelAltaProducto.inicializar();
		}
		return panelAltaProducto;
		
	}
	
	public JPanel getPanelBajaProducto() {
		
		if(this.panelBajaProducto == null) {
			this.panelBajaProducto = new PanelBajaProducto();
			panelBajaProducto.inicializar();
		}
		return panelBajaProducto;
		
	}
	
	public JPanel getPanelBusquedaProducto() {
		
		if(this.panelBusquedaProducto == null) {
			this.panelBusquedaProducto = new PanelBusquedaProducto();
			panelBusquedaProducto.inicializar();
		}
		return panelBusquedaProducto;
		
	}
	
	public JPanel getPanelEdicionProducto() {
		
		if(this.panelEdicionProducto == null) {
			this.panelEdicionProducto = new PanelEdicionProducto();
			panelEdicionProducto.inicializar();
		}
		return panelEdicionProducto;
		
	}
	
	public JPanel getPanelAltaOrden(JFrame frame, JPanel panel) {
		
		if(this.panelAltaOrden == null) {
			this.panelAltaOrden = new PanelAltaOrden();
			panelAltaOrden.inicializar(frame, panel);
		}
		return panelAltaOrden;
		
	}
	
	public JPanel getPanelAltaOrden2() {
		
		if(this.panelAltaOrden2 == null) {
			this.panelAltaOrden2 = new PanelAltaOrden2();
			panelAltaOrden2.inicializar();
		}
		return panelAltaOrden2;
		
	}
	
	public JPanel getPanelBajaOrden() {
		
		if(this.panelBajaOrden == null) {
			this.panelBajaOrden = new PanelBajaOrden();
			panelBajaOrden.inicializar();
		}
		return panelBajaOrden;
		
	}
	
	public JPanel getPanelRecorridoOrden(JFrame frame, JPanel panel) {
		
		if(this.panelRecorridoOrden == null) {
			this.panelRecorridoOrden = new PanelRecorridoOrden();
			panelRecorridoOrden.inicializar(frame, panel);
		}
		return panelRecorridoOrden;
		
	}
	
	public JPanel getPanelRecorridoOrden2() {
		
		if(this.panelRecorridoOrden2 == null) {
			this.panelRecorridoOrden2 = new PanelRecorridoOrden2();
			panelRecorridoOrden2.inicializar();
		}
		return panelRecorridoOrden2;
		
	}
	
	public JPanel getPanelPageRank() {
		
		if(this.panelPageRank == null) {
			this.panelPageRank = new PanelPageRank();
			panelPageRank.inicializar();
		}
		return panelPageRank;
		
	}
	
	public JPanel getPanelFlujoMaximo() {
		
		if(this.panelFlujoMaximo == null) {
			this.panelFlujoMaximo = new PanelFlujoMaximo();
			panelFlujoMaximo.inicializar();
		}
		return panelFlujoMaximo;
		
	}
	
	public JPanel getPanelHabilitacionSucursal() {
		
		if(this.panelHabilitacionSucursal == null) {
			this.panelHabilitacionSucursal = new PanelHabilitacionSucursal();
			panelHabilitacionSucursal.inicializar();
		}
		return panelHabilitacionSucursal;
		
	}
	
	public JPanel getPanelHabilitacionCamino() {
		
		if(this.panelHabilitacionCamino == null) {
			this.panelHabilitacionCamino = new PanelHabilitacionCamino();
			panelHabilitacionCamino.inicializar();
		}
		return panelHabilitacionCamino;
		
	}

	public JPanel getPanelGrafo() {
		
		if(this.panelGrafo == null) {
			this.panelGrafo = new PanelGrafo();
		}
		return panelGrafo;
		
	}
	
	public void iniciarFrameGrafo() {
		JFrame nuevoFrame = new JFrame("Grafo de situacion");
		nuevoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		nuevoFrame.setSize(800,800);
		nuevoFrame.add(this.getPanelGrafo());
		nuevoFrame.setVisible(true);
		this.getPanelGrafo().repaint();
	}
	
	
	public static void main(String[] args) {
		
		
		
		BaseDatos base_datos = BaseDatos.getInstancia();
		base_datos.setNombre("postgres");
		base_datos.setUser("postgres");
		base_datos.setPassword("123");
		
		
		/* PRUEBA DE CONEXION:
		Connection con = base_datos.getConeccion();
		if (con == null) {
			System.out.println("fracaso");
		}else {
			System.out.println("exito");
		}
		base_datos.cerrarConeccion();
		*/
		
		base_datos.cargarBaseDatos();
		
		App app = new App();
		app.armarMenu();
		
	}
	
	
}
