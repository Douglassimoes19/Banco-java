package classesviewer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import classesmodel.Funcionario;

public class ConsultaDadosMenuViewer extends JFrame {
    private static final long serialVersionUID = 1L;
    private Funcionario funcionario;

    public ConsultaDadosMenuViewer(Funcionario funcionario) {
        this.funcionario = funcionario;

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

        // Botão para consultar conta
        JButton btnNewButton = new JButton("Consultar Conta");
        btnNewButton.setBounds(10, 80, 143, 45);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                consultaConta();
            }
        });
        panel.add(btnNewButton);

        // Botão para consultar funcionário
        JButton btnNewButton_1 = new JButton("Consultar Funcionário");
        btnNewButton_1.setBounds(163, 80, 165, 45);
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                consultaFuncionario();
            }
        });
        panel.add(btnNewButton_1);

        // Botão para consultar cliente
        JButton btnNewButton_2 = new JButton("Consultar Cliente");
        btnNewButton_2.setBounds(338, 80, 136, 45);
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                consultaCliente();
            }
        });
        panel.add(btnNewButton_2);

        // Botão para voltar
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MenuFuncionarioViewer(funcionario).setVisible(true);
            }
        });
        btnVoltar.setBounds(385, 177, 89, 23);
        panel.add(btnVoltar);
    }

    
    public void consultaFuncionario() {
		/*
		 * StringBuilder dadosFuncionario = new StringBuilder();
		 * dadosFuncionario.append("Código: ").append(funcionario.getCodigoFuncionario()
		 * ).append("\n") .append("Nome: ").append(funcionario.getNome()).append("\n")
		 * .append("Cargo: ").append(funcionario.getCargo()).append("\n")
		 * .append("CPF: ").append(funcionario.getCpf()).append("\n")
		 * .append("Telefone: ").append(funcionario.getTelefone()).append("\n");
		 * 
		 * JOptionPane.showMessageDialog(this, dadosFuncionario.toString(),
		 * "Dados do Funcionário", JOptionPane.INFORMATION_MESSAGE);
		 */
    	
    	new ConsultarDadosFuncionarioVeiwer(funcionario).setVisible(true);
    }

    
    public void consultaCliente() {
        
    	new ConsultarDadosClienteViewer(funcionario).setVisible(true);
    }

    // Método para consulta de conta
    public void consultaConta() {
    	
    	new ConsultaDadosContaViewer(funcionario).setVisible(true);
    }
}
