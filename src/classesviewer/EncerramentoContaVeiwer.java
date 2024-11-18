package classesviewer;

import java.awt.*;
import javax.swing.*;

public class EncerramentoContaVeiwer extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	public EncerramentoContaVeiwer() {
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel("Encerramento de Conta");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(93, 34, 236, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Digite a Conta p/ Encerramento:");
		lblNewLabel_1.setBounds(35, 78, 187, 14);
		panel.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(232, 75, 123, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Enviar");
		btnNewButton.setBounds(266, 106, 89, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Voltar");
		btnNewButton_1.setBounds(45, 106, 89, 23);
		panel.add(btnNewButton_1);
		
        
        
	}

}
