package sistema.entidades.personas.ciclistas;

import java.util.StringTokenizer;

import sistema.entidades.personas.Persona;
import sistema.entidades.tiempo.Reloj;
import sistema.entidades.vehiculos.bicicletas.Bicicleta;
import sistema.interfaces.ObjetosConSalidaDeDatos;

/**
 * Clase que representa un ciclista.
 * 
 * @author Daniel Serrano Torres
 * @author Alvaro Quesada Pimentel
 */
public class Ciclista extends Persona implements ObjetosConSalidaDeDatos {
	/**
	 * La bicicleta que va a montar.
	 */
	private Bicicleta bicicletamontada;
	
	/**
	 * El reloj del ciclista.
	 */
	private Reloj reloj;
	
	/**
	 * La candencia de la pedalada del ciclista.
	 */
	private double cadencia;
	
	/**
	 * Número único del ciclista en la carrera
	 */
	private int numeromallot;
	
	/**
	 * Crea un ciclista.
	 * 
	 * @param nombre
	 * @param nummallot
	 * @param cadenciaCiclista
	 * @param bicicletaamontar
	 * @param relojCiclista
	 */
	public Ciclista(String nombre, int nummallot, double cadenciaCiclista, Bicicleta bicicletaamontar, Reloj relojCiclista) {
		/**
		 * Demomento el peso no es relevante, ni el cansancio
		 * 
		 * Esta clase hereda de persona @link Persona
		 */
		super(nombre, 50, 100);
		cadencia = cadenciaCiclista;
		bicicletamontada = bicicletaamontar;
		reloj = relojCiclista;
		numeromallot = nummallot;
	}
	
	/**
	 * Metodo que evalua la velociadad de la bicicleta, en fucion de ella
	 * decide si frenar o serguir pedaleando.
	 * 
	 * @return Una cadena de texto separada por tokens.
	 */
	@Override
	public void run() {
		pedalear();
	}
	
	/**
	 * Metodo para obtener una salida de datos de un objeto.
	 * 
	 * @return Una cadena de texto separada por tokens.
	 */
	public StringTokenizer mostrarDatos() {
		StringBuilder mensaje = new StringBuilder(getNombre());
		mensaje.append(",");
		mensaje.append(getPeso());
		mensaje.append(",");
		mensaje.append(getCadencia());

		StringTokenizer stringTokenizer = new StringTokenizer(mensaje.toString(), ",");

		return stringTokenizer;
	}
	
	/**
	 * Metodo que da pedaladas a la bicicleta
	 */
	public void pedalear() {
		bicicletamontada.darPedalada(cadencia);
	}
	
	/**
	 * Frena la bicicleta.
	 */
	public double frenar() {
		
		bicicletamontada.frenar();
		
		return bicicletamontada.getVelocidad();
	}
	
	/**
	 * Aumentar el piñón actual al mayor adyacente.
	 * 
	 * @return El piñón al que se ha cambiado.
	 */
	public int aumentarPinhon() {
		
		bicicletamontada.setPinhonactual(bicicletamontada.getPinhonactual()+1);
		
		return bicicletamontada.getPinhonactual();
	}
	
	/**
	 * Desminuir el piñón actual al menor adyacente.
	 * 
	 * @return El piñón al que se ha cambiado.
	 */
	public int disminuirPinhon() {
		
		bicicletamontada.setPinhonactual(bicicletamontada.getPinhonactual()-1);
		
		return bicicletamontada.getPinhonactual();
	}
	
	/**
	 * Aumentar el plato actual al mayor adyacente.
	 * 
	 * @return El plato al que se ha cambiado.
	 */
	public int aumentarPlato() {
		
		bicicletamontada.setPlatoactual(bicicletamontada.getPlatoactual()+1);
		
		return bicicletamontada.getPlatoactual();
	}
	
	/**
	 * Desminuir el plato actual al menor adyacente.
	 * 
	 * @return El plato al que se ha cambiado.
	 */
	public int disminuirPlato() {
		
		bicicletamontada.setPlatoactual(bicicletamontada.getPlatoactual()-1);
		
		return bicicletamontada.getPlatoactual();
	}
	
	/**
	 * Obtiene la bicicleta que se esta montando.
	 *  
	 * @return la bicicleta que se esta montando
	 */
	public Bicicleta getBicicletamontada() {
		return bicicletamontada;
	}
	
	/**
	 * Cambia la bicicleta que se esta montando.
	 * 
	 * @param bicicletamontada Bicicleta que se esta montando.
	 */
	public void setBicicletamontada(Bicicleta bicicletamontada) {
		this.bicicletamontada = bicicletamontada;
	}
	
	/**
	 * Obtiene el reloj.
	 *  
	 * @return el reloj
	 */
	public Reloj getReloj() {
		return reloj;
	}
	
	/**
	 * Cambia el reloj.
	 * 
	 * @param reloj Reloj del ciclista.
	 */
	public void setReloj(Reloj reloj) {
		this.reloj = reloj;
	}
	
	/**
	 * Obtiene la cadencia del ciclista
	 *  
	 * @return La cadencia
	 */
	public double getCadencia() {
		return cadencia;
	}
	
	/**
	 * Cambia la candencia del ciclista.
	 * 
	 * @param cadencia Cadencia nueva.
	 */
	public void setCadencia(int cadencia) {
		this.cadencia = cadencia;
	}
	
	/**
	 * Obtiene el número del mallot del ciclista.
	 * 
	 * @return Número del mallot.
	 */
	public int getNumeromallot() {
		return numeromallot;
	}
}
