package sistema.entrada.ordenes.especificas;

import sistema.entrada.ordenes.OrdenParaCiclista;

public class OrdenAumentarCadencia extends OrdenParaCiclista {
	
	private int aumentocadencia;
	
	public OrdenAumentarCadencia() {
		aumentocadencia = 0;
	}
	
	@Override
	public void ejecutarOrden() {
		getCiclista().setCadencia(aumentocadencia);
		mostrarMensaje();
	}
	
	/**
	 * Aumenta la cadencia del ciclista.
	 * @param aumentodecadencia El aumento de la cadencia.
	 */
	public void setAumentoCadencia(int aumentodecadencia) {
		aumentocadencia = aumentodecadencia;
	}
	
	@Override
	public void mostrarMensaje() {
		System.out.println(this.getClass().getName() + ": Aumentando la cadencia en :" + aumentocadencia);
	}
}
