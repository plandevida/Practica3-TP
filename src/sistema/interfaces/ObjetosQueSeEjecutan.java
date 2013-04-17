package sistema.interfaces;

public interface ObjetosQueSeEjecutan extends Runnable {
	@Override
	public void run();
	
	public void salir(boolean salir);
}
