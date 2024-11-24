package classesviewer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class menuviewer extends JFrame{
	
	//viewer inicial do sistema, apresenta apenas tre opções para entrar como cliente, funcionario ou sair do sistema;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//instanciar as variaveis
	JButton botao, botao2;
	
	//cria o construtor
	public menuviewer() {
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
        setSize(500, 330);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel("Menu Principal");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(166, 10, 127, 23);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Funcionário");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new LoginUsuarioViewer(2).setVisible(true);
				
			}
		});
		
		btnNewButton.setBounds(109, 116, 119, 48);
		panel.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("Sair do Programa");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);	
			} 
		});
		btnNewButton_2.setBounds(166, 237, 145, 23);
		panel.add(btnNewButton_2);
		JButton btnCliente = new JButton("Cliente");
		btnCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new LoginUsuarioViewer(1).setVisible(true);
			}
		});
		btnCliente.setBounds(265, 116, 119, 48);
		panel.add(btnCliente);
	}
	
}