package es.florida.adaev2;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Vista {

	JFrame appView;
	JTextField textField_usuario;
	JPasswordField passwordField_pass;
	JButton btn_newUser, btn_login, btn_logout;
	JTextField textField_newUser;
	JTextField textField_newPass;

	/**
	 * Método constructor de la clase Vista que llama a la inicialización de la
	 * interfaz gráfica.
	 */
	public Vista() {
		initialize();
		this.appView.setVisible(true);
	}

	/**
	 * Método que iniciliza la interfaz gráfica con todos los componentes y sus
	 * características.
	 */
	private void initialize() {
		appView = new JFrame();
		appView.setTitle("AEV2");
		appView.setBounds(100, 100, 500, 600);
		appView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		appView.getContentPane().setLayout(null);

		btn_newUser = new JButton("Añadir Nuevo Usuario");
		btn_newUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_newUser.setBounds(155, 332, 194, 21);
		appView.getContentPane().add(btn_newUser);

		textField_usuario = new JTextField();
		textField_usuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_usuario.setBounds(94, 68, 129, 21);
		appView.getContentPane().add(textField_usuario);
		textField_usuario.setColumns(10);

		JLabel lblNewLabel = new JLabel("Usuario:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(20, 72, 58, 13);
		appView.getContentPane().add(lblNewLabel);

		JLabel lblPass = new JLabel("Contraseña:");
		lblPass.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPass.setBounds(255, 72, 82, 13);
		appView.getContentPane().add(lblPass);

		btn_login = new JButton("Login");
		btn_login.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_login.setBounds(123, 136, 100, 21);
		appView.getContentPane().add(btn_login);

		passwordField_pass = new JPasswordField();
		passwordField_pass.setBounds(347, 68, 129, 21);
		appView.getContentPane().add(passwordField_pass);

		btn_logout = new JButton("Logout");
		btn_logout.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_logout.setBounds(255, 136, 100, 21);
		appView.getContentPane().add(btn_logout);

		JLabel lblNewLabel_1 = new JLabel("Agregar nuevos usuarios como administrador.");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(94, 189, 307, 28);
		appView.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Usuario:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(20, 249, 58, 28);
		appView.getContentPane().add(lblNewLabel_2);

		textField_newUser = new JTextField();
		textField_newUser.setBounds(94, 255, 129, 21);
		appView.getContentPane().add(textField_newUser);
		textField_newUser.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Iniciar sesión:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(202, 21, 87, 28);
		appView.getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Contraseña:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(255, 248, 75, 30);
		appView.getContentPane().add(lblNewLabel_4);

		textField_newPass = new JTextField();
		textField_newPass.setBounds(347, 255, 129, 21);
		appView.getContentPane().add(textField_newPass);
		textField_newPass.setColumns(10);
	}
}
