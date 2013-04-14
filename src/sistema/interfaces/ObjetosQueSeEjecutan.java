package sistema.interfaces;

public abstract class ObjetosQueSeEjecutan extends Thread {
	
	private Boolean exit = false;
	
	public void exit(Boolean salir) {
		exit = salir;
	}
}
