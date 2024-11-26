package classesviewer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.*;

import classescontroller.ControllerEndereco;
import classescontroller.ControllerFuncionario;
import classesdao.EnderecoDao;
import classesdao.FuncionarioDao;
import classesmodel.Endereco;
import classesmodel.Funcionario;

public class CadastrarFuncionarioViewer extends JFrame {

    private static final long serialVersionUID = 1L;

    private  ControllerFuncionario controllerFuncionario;

    // Declaração dos TextFields
    private JTextField codigoFuncionarioField;
    private JTextField cargoField;
    private JTextField nomeField;
    private JTextField cpfField;
    private JTextField dataNascimentoField;
    private JTextField telefoneField;
    private JTextField enderecoField;
    private JTextField numeroCasaField;
    private JTextField bairroField;
    private JTextField cepField;
    private JTextField cidadeField;
    private JTextField estadoField;
    private JTextField senhaField;
    private Funcionario funcionario;
    private Funcionario funcionarionovo;
    private Endereco endereconovo;
    private ControllerEndereco controllerendereco;

    public CadastrarFuncionarioViewer(Funcionario funcionario) {
        this.funcionario = funcionario;
        controllerFuncionario = new ControllerFuncionario();
        controllerendereco = new ControllerEndereco();

        // Configuração geral da janela
        setTitle("Cadastro de Funcionário");
        setSize(500, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Painel principal
        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        // Título
        JLabel lblTitulo = new JLabel("Cadastro de Funcionário");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblTitulo.setBounds(150, 10, 200, 20);
        panel.add(lblTitulo);

        // Labels e campos de texto
        JLabel lblCodigoFuncionario = new JLabel("Código do Funcionário:");
        lblCodigoFuncionario.setBounds(10, 50, 150, 14);
        panel.add(lblCodigoFuncionario);

        codigoFuncionarioField = new JTextField();
        codigoFuncionarioField.setBounds(180, 48, 120, 20);
        panel.add(codigoFuncionarioField);

        JLabel lblCargo = new JLabel("Cargo:");
        lblCargo.setBounds(10, 80, 150, 14);
        panel.add(lblCargo);

        cargoField = new JTextField();
        cargoField.setBounds(180, 78, 120, 20);
        panel.add(cargoField);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(10, 110, 150, 14);
        panel.add(lblNome);

        nomeField = new JTextField();
        nomeField.setBounds(180, 108, 120, 20);
        panel.add(nomeField);

        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setBounds(10, 140, 150, 14);
        panel.add(lblCpf);

        cpfField = new JTextField();
        cpfField.setBounds(180, 138, 120, 20);
        panel.add(cpfField);

        JLabel lblDataNascimento = new JLabel("Data de Nascimento:");
        lblDataNascimento.setBounds(10, 170, 150, 14);
        panel.add(lblDataNascimento);

        dataNascimentoField = new JTextField();
        dataNascimentoField.setBounds(180, 168, 120, 20);
        panel.add(dataNascimentoField);

        JLabel lblTelefone = new JLabel("Telefone:");
        lblTelefone.setBounds(10, 200, 150, 14);
        panel.add(lblTelefone);

        telefoneField = new JTextField();
        telefoneField.setBounds(180, 198, 120, 20);
        panel.add(telefoneField);

        JLabel lblEndereco = new JLabel("Endereço:");
        lblEndereco.setBounds(10, 230, 150, 14);
        panel.add(lblEndereco);

        enderecoField = new JTextField();
        enderecoField.setBounds(180, 228, 120, 20);
        panel.add(enderecoField);

        JLabel lblNumeroCasa = new JLabel("Número:");
        lblNumeroCasa.setBounds(10, 260, 150, 14);
        panel.add(lblNumeroCasa);

        numeroCasaField = new JTextField();
        numeroCasaField.setBounds(180, 258, 120, 20);
        panel.add(numeroCasaField);

        JLabel lblBairro = new JLabel("Bairro:");
        lblBairro.setBounds(10, 290, 150, 14);
        panel.add(lblBairro);

        bairroField = new JTextField();
        bairroField.setBounds(180, 288, 120, 20);
        panel.add(bairroField);

        JLabel lblCep = new JLabel("CEP:");
        lblCep.setBounds(10, 320, 150, 14);
        panel.add(lblCep);

        cepField = new JTextField();
        cepField.setBounds(180, 318, 120, 20);
        panel.add(cepField);

        JLabel lblCidade = new JLabel("Cidade:");
        lblCidade.setBounds(10, 350, 150, 14);
        panel.add(lblCidade);

        cidadeField = new JTextField();
        cidadeField.setBounds(180, 348, 120, 20);
        panel.add(cidadeField);

        JLabel lblEstado = new JLabel("Estado:");
        lblEstado.setBounds(10, 380, 150, 14);
        panel.add(lblEstado);

        estadoField = new JTextField();
        estadoField.setBounds(180, 378, 120, 20);
        panel.add(estadoField);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(10, 410, 150, 14);
        panel.add(lblSenha);

        senhaField = new JTextField();
        senhaField.setBounds(180, 408, 120, 20);
        panel.add(senhaField);

        // Botões
        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(180, 460, 89, 23);
        btnSalvar.addActionListener(e -> salvarFuncionario());
        panel.add(btnSalvar);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(10, 500, 89, 23);
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MenuFuncionarioViewer(funcionario).setVisible(true);
            }
        });
        panel.add(btnVoltar);
    }

    private void salvarFuncionario() {
    	 // Capturar dados diretamente dos campos
        String codigoFuncionario = codigoFuncionarioField.getText().trim();
        String cargo = cargoField.getText().trim();
        String nome = nomeField.getText().trim();
        String cpf = cpfField.getText().trim();
        String telefone = telefoneField.getText().trim();
        String enderecoLocal = enderecoField.getText().trim();
        int numeroCasa = Integer.parseInt(numeroCasaField.getText().trim());
        String bairro = bairroField.getText().trim();
        String cep = cepField.getText().trim();
        String cidade = cidadeField.getText().trim();
        String estado = estadoField.getText().trim();
        String senha = senhaField.getText().trim();

        // Capturar e converter data de nascimento
        String dataNascimentoStr = dataNascimentoField.getText().trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dataNascimento = LocalDate.parse(dataNascimentoStr, formatter);

        
        Endereco endereco = new Endereco(enderecoLocal, bairro, numeroCasa, cep, cidade, estado);

        // Criar objeto Funcionario diretamente com os parâmetros, incluindo o Endereco
        Funcionario funcionarion = new Funcionario(
        0, nome, cpf, dataNascimento, telefone, endereco, "FUNCIONARIO", senha, codigoFuncionario, cargo
        );

        // Processar o cadastro
        String resultadoFuncionario = controllerFuncionario.cadastrarFuncionario(funcionarion);
        if ("sucesso".equals(resultadoFuncionario)) {
            JOptionPane.showMessageDialog(this, "Funcionário cadastrado com sucesso!");
            limparCampos();
            dispose();
            new MenuFuncionarioViewer(this.funcionario).setVisible(true);
            
        } else {
        	// Validação simples
            if (funcionario.getCodigoFuncionario().isEmpty() ||
                funcionario.getCargo().isEmpty() ||
                funcionario.getNome().isEmpty() ||
                funcionario.getCpf().isEmpty() ||
                funcionario.getSenha().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos obrigatórios.");
                return;
            }

            // Processar o cadastro
            String resultado = controllerFuncionario.cadastrarFuncionario(funcionarionovo);
            String resultado2 = controllerendereco.cadastrarEndereco(funcionarionovo, endereconovo);

            if (resultado.equals("sucesso") && resultado2.equals("sucesso")) {
                JOptionPane.showMessageDialog(this, "Funcionário cadastrado com sucesso!");
                limparCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar funcionário: " + resultado);
            }
        }
    
        
     
        } 

    private void limparCampos() {
        codigoFuncionarioField.setText("");
        cargoField.setText("");
        nomeField.setText("");
        cpfField.setText("");
        dataNascimentoField.setText("");
        telefoneField.setText("");
        enderecoField.setText("");
        numeroCasaField.setText("");
        bairroField.setText("");
        cepField.setText("");
        cidadeField.setText("");
        estadoField.setText("");
        senhaField.setText("");
    }
}
