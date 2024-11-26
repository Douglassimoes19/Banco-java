package classesviewer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import classescontroller.ControllerConta;
import classesmodel.Conta;
import classesmodel.ContaCorrente;
import classesmodel.ContaPoupanca;
import classesmodel.Funcionario;

public class AlterarDadosContaViewer extends JFrame {
    private static final long serialVersionUID = 1L;

    private JTextField textFieldNumeroConta, textFieldTipoConta, textFieldLimiteDisponivel, textFieldDataVencimento, textFieldTaxaRendimento;
    private Funcionario funcionario;
    private ControllerConta controller;
    private Conta conta;
    private JLabel lblLimiteDisponivel, lblDataVencimento,lblTaxaRendimento;

    public AlterarDadosContaViewer(Funcionario funcionario) {
        this.funcionario = funcionario;
        this.controller = new ControllerConta();

        setTitle("Alterar Dados da Conta");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        getContentPane().add(panel, BorderLayout.CENTER);

        JLabel lblTitulo = new JLabel("Alterar Conta");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTitulo.setBounds(180, 11, 140, 20);
        panel.add(lblTitulo);

        JLabel lblNumeroConta = new JLabel("Número da Conta:");
        lblNumeroConta.setBounds(24, 60, 140, 14);
        panel.add(lblNumeroConta);

        textFieldNumeroConta = new JTextField();
        textFieldNumeroConta.setBounds(160, 57, 140, 20);
        panel.add(textFieldNumeroConta);
        textFieldNumeroConta.setColumns(10);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(320, 56, 100, 23);
        panel.add(btnBuscar);

        JLabel lblResultado = new JLabel("Resultado:");
        lblResultado.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblResultado.setBounds(24, 100, 100, 14);
        panel.add(lblResultado);

        JLabel lblTipoConta = new JLabel("Tipo de Conta:");
        lblTipoConta.setBounds(24, 130, 140, 14);
        panel.add(lblTipoConta);

        textFieldTipoConta = new JTextField();
        textFieldTipoConta.setBounds(160, 127, 140, 20);
        textFieldTipoConta.setEditable(false);
        panel.add(textFieldTipoConta);

        lblLimiteDisponivel = new JLabel("Limite Disponível:");
        lblLimiteDisponivel.setBounds(24, 160, 140, 14);
        panel.add(lblLimiteDisponivel);

        textFieldLimiteDisponivel = new JTextField();
        textFieldLimiteDisponivel.setBounds(160, 157, 140, 20);
        textFieldLimiteDisponivel.setEditable(false);
        panel.add(textFieldLimiteDisponivel);

        lblDataVencimento = new JLabel("Data de Vencimento:");
        lblDataVencimento.setBounds(24, 190, 140, 14);
        panel.add(lblDataVencimento);

        textFieldDataVencimento = new JTextField();
        textFieldDataVencimento.setBounds(160, 187, 140, 20);
        textFieldDataVencimento.setEditable(false);
        panel.add(textFieldDataVencimento);

        lblTaxaRendimento = new JLabel("Taxa de Rendimento:");
        lblTaxaRendimento.setBounds(24, 220, 140, 14);
        panel.add(lblTaxaRendimento);

        textFieldTaxaRendimento = new JTextField();
        textFieldTaxaRendimento.setBounds(160, 217, 140, 20);
        textFieldTaxaRendimento.setEditable(false);
        panel.add(textFieldTaxaRendimento);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(320, 300, 100, 23);
        btnSalvar.setEnabled(false); // Desabilitado até buscar os dados
        panel.add(btnSalvar);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(24, 300, 100, 23);
        panel.add(btnVoltar);

        // Ação do botão "Buscar"
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarConta();
                btnSalvar.setEnabled(true); // Habilita o botão salvar após carregar os dados
            }
        });

        // Ação do botão "Salvar"
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarAlteracoes();
            }
        });

        // Ação do botão "Voltar"
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
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

        conta = controller.buscarPorNumeroConta(numeroConta);
        if (conta != null) {
            textFieldTipoConta.setText(conta.getConta());

            if (conta instanceof ContaCorrente) {
                ContaCorrente cc = (ContaCorrente) conta;
                textFieldLimiteDisponivel.setText(String.valueOf(cc.getLimite()));
                textFieldDataVencimento.setText(cc.getDataVencimento().toString());
                textFieldTaxaRendimento.setText("");
                
                textFieldLimiteDisponivel.setEditable(true);
                textFieldDataVencimento.setEditable(true);

                textFieldLimiteDisponivel.setVisible(true);
                textFieldDataVencimento.setVisible(true);
                textFieldTaxaRendimento.setVisible(false);

                lblLimiteDisponivel.setVisible(true);
                lblDataVencimento.setVisible(true);
                lblTaxaRendimento.setVisible(false);
            } else if (conta instanceof ContaPoupanca) {
                ContaPoupanca cp = (ContaPoupanca) conta;
                textFieldTaxaRendimento.setText(String.valueOf(cp.getTaxaRendimento()));
                textFieldLimiteDisponivel.setText("");
                textFieldDataVencimento.setText("");

                textFieldTaxaRendimento.setEditable(true);

                textFieldLimiteDisponivel.setVisible(false);
                textFieldDataVencimento.setVisible(false);
                textFieldTaxaRendimento.setVisible(true);

                lblLimiteDisponivel.setVisible(false);
                lblDataVencimento.setVisible(false);
                lblTaxaRendimento.setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Conta não encontrada!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void salvarAlteracoes() {
        if (conta != null) {
            if (conta instanceof ContaCorrente) {
                ContaCorrente cc = (ContaCorrente) conta;
                cc.setLimite(Double.parseDouble(textFieldLimiteDisponivel.getText()));
                String data = textFieldDataVencimento.getText().trim();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate dataNascimento = null; 
                LocalDate datav = dataNascimento.parse(data, formatter);
                cc.setDataVencimento(datav);
            } else if (conta instanceof ContaPoupanca) {
                ContaPoupanca cp = (ContaPoupanca) conta;
                cp.setTaxaRendimento(Double.parseDouble(textFieldTaxaRendimento.getText()));
            }

            boolean sucesso = controller.atualizarConta(conta);
            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Conta atualizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao atualizar conta!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
