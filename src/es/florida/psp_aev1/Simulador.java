package es.florida.psp_aev1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Simulador {

	private JFrame frame;
	private JTextField textField_Primaria;
	private JTextField textField_Secundaria;
	private JTextField textField_Terciaria;
	private JTextField textField_Cuaternaria;

	/**
	 * método de entrada a la app
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Simulador window = new Simulador();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Simulador() {
		initialize();
	}

	
	/**
	 * configuración de la GUI y llamada a las simulaciones con el número de cada tipo
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.setTitle("AEV2 PSP.");
		frame.setBounds(500, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Simulador de Proteínas.");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 10, 252, 34);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Primaria.");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(20, 121, 59, 24);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Secundaria.");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(20, 177, 72, 24);
		frame.getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Terciaria.");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(21, 229, 59, 24);
		frame.getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Cuaternaria.");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(21, 281, 85, 24);
		frame.getContentPane().add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Tipo:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5.setBounds(20, 76, 45, 24);
		frame.getContentPane().add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Número:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_6.setBounds(217, 76, 72, 24);
		frame.getContentPane().add(lblNewLabel_6);

		textField_Primaria = new JTextField();
		textField_Primaria.setBounds(217, 126, 66, 19);
		frame.getContentPane().add(textField_Primaria);
		textField_Primaria.setColumns(10);

		textField_Secundaria = new JTextField();
		textField_Secundaria.setBounds(217, 182, 66, 19);
		frame.getContentPane().add(textField_Secundaria);
		textField_Secundaria.setColumns(10);

		textField_Terciaria = new JTextField();
		textField_Terciaria.setBounds(217, 234, 66, 19);
		frame.getContentPane().add(textField_Terciaria);
		textField_Terciaria.setColumns(10);

		textField_Cuaternaria = new JTextField();
		textField_Cuaternaria.setBounds(217, 286, 66, 19);
		frame.getContentPane().add(textField_Cuaternaria);
		textField_Cuaternaria.setColumns(10);

		JButton btnNewButton = new JButton("Simular");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(194, 366, 102, 34);
		frame.getContentPane().add(btnNewButton);

		btnNewButton.addActionListener(e -> {
			int primaria = Integer.parseInt(textField_Primaria.getText());
			int secundaria = Integer.parseInt(textField_Secundaria.getText());
			int terciaria = Integer.parseInt(textField_Terciaria.getText());
			int cuaternaria = Integer.parseInt(textField_Cuaternaria.getText());

			SimulacionMP.simularProteinaMP(primaria, secundaria, terciaria, cuaternaria);
			SimulacionMT.simularProteinaMT(primaria, secundaria, terciaria, cuaternaria);
		});
	}
}
