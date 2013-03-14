package sistema.entrada.ordenes.especificas;

import sistema.entrada.ordenes.OrdenParaCiclista;

public class OrdenBajarPlato extends OrdenParaCiclista {
	
	public OrdenBajarPlato() {
		
	}

	@Override
	public void ejecutarOrden() {
		getCiclista().disminuirPlato();
	}
	
	@Override
	public void mostrarMensaje() {
		System.out.println(this.getClass().getName() + ": Bajando de plato");
	}
}
