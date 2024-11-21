package classesviewer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;

public class LimiteViewer extends JFrame {
	public LimiteViewer() {
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		setSize(230, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Limite");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(79, 11, 107, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Situação:"); // disponivel ou indisponivel, se disponivel mostra o valor
		lblNewLabel_1.setBounds(10, 40, 208, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("R$:");// mostrar valor 
		
		 
		
		
		lblNewLabel_1_1.setBounds(10, 65, 208, 14);
		panel.add(lblNewLabel_1_1);
	}
}
