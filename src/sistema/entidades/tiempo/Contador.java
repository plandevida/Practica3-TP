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
	 * 
	 * 
	 */
	public boolean ejecutar() { 
		nuevoImpulso();
		
		return true;
	}
	/**
	 * Método que a�ade un nuevo impulso al actual.
	 * 
	 * 
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
