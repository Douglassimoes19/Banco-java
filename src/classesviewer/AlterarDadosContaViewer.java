package classesviewer;

import javax.swing.*;
import java.awt.*;

public class AlterarDadosContaViewer extends JFrame{//alterar essa viewer pois ele deve consultar antes de ter acesso aos campos do cliente
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	public AlterarDadosContaViewer() {
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		setSize(500, 330);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel("Alterar Conta");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(188, 11, 151, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Tipo de Conta:");
		lblNewLabel_1.setBounds(37, 128, 110, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Limite Disponível:");
		lblNewLabel_1_1.setBounds(37, 153, 110, 14);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Data Vencimento:");
		lblNewLabel_1_2.setBounds(37, 178, 110, 14);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_2 = new JLabel("Digite o numero da conta a ser alterada:");
		lblNewLabel_2.setBounds(38, 85, 228, 14);
		panel.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(141, 125, 110, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(141, 150, 110, 20);
		panel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(141, 175, 110, 20);
		panel.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(274, 82, 110, 20);
		panel.add(textField_3);
		
		JButton btnNewButton = new JButton("Enviar");
		btnNewButton.setBounds(385, 257, 89, 23);
		panel.add(btnNewButton);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(37, 257, 89, 23);
		panel.add(btnVoltar);
	}

}
