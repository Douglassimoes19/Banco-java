package classesviewer;

import javax.swing.*;

import classescontroller.ControllerCliente;
import classescontroller.ControllerUsuario;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import classesmodel.Cliente;
import classesmodel.Conta;
import classesmodel.Funcionario;
import classesdao.usuarioDao;

public class LoginUsuarioViewer extends JFrame {
	
	//essa viwer de login onde o usuario apos escolher a opção nos botões ira colocar suas credenciais
	//para entrar no sistema, o construtor inicia apenas pegando o tipo "1" para cliente "2" para funcionario
	//o painel alternará de acordo com o tipo. após a veirficação ele irá passar para proxima viewer, menuCliente
	//ou menuFuncionario; ps: caso seja a opção cliente ele verifica a conta do cliente antes de passar para a proxima viewer

    private static final long serialVersionUID = 1L;

    private JPasswordField senha;
    private JPasswordField senhaf;
    private JPanel panelCliente;
    private JPanel panelFuncionario;
    private JPanel panelPrincipal; // Painel principal para alternar entre cliente e funcionário
    private JTextField login;
    private JTextField loginf;
    private ControllerUsuario controllerUsuario;

    public LoginUsuarioViewer(int tipo) { 
        this.controllerUsuario = new ControllerUsuario(new usuarioDao());

        setTitle("Login");
        setSize(295, 320);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Inicia o painel principal
        panelPrincipal = new JPanel(new CardLayout());
        getContentPane().add(panelPrincipal, BorderLayout.CENTER);

        // configura os painéis cliente e funcionário
        panelCliente = criarPanelCliente();
        panelFuncionario = criarPanelFuncionario();

        // Adiciona ambos ao painel principal
        panelPrincipal.add(panelCliente, "Cliente");
        panelPrincipal.add(panelFuncionario, "Funcionario");

        // Alterna para o painel correto com base no tipo
        CardLayout layout = (CardLayout) panelPrincipal.getLayout();
        if (tipo == 1) {
            layout.show(panelPrincipal, "Cliente");
        } else {
            layout.show(panelPrincipal, "Funcionario");
        }
    }

    private JPanel criarPanelCliente() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("Login Cliente");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel.setBounds(68, 10, 150, 20);
        panel.add(lblNewLabel);

        JLabel lblSenha = new JLabel("Senha");
        lblSenha.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblSenha.setBounds(119, 125, 45, 14);
        panel.add(lblSenha);

        senha = new JPasswordField();
        senha.setBounds(68, 149, 144, 20);
        panel.add(senha);

        JLabel lblCpf = new JLabel("Login");
        lblCpf.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblCpf.setBounds(119, 71, 45, 14);
        panel.add(lblCpf);

        login = new JTextField();
        login.setBounds(68, 95, 144, 20);
        panel.add(login);

        JButton btnEnviar = new JButton("Entrar");
        btnEnviar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	//apos apertar esse botão, fará a autenticaçaõ do usuario e conferira as contas para dar opção de escolha caso 
            	// exista mais de uma conta para um mesmo usuario;
            	
            	String cpf = login.getText(); 
                String senha1 = new String(senha.getPassword()); 
                Conta contaSelecionada;
                
                Cliente cliente = controllerUsuario.autenticarCliente(cpf, senha1);
                 
                ControllerCliente controller =  new ControllerCliente(cliente, null);

                if (cliente != null) {
                    JOptionPane.showMessageDialog(null, "Login realizado com sucesso!");

                    // Recupera as contas associadas ao cliente;
                    List<Conta> contas = controller.buscarContasPorCliente(cliente);
                    System.out.println(contas);
                    if (contas == null || contas.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Nenhuma conta encontrada para este cliente.");
                        return;
                    }

                    

                    if (contas.size() > 1) {
                        // Exibe um diálogo para o cliente escolher uma conta
                        String[] opcoes = contas.stream()
                                .map(c -> c.getNumero() + " (" + c.getConta() + ")")
                                .toArray(String[]::new);
                        String escolha = (String) JOptionPane.showInputDialog(
                                null, 
                                "Selecione a conta:",  
                                "Escolha de Conta", 
                                JOptionPane.QUESTION_MESSAGE, 
                                null, 
                                opcoes, 
                                opcoes[0]);

                        if (escolha == null) {
                            JOptionPane.showMessageDialog(null, "Você deve selecionar uma conta.");
                            return;
                        }

                        // Identifica a conta selecionada
                        int indiceEscolhido = Arrays.asList(opcoes).indexOf(escolha);
                        contaSelecionada = contas.get(indiceEscolhido);
                    } else {
                        // Se há apenas uma conta, seleciona automaticamente
                        contaSelecionada = contas.get(0);
                    }

                    // Passa o cliente e a conta para a próxima tela
                    dispose();//some com a tela antes de enviar para a proxima 
                    new MenuClienteViewer(cliente, contaSelecionada).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "CPF ou senha incorretos!");
                    dispose();
                    new menuviewer().setVisible(true);
                }
            }
        });

        btnEnviar.setBounds(86, 194, 109, 40);
        panel.add(btnEnviar);

        return panel;
    }

    private JPanel criarPanelFuncionario() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("Login Funcionario");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel.setBounds(68, 10, 150, 20);
        panel.add(lblNewLabel);

        JLabel lblSenha = new JLabel("Senha");
        lblSenha.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblSenha.setBounds(119, 125, 45, 14);
        panel.add(lblSenha);

        senhaf = new JPasswordField();
        senhaf.setToolTipText("");
        senhaf.setBounds(68, 149, 144, 20);
        panel.add(senhaf);

        JLabel lblCpf = new JLabel("Login");
        lblCpf.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblCpf.setBounds(119, 71, 45, 14);
        panel.add(lblCpf);

        loginf = new JTextField();
        loginf.setToolTipText("");
        loginf.setBounds(68, 95, 144, 20);
        panel.add(loginf);

        JButton btnEnviar = new JButton("Entrar");
        btnEnviar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cpf = loginf.getText(); 
                String senha1 = new String(senhaf.getPassword()); 

                Funcionario funcionario = controllerUsuario.autenticarFuncionario(cpf, senha1);
                //System.out.println(funcionario.getNome());
                if (funcionario != null) {
                    JOptionPane.showMessageDialog(null, "Login realizado com sucesso!");
                    dispose(); // Fecha a tela de login
                    new MenuFuncionarioViewer(funcionario).setVisible(true); // Passa o funcionário para a próxima tela
                } else {
                    JOptionPane.showMessageDialog(null, "CPF ou senha incorretos!");
                    dispose();
                    new menuviewer().setVisible(true);
                }
            }
        });

        btnEnviar.setBounds(86, 194, 109, 40);
        panel.add(btnEnviar);

        return panel;
    }
}
