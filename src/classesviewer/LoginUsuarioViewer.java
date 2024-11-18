package classesviewer;

import javax.swing.*;
import java.awt.*;

public class LoginUsuarioViewer extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPasswordField passwordField;
    private JPanel panelCliente;
    private JPanel panelFuncionario;
    private JPanel panelPrincipal; // Painel principal para alternar entre cliente e funcionário

    public LoginUsuarioViewer(int tipo) {
        setTitle("Login");
        setSize(256, 213);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Inicializa o painel principal
        panelPrincipal = new JPanel(new CardLayout());
        getContentPane().add(panelPrincipal, BorderLayout.CENTER);

        // Inicializa e configura os painéis cliente e funcionário
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
        lblNewLabel.setBounds(55, 20, 144, 20);
        panel.add(lblNewLabel);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblSenha.setBounds(10, 75, 100, 14);
        panel.add(lblSenha);

        JButton btnEnviar = new JButton("Enviar");
        btnEnviar.setBounds(74, 100, 89, 23);
        panel.add(btnEnviar);

        passwordField = new JPasswordField();
        passwordField.setToolTipText("");
        passwordField.setBounds(55, 72, 144, 20);
        panel.add(passwordField);

        return panel;
    }

    private JPanel criarPanelFuncionario() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("Login Funcionario");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel.setBounds(45, 20, 150, 20);
        panel.add(lblNewLabel);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblSenha.setBounds(10, 75, 100, 14);
        panel.add(lblSenha);

        JButton btnEnviar = new JButton("Enviar");
        btnEnviar.setBounds(74, 100, 89, 23);
        panel.add(btnEnviar);

        passwordField = new JPasswordField();
        passwordField.setToolTipText("");
        passwordField.setBounds(55, 72, 144, 20);
        panel.add(passwordField);

        return panel;
    }
    
}