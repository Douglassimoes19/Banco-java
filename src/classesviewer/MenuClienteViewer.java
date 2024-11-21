package classesviewer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import classesmodel.*;
import classesdao.usuarioDao;
 

public class MenuClienteViewer extends JFrame {
	private Cliente cliente;
	
	public MenuClienteViewer(Cliente cliente) { 
		this.cliente = cliente;
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		setSize(330, 330);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Menu Cliente");
		lblNewLabel.setBounds(102, 81, 100, 19);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel.add(lblNewLabel);
		
		JButton btnDepsito = new JButton("Depósito");
		btnDepsito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDepsito.setBounds(53, 138, 100, 40);
		panel.add(btnDepsito);
		
		JButton btnSaque = new JButton("Saque");
		btnSaque.setBounds(163, 138, 100, 40);
		panel.add(btnSaque);
		
		JButton btnExtrato = new JButton("Extrato");
		btnExtrato.setBounds(53, 188, 100, 40);
		panel.add(btnExtrato);
		
		JButton btnConsultarLimite = new JButton("Sair ");
		btnConsultarLimite.setForeground(Color.WHITE);
		btnConsultarLimite.setBackground(new Color(204, 51, 0));
		btnConsultarLimite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnConsultarLimite.setBounds(239, 251, 67, 32);
		panel.add(btnConsultarLimite);
		 
		
		JLabel lblNewLabel_1 = new JLabel("Saldo disponivel: ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(39, 33, 128, 13);
		panel.add(lblNewLabel_1);
		
		String nome = cliente.getNome();
		
		JLabel lblNewLabel_1_1 = new JLabel("Bem vindo: " + nome);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(39, 10, 267, 13);
		panel.add(lblNewLabel_1_1);
		
		JButton btnConsultarLimite_1 = new JButton("Meu Limite");
		btnConsultarLimite_1.setBounds(163, 188, 100, 40);
		panel.add(btnConsultarLimite_1);
	}
}
