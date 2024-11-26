package classesviewer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;
import classescontroller.*;
import classesmodel.Funcionario;
import classesdao.*;

public class MenuFuncionarioViewer extends JFrame {

    private Funcionario funcionario;
    private ControllerFuncionario controller;

    public MenuFuncionarioViewer(Funcionario funcionario) {
        this.funcionario = funcionario;
        controller = new ControllerFuncionario();
        this.controller = controller;

        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);
        setSize(435, 413);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lblNewLabel = new JLabel("Menu Funcionário");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel.setBounds(10, 6, 147, 14);
        panel.add(lblNewLabel);

        // Botão: Abrir Conta
        JButton btnAbrirConta = new JButton("Abrir Conta");
        btnAbrirConta.setBounds(10, 65, 132, 36);
        btnAbrirConta.addActionListener(e -> abrirConta());
        panel.add(btnAbrirConta);

        JLabel lblNewLabel_1 = new JLabel("Opções de Conta:");
        lblNewLabel_1.setBounds(10, 41, 132, 14);
        panel.add(lblNewLabel_1);

        // Encerrar Conta
        JButton btnEncerrarConta = new JButton("Encerrar Conta");
        btnEncerrarConta.setBounds(152, 65, 132, 36);
        btnEncerrarConta.addActionListener(e -> encerrarConta());
        panel.add(btnEncerrarConta);

        JLabel lblNewLabel_1_1 = new JLabel("Gerenciar Dados:");
        lblNewLabel_1_1.setBounds(10, 109, 130, 14);
        panel.add(lblNewLabel_1_1);

        // Consultar Dados
        JButton btnConsultarDados = new JButton("Consultar Dados");
        btnConsultarDados.setBounds(10, 133, 130, 36);
        btnConsultarDados.addActionListener(e -> consultarDados());
        panel.add(btnConsultarDados);

        // Alterar Dados
        JButton btnAlterarDados = new JButton("Alterar Dados");
        btnAlterarDados.setBounds(152, 134, 130, 36);
        btnAlterarDados.addActionListener(e -> alterarDados());
        panel.add(btnAlterarDados);

        // Cadastrar Funcionários
        JButton btnCadastrarFuncionario = new JButton("Cadastrar Funcionários");
        btnCadastrarFuncionario.setBounds(10, 211, 147, 36);
        btnCadastrarFuncionario.addActionListener(e -> cadastrarFuncionario());
        panel.add(btnCadastrarFuncionario);

        JLabel lblNewLabel_1_1_1 = new JLabel("Cadastros:");
        lblNewLabel_1_1_1.setBounds(10, 187, 118, 14);
        panel.add(lblNewLabel_1_1_1);

        // Gerar Relatórios
        JButton btnGerarRelatorios = new JButton("Gerar Relatórios");
        btnGerarRelatorios.setBounds(10, 317, 157, 36);
        btnGerarRelatorios.addActionListener(e -> gerarRelatorios());
        panel.add(btnGerarRelatorios);

     // Botão Sair
        JButton btnSair = new JButton("Sair");
        btnSair.setBounds(323, 317, 88, 36);
        btnSair.setBackground(Color.RED);
        btnSair.setForeground(Color.WHITE);
        btnSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new menuviewer().setVisible(true); // Retorna ao menu principal
            }
        });
        panel.add(btnSair);
        
     // Saudação com o nome do cliente
        JLabel lblUsuarioNome = new JLabel("Bem-vindo, " + funcionario.getNome() + "!");
        lblUsuarioNome.setBounds(176, 3, 235, 20);
        lblUsuarioNome.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(lblUsuarioNome); 
    }

    // Métodos para os botões

    private void abrirConta() {
        
        String senhaInformada = JOptionPane.showInputDialog(this, 
            "Digite a senha do administrador para continuar:", 
            "Verificação de Senha", 
            JOptionPane.PLAIN_MESSAGE);

        // Verifica se o campo esta vazio
        if (senhaInformada == null || senhaInformada.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Acesso cancelado ou senha não informada.", 
                "Aviso", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Valida a senha 
        if (funcionario.getSenha().equals(senhaInformada.trim())) {
            // Senha correta, abrir a próxima janela
            dispose();
            new AberturaContaMenuViewer(funcionario).setVisible(true);
        } else {
            // Senha incorreta, exibir mensagem de erro
            JOptionPane.showMessageDialog(this, 
                "Senha incorreta. Tente novamente.", 
                "Erro", 
                JOptionPane.ERROR_MESSAGE);
        }
    }


    private void encerrarConta() {
    	String senhaInformada = JOptionPane.showInputDialog(this, 
                "Digite a senha do administrador para continuar:", 
                "Verificação de Senha", 
                JOptionPane.PLAIN_MESSAGE);

            // Verifica se o campo esta vazio
            if (senhaInformada == null || senhaInformada.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Acesso cancelado ou senha não informada.", 
                    "Aviso", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Valida a senha 
            if (funcionario.getSenha().equals(senhaInformada.trim())) {
                // Senha correta, abrir a próxima janela
                dispose();
                new EncerramentoContaVeiwer(funcionario).setVisible(true);
            } else {
                // Senha incorreta, exibir mensagem de erro
                JOptionPane.showMessageDialog(this, 
                    "Senha incorreta. Tente novamente.", 
                    "Erro", 
                    JOptionPane.ERROR_MESSAGE);
                dispose();
                new MenuFuncionarioViewer(funcionario).setVisible(true);
            }
    }

    private void consultarDados() {
        // Listar dados de funcionários
        //List<Funcionario> funcionarios = controller.listarFuncionarios();
        //StringBuilder sb = new StringBuilder("Funcionários cadastrados:\n");
        //for (Funcionario f : funcionarios) {
        //    sb.append("ID: ").append(f.getId())
         //     .append(", Nome: ").append(f.getNome())
        //      .append(", Cargo: ").append(f.getCargo())
          //    .append("\n");
        //}
        //JOptionPane.showMessageDialog(this, sb.toString());
    	String senhaInformada = JOptionPane.showInputDialog(this, 
                "Digite a senha do administrador para continuar:", 
                "Verificação de Senha", 
                JOptionPane.PLAIN_MESSAGE);

            // Verifica se o campo esta vazio
            if (senhaInformada == null || senhaInformada.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Acesso cancelado ou senha não informada.", 
                    "Aviso", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Valida a senha 
            if (funcionario.getSenha().equals(senhaInformada.trim())) {
                // Senha correta, abrir a próxima janela
                dispose();
                new ConsultaDadosMenuViewer(funcionario).setVisible(true);
            } else {
                // Senha incorreta, exibir mensagem de erro
                JOptionPane.showMessageDialog(this, 
                    "Senha incorreta. Tente novamente.", 
                    "Erro", 
                    JOptionPane.ERROR_MESSAGE);
                dispose();
                new MenuFuncionarioViewer(funcionario).setVisible(true);
            }
    	
    }

    private void alterarDados() {
        // Chamar método para atualizar dados
        JOptionPane.showMessageDialog(this, "Funcionalidade de alterar dados em manutenção! Entre em contato com o suporte.");
    }

    private void cadastrarFuncionario() {
    	String senhaInformada = JOptionPane.showInputDialog(this, 
                "Digite a senha do administrador para continuar:", 
                "Verificação de Senha", 
                JOptionPane.PLAIN_MESSAGE);

            // Verifica se o campo esta vazio
            if (senhaInformada == null || senhaInformada.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Acesso cancelado ou senha não informada.", 
                    "Aviso", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Valida a senha 
            if (funcionario.getSenha().equals(senhaInformada.trim())) {
                // Senha correta, abrir a próxima janela
                dispose();
                new CadastrarFuncionarioViewer(funcionario).setVisible(true);
            } else {
                // Senha incorreta, exibir mensagem de erro
                JOptionPane.showMessageDialog(this, 
                    "Senha incorreta. Tente novamente.", 
                    "Erro", 
                    JOptionPane.ERROR_MESSAGE);
                dispose();
                new MenuFuncionarioViewer(funcionario).setVisible(true);
            }
        
    }

    private void gerarRelatorios() {
        // Chamar método no controller para gerar relatórios
        JOptionPane.showMessageDialog(this, "Funcionalidade de gerar relatórios.");
    }

    private void sair() {
        // Fecha a aplicação
        dispose();
    }
}
