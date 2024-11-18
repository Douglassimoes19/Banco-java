package classesviewer;

import java.awt.*;
import javax.swing.*;

public class AlterarDadosFuncionarioViewer extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;

	public AlterarDadosFuncionarioViewer() {
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		setSize(500, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
        JLabel lblNewLabel = new JLabel("Alterar Funcionário");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(188, 11, 151, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Codigo Funcionário:");
		lblNewLabel_1.setBounds(37, 128, 145, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Cargo:");
		lblNewLabel_1_1.setBounds(37, 153, 110, 14);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Telefone:");
		lblNewLabel_1_2.setBounds(37, 178, 110, 14);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_2 = new JLabel("Digite o xxxxx do Funcionário a ser alterado:");
		lblNewLabel_2.setBounds(38, 85, 264, 14);
		panel.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(192, 124, 110, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(192, 149, 110, 20);
		panel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(192, 174, 110, 20);
		panel.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(312, 82, 110, 20);
		panel.add(textField_3);
		
		JButton btnNewButton = new JButton("Enviar");
		btnNewButton.setBounds(386, 377, 89, 23);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Local(endereço):");
		lblNewLabel_1_2_1.setBounds(37, 206, 110, 14);
		panel.add(lblNewLabel_1_2_1);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(192, 202, 110, 20);
		panel.add(textField_4);
		
		JLabel lblNewLabel_1_2_2 = new JLabel("Nº da Casa:");
		lblNewLabel_1_2_2.setBounds(37, 234, 110, 14);
		panel.add(lblNewLabel_1_2_2);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(192, 230, 110, 20);
		panel.add(textField_5);
		
		JLabel lblNewLabel_1_2_3 = new JLabel("Bairro:");
		lblNewLabel_1_2_3.setBounds(37, 296, 110, 14);
		panel.add(lblNewLabel_1_2_3);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(192, 292, 110, 20);
		panel.add(textField_6);
		
		JLabel lblNewLabel_1_2_4 = new JLabel("Cidade:");
		lblNewLabel_1_2_4.setBounds(37, 321, 110, 14);
		panel.add(lblNewLabel_1_2_4);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(192, 315, 110, 20);
		panel.add(textField_7);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(21, 377, 89, 23);
		panel.add(btnVoltar);
		
		JLabel lblNewLabel_1_2_4_1 = new JLabel("Estado:");
		lblNewLabel_1_2_4_1.setBounds(37, 352, 110, 14);
		panel.add(lblNewLabel_1_2_4_1);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(192, 346, 110, 20);
		panel.add(textField_8);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(192, 259, 110, 20);
		panel.add(textField_9);
		
		JLabel lblNewLabel_1_2_3_1 = new JLabel("CEP:");
		lblNewLabel_1_2_3_1.setBounds(37, 263, 110, 14);
		panel.add(lblNewLabel_1_2_3_1);
	}
}
