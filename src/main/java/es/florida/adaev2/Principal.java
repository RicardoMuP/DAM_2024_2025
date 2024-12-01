package es.florida.adaev2;

public class Principal {
	/**
	 * Clase principal ejecutable
	 * 
	 * @param args No hay args de entrada
	 */
	public static void main(String[] args) {

		Vista vista = new Vista();
		Modelo modelo = new Modelo();
		Controlador controlador = new Controlador(modelo, vista);
	}
}
