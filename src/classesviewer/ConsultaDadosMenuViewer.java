package classesviewer;

import java.awt.*;
import javax.swing.*;


public class ConsultaDadosMenuViewer extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConsultaDadosMenuViewer() {
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		setSize(500, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel("Consulta Dados");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(175, 11, 160, 14);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Consultar Conta");
		btnNewButton.setBounds(10, 80, 143, 45);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Consultar Funcionário");
		btnNewButton_1.setBounds(163, 80, 165, 45);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Consultar Cliente");
		btnNewButton_2.setBounds(338, 80, 136, 45);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Voltar");
		btnNewButton_3.setBounds(385, 177, 89, 23);
		panel.add(btnNewButton_3);
	}

}
