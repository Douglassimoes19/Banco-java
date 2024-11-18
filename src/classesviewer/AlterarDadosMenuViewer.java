package classesviewer;

import java.awt.*;
import javax.swing.*;

public class AlterarDadosMenuViewer extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AlterarDadosMenuViewer() {
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		setSize(500, 330);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel("Alterar Dados");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(179, 11, 130, 14);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Alterar Conta");
		btnNewButton.setBounds(10, 104, 123, 39);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Alterar Funcionário");
		btnNewButton_1.setBounds(143, 104, 193, 39);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Alterar Cliente");
		btnNewButton_2.setBounds(346, 104, 128, 39);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Voltar");
		btnNewButton_3.setBounds(10, 257, 89, 23);
		panel.add(btnNewButton_3);
	}
	
}
