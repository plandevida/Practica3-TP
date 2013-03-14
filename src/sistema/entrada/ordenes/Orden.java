package sistema.entrada.ordenes;

import java.util.PriorityQueue;

/**
 * Clase que representa una orden a realizar por un elemento
 * único del sistema.
 * Es abstracta e implementa comparable para poder ser
 * añadida a colas {@link PriorityQueue}
 * 
 * @author Daniel Serrano Torres
 * @author Alvaro Quesada Pimentel
 */
public abstract class Orden implements Comparable<Orden> {
	
	/**
	 * Método para mostrar mensajes de las ordenes.
	 */
	public abstract void mostrarMensaje();
	
	/**
	 * Ejecuta la orden, mandando el mensaje del
	 * comando al objeto que lo debe ejecutar.
	 */
	public abstract void ejecutarOrden();
	
	@Override
	public int compareTo(Orden o) {
		int resultado = 0;
		
		if ( o != null ) {
			if (! this.equals(o)) {
				resultado = -1;
			}
		}
		
		return resultado;
	}
}
