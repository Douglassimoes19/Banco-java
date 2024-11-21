package classesviewer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class SaqueViewer extends JFrame {
	private JTextField textField;
	public SaqueViewer() {
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		setSize(230, 180);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Depósito");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(77, 11, 111, 26);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Digite o Valor do Saque:");
		lblNewLabel_1.setBounds(33, 48, 171, 14);
		panel.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(55, 73, 111, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Enviar");
		btnNewButton.setBounds(65, 104, 89, 23);
		panel.add(btnNewButton);
	}
}
