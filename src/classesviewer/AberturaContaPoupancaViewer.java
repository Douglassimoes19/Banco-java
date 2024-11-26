package classesviewer;

import javax.swing.*;

import classesmodel.*;
import classescontroller.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AberturaContaPoupancaViewer extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField agencia;
    private JTextField numero_conta;
    private JTextField nome_cliente;
    private JTextField cpf_cliente;
    private JTextField data_nascimento;
    private JTextField telefone_contato;
    private JTextField cep;
    private JTextField local;
    private JTextField bairro;
    private JTextField numero;
    private JTextField cidade;
    private JTextField estado;
    private JTextField senha_cliente;
    private Funcionario funcionario;

    public AberturaContaPoupancaViewer(Funcionario funcionario) {
    	this.funcionario = funcionario;
        // Configuração geral da janela
        setTitle("Abertura de Conta Poupança");
        setSize(500, 576);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Painel principal
        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        // Título
        JLabel lblTitulo = new JLabel("Conta Poupança - CP");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblTitulo.setBounds(167, 11, 165, 19);
        panel.add(lblTitulo);

        // Campos e labels diretamente no construtor
        JLabel lblAgencia = new JLabel("Agência:");
        lblAgencia.setBounds(10, 66, 150, 14);
        panel.add(lblAgencia);

        agencia = new JTextField();
        agencia.setBounds(150, 64, 120, 20);
        panel.add(agencia);

        JLabel lblNumeroConta = new JLabel("Número da Conta:");
        lblNumeroConta.setBounds(10, 94, 150, 14);
        panel.add(lblNumeroConta);

        numero_conta = new JTextField();
        numero_conta.setBounds(150, 92, 120, 20);
        panel.add(numero_conta);

        JLabel lblNomeCliente = new JLabel("Nome do Cliente:");
        lblNomeCliente.setBounds(10, 125, 150, 14);
        panel.add(lblNomeCliente);

        nome_cliente = new JTextField();
        nome_cliente.setBounds(150, 123, 120, 20);
        panel.add(nome_cliente);

        JLabel lblCpfCliente = new JLabel("CPF do Cliente:");
        lblCpfCliente.setBounds(10, 153, 150, 14);
        panel.add(lblCpfCliente);

        cpf_cliente = new JTextField();
        cpf_cliente.setBounds(150, 151, 120, 20);
        panel.add(cpf_cliente);

        JLabel lblDataNascimento = new JLabel("Data de Nascimento:");
        lblDataNascimento.setBounds(10, 181, 150, 14);
        panel.add(lblDataNascimento);

        data_nascimento = new JTextField();
        data_nascimento.setBounds(150, 179, 120, 20);
        panel.add(data_nascimento);

        JLabel lblTelefoneContato = new JLabel("Telefone de Contato:");
        lblTelefoneContato.setBounds(10, 209, 150, 14);
        panel.add(lblTelefoneContato);

        telefone_contato = new JTextField();
        telefone_contato.setBounds(150, 207, 120, 20);
        panel.add(telefone_contato);

        // Endereço
        JLabel lblEnderecoTitulo = new JLabel("Endereço do Cliente:");
        lblEnderecoTitulo.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblEnderecoTitulo.setBounds(10, 237, 178, 14);
        panel.add(lblEnderecoTitulo);

        JLabel lblCep = new JLabel("CEP:");
        lblCep.setBounds(10, 264, 150, 14);
        panel.add(lblCep);

        cep = new JTextField();
        cep.setBounds(150, 262, 120, 20);
        panel.add(cep);

        JLabel lblLocal = new JLabel("Local:");
        lblLocal.setBounds(10, 292, 150, 14);
        panel.add(lblLocal);

        local = new JTextField();
        local.setBounds(150, 290, 120, 20);
        panel.add(local);

        JLabel lblBairro = new JLabel("Bairro:");
        lblBairro.setBounds(10, 320, 150, 14);
        panel.add(lblBairro);

        bairro = new JTextField();
        bairro.setBounds(150, 318, 120, 20);
        panel.add(bairro);

        JLabel lblNumero = new JLabel("Número:");
        lblNumero.setBounds(10, 348, 150, 14);
        panel.add(lblNumero);

        numero = new JTextField();
        numero.setBounds(150, 346, 120, 20);
        panel.add(numero);

        JLabel lblCidade = new JLabel("Cidade:");
        lblCidade.setBounds(10, 376, 150, 14);
        panel.add(lblCidade);

        cidade = new JTextField();
        cidade.setBounds(150, 374, 120, 20);
        panel.add(cidade);

        JLabel lblEstado = new JLabel("Estado:");
        lblEstado.setBounds(10, 404, 150, 14);
        panel.add(lblEstado);

        estado = new JTextField();
        estado.setBounds(150, 402, 120, 20);
        panel.add(estado);

        JLabel lblSenhaCliente = new JLabel("Senha Cliente:");
        lblSenhaCliente.setBounds(10, 428, 150, 14);
        panel.add(lblSenhaCliente);

        senha_cliente = new JTextField();
        senha_cliente.setBounds(150, 430, 120, 20);
        panel.add(senha_cliente);

        // Botões
        JButton btnEnviar = new JButton("Enviar");
        btnEnviar.addActionListener(e -> enviarDados());
        
        btnEnviar.setBounds(387, 488, 89, 41);
        panel.add(btnEnviar);
        

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	dispose();
            	new AberturaContaMenuViewer(funcionario).setVisible(true);
            }
         });
        btnVoltar.setBounds(10, 506, 89, 23);
        panel.add(btnVoltar);
    }
    
    public void enviarDados() {
    	try {
            // Capturar os dados dos campos
            String cpf = cpf_cliente.getText().trim();
            String nome = nome_cliente.getText().trim();
            String agencia = this.agencia.getText().trim();
            String numeroConta = numero_conta.getText().trim();
            String telefone = telefone_contato.getText().trim();
            String cep = this.cep.getText().trim();
            String local = this.local.getText().trim();
            String bairro = this.bairro.getText().trim();
            String numero = this.numero.getText().trim();
            String cidade = this.cidade.getText().trim();
            String estado = this.estado.getText().trim();
            String senha = senha_cliente.getText().trim();
            String dataStr = data_nascimento.getText().trim();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dataNascimento = null; 
            


            LocalDate data = dataNascimento.parse(dataStr, formatter);
            		
            // Validação básica dos campos
            if (cpf.isEmpty() || nome.isEmpty() || agencia.isEmpty() || numeroConta.isEmpty() || senha.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos obrigatórios.");
                return;
            }

            dispose();

         // Usar a Controller para processar o cadastro
            ControllerCliente controller = new ControllerCliente(null, funcionario);
            String result = controller.processarCadastroCliente(cpf, nome, data, telefone, cep, local, bairro, numero, cidade, estado, agencia, numeroConta, senha, "POUPANÇA",0,null);
            
            if(result.equals("sucesso")) {
            	// Mensagem de sucesso
                JOptionPane.showMessageDialog(null, "Conta poupança criada com sucesso!");

                // Limpar os campos
                limparCampos();
                new MenuFuncionarioViewer(funcionario).setVisible(true);
            }
            
        } catch (Exception ex) {
        	limparCampos();
            JOptionPane.showMessageDialog(null, "Erro ao criar conta: " + ex.getMessage());
        }
    
    }
    
    private void limparCampos() {
        agencia.setText("");
        numero_conta.setText("");
        nome_cliente.setText("");
        cpf_cliente.setText("");
        data_nascimento.setText("");
        telefone_contato.setText("");
        cep.setText("");
        local.setText("");
        bairro.setText("");
        numero.setText("");
        cidade.setText("");
        estado.setText("");
        senha_cliente.setText("");
    }
    
    
    
}