package classesviewer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import classesmodel.*;
import classescontroller.*;

public class MenuClienteViewer extends JFrame {
	
	//nessa viewer o cliente tem acesso aos dados dele, apos selecionar a conta desejada ele podera fazer depositos e saques
	//alem de ver extratos, caso seja conta corrente ele podera ver o limite de credito, se for poupança verá a taxa de rendimento
	
	
    private Cliente cliente;
    private ControllerCliente controller;
    private Conta conta;
    JLabel lblSaldo;
    JPanel panel;

    public MenuClienteViewer(Cliente cliente,Conta conta) {
        this.cliente = cliente;
        this.conta = conta;
        this.controller = new ControllerCliente(cliente);
        cliente.setConta(conta); 

        panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(null);

        JLabel lblTitulo = new JLabel("Menu Cliente");
        lblTitulo.setBounds(120, 30, 150, 30);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
        panel.add(lblTitulo);

        // Botão Depósito
        JButton btnDeposito = new JButton("Depósito");
        btnDeposito.setBounds(50, 100, 130, 40);
        btnDeposito.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                depositar();
            }
        });
        panel.add(btnDeposito);

        // Botão Saque
        JButton btnSaque = new JButton("Saque");
        btnSaque.setBounds(220, 100, 130, 40);
        btnSaque.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sacar();
            }
        });
        panel.add(btnSaque);

        // Botão Extrato
        JButton btnExtrato = new JButton("Extrato");
        btnExtrato.setBounds(50, 160, 130, 40);
        btnExtrato.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                verExtrato();
            }
        });
        panel.add(btnExtrato);

        if(conta.getConta().equals("CORRENTE")) {
        	// Botão Consultar Limite
            JButton btnLimite = new JButton("Consultar Limite");
            btnLimite.setBounds(220, 160, 130, 40);
            btnLimite.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    consultarLimite();
                }
            });
            panel.add(btnLimite);
        }else {
        	JButton btnRendimento = new JButton("Rendimento");
            btnRendimento.setBounds(220, 160, 130, 40);
            btnRendimento.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    consultarRendimento();
                }
            });
            panel.add(btnRendimento);
        }
        

        // Botão Sair
        JButton btnSair = new JButton("Sair");
        btnSair.setBounds(150, 250, 100, 40);
        btnSair.setBackground(Color.RED);
        btnSair.setForeground(Color.WHITE);
        btnSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new menuviewer().setVisible(true); // Retorna ao menu principal
            }
        });
        panel.add(btnSair);

        // Exibir saldo disponível 
        lblSaldo = new JLabel("Saldo disponível: " + conta.consultarSaldo(cliente));
        System.out.println(cliente.getConta());
        lblSaldo.setBounds(50, 220, 250, 30);
        lblSaldo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel.add(lblSaldo);

        // exibe com o nome do cliente
        JLabel lblUsuarioNome = new JLabel("Bem-vindo, " + cliente.getNome() + "!");
        lblUsuarioNome.setBounds(50, 10, 300, 20);
        lblUsuarioNome.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(lblUsuarioNome); 
    }

    // Métodos de operação
    public void depositar() {
        // Exibe a caixa de diálogo para inserir o valor
        String valorStr = JOptionPane.showInputDialog(this, "Digite o valor a depositar:");

        // Verifica se o valor foi informado
        if (valorStr != null) {
            try {
                // Converte o valor para double
                double valor = Double.parseDouble(valorStr);

                // Verifica se o valor é válido para depósito (não pode ser negativo)
                if (valor <= 0) {
                    JOptionPane.showMessageDialog(this, "Valor inválido. O valor deve ser maior que zero.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Acessa a controller para realizar o depósito
                boolean sucesso = controller.depositar(cliente, valor);
                cliente.getConta().setSaldo(cliente.getConta().getSaldo() + valor);
 

                // Exibe mensagem de sucesso ou erro conforme o resultado
                if (sucesso) {
                	atualizarSaldo();
                    JOptionPane.showMessageDialog(this, "Depósito realizado com sucesso!", "Depósito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Não foi possível realizar o depósito.", "Erro", JOptionPane.ERROR_MESSAGE);
                }

            } catch (NumberFormatException e) {
                // Exibe mensagem de erro caso o valor informado não seja numérico
                JOptionPane.showMessageDialog(this, "Valor inválido. Por favor, insira um número.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void atualizarSaldo() {
        // Aqui você vai atualizar o JLabel ou qualquer componente que exibe o saldo do cliente
        double saldoAtual = cliente.getConta().getSaldo();  // Obtém o saldo da conta do cliente
        System.out.println(saldoAtual);
        lblSaldo.setText("Saldo: " + String.format("%.2f", saldoAtual));  // Atualiza o texto do JLabel com o saldo formatado
        panel.add(lblSaldo);
    }

    public void sacar() {
        // Exibe a caixa de diálogo para inserir o valor do saque
        String valorStr = JOptionPane.showInputDialog(MenuClienteViewer.this, "Digite o valor a sacar:");

        // Verifica se o valor foi informado
        if (valorStr != null) {
            try { 
                // Converte o valor para double
                double valor = Double.parseDouble(valorStr);

                // Verifica se o valor é válido para saque (não pode ser negativo)
                if (valor <= 0) {
                    JOptionPane.showMessageDialog(MenuClienteViewer.this, "Valor inválido. O valor deve ser maior que zero.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Verifica se o saldo é suficiente para realizar o saque
                if (cliente.getConta().getSaldo() < valor) {
                    JOptionPane.showMessageDialog(MenuClienteViewer.this, "Saldo insuficiente.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Acessa a controller para realizar o saque
                boolean sucesso = controller.sacar(cliente, valor);
                

                // Exibe mensagem de sucesso ou erro conforme o resultado
                if (sucesso) {
                	atualizarSaldo(); 
                    JOptionPane.showMessageDialog(MenuClienteViewer.this, "Saque realizado com sucesso!", "Saque", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(MenuClienteViewer.this, "Não foi possível realizar o saque.", "Erro", JOptionPane.ERROR_MESSAGE);
                }

            } catch (NumberFormatException ex) {
                // Exibe mensagem de erro caso o valor informado não seja numérico
                JOptionPane.showMessageDialog(MenuClienteViewer.this, "Valor inválido. Por favor, insira um número.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    public void verExtrato() {
        String extrato = conta.getExtrato();
        JOptionPane.showMessageDialog(this, extrato, "Extrato", JOptionPane.INFORMATION_MESSAGE);
    }

    public void consultarLimite() {
        double limite = controller.consultarLimite();
        JOptionPane.showMessageDialog(this, "Seu limite é: R$ " + limite, "Consultar Limite", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void consultarRendimento() {
    	// Verifica se o cliente tem uma conta poupança
    	
    	System.out.println(cliente.getConta().getNumero());
        ContaPoupanca contaPoupanca = controller.consultarContaPoupanca(cliente);

        if (contaPoupanca != null) {
            // Obtém o rendimento da conta poupança
            double rendimento = contaPoupanca.getTaxaRendimento();
            JOptionPane.showMessageDialog(this, "O rendimento da sua conta poupança é: " + rendimento, "Rendimento", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Caso o cliente não tenha uma conta poupança
            JOptionPane.showMessageDialog(this, "Você não possui uma conta poupança.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
