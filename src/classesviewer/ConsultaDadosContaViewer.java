package classesviewer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import classescontroller.ControllerConta;
import classesmodel.Conta;
import classesmodel.ContaCorrente;
import classesmodel.ContaPoupanca;
import classesmodel.Funcionario;

public class ConsultaDadosContaViewer extends JFrame {
    private static final long serialVersionUID = 1L;

    private JTextField textFieldNumeroConta;
    private Funcionario funcionario;
    private JLabel lblTipoConta, lblNomeCliente, lblCpfCliente, lblSaldoConta, lblLimiteDisponivel, lblDataVencimento, lblTaxaRendimento;
    private ControllerConta controller;

    public ConsultaDadosContaViewer(Funcionario funcionario) {
        this.funcionario = funcionario;
        this.controller = new ControllerConta();

        setTitle("Consultar Dados da Conta");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        getContentPane().add(panel, BorderLayout.CENTER);

        JLabel lblTitulo = new JLabel("Consultar Conta");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTitulo.setBounds(165, 11, 174, 14);
        panel.add(lblTitulo);

        JLabel lblBuscarConta = new JLabel("Buscar Pelo Número da Conta:");
        lblBuscarConta.setBounds(24, 87, 184, 14);
        panel.add(lblBuscarConta);

        textFieldNumeroConta = new JTextField();
        textFieldNumeroConta.setBounds(205, 84, 151, 20);
        panel.add(textFieldNumeroConta);
        textFieldNumeroConta.setColumns(10);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(370, 83, 89, 23);
        panel.add(btnBuscar);

        JLabel lblResultadoBusca = new JLabel("Resultado da Busca:");
        lblResultadoBusca.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblResultadoBusca.setBounds(23, 124, 118, 14);
        panel.add(lblResultadoBusca);

        lblTipoConta = new JLabel("Tipo de Conta:");
        lblTipoConta.setBounds(24, 157, 315, 14);
        panel.add(lblTipoConta);

        lblNomeCliente = new JLabel("Nome do Cliente:");
        lblNomeCliente.setBounds(24, 179, 315, 14);
        panel.add(lblNomeCliente);

        lblCpfCliente = new JLabel("CPF do Cliente:");
        lblCpfCliente.setBounds(24, 204, 315, 14);
        panel.add(lblCpfCliente);

        lblSaldoConta = new JLabel("Saldo da Conta:");
        lblSaldoConta.setBounds(24, 229, 315, 14);
        panel.add(lblSaldoConta);

        lblLimiteDisponivel = new JLabel("Limite Disponível:");
        lblLimiteDisponivel.setBounds(24, 254, 315, 14);
        lblLimiteDisponivel.setVisible(false); // Inicialmente oculto
        panel.add(lblLimiteDisponivel);

        lblDataVencimento = new JLabel("Data de Vencimento:");
        lblDataVencimento.setBounds(24, 279, 315, 14);
        lblDataVencimento.setVisible(false); // Inicialmente oculto
        panel.add(lblDataVencimento);

        lblTaxaRendimento = new JLabel("Taxa de Rendimento:");
        lblTaxaRendimento.setBounds(24, 304, 315, 14);
        lblTaxaRendimento.setVisible(false); // Inicialmente oculto
        panel.add(lblTaxaRendimento);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(385, 327, 89, 23);
        panel.add(btnVoltar);

        // Ação para o botão "Buscar"
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarConta();
            }
        });

        // Ação para o botão "Voltar"
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a janela atual
                new MenuFuncionarioViewer(funcionario).setVisible(true);
            }
        });
    }

    private void buscarConta() {
        String numeroConta = textFieldNumeroConta.getText();

        if (numeroConta.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, insira o número da conta.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Conta conta = controller.buscarPorNumeroConta(numeroConta);
        if (conta != null) {
            // Atualiza os labels com os dados da conta
            lblTipoConta.setText("Tipo de Conta: " + conta.getConta());
            lblNomeCliente.setText("Nome do Cliente: " + conta.getCliente().getNome());
            lblCpfCliente.setText("CPF do Cliente: " + conta.getCliente().getCpf());
            lblSaldoConta.setText("Saldo da Conta: R$ " + conta.getSaldo());

            if (conta instanceof ContaCorrente) {
                ContaCorrente contaCorrente = (ContaCorrente) conta;

                // Exibe apenas campos relevantes para Conta Corrente
                lblLimiteDisponivel.setVisible(true);
                lblDataVencimento.setVisible(true);
                lblTaxaRendimento.setVisible(false); // Oculta Taxa de Rendimento

                lblLimiteDisponivel.setText("Limite Disponível: R$ " + contaCorrente.getLimite());
                lblDataVencimento.setText("Data de Vencimento: " + contaCorrente.getDataVencimento());
            } else if (conta instanceof ContaPoupanca) {
                ContaPoupanca contaPoupanca = (ContaPoupanca) conta;

                // Exibe apenas campos relevantes para Conta Poupança
                lblTaxaRendimento.setVisible(true);
                lblLimiteDisponivel.setVisible(false); // Oculta Limite
                lblDataVencimento.setVisible(false); // Oculta Data de Vencimento

                lblTaxaRendimento.setText("Taxa de Rendimento: " + contaPoupanca.getTaxaRendimento());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Conta não encontrada!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
