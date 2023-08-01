package TP.datos;

public class Stock {
	
	private Producto prod;
	private int cantidad;
	
	
	public Stock(Producto prod, int cantidad) {
		super();
		this.prod = prod;
		this.cantidad = cantidad;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Stock) {
			Stock aux = (Stock) o;
			if (aux.getProd().equals(this.getProd())) {
				return true;
			}
		}
		return false;
	}
	
	public Producto getProd() {
		return prod;
	}
	public void setProd(Producto prod) {
		this.prod = prod;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	

}
