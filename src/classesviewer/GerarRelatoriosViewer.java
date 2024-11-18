package classesviewer;

import java.awt.*;
import javax.swing.*;

public class GerarRelatoriosViewer extends JFrame {//melhorar essa viewer de relatorios
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GerarRelatoriosViewer() {
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		setSize(450, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel("Gerar Relatórios");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(161, 11, 138, 14);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Gerar Relatório Geral");
		btnNewButton.setBounds(101, 55, 255, 23);
		panel.add(btnNewButton);
	}
}
