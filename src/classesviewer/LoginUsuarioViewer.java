package classesviewer;

import javax.swing.*;
import classescontroller.ControllerUsuario;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import classesmodel.Cliente;
import classesmodel.Funcionario;
import classesdao.usuarioDao;

public class LoginUsuarioViewer extends JFrame {

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
                String cpf = login.getText(); // Para JTextField
                String senha1 = new String(senha.getPassword()); // Para JPasswordField

                Cliente cliente = controllerUsuario.autenticarCliente(cpf, senha1);
                if (cliente != null) {
                    JOptionPane.showMessageDialog(null, "Login realizado com sucesso!");
                    dispose(); // Fecha a tela de login
                    new MenuClienteViewer(cliente).setVisible(true); // Passa o cliente para a próxima tela
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
                String cpf = loginf.getText(); // Para JTextField
                String senha1 = new String(senhaf.getPassword()); // Para JPasswordField

                Funcionario funcionario = controllerUsuario.autenticarFuncionario(cpf, senha1);
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
