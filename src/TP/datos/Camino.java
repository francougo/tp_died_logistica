package TP.datos;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Camino {
	
	private ArrayList<Sucursal> sucursales;
	private double tiempo_maximo;

	@Override
	public String toString() {
		String rdo = sucursales.stream().map(s -> s.toString() + " -> ").collect(Collectors.joining());
		rdo = rdo + "DURACION (mins): " + tiempo_maximo;
		return rdo;
	}
	
	public Camino(ArrayList<Sucursal> ss) {
		
		sucursales = ss;
		tiempo_maximo = GestorRutas.getInstancia().obtenerDuracionCamino(ss);
		
	}
	
	public ArrayList<Sucursal> getSucursales(){
		return sucursales;
	}
	
	public double getTiempoMax() {
		return tiempo_maximo;
	}
	
}

