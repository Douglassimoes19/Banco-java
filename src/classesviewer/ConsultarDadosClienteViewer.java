package classesviewer;

import java.awt.*;
import javax.swing.*;

public class ConsultarDadosClienteViewer extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	public ConsultarDadosClienteViewer() {
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		setSize(500, 460);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel("Consultar Cliente");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(150, 11, 157, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Buscar Por:");
		lblNewLabel_1.setBounds(10, 52, 86, 14);
		panel.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(81, 49, 123, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Resultado da Busca:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(10, 82, 123, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Nome:");
		lblNewLabel_3.setBounds(10, 107, 194, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("CPF:");
		lblNewLabel_3_1.setBounds(10, 132, 194, 14);
		panel.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_2 = new JLabel("Data de Nascimento:");
		lblNewLabel_3_2.setBounds(10, 157, 194, 14);
		panel.add(lblNewLabel_3_2);
		
		JLabel lblNewLabel_3_3 = new JLabel("Telefone:");
		lblNewLabel_3_3.setBounds(10, 182, 194, 14);
		panel.add(lblNewLabel_3_3);
		
		JLabel lblNewLabel_3_4 = new JLabel("Local(endereço):");
		lblNewLabel_3_4.setBounds(10, 207, 194, 14);
		panel.add(lblNewLabel_3_4);
		
		JLabel lblNewLabel_3_5 = new JLabel("Nº da Casa:");
		lblNewLabel_3_5.setBounds(10, 232, 194, 14);
		panel.add(lblNewLabel_3_5);
		
		JLabel lblNewLabel_3_5_1 = new JLabel("CEP");
		lblNewLabel_3_5_1.setBounds(10, 257, 194, 14);
		panel.add(lblNewLabel_3_5_1);
		
		JLabel lblNewLabel_3_5_2 = new JLabel("Bairro:");
		lblNewLabel_3_5_2.setBounds(10, 282, 194, 14);
		panel.add(lblNewLabel_3_5_2);
		
		JLabel lblNewLabel_3_5_3 = new JLabel("Cidade:");
		lblNewLabel_3_5_3.setBounds(10, 307, 194, 14);
		panel.add(lblNewLabel_3_5_3);
		
		JLabel lblNewLabel_3_5_4 = new JLabel("Estado:");
		lblNewLabel_3_5_4.setBounds(10, 332, 194, 14);
		panel.add(lblNewLabel_3_5_4);
		
		/*
		 * JButton btnNewButton = new JButton("Enviar"); btnNewButton.setBounds(385,
		 * 371, 89, 39); panel.add(btnNewButton);
		 */
		
		JButton btnNewButton_1 = new JButton("Voltar");
		btnNewButton_1.setBounds(10, 387, 89, 23);
		panel.add(btnNewButton_1);
	}

}
