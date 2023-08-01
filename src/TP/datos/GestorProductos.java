package TP.datos;

import java.util.ArrayList;
import java.util.function.Predicate;

import TP.dao.BaseDatos;

public class GestorProductos {
	
	private ArrayList<Producto> productos;
	private static int id_prod = 0;
	//private static int instancia = 0;
	private static GestorProductos gestor;
	
	private GestorProductos() {
		
		productos = new ArrayList<>();
		//instancia++;
		
	}
	
	public static GestorProductos getInstancia(){
		if (gestor  == null) {
			gestor = new GestorProductos();
			return gestor;
		}else {
			return gestor;
		}
	}
	
	public Producto agregarProducto(String nombre, String desc, double precio_unitario, double peso_kg) {
		
		Producto nueva = new Producto(nombre, desc, precio_unitario, peso_kg, id_prod);
		id_prod++;
		productos.add(nueva);
		BaseDatos.getInstancia().nuevoProducto(nueva);
		return nueva;
		
	}
	
	public ArrayList<Producto> busqueda(Predicate<Producto> criterio) {
		
		ArrayList<Producto> rdo = new ArrayList<>();
		for(Producto p: this.productos) {
			if(criterio.test(p)) {
				rdo.add(p);
			}
		}
		return rdo;
		
	}
	
	public boolean bajaProducto(Producto prod) {
		
		if (prod == null) {
			return false;
		}else {
			productos.remove(prod);
			BaseDatos.getInstancia().eliminarProducto(prod);
			return true;
		}
		
	}
	
	public boolean editarProductos(String nombre, String desc, double precio_unitario, double peso_kg, Producto prod) {
		
		if (prod == null) {
			return false;
		}else {
			prod.modificarAtributos(nombre, desc, precio_unitario, peso_kg);
			BaseDatos.getInstancia().modificarProducto(prod);
			return true;
		}
		
	}

	public void setProductos(ArrayList<Producto> productos) {
		// TODO Auto-generated method stub
		this.productos = productos;
	}

	public ArrayList<Producto> getProductos() {
		return this.productos;
	}
	
	public void setIdMinima(int id) {
		GestorProductos.id_prod = id;
	}
}
