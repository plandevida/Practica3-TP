package sistema.salidadatos;

import java.util.Collection;

import sistema.interfaces.ObjetosConSalidaDeDatos;

public interface InterfaceSalidaDatos {
	
	/**
	 * Registra un objeto para ser mostrada su salida.
	 * 
	 * @param objetoconsalidadatos Objeto a reistrar.
	 */
	public void registrarObjetoConSalidaDatos(ObjetosConSalidaDeDatos objetoconsalidadatos);	
	
	/**
	 * Registra todos los elementos de una colección.
	 * 
	 * @param listadeobjetosconsalidadatos
	 */
	public void registrarObjetoConSalidaDatos(Collection<ObjetosConSalidaDeDatos> listadeobjetosconsalidadatos);
	
	/**
	 * Muestra la salida de datos personalizada para cada tipo de elemento.
	 */
	public void mostrarDatos();
}
