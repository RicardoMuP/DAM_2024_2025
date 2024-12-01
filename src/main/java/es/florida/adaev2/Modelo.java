package es.florida.adaev2;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Modelo {
	private Connection conexion;
	private boolean usuarioAutenticado = false;
	private String usuarioActual;

	/**
	 * establece conexion con la base de datos con el usuario root
	 */
	public Modelo() {
		try {

			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/population", "root", "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * método que recibe el usuario y contraseña, genera un hash de la contraseña y
	 * verifica si hay coincidencia en la tabla
	 * 
	 * @param usuario
	 * @param contrasenya
	 * @return true si hay coincidencia false en caso contrario
	 */
	public boolean verificarLogin(String usuario, String contrasenya) {
		try {
			String contrasenyaHash = generaHash(contrasenya);
			String sql = "SELECT * FROM users WHERE login = ? AND password = ?";
			PreparedStatement stmt = conexion.prepareStatement(sql);
			stmt.setString(1, usuario);
			stmt.setString(2, contrasenyaHash);
			System.out.println(contrasenyaHash);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				usuarioActual = usuario;
				usuarioAutenticado = true;
			}
			rs.close();
			stmt.close();
			return usuarioAutenticado;
		} catch (SQLException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * cierra la sesion del usuario y guarda el usuario actual como null
	 */
	public void cerrarSesion() {
		usuarioActual = null;
		try {
			if (conexion != null) {
				conexion.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * método para agregar nuevos usuarios
	 * 
	 * @param usuario     que queremos agregar
	 * @param contrasenya del usuario que queremos agregar
	 * @return true si el usuario ha sido agregado, false en caso contrario
	 */
	public boolean agregarNuevoUsuario(String usuario, String contrasenya) {
		try {

			String sqlCheck = "SELECT * FROM users WHERE login = ?";
			PreparedStatement stmtCheck = conexion.prepareStatement(sqlCheck);
			stmtCheck.setString(1, usuario);
			ResultSet rsCheck = stmtCheck.executeQuery();
			if (rsCheck.next()) {
				return false; //
			}

			String contrasenyaHash = generaHash(contrasenya);
			String sql = "INSERT INTO users (login, password) VALUES (?, ?)";
			PreparedStatement stmt = conexion.prepareStatement(sql);
			stmt.setString(1, usuario);
			stmt.setString(2, contrasenyaHash);
			int filasAfectadas = stmt.executeUpdate();

			return filasAfectadas > 0;
		} catch (SQLException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * método para generar el hash de la cadena introducida
	 * 
	 * @param password
	 * @return String que representa el hash de password en formato hexadecimal
	 * @throws NoSuchAlgorithmException
	 */
	private String generaHash(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] messageDigest = md.digest(password.getBytes());
		StringBuilder hexString = new StringBuilder();
		for (byte b : messageDigest) {
			hexString.append(Integer.toHexString(0xFF & b));
		}
		return hexString.toString();
	}

	/**
	 * método para cerrar la conexión
	 */
	public void cerrarConexion() {
		try {
			if (conexion != null) {
				conexion.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return el nombre del usuario autenticado actualmente
	 */
	public String getUsuarioAutenticado() {
		return usuarioActual;
	}
}
