package TP.datos;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import TP.dao.BaseDatos;

public class GestorRutas {
	
	private ArrayList<Ruta> rutas;
	private static int id_ruta = 0;
	//private static int instancia = 0;
	private static GestorRutas gestor;
	private final double factor_amortiguacion = 0.5;
	private Set<PR_Sucursal> pageranks;

	private GestorRutas() {
		
		rutas = new ArrayList<>();
		pageranks = new HashSet<>();
		//instancia++;
		
	}
	
	public static GestorRutas getInstancia(){
		if (gestor  == null) {
			gestor = new GestorRutas();
			return gestor;
		}else {
			return gestor;
		}
	}
	
	public Ruta agregarRuta(Sucursal origen, Sucursal destino, Double capacidad, Double duracion_min, boolean estado) {
		
		Ruta nueva = new Ruta(origen, destino, capacidad, duracion_min, estado, id_ruta);
		id_ruta++;
		rutas.add(nueva);
		BaseDatos.getInstancia().nuevaRuta(nueva);
		return nueva;
		
	}
	
	public ArrayList<Ruta> busqueda(Predicate<Ruta> criterio) {
		
		ArrayList<Ruta> rdo = new ArrayList<>();
		for(Ruta r: this.rutas) {
			if(criterio.test(r)) {
				rdo.add(r);
			}
		}
		return rdo;
		
	}
	
	public boolean bajaRuta(Ruta rut) {
		
		if (rut == null ) {
			return false;
		}else {
			rutas.remove(rut);
			BaseDatos.getInstancia().eliminarRuta(rut);
			return true;
		}
		
	}
	
	public boolean editarRutas(Sucursal origen, Sucursal destino, Double capacidad, Double duracion_min, boolean estado, Ruta ruta) {
		
		if (ruta == null) {
			return false;
		}else {
			ruta.modificarAtributos(origen, destino, capacidad,duracion_min, estado);
			BaseDatos.getInstancia().modificarRuta(ruta);
			return true;
		}
		
	}
	
	public ArrayList<Sucursal> getDestinos (Sucursal s){
		//obtiene las sucursales adyacentes a partir de una sucursal origen
		
		ArrayList<Sucursal> rdo = new ArrayList<>();
		for(Ruta r: rutas) {
			if(r.getOrigen().equals(s) && r.isEstado() == true) {
				rdo.add(r.getDestino());
			}
		}
		return rdo;
		
	}
	
	public int gradoSalida(Sucursal suc) {
		
		return this.getDestinos(suc).size();
		
	}
	
	public ArrayList<Sucursal> getOrigenes (Sucursal suc) {
		
		ArrayList<Sucursal> rdo = new ArrayList<>();
		for (Ruta r: rutas) {
			if(r.getDestino().equals(suc) && r.isEstado() == true) {
				rdo.add(r.getOrigen());
			}
		}
		return rdo;
		
	}
	
	public int gradoEntrada(Sucursal suc) {
		
		return this.getOrigenes(suc).size();
		
	}
	
	public double pageRank(Sucursal suc) {
		
		double aux = 0;
		double pr;
		
		if(this.gradoEntrada(suc) == 0) {
			pageranks.add(new PR_Sucursal(suc, 1-factor_amortiguacion));
			return (1-factor_amortiguacion);
		}else {
			for(Sucursal s: this.getOrigenes(suc)) {
				aux += (pageRank(s) / this.gradoSalida(s));
			}
			aux *= factor_amortiguacion;
		}
		pr = aux + (1-factor_amortiguacion);
		pageranks.add(new PR_Sucursal(suc, pr));
		return (pr);
		
	}
	
	public double obtenerFlujoMaximo(ArrayList<ArrayList<Sucursal>> caminos) {
		//obtiene el flujo maximo (capacidad) de los caminos que van desde el puerto al centro
		//llamarlo con el resultado de "caminosPosibles(puerto, centro)"
		
		/*
		for (ArrayList<Sucursal> a: caminos) {
			System.out.println("camino: ");
			for (Sucursal s: a) { 
					System.out.print(s + "->");
			}
			System.out.print("\n");
		}
		*/
		
		double min;
		double max = 0;
		int i;
		for(ArrayList<Sucursal> camino: caminos) {
			min = 10000.0;
			for(i=0; i<camino.size()-1; i++) {
				final int copia = i;
				ArrayList<Ruta> aux = gestor.busqueda(r -> r.getOrigen().equals(camino.get(copia)) && r.getDestino().equals(camino.get(copia+1)));
				double cap = aux.get(0).getCapacidad();
				System.out.println(cap);
				if (cap <= min) {
					min  = cap;
				}
			}
			if (min >= max && camino.size()!=1) {
				max = min;
			}
		}
		
		return max;
	}
	

	public Set<Sucursal> sucursalesCandidatas (Sucursal puerto, Sucursal destino){
		//retorna un set con las posibles sucursales origen para una orden
		//deberiamos de llamar a esta funcion desde otra que se activa cuando
		//el usuario selecciona una orden para elegir el camino, junto con otra que tire los caminos y el tiempo que demora cada uno
	    
		ArrayList<ArrayList<Sucursal>> resultado = new ArrayList<>();
		ArrayList<Sucursal> candidato = new ArrayList<>();
		candidato.add(puerto);

	    funcion_recursiva(puerto, destino, candidato, resultado);
	    
	    return resultado.stream().flatMap(List::stream).collect(Collectors.toSet());
	    
	}

	public void funcion_recursiva(Sucursal origen, Sucursal destino, ArrayList<Sucursal> candidato, ArrayList<ArrayList<Sucursal>> resultado){
	    //funcion de existeCamino, guarda datos en resultado
		
		ArrayList<Sucursal> auxiliar = null;
		for (Sucursal s: (gestor.getDestinos(origen))){
	        auxiliar = (ArrayList<Sucursal>) candidato.stream().collect(Collectors.toList());
	        if (s.equals(destino)){
	            auxiliar.add(s);
	            resultado.add(new ArrayList<Sucursal>(auxiliar));
	        }
	        else {
	        	 if(!auxiliar.contains(s)){
		                auxiliar.add(s);
		                this.funcion_recursiva(s, destino, auxiliar, resultado);
	        	 }
	        }
		}
	}
	
	public Set<Sucursal> sucursalesPosibles(OrdenProvision ord, Sucursal puerto){
		//retorna la sucursales que pueden cumplir con el pedido de orden

		Set<Sucursal> aux = this.sucursalesCandidatas(puerto, ord.getDestino());
		
		Set<Sucursal> retorno = new HashSet<>();
		for(Sucursal s: aux) {
			if(s.tieneProductos(ord)) {
				retorno.add(s);
			}
		}
		return retorno;
		
	}
	
	public ArrayList<ArrayList<Sucursal>> caminosPosibles(Sucursal destino, Sucursal origen){
		//retorna los caminos posibles que puede elegir el usuario cuando selecciona un nodo de origen
		
		ArrayList<ArrayList<Sucursal>> resultado = new ArrayList<>();
		ArrayList<Sucursal> candidato = new ArrayList<>();
		candidato.add(origen);

	    funcion_recursiva(origen, destino, candidato, resultado);
	    
	    return resultado;
		
	}
	
	public double obtenerDuracionCamino(ArrayList<Sucursal> camino) {
		//obtiene la duracion del camino
		
		double acum = 0;
		int i;
		for(i=0; i<camino.size()-1; i++) {
			final int copia = i;
			ArrayList<Ruta> aux = gestor.busqueda(r -> r.getOrigen().equals(camino.get(copia)) && r.getDestino().equals(camino.get(copia+1)));
			acum += aux.get(0).getDuracion_min();
		}
		return acum;
		
	}
	
	
	public Set<PR_Sucursal> getPageRanks(){
		
		return pageranks;
		
	}

	public void setRutas(ArrayList<Ruta> rutas) {
		// TODO Auto-generated method stub
		this.rutas = rutas;
	}
	
	public void setIdMinima(int id) {
		GestorRutas.id_ruta = id;
	}

	public ArrayList<Ruta> getRutas() {
		return rutas;
	}
}
