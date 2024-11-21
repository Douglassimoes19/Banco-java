package classesviewer;

import javax.swing.*;
import java.awt.*;

public class AberturaContaMenuViewer extends JFrame{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AberturaContaMenuViewer() {
		
        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);
        setSize(500, 330);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel lblNewLabel = new JLabel("Abertura de Conta");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel.setBounds(175, 11, 151, 14);
        panel.add(lblNewLabel);
        
        JButton btnNewButton = new JButton("Conta Poupança - CP");
        btnNewButton.setBounds(68, 120, 156, 38);
        panel.add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("Conta Corrente - CC");
        btnNewButton_1.setBounds(68, 169, 156, 38);
        panel.add(btnNewButton_1);
        
        JLabel lblNewLabel_1 = new JLabel("Tipo de conta:");
        lblNewLabel_1.setBounds(68, 95, 89, 14);
        panel.add(lblNewLabel_1);
        
        JButton btnNewButton_2 = new JButton("Voltar");
        btnNewButton_2.setBounds(385, 257, 89, 23);
        panel.add(btnNewButton_2);
        
	}

}
