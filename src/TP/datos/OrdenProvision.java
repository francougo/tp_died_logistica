package TP.datos;

import java.util.ArrayList;
import java.util.Optional;

import TP.dao.BaseDatos;

public class OrdenProvision {
	
	private int id;
	private String fecha;
	private Sucursal destino;
	private double tiempo_maximo;
	private ArrayList<Stock> productos;
	private Estado_Orden estado;
	
	public OrdenProvision(String fecha, Sucursal destino, int id) {
		super();
		this.fecha = fecha;
		this.destino = destino;
		this.id = id;
		this.productos = new ArrayList<>();
		this.estado = Estado_Orden.INICIADA;
	}
	
	public void agregarStock(Producto p, int cantidad) {
		//agrega un producto (o cantidad si ya se agrego el producto) a una orden
		
		Stock existe = this.productos.stream().filter(s -> s.getProd().equals(p)).findFirst().orElse(null);
		if(existe == null) {
			//System.out.println("producto no existe");
			Stock nuevo = new Stock(p, cantidad);
			productos.add(nuevo);
			BaseDatos.getInstancia().nuevoStockOrden(nuevo, this.id);
		}else {
			//System.out.println("producto existe");
			existe.setCantidad(existe.getCantidad() + cantidad);
			BaseDatos.getInstancia().modificarStockOrden(existe, this.id);
		}

	}
	
	
	public boolean bajaStock(Producto prod) {
		//se da de baja el producto(stock) en la orden
		
		Stock existe = this.productos.stream().filter(s -> s.getProd().equals(prod)).findFirst().orElse(null);
		if(existe == null) {
			return false;
		}else {
			productos.remove(existe);
			BaseDatos.getInstancia().eliminarStockOrden(existe, this.id);
			return true;
		}
		
	}
	
	public void confirmarOrden() {
		//confirma la orden cuando no se van a agregar mas productos a la misma.
		if (productos.size() > 0) {
			this.estado = Estado_Orden.PENDIENTE;
			BaseDatos.getInstancia().modificarOrden(this);
		}
	}
	
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public Sucursal getDestino() {
		return destino;
	}
	public void setDestino(Sucursal destino) {
		this.destino = destino;
	}
	public double getTiempo_maximo() {
		return tiempo_maximo;
	}
	public void setTiempo_maximo(double d) {
		this.tiempo_maximo = d;
	}
	public ArrayList<Stock> getProductos() {
		return productos;
	}
	public void setProductos(ArrayList<Stock> productos) {
		this.productos = productos;
	}
	public int getId() {
		return id;
	}
	public String getEstado() {
		return estado.toString();
	}
	public Estado_Orden getEstadoTipo() {
		return estado;
	}

	public void setEstado(Estado_Orden estado) {
		this.estado = estado;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setStock(ArrayList<Stock> stocks) {
		// TODO Auto-generated method stub
		this.productos = stocks;
	}
	
	public String toString() {
		
		String retorno = new String("Destino: " + destino.toString() + "- ID: " + ((Integer)id).toString() + "- Estado: " + estado.toString());
		return retorno;
		
	}
	

}
