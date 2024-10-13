package es.florida.adaev1;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * clase por defecto
 * 
 * @author Ricardo Munyoz Pastor
 */
public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtFieldBuscarCadena;
	private JTextField txtFieldReemplazarCadena;
	private JTextField textFieldCargaDirectorio;
	private JList<String> listCargaDirectorio;
	private JList<String> listBuscarCadena;
	private JList<String> listReemplazarCadena;
	private JTextField txtFieldCoincidencias;

	/**
	 * Método para arrancar la app
	 * 
	 * @param args: no utilizados
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				Principal frame = new Principal();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Método para crear la interfaz gráfica
	 */
	public Principal() {
		setTitle("AD AEV1 RMP");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 20, 630, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnCargaDirectorio = new JButton("Mostrar");
		btnCargaDirectorio.setBounds(451, 25, 150, 35);
		contentPane.add(btnCargaDirectorio);

		JLabel lblCargaDirectorio = new JLabel("Elige que directorio quieres mostrar:");
		lblCargaDirectorio.setBounds(18, 25, 225, 35);
		contentPane.add(lblCargaDirectorio);

		JScrollPane scrollPaneCargaDirectorio = new JScrollPane();
		scrollPaneCargaDirectorio.setBounds(18, 83, 583, 150);
		contentPane.add(scrollPaneCargaDirectorio);

		listCargaDirectorio = new JList<>();
		scrollPaneCargaDirectorio.setViewportView(listCargaDirectorio);

		txtFieldBuscarCadena = new JTextField();
		txtFieldBuscarCadena.setBounds(294, 256, 135, 35);
		contentPane.add(txtFieldBuscarCadena);
		txtFieldBuscarCadena.setColumns(10);

		JButton btnBuscarCadena = new JButton("Buscar");
		btnBuscarCadena.setBounds(451, 256, 150, 35);
		contentPane.add(btnBuscarCadena);

		JLabel lblBuscarCadena = new JLabel("Introduce la cadena que quieres buscar:");
		lblBuscarCadena.setBounds(18, 256, 238, 35);
		contentPane.add(lblBuscarCadena);

		JScrollPane scrollPaneBuscarCadena = new JScrollPane();
		scrollPaneBuscarCadena.setBounds(18, 315, 583, 150);
		contentPane.add(scrollPaneBuscarCadena);

		listBuscarCadena = new JList<>();
		scrollPaneBuscarCadena.setViewportView(listBuscarCadena);

		JLabel lblReemplazarCadena = new JLabel("Introduce la cadena que reemplace la buscada:");
		lblReemplazarCadena.setBounds(18, 486, 267, 35);
		contentPane.add(lblReemplazarCadena);

		txtFieldReemplazarCadena = new JTextField();
		txtFieldReemplazarCadena.setBounds(294, 486, 135, 35);
		contentPane.add(txtFieldReemplazarCadena);
		txtFieldReemplazarCadena.setColumns(10);

		JButton btnReemplazarCadena = new JButton("Reemplazar");
		btnReemplazarCadena.setBounds(451, 486, 150, 35);
		contentPane.add(btnReemplazarCadena);

		JScrollPane scrollPaneReemplazarCadena = new JScrollPane();
		scrollPaneReemplazarCadena.setBounds(18, 541, 583, 150);
		contentPane.add(scrollPaneReemplazarCadena);

		listReemplazarCadena = new JList<>();
		scrollPaneReemplazarCadena.setViewportView(listReemplazarCadena);

		textFieldCargaDirectorio = new JTextField();
		textFieldCargaDirectorio.setBounds(294, 25, 135, 35);
		contentPane.add(textFieldCargaDirectorio);
		textFieldCargaDirectorio.setColumns(10);

		btnCargaDirectorio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String rutaDirectorio = textFieldCargaDirectorio.getText();
				listarContenido(rutaDirectorio);
			}
		});

		JLabel lblCoincidencias = new JLabel("Coincidencias encontradas:");
		lblCoincidencias.setBounds(18, 650, 250, 35);
		contentPane.add(lblCoincidencias);

		txtFieldCoincidencias = new JTextField();
		txtFieldCoincidencias.setBounds(294, 690, 150, 35);
		txtFieldCoincidencias.setEditable(false);
		contentPane.add(txtFieldCoincidencias);

		btnBuscarCadena.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String cadenaBuscada = txtFieldBuscarCadena.getText();
				listBuscarCadena(cadenaBuscada);
				int totalCoincidencias = contarCoincidencias(cadenaBuscada);
				txtFieldCoincidencias.setText(String.valueOf(totalCoincidencias));
			}
		});

		btnReemplazarCadena.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String cadenaBuscada = txtFieldBuscarCadena.getText();
				String cadenaReemplazo = txtFieldReemplazarCadena.getText();
				reemplazarCadena(cadenaBuscada, cadenaReemplazo);
			}
		});
	}

	/**
	 * Método para buscar y listar archivos y directorios y mostrar la información
	 * requerida de un directorio dado por el usuario
	 * 
	 * @param rutaDirectorio: ruta del directorio en el que buscar contenido
	 */
	public void listarContenido(String rutaDirectorio) {
		DefaultListModel<String> informacion = new DefaultListModel<>();
		File directorio = new File(rutaDirectorio);
		if (directorio.exists() && directorio.isDirectory()) {
			List<File> listaDirectorios = new ArrayList<>();
			listaDirectorios.add(directorio);
			while (!listaDirectorios.isEmpty()) {
				File directorioActual = listaDirectorios.remove(listaDirectorios.size() - 1);
				File[] arrayArchivos = directorioActual.listFiles();
				if (arrayArchivos != null) {
					for (File archivo : arrayArchivos) {
						String ruta = archivo.getAbsolutePath();
						String nombre = archivo.getName();
						String extension = extensionArchivo(archivo);
						long tamano = archivo.length();
						String fechaModificacion = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
								.format(new java.util.Date(archivo.lastModified()));
						String informacionMostrada = "Ruta: " + ruta + ", Nombre: " + nombre + ", Extensión: "
								+ extension + ", Tamaño: " + tamano + " bytes, Última modificación: "
								+ fechaModificacion;
						informacion.addElement(informacionMostrada);
						if (archivo.isDirectory()) {
							listaDirectorios.add(archivo);
						}
					}
				}
			}
		} else {
			informacion.addElement("El directorio no existe o no es un directorio.");
			JOptionPane.showMessageDialog(null, "El directorio no existe o no es un directorio.");
		}

		listCargaDirectorio.setModel(informacion);
	}

	/**
	 * Método para comprobar la extensión
	 * 
	 * @param archivo: archivo que se comprueba
	 * @return puede devolver la extensión del archivo o si no es válido devuelve
	 *         "Sin extensión"
	 */
	public String extensionArchivo(File archivo) {
		String nombreExtension = archivo.getName();
		int posicionPunto = nombreExtension.lastIndexOf('.');
		if (posicionPunto > 0 && posicionPunto < nombreExtension.length() - 1) {
			return nombreExtension.substring(posicionPunto + 1);
		} else {
			return "Sin extensión";
		}
	}

	/**
	 * Método para comprobar que elementos de la lista son válidos para contar
	 * cuantas veces aparece la cadena buscada
	 * 
	 * @param cadenaBuscada: cadena buscada en los archivos
	 * @return entero que muestra la cantidad de veces que aparece la cadena por
	 *         archivo
	 */
	public int contarCoincidencias(String cadenaBuscada) {
		int totalCoincidencias = 0;
		DefaultListModel<String> modeloLista = (DefaultListModel<String>) listCargaDirectorio.getModel();
		for (int i = 0; i < modeloLista.size(); i++) {
			String informacionArchivo = modeloLista.get(i);
			String rutaArchivo = informacionArchivo.split(",")[0].split(": ")[1];
			File archivo = new File(rutaArchivo);
			if (archivo.isFile() && archivo.canRead() && compruebaTextoPlano(archivo)) {
				String contenido = leerArchivo(archivo);
				int contador = contarCoincidenciasArchivo(contenido, cadenaBuscada);
				totalCoincidencias += contador;
			} else {
				totalCoincidencias += 0;
			}
		}
		return totalCoincidencias;
	}

	/**
	 * Método para filtrar que el archivo seleccionado es de texto plano (aunque
	 * esta solo limitado a unas pocas extensiones)
	 * 
	 * @param archivo: archivo del que se quiere comprobar la extensión
	 * @return un boolean que nos dice si la extension del archivo es una las
	 *         comprobadas
	 */
	public boolean compruebaTextoPlano(File archivo) {
		String extension = extensionArchivo(archivo).toLowerCase();
		return extension.equals("txt") || extension.equals("java") || extension.equals("json")
				|| extension.equals("html") || extension.equals("xml") || extension.equals("log");
	}

	/**
	 * Método para leer un archivo
	 * 
	 * @param archivo: archivo que vamos a leer
	 * @return devuelve el contenido del archivo si no ha habido un error de lectura
	 */
	public String leerArchivo(File archivo) {
		StringBuilder contenido = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
			String linea;
			while ((linea = br.readLine()) != null) {
				contenido.append(linea).append("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contenido.toString();
	}

	/**
	 * Método para contar las coincidencias de la cadena buscada en un archivo
	 * 
	 * @param contenidoArchivo: contenido en el que se busca
	 * @param cadenaBuscada:    cadena que se busca
	 * @return nos devuelve en forma de entero las veces que aparece la cadena
	 */
	public int contarCoincidenciasArchivo(String contenidoArchivo, String cadenaBuscada) {
		int contador = 0;
		int indice = 0;
		while ((indice = contenidoArchivo.indexOf(cadenaBuscada, indice)) != -1) {
			contador++;
			indice += cadenaBuscada.length();
		}
		return contador;
	}

	/**
	 * Método para buscar la cadena introducida por el usuario
	 * 
	 * @param cadenaBuscada: cadena que se busca
	 */
	public void listBuscarCadena(String cadenaBuscada) {
		DefaultListModel<String> listaModelo = new DefaultListModel<>();
		DefaultListModel<String> listaModeloCompleta = (DefaultListModel<String>) listCargaDirectorio.getModel();
		for (int i = 0; i < listaModeloCompleta.size(); i++) {
			String informacionArchivo = listaModeloCompleta.get(i);
			String rutaArchivo = informacionArchivo.split(",")[0].split(": ")[1].trim();
			File archivo = new File(rutaArchivo);
			if (archivo.isFile() && compruebaTextoPlano(archivo)) {
				String contenido = leerArchivo(archivo);
				int contador = contarCoincidenciasArchivo(contenido, cadenaBuscada);
				listaModelo.addElement(archivo.getName() + " - " + contador + " coincidencias");
			} else {
				listaModelo.addElement(archivo.getName() + " - 0 coincidencias");
			}
		}
		listBuscarCadena.setModel(listaModelo);
	}

	/**
	 * Método para reemplazar la cadena encontrada por una nueva introducida por el
	 * usuario (el remplazo no sobrescribe el archivo solo crea uno nuevo)
	 * 
	 * @param cadenaBuscada:   cadena que se tiene que reemplazar
	 * @param cadenaReemplazo: cadena que reemplazará a la buscada
	 */
	public void reemplazarCadena(String cadenaBuscada, String cadenaReemplazo) {
		DefaultListModel<String> listaModeloOriginal = (DefaultListModel<String>) listCargaDirectorio.getModel();
		DefaultListModel<String> listaModeloNueva = new DefaultListModel<>();
		for (int i = 0; i < listaModeloOriginal.size(); i++) {
			String informacionArchivo = listaModeloOriginal.get(i);
			String rutaArchivo = informacionArchivo.split(",")[0].split(": ")[1];
			File archivo = new File(rutaArchivo);
			if (archivo.isFile() && compruebaTextoPlano(archivo)) {
				String contenido = leerArchivo(archivo);
				int contador = contarCoincidenciasArchivo(contenido, cadenaBuscada);
				if (contador > 0) {
					String nuevoNombre = archivo.getParent() + File.separator + "MOD_" + archivo.getName();
					File nuevoArchivo = new File(nuevoNombre);
					String contenidoNuevo = contenido.replace(cadenaBuscada, cadenaReemplazo);
					try (BufferedWriter bw = new BufferedWriter(new FileWriter(nuevoArchivo))) {
						bw.write(contenidoNuevo);
					} catch (IOException e) {
						listaModeloNueva.addElement(archivo.getName() + " - Error al escribir en el nuevo archivo");
					}
					listaModeloNueva.addElement(archivo.getName() + " - " + contador
							+ " reemplazos (nuevo archivo creado: " + nuevoArchivo.getName() + ")");
				} else {
					listaModeloNueva.addElement(archivo.getName() + " - 0 reemplazos");
				}
			} else {
				listaModeloNueva.addElement(archivo.getName() + " - 0 reemplazos");
			}
		}
		listReemplazarCadena.setModel(listaModeloNueva);
	}
}
