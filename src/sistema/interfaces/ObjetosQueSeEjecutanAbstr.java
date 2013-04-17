package sistema.interfaces;

public abstract class ObjetosQueSeEjecutanAbstr implements ObjetosQueSeEjecutan {
	
	@SuppressWarnings("unused")
	private Boolean exit = false;
	
	public void exit(Boolean salir) {
		exit = salir;
	}
}
