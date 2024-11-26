package classesviewer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import classescontroller.ControllerCliente;
import classesmodel.Cliente;
import classesmodel.Funcionario;

public class ConsultarDadosClienteViewer extends JFrame {

    private static final long serialVersionUID = 1L;

    // Declaração dos componentes
    private JTextField cpfField;
    private JLabel nomeLabel;
    private JLabel cpfLabel;
    private JLabel dataNascimentoLabel;
    private JLabel telefoneLabel;
    private JLabel localLabel;
    private JLabel numeroCasaLabel;
    private JLabel cepLabel;
    private JLabel bairroLabel;
    private JLabel cidadeLabel;
    private JLabel estadoLabel;

    private Funcionario funcionario;

    public ConsultarDadosClienteViewer(Funcionario funcionario) {
        this.funcionario = funcionario;

        // Configurações da janela
        setTitle("Consultar Cliente");
        setSize(500, 460);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Painel principal
        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        // Título
        JLabel lblTitulo = new JLabel("Consultar Cliente");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblTitulo.setBounds(150, 11, 157, 14);
        panel.add(lblTitulo);

        // Campo para CPF
        JLabel lblBuscarPor = new JLabel("Buscar Por CPF:");
        lblBuscarPor.setBounds(10, 52, 100, 14);
        panel.add(lblBuscarPor);

        cpfField = new JTextField();
        cpfField.setBounds(120, 49, 150, 20);
        panel.add(cpfField);

        // Labels para exibir os resultados
        JLabel lblResultado = new JLabel("Resultado da Busca:");
        lblResultado.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblResultado.setBounds(10, 82, 150, 14);
        panel.add(lblResultado);

        nomeLabel = new JLabel("Nome: ");
        nomeLabel.setBounds(10, 107, 300, 14);
        panel.add(nomeLabel);

        cpfLabel = new JLabel("CPF: ");
        cpfLabel.setBounds(10, 132, 300, 14);
        panel.add(cpfLabel);

        dataNascimentoLabel = new JLabel("Data de Nascimento: ");
        dataNascimentoLabel.setBounds(10, 157, 300, 14);
        panel.add(dataNascimentoLabel);

        telefoneLabel = new JLabel("Telefone: ");
        telefoneLabel.setBounds(10, 182, 300, 14);
        panel.add(telefoneLabel);

        localLabel = new JLabel("Local(endereço): ");
        localLabel.setBounds(10, 207, 300, 14);
        panel.add(localLabel);

        numeroCasaLabel = new JLabel("Nº da Casa: ");
        numeroCasaLabel.setBounds(10, 232, 300, 14);
        panel.add(numeroCasaLabel);

        cepLabel = new JLabel("CEP: ");
        cepLabel.setBounds(10, 257, 300, 14);
        panel.add(cepLabel);

        bairroLabel = new JLabel("Bairro: ");
        bairroLabel.setBounds(10, 282, 300, 14);
        panel.add(bairroLabel);

        cidadeLabel = new JLabel("Cidade: ");
        cidadeLabel.setBounds(10, 307, 300, 14);
        panel.add(cidadeLabel);

        estadoLabel = new JLabel("Estado: ");
        estadoLabel.setBounds(10, 332, 300, 14);
        panel.add(estadoLabel);

        // Botão para buscar
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(280, 47, 100, 23);
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarCliente();
            }
        });
        panel.add(btnBuscar);

        // Botão para voltar
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(10, 387, 89, 23);
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ConsultaDadosMenuViewer(funcionario).setVisible(true);
            }
        });
        panel.add(btnVoltar);
    }

    /**
     * Método para buscar cliente pelo CPF.
     */
    private void buscarCliente() {
        try {
            // Captura o CPF do campo de texto
            String cpf = cpfField.getText().trim();

            // Valida se o CPF foi informado
            if (cpf.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, insira o CPF para buscar.");
                return;
            }

            // Usa a Controller para buscar os dados do cliente
            ControllerCliente controllerCliente = new ControllerCliente(null,funcionario);
            Cliente cliente = controllerCliente.buscarPorCpf(cpf);
 
            if (cliente != null) {
                // Atualiza os labels com os dados do cliente
                nomeLabel.setText("Nome: " + cliente.getNome());
                cpfLabel.setText("CPF: " + cliente.getCpf());
                dataNascimentoLabel.setText("Data de Nascimento: " + cliente.getDataNascimento());
                telefoneLabel.setText("Telefone: " + cliente.getTelefone());
                localLabel.setText("Local(endereço): " + cliente.getEndereco().getLocal());
                numeroCasaLabel.setText("Nº da Casa: " + cliente.getEndereco().getNumeroCasa());
                cepLabel.setText("CEP: " + cliente.getEndereco().getCep());
                bairroLabel.setText("Bairro: " + cliente.getEndereco().getBairro());
                cidadeLabel.setText("Cidade: " + cliente.getEndereco().getCidade());
                estadoLabel.setText("Estado: " + cliente.getEndereco().getEstado());
            } else {
                JOptionPane.showMessageDialog(this, "Nenhum cliente encontrado com o CPF informado.");
                limparCampos();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar cliente: " + ex.getMessage());
        }
    }

    /**
     * Método para limpar os campos de resultado.
     */
    private void limparCampos() {
        nomeLabel.setText("Nome: ");
        cpfLabel.setText("CPF: ");
        dataNascimentoLabel.setText("Data de Nascimento: ");
        telefoneLabel.setText("Telefone: ");
        localLabel.setText("Local(endereço): ");
        numeroCasaLabel.setText("Nº da Casa: ");
        cepLabel.setText("CEP: ");
        bairroLabel.setText("Bairro: ");
        cidadeLabel.setText("Cidade: ");
        estadoLabel.setText("Estado: ");
    }
}
