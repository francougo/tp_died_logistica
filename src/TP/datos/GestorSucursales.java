package TP.datos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import TP.dao.BaseDatos;

public class GestorSucursales {
	
	private ArrayList<Sucursal> sucursales;
	private static int id_sucursal = 0;
	private static GestorSucursales gestor;
	
	private GestorSucursales() {
		
		sucursales = new ArrayList<>();
		
		
	}
	
	public static GestorSucursales getInstancia(){
		if (gestor  == null) {
			gestor = new GestorSucursales();
			return gestor;
		}else {
			return gestor;
		}
	}
	
	public Sucursal agregarSucursal(String nombre, Double hs_apertura, Double hs_cierre, boolean estado) {
		
		Sucursal nueva = new Sucursal(nombre, hs_apertura, hs_cierre, estado, id_sucursal);
		id_sucursal++;
		sucursales.add(nueva);
		BaseDatos.getInstancia().nuevaSucursal(nueva);
		return nueva;
		
	}
	
	public ArrayList<Sucursal> busqueda(Predicate<Sucursal> criterio) {
		
		ArrayList<Sucursal> rdo = new ArrayList<>();
		for(Sucursal s: this.sucursales) {
			if(criterio.test(s)) {
				rdo.add(s);
			}
		}
		return rdo;
		
	}
	
	public boolean bajaSucursal(Sucursal suc) {
		
		if (suc ==null) {
			return false;
		}else {
			sucursales.remove(suc);
			BaseDatos.getInstancia().eliminarSucursal(suc);
			return true;
		}
		
	}
	
	public boolean editarSucursal(Sucursal suc, String nombre, Double hs_apertura, Double hs_cierre, boolean estado) {
		
		if (suc == null) {
			return false;
		}else {
			suc.modificarAtributos(nombre, hs_apertura, hs_cierre, estado);
			BaseDatos.getInstancia().modificarSucursal(suc);
			return true;
		}
		
	}
	
	public Sucursal getCentro() {
		
		Sucursal aux = this.busqueda(s -> s.getNombre().equals("Centro")).stream().findAny().orElse(null);
		
		return aux;
		
	}
	
	public Sucursal getPuerto() {
		
		Sucursal aux = this.busqueda(s -> s.getNombre().equals("Puerto")).stream().findAny().orElse(null);
		
		return aux;
		
	}

	public void setSucursales(ArrayList<Sucursal> sucursales) {
		this.sucursales = sucursales;
	}

	public ArrayList<Sucursal> getSucursales() {
		return sucursales;
	}

	public void setIdMinima(int i) {
		GestorSucursales.id_sucursal = i;
	}
}
