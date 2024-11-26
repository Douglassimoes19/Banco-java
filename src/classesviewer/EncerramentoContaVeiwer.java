package classesviewer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import classescontroller.ControllerConta;
import classesmodel.Funcionario;

public class EncerramentoContaVeiwer extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField numConta;
	private ControllerConta controller;
	private Funcionario funcionario;
	
	public EncerramentoContaVeiwer(Funcionario func) {
		funcionario = func;
		this.controller = new ControllerConta();
		
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
		
		numConta = new JTextField();
		numConta.setBounds(232, 75, 123, 20);
		panel.add(numConta);
		numConta.setColumns(10);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	dispose();
            	enviarDados();
            }
         });
		btnEnviar.setBounds(266, 106, 89, 23);
		panel.add(btnEnviar);
		
		JButton btnNewButton_1 = new JButton("Voltar");
		btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	dispose();
            	new MenuFuncionarioViewer(funcionario).setVisible(true);
            }
         });
		btnNewButton_1.setBounds(45, 106, 89, 23);
		panel.add(btnNewButton_1);
		
        
        
	}
	
	public void enviarDados() {
        String conta = this.numConta.getText().trim();

        // Validando se o campo não está vazio
        if (conta.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, insira um número de conta válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Solicitando à Controller que exclua a conta
            boolean sucesso = controller.encerrarConta(conta);

            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Conta encerrada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                dispose(); // Fecha a janela após o sucesso
            } else {
                JOptionPane.showMessageDialog(this, "Conta não encontrada ou não pôde ser encerrada.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao encerrar conta: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
