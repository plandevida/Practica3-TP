package sistema.entrada.ordenes.especificas;

import sistema.entrada.ordenes.OrdenParaCiclista;

public class OrdenFrenar extends OrdenParaCiclista {
	
	public OrdenFrenar() {
	
	}

	@Override
	public void ejecutarOrden() {
		getCiclista().frenar();
		mostrarMensaje();
	}
	
	@Override
	public void mostrarMensaje() {
		System.out.println(this.getClass().getName() + ": Frenando");
	}
}
