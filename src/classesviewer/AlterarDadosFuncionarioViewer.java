package classesviewer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import classescontroller.ControllerFuncionario;
import classesmodel.Funcionario;

public class AlterarDadosFuncionarioViewer extends JFrame {
    private static final long serialVersionUID = 1L;

    // Campos para busca e edição
    private JTextField codigoField, txtCargo, txtNome, txtCpf, txtDataNascimento, txtTelefone,
            txtLocal, txtNumeroCasa, txtCep, txtBairro, txtCidade, txtEstado;

    // Funcionário atualmente selecionado
    private Funcionario funcionario;
    private Funcionario funcionario1;

    public AlterarDadosFuncionarioViewer(Funcionario funcionario) {
        this.funcionario = funcionario;

        // Configuração do painel principal
        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);
        setSize(500, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Título
        JLabel lblTitulo = new JLabel("Consultar e Alterar Funcionário");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblTitulo.setBounds(149, 11, 250, 14);
        panel.add(lblTitulo);

        // Campo para buscar o funcionário
        JLabel lblBuscarPor = new JLabel("Código do Funcionário:");
        lblBuscarPor.setBounds(23, 50, 150, 14);
        panel.add(lblBuscarPor);

        codigoField = new JTextField();
        codigoField.setBounds(160, 47, 161, 20);
        panel.add(codigoField);

        // Botão de busca
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarFuncionario();
            }
        });
        btnBuscar.setBounds(350, 46, 89, 23);
        panel.add(btnBuscar);

        // Labels e campos editáveis
        int yPos = 100;
        int spacing = 30;

        txtCargo = addField("Cargo:", panel, yPos); yPos += spacing;
        txtNome = addField("Nome:", panel, yPos); yPos += spacing;
        txtCpf = addField("CPF:", panel, yPos); yPos += spacing;
        txtDataNascimento = addField("Data de Nascimento:", panel, yPos); yPos += spacing;
        txtTelefone = addField("Telefone:", panel, yPos); yPos += spacing;
        txtLocal = addField("Local (Endereço):", panel, yPos); yPos += spacing;
        txtNumeroCasa = addField("Número da Casa:", panel, yPos); yPos += spacing;
        txtCep = addField("CEP:", panel, yPos); yPos += spacing;
        txtBairro = addField("Bairro:", panel, yPos); yPos += spacing;
        txtCidade = addField("Cidade:", panel, yPos); yPos += spacing;
        txtEstado = addField("Estado:", panel, yPos); yPos += spacing;

        // Botão de salvar alterações
        JButton btnSalvar = new JButton("Salvar Alterações");
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                salvarAlteracoes();
            }
        });
        btnSalvar.setBounds(275, yPos + 20, 150, 23);
        panel.add(btnSalvar);

        // Botão para voltar
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MenuFuncionarioViewer(funcionario).setVisible(true);
            }
        });
        btnVoltar.setBounds(10, yPos + 20, 89, 23);
        panel.add(btnVoltar);
    }

    /**
     * Método para adicionar campos ao painel.
     */
    private JTextField addField(String label, JPanel panel, int yPosition) {
        JLabel lbl = new JLabel(label);
        lbl.setBounds(23, yPosition, 150, 14);
        panel.add(lbl);

        JTextField field = new JTextField();
        field.setBounds(160, yPosition, 161, 20);
        panel.add(field);

        return field;
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

        Funcionario funcionario = buscarFuncionarioPorCodigo(codigo);

        if (funcionario == null) {
            JOptionPane.showMessageDialog(this, "Funcionário não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            preencherCampos(funcionario);
        }
    }

    /**
     * Busca o funcionário no Controller.
     */
    private Funcionario buscarFuncionarioPorCodigo(String codigo) {
        ControllerFuncionario controller = new ControllerFuncionario();
        return controller.buscarFuncionarioPorCodigo(codigo);
    }

    /**
     * Preenche os campos com os dados do funcionário.
     */
    private void preencherCampos(Funcionario funcionario) {
        this.funcionario1 = funcionario;
        txtCargo.setText(funcionario.getCargo());
        txtNome.setText(funcionario.getNome());
        txtCpf.setText(funcionario.getCpf());
        txtDataNascimento.setText(funcionario.getDataNascimento().toString());
        txtTelefone.setText(funcionario.getTelefone());
        txtLocal.setText(funcionario.getEndereco().getLocal());
        txtNumeroCasa.setText(String.valueOf(funcionario.getEndereco().getNumeroCasa()));
        txtCep.setText(funcionario.getEndereco().getCep());
        txtBairro.setText(funcionario.getEndereco().getBairro());
        txtCidade.setText(funcionario.getEndereco().getCidade());
        txtEstado.setText(funcionario.getEndereco().getEstado());
    }

    /**
     * Método para salvar as alterações no funcionário.
     */
    private void salvarAlteracoes() {
        if (funcionario1 == null) {
            JOptionPane.showMessageDialog(this, "Nenhum funcionário selecionado para alteração.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Captura os dados editados
        funcionario1.setCargo(txtCargo.getText());
        funcionario1.setNome(txtNome.getText());
        funcionario1.setCpf(txtCpf.getText());
        
        //funcionario1.setDataNascimento(txtDataNascimento.getText());
        funcionario1.setTelefone(txtTelefone.getText());
        funcionario1.getEndereco().setLocal(txtLocal.getText());
        funcionario1.getEndereco().setNumeroCasa(Integer.parseInt(txtNumeroCasa.getText()));
        funcionario1.getEndereco().setCep(txtCep.getText());
        funcionario1.getEndereco().setBairro(txtBairro.getText());
        funcionario1.getEndereco().setCidade(txtCidade.getText());
        funcionario1.getEndereco().setEstado(txtEstado.getText());

        // Atualiza os dados pelo Controller
        ControllerFuncionario controller = new ControllerFuncionario();
        boolean atualizado = controller.atualizarFuncionario(funcionario1);

        if (atualizado) {
            JOptionPane.showMessageDialog(this, "Funcionário atualizado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar o funcionário.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
