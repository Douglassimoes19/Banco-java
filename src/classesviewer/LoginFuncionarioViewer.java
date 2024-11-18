package classesviewer;

import javax.swing.*;
import java.awt.*;

public class LoginFuncionarioViewer extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPasswordField passwordField;
	public LoginFuncionarioViewer() {
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		setSize(250, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(89, 33, 46, 20);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Senha:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 75, 100, 14);
		panel.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Enviar");
		btnNewButton.setBounds(74, 100, 89, 23);
		panel.add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("");
		passwordField.setBounds(55, 72, 144, 20);
		panel.add(passwordField);
        
	}
	
}
