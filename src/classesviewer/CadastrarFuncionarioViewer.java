package classesviewer;

import java.awt.*;
import javax.swing.*;


public class CadastrarFuncionarioViewer extends JFrame {//add input dialog de cadastro feito com sucesso
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_5;
	private JTextField textField_2;
	private JTextField textField_1;
	private JTextField textField;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;

	public CadastrarFuncionarioViewer() {
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		setSize(500, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel("Cadastrar Funcionário");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(149, 11, 196, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Codigo Funcionário:");
		lblNewLabel_1.setBounds(37, 128, 145, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Cargo:");
		lblNewLabel_1_1.setBounds(37, 153, 110, 14);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Telefone:");
		lblNewLabel_1_2.setBounds(37, 265, 110, 14);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_2 = new JLabel("Digite as Informações do Funcionário:");
		lblNewLabel_2.setBounds(38, 85, 264, 14);
		panel.add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(192, 149, 110, 20);
		panel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(192, 265, 110, 20);
		panel.add(textField_2);
		
		JButton btnNewButton = new JButton("Enviar");
		btnNewButton.setBounds(385, 508, 89, 23);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Local(endereço):");
		lblNewLabel_1_2_1.setBounds(37, 293, 110, 14);
		panel.add(lblNewLabel_1_2_1);
		
		JLabel lblNewLabel_1_2_2 = new JLabel("Nº da Casa:");
		lblNewLabel_1_2_2.setBounds(37, 321, 110, 14);
		panel.add(lblNewLabel_1_2_2);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(192, 320, 110, 20);
		panel.add(textField_5);
		
		JLabel lblNewLabel_1_2_3 = new JLabel("Bairro:");
		lblNewLabel_1_2_3.setBounds(37, 383, 110, 14);
		panel.add(lblNewLabel_1_2_3);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(192, 382, 110, 20);
		panel.add(textField_6);
		
		JLabel lblNewLabel_1_2_4 = new JLabel("Cidade:");
		lblNewLabel_1_2_4.setBounds(37, 408, 110, 14);
		panel.add(lblNewLabel_1_2_4);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(192, 405, 110, 20);
		panel.add(textField_7);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(21, 508, 89, 23);
		panel.add(btnVoltar);
		
		JLabel lblNewLabel_1_2_4_1 = new JLabel("Estado:");
		lblNewLabel_1_2_4_1.setBounds(37, 439, 110, 14);
		panel.add(lblNewLabel_1_2_4_1);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(192, 436, 110, 20);
		panel.add(textField_8);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(192, 349, 110, 20);
		panel.add(textField_9);
		
		JLabel lblNewLabel_1_2_3_1 = new JLabel("CEP:");
		lblNewLabel_1_2_3_1.setBounds(37, 350, 110, 14);
		panel.add(lblNewLabel_1_2_3_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(192, 293, 110, 20);
		panel.add(textField);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(192, 125, 110, 20);
		panel.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(192, 178, 110, 20);
		panel.add(textField_4);
		
		JLabel lblNewLabel_1_2_5 = new JLabel("Nome:");
		lblNewLabel_1_2_5.setBounds(37, 181, 110, 14);
		panel.add(lblNewLabel_1_2_5);
		
		JLabel lblNewLabel_1_2_5_1 = new JLabel("CPF:");
		lblNewLabel_1_2_5_1.setBounds(37, 212, 110, 14);
		panel.add(lblNewLabel_1_2_5_1);
		
		textField_10 = new JTextField();
		textField_10.setColumns(10);
		textField_10.setBounds(192, 209, 110, 20);
		panel.add(textField_10);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("Data Nascimento");
		lblNewLabel_1_2_1_1.setBounds(37, 240, 122, 14);
		panel.add(lblNewLabel_1_2_1_1);
		
		textField_11 = new JTextField();
		textField_11.setColumns(10);
		textField_11.setBounds(192, 237, 110, 20);
		panel.add(textField_11);
		
		JLabel lblNewLabel_1_2_4_1_1 = new JLabel("Senha:");
		lblNewLabel_1_2_4_1_1.setBounds(37, 467, 110, 14);
		panel.add(lblNewLabel_1_2_4_1_1);
		
		textField_12 = new JTextField();
		textField_12.setColumns(10);
		textField_12.setBounds(192, 464, 110, 20);
		panel.add(textField_12);
	}

}
