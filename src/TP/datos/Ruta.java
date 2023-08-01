package TP.datos;

public class Ruta {
	
	private Sucursal origen;
	private Sucursal destino;
	private double capacidad;
	private double duracion_min;
	private boolean estado;
	private int id;
	
	
	public Ruta(Sucursal origen, Sucursal destino, Double capacidad, Double duracion_min, boolean estado, int id) {
		
		this.origen = origen;
		this.destino = destino;
		this.capacidad = capacidad;
		this.duracion_min = duracion_min;
		this.estado = estado;
		this.id = id;
		
	}
	
	public void modificarAtributos(Sucursal origen, Sucursal destino, Double capacidad, Double duracion_min, boolean estado) {
		
		this.origen = origen;
		this.destino = destino;
		this.capacidad = capacidad;
		this.duracion_min = duracion_min;
		this.estado = estado;
		
	}
	
	public void deshabilitar() {
		estado = false;
	}
	public void habilitar() {
		estado = true;
	}
	
	public Sucursal getOrigen() {
		return origen;
	}
	public void setOrigen(Sucursal origen) {
		this.origen = origen;
	}
	public Sucursal getDestino() {
		return destino;
	}
	public void setDestino(Sucursal destino) {
		this.destino = destino;
	}
	public double getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(Double capacidad) {
		this.capacidad = capacidad;
	}
	public double getDuracion_min() {
		return duracion_min;
	}
	public void setDuracion_min(Double duracion_min) {
		this.duracion_min = duracion_min;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String toString() {
		String retorno = new String(origen.toString() + "-" + destino.toString()); 
		return retorno;
	}

}
