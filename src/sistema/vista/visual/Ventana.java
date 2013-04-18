package sistema.vista.visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.text.JTextComponent;

import sistema.entrada.parseador.parser.ParseadorComandos;

/**
 * Vista del sistema en un componente de swing.
 * Con esta clase se puede mostrar una cantidad
 * "infinita" de datos en áreas de texto.
 * 
 * @author Daniel Serrano Torres
 * @author Alvaro Quesada Pimentel
 */
public class Ventana extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private List<JTextComponent> componenetescreados;
	
	private ParseadorComandos parsercomandos;
	
	private JPanel panelCiclistas;
	private JTextField camporeloj;
	
	JTextField campocomandos;
	
	public Ventana(ParseadorComandos parser) {
		parsercomandos = parser;
		componenetescreados = new ArrayList<JTextComponent>();
		
		Init();
	}
	
	/**
	 * Crea la interfaz de la ventana y sus componentes.
	 */
	private void Init() {
		
		setTitle("Carrera ciclista");
		setPreferredSize(new Dimension(700, 300));
		
		JPanel panelPrincipal = new JPanel(new BorderLayout());
		
		panelCiclistas = new JPanel();
		panelCiclistas.setLayout(new GridLayout(0, 2, 10, 10));
		panelCiclistas.setBackground(Color.WHITE);
		
		panelPrincipal.add(crearRelojero(), BorderLayout.NORTH);
		panelPrincipal.add(panelCiclistas, BorderLayout.CENTER);
		panelPrincipal.add(crearComandero(), BorderLayout.SOUTH);
		
		setContentPane(panelPrincipal);

		setVisible(true);
		pack();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private JPanel crearRelojero() {
		JPanel panel = new JPanel();
		
		JLabel relojetiqueta = new JLabel("Reloj: ");
		
		camporeloj = new JTextField();
		camporeloj.setBounds(new Rectangle(120, 50));
		camporeloj.setName("reloj");
		
		componenetescreados.add(camporeloj);
		
		panel.add(relojetiqueta);
		panel.add(camporeloj);
		
		return panel;
	}
	
	/**
	 * Crea el campo de texto donde se insertan los comando al sistema.
	 * 
	 * @return Panel con el cuadro de texto.
	 */
	private JPanel crearComandero() {
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(120, 60));
		
		JLabel campocomandosetiqueta = new JLabel("Comandos: ");
	
		campocomandos = new JTextField();
		campocomandos.setSize(new Dimension(120, 60));
		campocomandos.setText("Escriba un comando");
		
		campocomandos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				campocomandos.setText("");
			}
		});
		
		campocomandos.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String comando = ((JTextField) e.getSource()).getText();
				
				parsercomandos.parse(comando);
			}
		});
		
		panel.add(campocomandosetiqueta);
		panel.add(campocomandos);
		
		return panel;
	}
	
	/**
	 * Añade una nueva área de texto.
	 * El nombre del área de texto es importante
	 * para determinar si se añade una nueva o se
	 * modifican sus datos.
	 * 
	 * @param nombre Nombre del area de texto.
	 */
	private void añadirTextArea(String nombre) {
		
		JTextArea nuevaTextArea = new JTextArea();
		nuevaTextArea.setName(nombre);
		nuevaTextArea.setPreferredSize(new Dimension(100, 200));
		nuevaTextArea.setEditable(false);
		nuevaTextArea.setBorder(new TitledBorder(nombre));
		
		componenetescreados.add(nuevaTextArea);
		
		panelCiclistas.add(nuevaTextArea);
		panelCiclistas.validate();
	}
	
	/**
	 * Añade nueva información en un área de texto o crea una nueva
	 * si fuese necesario.
	 * 
	 * @param objetoamostrar Objeto que mostrará sus datos.
	 * @param mensajeFormateado Los datos formateados a poner en el área.
	 */
	public void ponerDatosEnVentana(String id, String mensaje) {
		JTextComponent componenteexistente = null;
		
		for (JTextComponent componente : componenetescreados) {
			if (componente.getName().equals(id)) {
				componente.setText(mensaje);
				
				componenteexistente = componente;
			}
		}
		
		if (componenteexistente == null) {
			añadirTextArea(id);
			
			ponerDatosEnVentana(id, mensaje);
		}

		/**
		 * Formatea los datos del objeto que se va a mostrar
		 * y lo pone en su área de texto en base al nombre de
		 * ambos.
		 * 
		 * @param objetoamostrar Objeto que mostrará sus datos.
		 */
//	public void ponerDatosEnVentana(ObjetosConSalidaDeDatos objetoamostrar) {
//		StringTokenizer mensaje = objetoamostrar.mostrarDatos();
//
//		String formato = mensaje.nextToken();
//		
//		StringBuilder datos = new StringBuilder();
//		
//		switch (formato) {
//
//		// Caso para el formato de la bicicleta
//		case "#bicicleta#":
//			datos.append("velocidad: ")
//				.append(mensaje.nextToken());
//			
//			añadirEnTextArea(objetoamostrar, datos.toString());
//			
//			break;
//
//		// Caso para el formato del ciclista
//		case "#ciclista#":
//			datos.append("nombre: ")
//			.append(mensaje.nextToken()).append('\n')
//			.append("peso: ")
//			.append(mensaje.nextToken()).append('\n')
//			.append("cadencia: ")
//			.append(mensaje.nextToken());
//
//			añadirEnTextArea(objetoamostrar, datos.toString());
//			
//			break;
//
//		// Caso para el formato reloj
//		case "#reloj#":
//			datos.append((String) mensaje.nextToken()).append("h ")
//					.append((String) mensaje.nextToken())
//					.append("m ")
//					.append((String) mensaje.nextToken())
//					.append("s ")
//					.append((String) mensaje.nextToken())
//					.append(" impulsos");
//
//			camporeloj.setText(datos.toString());
//			
//			break;
//
//		default:
//			break;
//		}
	}
	
	/**
	 * Añade nueva información en un área de texto o crea una nueva
	 * si fuese necesario.
	 * 
	 * @param objetoamostrar Objeto que mostrará sus datos.
	 * @param mensajeFormateado Los datos formateados a poner en el área.
	 */
//	private void añadirEnTextArea(ObjetosConSalidaDeDatos objetoamostrar, String mensajeFormateado) {
//		
//		JTextArea areaExistente = null;
//		
//		for (JTextArea area : areasCreadas) {
//			if (area.getName().equals(objetoamostrar.getIdentificadorSalidaDatos())) {
//				area.setText(mensajeFormateado);
//				
//				areaExistente = area;
//			}
//		}
//		
//		if (areaExistente == null) {
//			añadirTextArea(objetoamostrar.getIdentificadorSalidaDatos());
//			
//			añadirEnTextArea(objetoamostrar, mensajeFormateado);
//		}
//	}
//	
//	/**
//	 * Limpia el contenido de las áreas de texto.
//	 */
//	public void limpia() {
//		for (JTextArea area : areasCreadas) {
//			area.setText("");
//		}
//	}
	
	public void limpia() {
		for (JTextComponent area : componenetescreados) {
			area.setText("");
		}
	}
}
