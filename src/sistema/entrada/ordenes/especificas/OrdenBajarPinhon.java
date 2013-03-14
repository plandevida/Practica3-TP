package sistema.entrada.ordenes.especificas;

import sistema.entrada.ordenes.OrdenParaCiclista;

public class OrdenBajarPinhon extends OrdenParaCiclista {
	
	@Override
	public void ejecutarOrden() {
		getCiclista().aumentarPinhon();
		mostrarMensaje();
	}
	
	@Override
	public void mostrarMensaje() {
		System.out.println(this.getClass().getName() + ": Aumentando de piñón");
	}
}
