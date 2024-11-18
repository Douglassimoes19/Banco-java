package classesviewer;

import javax.swing.*;
import java.awt.*;

public class AberturaContaPoupancaViewer extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	
	public AberturaContaPoupancaViewer() {
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel("Conta Poupança - CP");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(167, 11, 165, 19);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Agência:");
		lblNewLabel_1.setBounds(10, 66, 63, 14);
		panel.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(67, 63, 159, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(122, 91, 104, 20);
		panel.add(textField_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Número da Conta:");
		lblNewLabel_1_1.setBounds(10, 94, 106, 14);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Nome do Cliente:");
		lblNewLabel_1_1_1.setBounds(10, 125, 106, 14);
		panel.add(lblNewLabel_1_1_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(109, 122, 117, 20);
		panel.add(textField_2);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("CPF do Cliente:");
		lblNewLabel_1_1_1_1.setBounds(10, 153, 106, 14);
		panel.add(lblNewLabel_1_1_1_1);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(109, 150, 117, 20);
		panel.add(textField_3);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Data de Nascimento:");
		lblNewLabel_1_1_1_1_1.setBounds(10, 181, 123, 14);
		panel.add(lblNewLabel_1_1_1_1_1);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(132, 178, 94, 20);
		panel.add(textField_4);
		
		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Telefone de Contato:");
		lblNewLabel_1_1_1_1_1_1.setBounds(10, 209, 123, 14);
		panel.add(lblNewLabel_1_1_1_1_1_1);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(132, 206, 94, 20);
		panel.add(textField_5);
		
		JLabel lblNewLabel_1_1_1_1_1_1_1 = new JLabel("Endereço do Cliente:");
		lblNewLabel_1_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_1_1_1_1_1_1.setBounds(10, 237, 178, 14);
		panel.add(lblNewLabel_1_1_1_1_1_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("CEP:");
		lblNewLabel_2.setBounds(10, 264, 46, 14);
		panel.add(lblNewLabel_2);
		
		textField_6 = new JTextField();
		textField_6.setBounds(41, 261, 112, 20);
		panel.add(textField_6);
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(218, 258, 103, 20);
		panel.add(textField_7);
		
		JLabel lblNewLabel_2_1 = new JLabel("Local:");
		lblNewLabel_2_1.setBounds(177, 262, 46, 14);
		panel.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Bairro:");
		lblNewLabel_2_1_1.setBounds(10, 292, 63, 14);
		panel.add(lblNewLabel_2_1_1);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(59, 289, 94, 20);
		panel.add(textField_8);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Número:");
		lblNewLabel_2_1_1_1.setBounds(331, 261, 63, 14);
		panel.add(lblNewLabel_2_1_1_1);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(388, 258, 86, 20);
		panel.add(textField_9);
		
		JLabel lblNewLabel_2_1_1_2 = new JLabel("Cidade:");
		lblNewLabel_2_1_1_2.setBounds(177, 292, 63, 14);
		panel.add(lblNewLabel_2_1_1_2);
		
		textField_10 = new JTextField();
		textField_10.setColumns(10);
		textField_10.setBounds(228, 289, 92, 20);
		panel.add(textField_10);
		
		JLabel lblNewLabel_2_1_1_2_1 = new JLabel("Estado:");
		lblNewLabel_2_1_1_2_1.setBounds(10, 320, 63, 14);
		panel.add(lblNewLabel_2_1_1_2_1);
		
		textField_11 = new JTextField();
		textField_11.setColumns(10);
		textField_11.setBounds(59, 317, 94, 20);
		panel.add(textField_11);
		
		JLabel lblNewLabel_3 = new JLabel("Senha Cliente:");
		lblNewLabel_3.setBounds(10, 357, 86, 14);
		panel.add(lblNewLabel_3);
		
		textField_12 = new JTextField();
		textField_12.setBounds(102, 354, 98, 20);
		panel.add(textField_12);
		textField_12.setColumns(10);
		
		JButton btnNewButton = new JButton("Enviar");
		btnNewButton.setBounds(385, 409, 89, 41);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Voltar");
		btnNewButton_1.setBounds(7, 427, 89, 23);
		panel.add(btnNewButton_1);
		
		//mensagem de abertura de cona realizada com sucesso! ps: realocar no local certo
		//JOptionPane.showMessageDialog(null, * "Conta aberta com sucesso!");
		
	}
}
