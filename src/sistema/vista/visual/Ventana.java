package sistema.vista.visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import sistema.entrada.parseador.parser.ParseadorComandos;
import sistema.interfaces.ObjetosConSalidaDeDatos;
import sistema.vista.InterfaceSalidaDatos;

public class Ventana extends JFrame implements InterfaceSalidaDatos {
	
	private static final long serialVersionUID = 1L;

	// Lista de objetos que se van a mostrar
	private List<ObjetosConSalidaDeDatos> registroobjetossalidadatos;
	private List<JTextArea> areasCreadas;
	
	private ParseadorComandos parsercomandos;
	
	private JPanel panelCiclistas;
	
	public Ventana(ParseadorComandos parser) {
		parsercomandos = parser;
		
		Init();
	}
	
	public Ventana( List<ObjetosConSalidaDeDatos> objetosAMostrar, ParseadorComandos parser) {
		registroobjetossalidadatos = objetosAMostrar;
		parsercomandos = parser;
		
		Init();
	}
	
	private void Init() {
		
		setTitle("Principal");
		setPreferredSize(new Dimension(500, 300));
		
		JPanel panelPrincipal = new JPanel(new BorderLayout());
		
		panelCiclistas = new JPanel();
		panelCiclistas.setLayout(new GridLayout());
		
		panelPrincipal.add(panelCiclistas, BorderLayout.CENTER);
		panelPrincipal.add(crearComandero(), BorderLayout.SOUTH);
		
		setContentPane(panelPrincipal);
		
		if (registroobjetossalidadatos != null) {
			for(int i = 0; i < registroobjetossalidadatos.size(); i++) {
				añadirTextAreas("nuevaAuto");
			}
		}

		setVisible(true);
		pack();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	}
	
	private JPanel crearComandero() {
		JPanel panel = new JPanel();
		
		JLabel campocomandosetiqueta = new JLabel("Comandos: ");
		
		JTextField campocomandos = new JTextField();
		campocomandos.setBounds(new Rectangle(120, 50));
		
		campocomandos.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				try {
					parsercomandos.parse(e.getDocument().getText(0, e.getDocument().getLength()));
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				try {
					parsercomandos.parse(e.getDocument().getText(0, e.getDocument().getLength()));
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				try {
					parsercomandos.parse(e.getDocument().getText(0, e.getDocument().getLength()));
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		panel.add(campocomandosetiqueta);
		panel.add(campocomandos);
		
		return panel;
	}
	
	private void añadirTextAreas(String nombre) {
		
		if (areasCreadas == null) {
			areasCreadas = new ArrayList<JTextArea>();
		}
		
		JTextArea nuevaTextArea = new JTextArea();
		nuevaTextArea.setName(nombre);
		nuevaTextArea.setPreferredSize(new Dimension(100, 200));
		nuevaTextArea.setEditable(false);
		nuevaTextArea.setBorder(new TitledBorder(nombre));
		
		areasCreadas.add(nuevaTextArea);
		
		panelCiclistas.add(nuevaTextArea);
		panelCiclistas.validate();
	}

	@Override
	public void registrarObjetoConSalidaDatos(ObjetosConSalidaDeDatos objetoconsalidadatos) {
		if (registroobjetossalidadatos == null) {
			registroobjetossalidadatos = new ArrayList<ObjetosConSalidaDeDatos>();
		}
		registroobjetossalidadatos.add(objetoconsalidadatos);
		
	}

	@Override
	public void registrarObjetoConSalidaDatos(ArrayList<ObjetosConSalidaDeDatos> listadeobjetosconsalidadatos) {
		for (ObjetosConSalidaDeDatos objetoaregistrar : listadeobjetosconsalidadatos) {
			registrarObjetoConSalidaDatos(objetoaregistrar);
		}
	}

	@Override
	public void mostrarDatos() {
		if (registroobjetossalidadatos != null) {

			for (ObjetosConSalidaDeDatos objetoamostrar : registroobjetossalidadatos) {
				
				StringTokenizer mensaje = objetoamostrar.mostrarDatos();

				String formato = mensaje.nextToken();
				
				StringBuilder datos = new StringBuilder();
				
				switch (formato) {

				// Caso para el formato de la bicicleta
				case "#bicicleta#":
					datos.append("velocidad: ")
						.append(mensaje.nextToken());
					
					break;

				// Caso para el formato del ciclista
				case "#ciclista#":
					datos.append("nombre: ")
					.append(mensaje.nextToken()).append('\n')
					.append("peso: ")
					.append(mensaje.nextToken()).append('\n')
					.append("cadencia: ")
					.append(mensaje.nextToken());
					
					break;

				// Caso para el formato reloj
				case "#reloj#":
					datos.append((String) mensaje.nextToken()).append("h ")
							.append((String) mensaje.nextToken())
							.append("m ")
							.append((String) mensaje.nextToken())
							.append("s ")
							.append((String) mensaje.nextToken())
							.append(" impulsos");
					
					break;

				default:
					break;
				}

			}
		}
			
	}

	
}
