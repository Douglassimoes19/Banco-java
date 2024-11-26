package classesviewer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import classescontroller.ControllerCliente;
import classesmodel.Cliente;
import classesmodel.Funcionario;

public class AlterarDadosClienteViewer extends JFrame {
    private static final long serialVersionUID = 1L;

    // Campos para busca e edição
    private JTextField codigoField, txtNome, txtTelefone, txtLocal, txtNumeroCasa, txtCep,
            txtBairro, txtCidade, txtEstado;

    // Cliente atualmente selecionado
    private Cliente cliente;
    private Funcionario funcionario;

    public AlterarDadosClienteViewer(Funcionario funcionario) {
    	this.funcionario = funcionario;
        // Configuração do painel principal
        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);
        setSize(500, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Título
        JLabel lblTitulo = new JLabel("Consultar e Alterar Cliente");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblTitulo.setBounds(149, 11, 250, 14);
        panel.add(lblTitulo);

        // Campo para buscar o cliente
        JLabel lblBuscarPor = new JLabel("CPF do Cliente:");
        lblBuscarPor.setBounds(23, 50, 150, 14);
        panel.add(lblBuscarPor);

        codigoField = new JTextField();
        codigoField.setBounds(160, 47, 161, 20);
        panel.add(codigoField);

        // Botão de busca
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarCliente();
            }
        });
        btnBuscar.setBounds(350, 46, 89, 23);
        panel.add(btnBuscar);

        // Labels e campos editáveis
        int yPos = 100;
        int spacing = 30;

        txtNome = addField("Nome:", panel, yPos); yPos += spacing;
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
                // Implementar a lógica para voltar ao menu principal
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
     * Método para buscar os dados do cliente pelo código.
     */
    private void buscarCliente() {
        String codigo = codigoField.getText().trim();

        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, insira o CPF do cliente.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        Cliente cliente = buscarClientePorCodigo(codigo);

        if (cliente == null) {
            JOptionPane.showMessageDialog(this, "Cliente não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            preencherCampos(cliente);
        }
    }

    /**
     * Busca o cliente no Controller.
     */
    private Cliente buscarClientePorCodigo(String cpf) {
        ControllerCliente controller = new ControllerCliente(cliente, funcionario);
        return controller.buscarPorCpf(cpf);
    }

    /**
     * Preenche os campos com os dados do cliente.
     */
    private void preencherCampos(Cliente cliente) {
        this.cliente = cliente;
        txtNome.setText(cliente.getNome());
        txtTelefone.setText(cliente.getTelefone());
        txtLocal.setText(cliente.getEndereco().getLocal());
        txtNumeroCasa.setText(String.valueOf(cliente.getEndereco().getNumeroCasa()));
        txtCep.setText(cliente.getEndereco().getCep());
        txtBairro.setText(cliente.getEndereco().getBairro());
        txtCidade.setText(cliente.getEndereco().getCidade());
        txtEstado.setText(cliente.getEndereco().getEstado());
    }

    /**
     * Método para salvar as alterações no cliente.
     */
    private void salvarAlteracoes() {
        if (cliente == null) {
            JOptionPane.showMessageDialog(this, "Nenhum cliente selecionado para alteração.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Captura os dados editados
        cliente.setNome(txtNome.getText());
        cliente.setTelefone(txtTelefone.getText());
        cliente.getEndereco().setLocal(txtLocal.getText());
        cliente.getEndereco().setNumeroCasa(Integer.parseInt(txtNumeroCasa.getText()));
        cliente.getEndereco().setCep(txtCep.getText());
        cliente.getEndereco().setBairro(txtBairro.getText());
        cliente.getEndereco().setCidade(txtCidade.getText());
        cliente.getEndereco().setEstado(txtEstado.getText());

        // Atualiza os dados pelo Controller
        ControllerCliente controller = new ControllerCliente(cliente,funcionario);
        boolean atualizado = controller.atualizarCliente(cliente);

        if (atualizado) {
            JOptionPane.showMessageDialog(this, "Cliente atualizado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar o cliente.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
