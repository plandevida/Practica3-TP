package sistema.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sistema.entidades.carretera.tramocarreraciclista.TramoCiclista;
import sistema.entidades.personas.ciclistas.Ciclista;
import sistema.entidades.tiempo.Reloj;
import sistema.entidades.vehiculos.bicicletas.Bicicleta;
import sistema.entrada.lectura.Lector;
import sistema.entrada.ordenes.Dispatcher;
import sistema.entrada.parseador.parser.ParseadorCarrera;
import sistema.entrada.parseador.parser.ParseadorComandos;
import sistema.factoresexternos.FactoresExternos;
import sistema.interfaces.ObjetosQueSeEjecutan;
import sistema.salidadatos.consola.SalidaDatos;
import sistema.salidadatos.visual.Ventana;

/**
 * Clase principal que inicia la aplicación.
 * @author Daniel Serrano Torres
 * @author Alvaro Quesada Pimentel
 */
public class Manager {
	
	// Contexto de los ficheros del sistema.
	public static final String COMANDOS_FOLDER_PATH = "resources/instrucciones/";
	public static final String CONFIG_FOLDER_PATH = "resources/configuracion/";
	public static final String DEFAULT_CONFIG_PATH = "resources/configuracion/carrera";
	public static final String DEFAULT_COMANDOS_PATH = "resources/instrucciones/comandos";
	
	
	private List<ObjetosQueSeEjecutan> listaejecutables;
	private Map<Integer, TramoCiclista> carreteradecarreraciclsta;
	
	// Entidades del sistema.
	private SalidaDatos salidadatos;
	private List<Ciclista> ciclistas;
	private Bicicleta bicicleta;
	private Bicicleta bicicleta1;
	private Bicicleta bicicleta2;
	private Bicicleta bicicleta3;
	//
	private List<FactoresExternos> factores;
	private Ventana ventana;
	//
	private Reloj reloj;
	
	// Elemetos del sistema
	private Dispatcher dispatcher;
	private ParseadorComandos parser;
	private Lector lector;
	
	/**
	 * Carga la carretera de la carrera ciclista.
	 */
	private void cargarConfiguracion() {
		Lector lectorConfiguracion = new Lector(DEFAULT_CONFIG_PATH, true);
		
		String configuracioncarreraciclista = lectorConfiguracion.cargarFicheroCompelto();
		
		construirCarretera(configuracioncarreraciclista);
	}
	
	/**
	 * Construye el mapa con la configuración de la carrera ciclista.
	 * 
	 * @param datos Cadena con el contenido de la carrera ciclista.
	 */
	private void construirCarretera(String datos) {
		
		carreteradecarreraciclsta = new HashMap<Integer, TramoCiclista>();
		
		ParseadorCarrera parseadorcarrera = new ParseadorCarrera(carreteradecarreraciclsta);
		
		parseadorcarrera.parse(datos);
		
	
	}
	
	/**
	 * Inicializa el contexto de la aplicación.
	 */
	public void iniciar() {
		
		reloj = new Reloj();
		ventana = new Ventana();
		ciclistas = new ArrayList<Ciclista>();
		factores = new ArrayList<FactoresExternos>();
		
		// Bicicletas para los ciclistas.
		bicicleta = new Bicicleta();
		bicicleta1 = new Bicicleta();
		bicicleta2 = new Bicicleta();
		bicicleta3 = new Bicicleta();
		
		factores.add(new FactoresExternos(bicicleta, carreteradecarreraciclsta));
		factores.add(new FactoresExternos(bicicleta1, carreteradecarreraciclsta));
		factores.add(new FactoresExternos(bicicleta2, carreteradecarreraciclsta));
		factores.add(new FactoresExternos(bicicleta3, carreteradecarreraciclsta));
		
		ciclistas.add(new Ciclista("Pamela", 1, 0.5, bicicleta, reloj));
		ciclistas.add(new Ciclista("Pedro", 2, 1.5, bicicleta1, reloj));
		ciclistas.add(new Ciclista("Ana", 3, 1.0, bicicleta2, reloj));
		ciclistas.add(new Ciclista("Juan", 4, 0.75, bicicleta3, reloj));
		
		listaejecutables = new ArrayList<ObjetosQueSeEjecutan>();
		salidadatos = new SalidaDatos();
		
		// Se registran los elementos ejecutables en una lista.
		listaejecutables.add(reloj);
		
		// Se registran los elementos con salida de datos.
		for (Ciclista ciclista : ciclistas) {
			listaejecutables.add(ciclista);
			salidadatos.registrarObjetoConSalidaDatos(ciclista);
		}
		
		//Se registran los factores externos
		for (FactoresExternos factor : factores){
			listaejecutables.add(factor);
		}
		
		salidadatos.registrarObjetoConSalidaDatos(reloj);
		salidadatos.registrarObjetoConSalidaDatos(bicicleta);
		salidadatos.registrarObjetoConSalidaDatos(bicicleta1);
		salidadatos.registrarObjetoConSalidaDatos(bicicleta2);
		salidadatos.registrarObjetoConSalidaDatos(bicicleta3);
		
		dispatcher = new Dispatcher();
		parser = new ParseadorComandos(dispatcher, listaejecutables);
		
		lector = new Lector(DEFAULT_COMANDOS_PATH, false);
	}
	
	/**
	 * Ejecuta la aplicación.
	 */
	public void ejecutar() {
		
		for (ObjetosQueSeEjecutan objetoejecutable : listaejecutables) {
			objetoejecutable.start();
		}
		
		while ( reloj.getHoras() < 2 ) {
			
			parser.parse(lector.leerTeclado());
			parser.parse(lector.leerFichero());
			parser.dispatch();
			
			salidadatos.mostrarDatos();
		}
	}
	
	/**
	 * Finaliza el contexto de la aplicación.
	 */
	public void finalizar() {
		lector.finalizarLecturas();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Manager manager = new Manager();
		
		manager.cargarConfiguracion();
		manager.iniciar();
		manager.ejecutar();
		manager.finalizar();
	}
}
