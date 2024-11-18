package classesviewer;

import java.awt.*;
import javax.swing.*;

public class ConsultarDadosFuncionarioVeiwer extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	public ConsultarDadosFuncionarioVeiwer() {
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		setSize(500, 510);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel("Consultar Funcionário");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(149, 11, 192, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Buscar Por:");
		lblNewLabel_1.setBounds(23, 68, 86, 14);
		panel.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(100, 65, 161, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Resultados da Busca:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(23, 107, 139, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Código Funcionario:");
		lblNewLabel_3.setBounds(23, 134, 238, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("Cargo:");
		lblNewLabel_3_1.setBounds(23, 159, 238, 14);
		panel.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_2 = new JLabel("Nome:");
		lblNewLabel_3_2.setBounds(23, 184, 238, 14);
		panel.add(lblNewLabel_3_2);
		
		JLabel lblNewLabel_3_3 = new JLabel("CPF:");
		lblNewLabel_3_3.setBounds(23, 209, 238, 14);
		panel.add(lblNewLabel_3_3);
		
		JLabel lblNewLabel_3_4 = new JLabel("Data de Nascimento:");
		lblNewLabel_3_4.setBounds(23, 234, 238, 14);
		panel.add(lblNewLabel_3_4);
		
		JLabel lblNewLabel_3_5 = new JLabel("Telefone:");
		lblNewLabel_3_5.setBounds(23, 259, 238, 14);
		panel.add(lblNewLabel_3_5);
		
		JLabel lblNewLabel_3_6 = new JLabel("Local(endereço):");
		lblNewLabel_3_6.setBounds(23, 284, 238, 14);
		panel.add(lblNewLabel_3_6);
		
		JLabel lblNewLabel_3_7 = new JLabel("Nº da Casa:");
		lblNewLabel_3_7.setBounds(23, 309, 238, 14);
		panel.add(lblNewLabel_3_7);
		
		JLabel lblNewLabel_3_8 = new JLabel("CEP:");
		lblNewLabel_3_8.setBounds(23, 334, 238, 14);
		panel.add(lblNewLabel_3_8);
		
		JLabel lblNewLabel_3_9 = new JLabel("Bairro:");
		lblNewLabel_3_9.setBounds(23, 359, 238, 14);
		panel.add(lblNewLabel_3_9);
		
		JLabel lblNewLabel_3_10 = new JLabel("Cidade:");
		lblNewLabel_3_10.setBounds(23, 384, 238, 14);
		panel.add(lblNewLabel_3_10);
		
		JLabel lblNewLabel_3_10_1 = new JLabel("Estado:");
		lblNewLabel_3_10_1.setBounds(23, 409, 46, 14);
		panel.add(lblNewLabel_3_10_1);
		
		JButton btnNewButton = new JButton("Enviar");
		btnNewButton.setBounds(385, 419, 89, 41);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Voltar");
		btnNewButton_1.setBounds(10, 437, 89, 23);
		panel.add(btnNewButton_1);
	}

}
