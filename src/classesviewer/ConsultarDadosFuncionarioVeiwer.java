package classesviewer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import classescontroller.ControllerFuncionario;
import classesmodel.Funcionario;

public class ConsultarDadosFuncionarioVeiwer extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField codigoField;
    private JLabel lblCodigoFuncionario, lblCargo, lblNome, lblCpf, lblDataNascimento, lblTelefone, lblLocal,
            lblNumeroCasa, lblCep, lblBairro, lblCidade, lblEstado;
    private Funcionario funcionario1;

    public ConsultarDadosFuncionarioVeiwer(Funcionario funcionario1) {
    	this.funcionario1 = funcionario1;
        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);
        setSize(500, 510);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lblTitulo = new JLabel("Consultar Funcionário");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblTitulo.setBounds(149, 11, 192, 14);
        panel.add(lblTitulo);

        JLabel lblBuscarPor = new JLabel("Código do Funcionário:");
        lblBuscarPor.setBounds(23, 68, 150, 14);
        panel.add(lblBuscarPor);

        codigoField = new JTextField();
        codigoField.setBounds(160, 65, 161, 20);
        panel.add(codigoField);
        codigoField.setColumns(10);

        JLabel lblResultados = new JLabel("Resultados da Busca:");
        lblResultados.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblResultados.setBounds(23, 107, 139, 14);
        panel.add(lblResultados);

        lblCodigoFuncionario = new JLabel("Código Funcionario:");
        lblCodigoFuncionario.setBounds(23, 134, 238, 14);
        panel.add(lblCodigoFuncionario);

        lblCargo = new JLabel("Cargo:");
        lblCargo.setBounds(23, 159, 238, 14);
        panel.add(lblCargo);

        lblNome = new JLabel("Nome:");
        lblNome.setBounds(23, 184, 238, 14);
        panel.add(lblNome);

        lblCpf = new JLabel("CPF:");
        lblCpf.setBounds(23, 209, 238, 14);
        panel.add(lblCpf);

        lblDataNascimento = new JLabel("Data de Nascimento:");
        lblDataNascimento.setBounds(23, 234, 238, 14);
        panel.add(lblDataNascimento);

        lblTelefone = new JLabel("Telefone:");
        lblTelefone.setBounds(23, 259, 238, 14);
        panel.add(lblTelefone);

        lblLocal = new JLabel("Local(endereço):");
        lblLocal.setBounds(23, 284, 238, 14);
        panel.add(lblLocal);

        lblNumeroCasa = new JLabel("Nº da Casa:");
        lblNumeroCasa.setBounds(23, 309, 238, 14);
        panel.add(lblNumeroCasa);

        lblCep = new JLabel("CEP:");
        lblCep.setBounds(23, 334, 238, 14);
        panel.add(lblCep);

        lblBairro = new JLabel("Bairro:");
        lblBairro.setBounds(23, 359, 238, 14);
        panel.add(lblBairro);

        lblCidade = new JLabel("Cidade:");
        lblCidade.setBounds(23, 384, 238, 14);
        panel.add(lblCidade);

        lblEstado = new JLabel("Estado:");
        lblEstado.setBounds(23, 409, 238, 14);
        panel.add(lblEstado);

        JButton btnEnviar = new JButton("Enviar");
        btnEnviar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarFuncionario();
            }
        });
        btnEnviar.setBounds(385, 419, 89, 41);
        panel.add(btnEnviar);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ConsultaDadosMenuViewer(funcionario1).setVisible(true);
            }
        });
        btnVoltar.setBounds(10, 437, 89, 23);
        panel.add(btnVoltar);
    }

    /**
     * Método para buscar os dados do funcionário pelo código.
     */
    private void buscarFuncionario() {
        String codigo = codigoField.getText().trim();

        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, insira o código do funcionário.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        System.out.println(codigo);

        
        Funcionario funcionario = buscarFuncionarioPorCodigo(codigo);

        if (funcionario == null) {
            JOptionPane.showMessageDialog(this, "Funcionário não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            preencherCampos(funcionario);
        }
    }

    
    private Funcionario buscarFuncionarioPorCodigo(String codigo) {
        
    	ControllerFuncionario controller = new ControllerFuncionario();
        
        // Faz a busca pelo funcionário e retorna
        return controller.buscarFuncionarioPorCodigo(codigo);
        
    }

    /**
     * Preenche os campos com os dados do funcionário.
     */
    private void preencherCampos(Funcionario funcionario) {
        lblCodigoFuncionario.setText("Código Funcionario: " + funcionario.getCodigoFuncionario());
        lblCargo.setText("Cargo: " + funcionario.getCargo());
        lblNome.setText("Nome: " + funcionario.getNome());
        lblCpf.setText("CPF: " + funcionario.getCpf());
        lblDataNascimento.setText("Data de Nascimento: " + funcionario.getDataNascimento());
        lblTelefone.setText("Telefone: " + funcionario.getTelefone());
        lblLocal.setText("Local(endereço): " + funcionario.getEndereco().getLocal());
        lblNumeroCasa.setText("Nº da Casa: " + funcionario.getEndereco().getNumeroCasa());
        lblCep.setText("CEP: " + funcionario.getEndereco().getCep());
        lblBairro.setText("Bairro: " + funcionario.getEndereco().getBairro());
        lblCidade.setText("Cidade: " + funcionario.getEndereco().getCidade());
        lblEstado.setText("Estado: " + funcionario.getEndereco().getEstado());
    }
}
