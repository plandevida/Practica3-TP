package sistema.entidades.tiempo;

import sistema.interfaces.ObjetosQueSeEjecutan;

public class Contador implements ObjetosQueSeEjecutan {
	//el numero de impulsos que lleva 
	protected int impulsos;
	
	public Contador() {
		impulsos = 0;
	}
	/**
	 * Metodo que ejecuta.
	 */
	public void run() {
		while (!exit) {
			nuevoImpulso();
		}
	}
	
	// Variable que controla la salida del hilo.
	private boolean exit = false;
	
	/**
	 * Método que cambia el estado de salida del hilo.
	 * @param salir
	 */
	public void salir(boolean salir) {
		exit = salir;
	}
	
	/**
	 * Método que añade un nuevo impulso al actual.
	 */
	public void nuevoImpulso() {
		impulsos++;
	}
	/**
	 * Metodo para obtener los impulsos.
	 * 
	 * @return Los impulsos.
	 */
	public int getImpulsos() {
		return impulsos;
	}
}
