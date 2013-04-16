package sistema.vista.visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import sistema.interfaces.ObjetosConSalidaDeDatos;
import sistema.vista.InterfaceSalidaDatos;

public class Ventana extends JFrame implements InterfaceSalidaDatos {
	
	private static final long serialVersionUID = 1L;

	// Lista de objetos que se van a mostrar
	private List<ObjetosConSalidaDeDatos> registroobjetossalidadatos;
	private List<JTextArea> areasCreadas;
	
	private JPanel panelCiclistas;
	
	public Ventana() {
		
		Init();
	}
	
	public Ventana( List<ObjetosConSalidaDeDatos> objetosAMostrar) {
		registroobjetossalidadatos = objetosAMostrar;
		
		Init();
	}
	
	private void Init() {
		
		setTitle("Principal");
		setPreferredSize(new Dimension(500, 300));
		
		JPanel panelPrincipal = new JPanel(new BorderLayout());
		
		panelCiclistas = new JPanel();
		
//		JTextArea textcicli1 = new JTextArea("cicli1");
//		textcicli1.setPreferredSize(new Dimension(100, 200));
//		textcicli1.setEditable(false);
//		textcicli1.setBorder(new TitledBorder(textcicli1.getText()));
//		
//		JTextArea textcicli2 = new JTextArea("cicli2");
//		textcicli2.setEditable(false);
//		textcicli2.setBorder(new TitledBorder(textcicli2.getText()));
//		
//		JTextArea textcicli3 = new JTextArea("cicli3");
//		textcicli3.setEditable(false);
//		textcicli3.setBorder(new TitledBorder(textcicli3.getText()));
//		
//		JTextArea textcicli4 = new JTextArea("cicli4");
//		textcicli4.setEditable(false);
//		textcicli4.setBorder(new TitledBorder(textcicli4.getText()));
		
		panelCiclistas.setLayout(new GridLayout());
		
//		panelCiclistas.add(textcicli1);
//		panelCiclistas.add(textcicli2);
//		panelCiclistas.add(textcicli3);
//		panelCiclistas.add(textcicli4);
		
		panelPrincipal.add(panelCiclistas, BorderLayout.CENTER);
		panelPrincipal.add(crearBotones(), BorderLayout.SOUTH);
		
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
	
	private JPanel crearBotones() {
		JPanel panel = new JPanel();
		
		JButton botonAñadir = new JButton("Añadir");
		
		botonAñadir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				añadirTextAreas("nueva");
			}
		});
		
		panel.add(botonAñadir);
		
		return panel;
	}
	
	public void añadirTextAreas(String nombre) {
		
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
