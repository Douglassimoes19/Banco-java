package classesviewer;

import javax.swing.*;
import java.awt.*;


public class MenuFuncionarioViewer extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MenuFuncionarioViewer() {
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		setSize(500, 330);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel("Menu Funcionário");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(178, 11, 147, 14);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Abrir Conta");
		btnNewButton.setBounds(10, 87, 132, 36);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Opções de Conta:");
		lblNewLabel_1.setBounds(10, 62, 118, 14);
		panel.add(lblNewLabel_1);
		
		JButton btnFecharconta = new JButton("Encerrar Conta");
		btnFecharconta.setBounds(10, 134, 132, 36);
		panel.add(btnFecharconta);
		
		JLabel lblNewLabel_1_1 = new JLabel("Gerenciar Dados:");
		lblNewLabel_1_1.setBounds(152, 62, 118, 14);
		panel.add(lblNewLabel_1_1);
		
		JButton btnNewButton_1 = new JButton("Consultar Dados");
		btnNewButton_1.setBounds(152, 87, 130, 36);
		panel.add(btnNewButton_1);
		
		JButton btnFecharconta_1 = new JButton("Alterar Dados");
		btnFecharconta_1.setBounds(152, 134, 130, 36);
		panel.add(btnFecharconta_1);
		
		JButton btnNewButton_1_1 = new JButton("Cadastrar Funcionários");
		btnNewButton_1_1.setBounds(292, 87, 182, 36);
		panel.add(btnNewButton_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Cadastros:");
		lblNewLabel_1_1_1.setBounds(292, 62, 118, 14);
		panel.add(lblNewLabel_1_1_1);
		
		JButton btnFecharconta_1_1 = new JButton("Gerar Relatórios");
		btnFecharconta_1_1.setBounds(10, 244, 157, 36);
		panel.add(btnFecharconta_1_1);
		
		JButton btnNewButton_2 = new JButton("Sair");
		btnNewButton_2.setBounds(385, 257, 89, 23);
		panel.add(btnNewButton_2);
        
		
	}
}
