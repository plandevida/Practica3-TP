package sistema.entrada.ordenes.especificas;

import sistema.entrada.ordenes.OrdenParaCiclista;

public class OrdenSubirPinhon extends OrdenParaCiclista {
	
	public OrdenSubirPinhon(){
		
	}
	
	@Override
	public void ejecutarOrden() {
		getCiclista().aumentarPinhon();
		mostrarMensaje();
	}
	
	@Override
	public void mostrarMensaje() {
		System.out.println(this.getClass().getName() + ": Subiendo de piñón");
	}
}
