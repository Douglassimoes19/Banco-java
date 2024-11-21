package classesviewer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPasswordField;

public class SenhaClienteViewer extends JFrame{
	private JPasswordField passwordField;
	public SenhaClienteViewer() {
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		setSize(250, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel("Acesso Restrito");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(60, 11, 140, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("digite a senha de cliente:");
		lblNewLabel_1.setBounds(47, 36, 205, 14);
		panel.add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(47, 57, 143, 20);
		panel.add(passwordField);
		
	}

}
