package TP.datos;

public enum Estado_Orden {

	INICIADA, EN_PROCESO, PENDIENTE, TERMINADA
	
	/*
	 * INICIADA: aquella que empezo pero no se ha confirmado/configurado/guardado
	 * 			no estan en la base de datos sino en memoria.
	 * PENDIENTE: orden confirmada que no tiene asignado un camino
	 * EN_PROCESO: orden confirmada que tiene asignado un camino para transportarse pero todavia no llego
	 * TERMINADA: orden la cual se ha completado
	 * 			habra que borrarlas de la base de datos (o no).
	 */
	
}
