package classesviewer;

import java.awt.*;
import javax.swing.*;


public class ConsultaDadosContaViewer extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	public ConsultaDadosContaViewer() {
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel("Consultar Conta");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(165, 11, 174, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Buscar Pelo Númerro da Conta:");
		lblNewLabel_1.setBounds(24, 87, 184, 14);
		panel.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(205, 84, 151, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Resultado da Busca:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(23, 124, 118, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Tipo de Conta:");
		lblNewLabel_3.setBounds(24, 157, 315, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("Nome do Cliente:");
		lblNewLabel_3_1.setBounds(24, 179, 315, 14);
		panel.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_2 = new JLabel("CPF do Cliente:");
		lblNewLabel_3_2.setBounds(24, 204, 315, 14);
		panel.add(lblNewLabel_3_2);
		
		JLabel lblNewLabel_3_3 = new JLabel("Saldo da Conta:");
		lblNewLabel_3_3.setBounds(24, 229, 315, 14);
		panel.add(lblNewLabel_3_3);
		
		JLabel lblNewLabel_3_4 = new JLabel("Limite Disponivel:");
		lblNewLabel_3_4.setBounds(24, 254, 315, 14);
		panel.add(lblNewLabel_3_4);
		
		JLabel lblNewLabel_3_5 = new JLabel("Data de Vencimento:");
		lblNewLabel_3_5.setBounds(24, 279, 315, 14);
		panel.add(lblNewLabel_3_5);
		
		JButton btnNewButton = new JButton("Voltar");
		btnNewButton.setBounds(385, 327, 89, 23);
		panel.add(btnNewButton);
	}
}
