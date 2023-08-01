package TP.datos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.HashSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import TP.dao.BaseDatos;

public class Sucursal {
	
	private String nombre;
	private Double hs_apertura;
	private Double hs_cierre;
	private boolean estado;
	private int id;
	private ArrayList<Stock> productos_stock;
	private ArrayList<OrdenProvision> ordenes;
	private int id_orden;
	
	//para la UI
	private int coord_x;
	private int coord_y;
	
	public Sucursal(String nombre, Double hs_apertura, Double hs_cierre, boolean estado, int id) {
		
		this.nombre = nombre;
		this.hs_apertura = hs_apertura;
		this.hs_cierre = hs_cierre;
		this.estado = estado;
		this.id = id;
		this.id_orden = 0;
		this.productos_stock = new ArrayList<>();
		this.ordenes = new ArrayList<>();
		
	}
	
	public void modificarAtributos(String nombre, Double hs_apertura, Double hs_cierre, boolean estado) {
		this.nombre = nombre;
		this.hs_apertura = hs_apertura;
		this.hs_cierre = hs_cierre;
		this.estado = estado;
	}
	
	
	public void agregarStock(Producto p, int cantidad) {
		//agrega Stock a la lista de productos de la sucursal
		
		Stock existe = this.productos_stock.stream().filter(s -> s.getProd().equals(p)).findFirst().orElse(null);
		if(existe == null) {
			Stock nuevo = new Stock(p, cantidad);
			productos_stock.add(nuevo);
			BaseDatos.getInstancia().nuevoStockSucursal(nuevo, this.id);
		}else {
			existe.setCantidad(existe.getCantidad() + cantidad);
			BaseDatos.getInstancia().modificarStockSucursal(existe, this.id);
		}

	}
	
	public ArrayList<Stock> busqueda(Predicate<Stock> criterio) {
		//busca Stocks por un criterio determinado
		
		ArrayList<Stock> rdo = new ArrayList<>();
		for(Stock s: this.productos_stock) {
			if(criterio.test(s)) {
				rdo.add(s);
			}
		}
		return rdo;
		
	}
	
	public boolean bajaStock(Producto prod) {
		//se da de baja el producto(stock) en la sucursal
		
		Stock existe = this.productos_stock.stream().filter(s -> s.getProd().equals(prod)).findFirst().orElse(null);
		if(existe == null) {
			return false;
		}else {
			productos_stock.remove(existe);
			BaseDatos.getInstancia().eliminarStockSucursal(existe, this.id);
			return true;
		}
		
	}
	
	public OrdenProvision crearOrden(String fecha) {
		//crea una nueva orden de provision y la agrega a la lista de ordenes
		
		//System.out.println("Se llamo a crear orden"+ (this.id_orden + (1000*this.id)));
		OrdenProvision nueva = new OrdenProvision(fecha, this, ((1000*this.id)+this.id_orden));
		ordenes.add(nueva);
		id_orden++;
		BaseDatos.getInstancia().nuevaOrden(nueva);
		return nueva;
		
	}
	
	public void editarOrden(OrdenProvision o, Estado_Orden estado, double d) {
		o.setEstado(estado);
		o.setTiempo_maximo(d);
		BaseDatos.getInstancia().modificarOrden(o);
	}
	
	public boolean bajaOrden(OrdenProvision ord) {
		if (ord ==null) {
			return false;
		}else {
			ordenes.remove(ord);
			BaseDatos.getInstancia().eliminarOrden(ord);
			return true;
		}
	}
	
	public boolean tieneProductos(OrdenProvision ord) {
		//evalua si la sucursal puede cumplir con el pedido de orden de productos
		
		for(Stock s: ord.getProductos()) {
			Stock aux = this.getProductos_stock().stream().filter(stock -> stock.equals(s)).findFirst().orElse(null);
			if(aux == null || aux.getCantidad() < s.getCantidad()) {
				return false;
			}
		}
		
		return true;
		
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Sucursal) {
			Sucursal aux = (Sucursal) o;
			if (aux.getId() == this.getId()) {
				return true;
			}
		}
		return false;
	}
	        
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getHs_apertura() {
		return hs_apertura;
	}

	public void setHs_apertura(Double hs_apertura) {
		this.hs_apertura = hs_apertura;
	}

	public Double getHs_cierre() {
		return hs_cierre;
	}

	public void setHs_cierre(Double hs_cierre) {
		this.hs_cierre = hs_cierre;
	}

	public boolean getEstado() {
		return estado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Stock> getProductos_stock() {
		return productos_stock;
	}

	public void setProductos_stock(ArrayList<Stock> productos_stock) {
		this.productos_stock = productos_stock;
	}

	public ArrayList<OrdenProvision> getOrdenes() {
		return ordenes;
	}

	public void setOrdenes(ArrayList<OrdenProvision> ordenes) {
		this.ordenes = ordenes;
	}
	
	public void deshabilitar() {
		estado = false;
	}
	public void habilitar() {
		estado = true;
	}

	public void setStock(ArrayList<Stock> stocks) {
		// TODO Auto-generated method stub
		this.productos_stock = stocks;
	}

	@Override
	public String toString() {
		return this.nombre;
	}
	
	public void setIdOrdenMinima(int id) {
		id_orden = id;
	}

	public int getCoord_x() {
		return coord_x;
	}

	public void setCoord_x(int coord_x) {
		this.coord_x = coord_x;
	}

	public int getCoord_y() {
		return coord_y;
	}

	public void setCoord_y(int coord_y) {
		this.coord_y = coord_y;
	}
	
	public boolean isCentro() {
		return this.nombre.equals("Centro");
	}
	public boolean isPuerto() {
		return this.nombre.equals("Puerto");
	}
	
	
}
