package classesviewer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import classesmodel.Funcionario;

public class AlterarDadosMenuViewer extends JFrame {
    private static final long serialVersionUID = 1L;
    private Funcionario funcionario;

    public AlterarDadosMenuViewer(Funcionario funcionario) {
        this.funcionario = funcionario;

        // Configurações gerais da janela
        setTitle("Alterar Dados");
        setSize(500, 330);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Painel principal
        JPanel panel = new JPanel();
        panel.setLayout(null);
        getContentPane().add(panel, BorderLayout.CENTER);

        // Título
        JLabel lblTitulo = new JLabel("Alterar Dados");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblTitulo.setBounds(179, 20, 130, 20);
        panel.add(lblTitulo);

        // Botão Alterar Conta
        JButton btnAlterarConta = new JButton("Alterar Conta");
        btnAlterarConta.setBounds(10, 104, 123, 39);
        panel.add(btnAlterarConta);

        // Botão Alterar Funcionário
        JButton btnAlterarFuncionario = new JButton("Alterar Funcionário");
        btnAlterarFuncionario.setBounds(143, 104, 193, 39);
        panel.add(btnAlterarFuncionario);

        // Botão Alterar Cliente
        JButton btnAlterarCliente = new JButton("Alterar Cliente");
        btnAlterarCliente.setBounds(346, 104, 128, 39);
        panel.add(btnAlterarCliente);

        // Botão Voltar
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(10, 257, 89, 23);
        panel.add(btnVoltar);

        // Ações para os botões
        btnAlterarConta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                //JOptionPane.showMessageDialog(panel, "Tela de alteração de conta não implementada.");
            	new AlterarDadosContaViewer(funcionario).setVisible(true);
            }
        });

        btnAlterarFuncionario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                //JOptionPane.showMessageDialog(panel, "Tela de alteração de funcionário não implementada.");
            	new AlterarDadosFuncionarioViewer(funcionario).setVisible(true);
            }
        });

        btnAlterarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                //JOptionPane.showMessageDialog(panel, "Tela de alteração de cliente não implementada.");
            	new AlterarDadosClienteViewer(funcionario).setVisible(true);
            }
        });

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Fechar a tela atual e retornar à tela anterior
                dispose();
            }
        });
    }
}
