package sistema.salidadatos.visual;

import java.awt.GridLayout;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

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
		
		setLayout(new GridLayout());
		
		panel1.add(textcicli1);
		panel2.add(textcicli2);
		panel3.add(textcicli3);
		panel4.add(textcicli4);
		
		getContentPane().add(panel1);
		getContentPane().add(panel2);
		getContentPane().add(panel3);
		getContentPane().add(panel4);

		setVisible(true);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	
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

				switch (numerodetokens) {

				// Caso para el formato de la bicicleta
				case 1:
					System.out.println("-- Bicicleta --");
					System.out.println("velocidad: " + mensaje.nextElement());
					
					break;

				// Caso para el formato del ciclista
				case 3:
					System.out.println("-- Ciclista --");
					System.out.println("nombre: " + mensaje.nextElement());
					System.out.println("peso: " + mensaje.nextElement());
					System.out.println("cadencia: " + mensaje.nextElement());
					
					break;

				// Caso para el formato reloj
				case 4:
					System.out.println("-- Reloj --");

					StringBuilder formato = new StringBuilder(
							(String) mensaje.nextElement()).append("h ")
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
