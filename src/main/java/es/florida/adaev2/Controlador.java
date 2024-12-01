package es.florida.adaev2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class Controlador {

	private Modelo modelo;
	private Vista vista;

	/**
	 * método constructor que recibe las instancias de modelo y vista, también
	 * gestiona los botones de login, logout y usuario nuevo y sus respectivas
	 * acciones
	 * 
	 * @param modelo
	 * @param vista
	 */
	public Controlador(Modelo modelo, Vista vista) {
		this.modelo = modelo;
		this.vista = vista;

		vista.btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usuario = vista.textField_usuario.getText();
				String contrasenya = new String(vista.passwordField_pass.getPassword());
				System.out.println(usuario);
				System.out.println(contrasenya);
				if (modelo.verificarLogin(usuario, contrasenya)) {
					System.out.println("Conectado.");
				} else {
					JOptionPane.showMessageDialog(null, "Error", "Login inválido.", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		vista.btn_logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Cierre de conexión.");
				modelo.cerrarConexion();
			}
		});

		vista.btn_newUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nuevoUsuario = vista.textField_newUser.getText();
				String nuevaContrasenya = new String(vista.textField_newPass.getText());
				boolean usuarioAgregado = modelo.agregarNuevoUsuario(nuevoUsuario, nuevaContrasenya);

				if (usuarioAgregado) {
					JOptionPane.showMessageDialog(null, "Usuario agregado con éxito", "Éxito",
							JOptionPane.INFORMATION_MESSAGE);

					vista.textField_newUser.setText("");
					vista.textField_newPass.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "Error al agregar el usuario. El usuario puede que ya exista.",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

}
