package TP.datos;

public class Producto {
	
	private String nombre;
	private String desc;
	private double precio_unitario;
	private double peso_kg;
	private int id;
	
	public Producto(String nombre, String desc, double precio_unitario, double peso_kg, int id) {
		
		this.nombre = nombre;
		this.desc = desc;
		this.precio_unitario = precio_unitario;
		this.peso_kg = peso_kg;
		this.id = id;
		
	}
	
	public void modificarAtributos(String nombre, String desc, double precio_unitario, double peso_kg) {
		this.nombre = nombre;
		this.desc = desc;
		this.precio_unitario = precio_unitario;
		this.peso_kg = peso_kg;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Producto) {
			Producto aux = (Producto) o;
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
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public double getPrecio_unitario() {
		return precio_unitario;
	}
	public void setPrecio_unitario(double precio_unitario) {
		this.precio_unitario = precio_unitario;
	}
	public double getPeso_kg() {
		return peso_kg;
	}
	public void setPeso_kg(double peso_kg) {
		this.peso_kg = peso_kg;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return this.nombre;
	}
	
	

}
