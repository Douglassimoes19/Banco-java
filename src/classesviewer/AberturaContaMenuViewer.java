package classesviewer;

import javax.swing.*;
import java.awt.*;
import classescontroller.*;

public class AberturaContaMenuViewer extends JFrame {

    private ControllerConta controller;

    public AberturaContaMenuViewer() {
    	controller = new ControllerConta();

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

        JButton btnContaPoupanca = new JButton("Conta Poupança - CP");
        btnContaPoupanca.setBounds(68, 120, 156, 38);
        btnContaPoupanca.addActionListener(e -> abrirContaPoupanca());
        panel.add(btnContaPoupanca);

        JButton btnContaCorrente = new JButton("Conta Corrente - CC");
        btnContaCorrente.setBounds(68, 169, 156, 38);
        btnContaCorrente.addActionListener(e -> abrirContaCorrente());
        panel.add(btnContaCorrente);

        JLabel lblNewLabel_1 = new JLabel("Tipo de conta:");
        lblNewLabel_1.setBounds(68, 95, 89, 14);
        panel.add(lblNewLabel_1);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(385, 257, 89, 23);
        btnVoltar.addActionListener(e -> voltar());
        panel.add(btnVoltar);
    }

    private void abrirContaPoupanca() {
        // Abrir a tela para inserir dados da Conta Poupança
        new AberturaContaPoupancaViewer().setVisible(true);
        dispose();
    }

    private void abrirContaCorrente() {
        // Abrir a tela para inserir dados da Conta Corrente
        new AberturaContaCorrenteViewer().setVisible(true);
        dispose();
    }

    private void voltar() {
        // Fechar a janela e retornar ao menu principal
        dispose();
    }
}
