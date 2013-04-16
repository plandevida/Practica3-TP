package sistema.salidadatos.visual;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import sistema.interfaces.ObjetosConSalidaDeDatos;
import sistema.salidadatos.InterfaceSalidaDatos;

public class Ventana extends JFrame implements InterfaceSalidaDatos {
	
	private static final long serialVersionUID = 1L;
	
	private JTextArea textcicli1;
	private JTextArea textcicli2;
	private JTextArea textcicli3;
	private JTextArea textcicli4;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JPanel panel4;

	// Lista de objetos que se van a mostrar
	private List<ObjetosConSalidaDeDatos> registroobjetossalidadatos;
	
	public Ventana(){
		
		registroobjetossalidadatos = new ArrayList<ObjetosConSalidaDeDatos>();
		
		init();
	}
	
	private void init() {
		
		setTitle("principal");
		setBounds(new Rectangle(500,500));
		
		setLayout(new BorderLayout(10, 10));
		
		JPanel panelCiclistas = new JPanel();
		
		textcicli1 = new JTextArea("cicli1");
		textcicli1.setEditable(false);
		
		textcicli2 = new JTextArea("cicli2");
		textcicli2.setEditable(false);
		
		textcicli3 = new JTextArea("cicli3");
		textcicli3.setEditable(false);
		
		textcicli4 = new JTextArea("cicli4");
		textcicli4.setEditable(false);
		
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		panel4 = new JPanel();
		
		panelCiclistas.setLayout(new GridLayout());
		
		panel1.add(textcicli1);
		panel2.add(textcicli2);
		panel3.add(textcicli3);
		panel4.add(textcicli4);
		
		panelCiclistas.add(panel1);
		panelCiclistas.add(panel2);
		panelCiclistas.add(panel3);
		panelCiclistas.add(panel4);
		
		getContentPane().add(panelCiclistas, BorderLayout.CENTER);
		getContentPane().add(crearBotones(), BorderLayout.SOUTH);

		setVisible(true);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	}
	
	private JPanel crearBotones() {
		JPanel panel = new JPanel();
		
		JButton botonAñadir = new JButton();
		
		botonAñadir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				añadirTextArea();
			}
		});
		
		return panel;
	}
	
	public void añadirTextArea() {
		
		JTextArea textcicli = new JTextArea("cicli" + registroobjetossalidadatos.size()+1);
		textcicli.setEditable(false);
		
		JPanel panel = new JPanel();
		
		panel.add(textcicli1);
		
		getContentPane().add(panel);
	}

	@Override
	public void registrarObjetoConSalidaDatos(ObjetosConSalidaDeDatos objetoconsalidadatos) {
		registroobjetossalidadatos.add(objetoconsalidadatos);
		
	}

	@Override
	public void registrarObjetoConSalidaDatos(Collection<ObjetosConSalidaDeDatos> listadeobjetosconsalidadatos) {
		for (ObjetosConSalidaDeDatos objetoaregistrar : listadeobjetosconsalidadatos) {
			registroobjetossalidadatos.add(objetoaregistrar);
		}
		
	}

	@Override
	public void mostrarDatos() {
		if (registroobjetossalidadatos != null) {

			for (ObjetosConSalidaDeDatos objetoamostrar : registroobjetossalidadatos) {

				int numerodetokens = objetoamostrar.mostrarDatos().countTokens();
				StringTokenizer mensaje = objetoamostrar.mostrarDatos();

				StringBuilder formato = new StringBuilder();
				
				switch (numerodetokens) {

				// Caso para el formato de la bicicleta
				case 1:
					formato.append("-- Bicicleta --")
						.append("velocidad: ")
						.append(mensaje.nextElement());
					
					break;

				// Caso para el formato del ciclista
				case 3:
					formato.append("-- Ciclista --")
					.append("nombre: ")
					.append(mensaje.nextElement())
					.append("peso: ")
					.append(mensaje.nextElement())
					.append("cadencia: ")
					.append(mensaje.nextElement());
					
					break;

				// Caso para el formato reloj
				case 4:
					System.out.println("-- Reloj --");

					formato.append((String) mensaje.nextElement()).append("h ")
							.append((String) mensaje.nextElement())
							.append("m ")
							.append((String) mensaje.nextElement())
							.append("s ")
							.append((String) mensaje.nextElement())
							.append(" impulsos");

					System.out.println(formato.toString());
					
					break;

				default:
					while (mensaje.hasMoreElements())
						System.out.println(mensaje.nextElement());
					
					break;
				}

			}
		}
			
	}

	
}
