
package sistema.entrada.ordenes.especificas;

import sistema.entrada.ordenes.Orden;
import sistema.entrada.parseador.lexer.Comandos;

public class OrdenDesconocida extends Orden {

	@Override
	public void ejecutarOrden() {
//		mostrarMensaje();
	}
	
	@Override
	public void mostrarMensaje() {
		System.err.println("NO SE RECONOCE LA ORDEN");
		System.out.println("Ordenes del sistema: ");
		
		Comandos[] comandos = Comandos.values();
		
		for (Comandos comando : comandos) {
			System.out.println(comando.ordinal() + ": " + comando.name());
		}
	}
}
