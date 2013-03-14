package sistema.entrada.ordenes.especificas;

import sistema.entrada.ordenes.OrdenParaCiclista;

public class OrdenSubirPlato extends OrdenParaCiclista {
	
	public OrdenSubirPlato(){
		
	}

	@Override
	public void ejecutarOrden() {
		getCiclista().aumentarPlato();
		mostrarMensaje();
	}
	
	@Override
	public void mostrarMensaje() {
		System.out.println(this.getClass().getName() + ": Subiendo de plato");
	}
}
