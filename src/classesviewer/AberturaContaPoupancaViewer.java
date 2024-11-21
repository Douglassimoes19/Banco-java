package classesviewer;

import javax.swing.*;
import java.awt.*;

public class AberturaContaPoupancaViewer extends JFrame {

	/**
	 * 
	 */
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
	
	public AberturaContaPoupancaViewer() {
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel("Conta Poupança - CP");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(167, 11, 165, 19);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Agência:");
		lblNewLabel_1.setBounds(10, 66, 63, 14);
		panel.add(lblNewLabel_1);
		
		agencia = new JTextField();
		agencia.setBounds(67, 64, 159, 20);
		panel.add(agencia);
		agencia.setColumns(10);
		
		numero_conta = new JTextField();
		numero_conta.setColumns(10);
		numero_conta.setBounds(122, 91, 104, 20);
		panel.add(numero_conta);
		
		JLabel lblNewLabel_1_1 = new JLabel("Número da Conta:");
		lblNewLabel_1_1.setBounds(10, 94, 106, 14);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Nome do Cliente:");
		lblNewLabel_1_1_1.setBounds(10, 125, 106, 14);
		panel.add(lblNewLabel_1_1_1);
		
		nome_cliente = new JTextField();
		nome_cliente.setColumns(10);
		nome_cliente.setBounds(109, 122, 117, 20);
		panel.add(nome_cliente);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("CPF do Cliente:");
		lblNewLabel_1_1_1_1.setBounds(10, 153, 106, 14);
		panel.add(lblNewLabel_1_1_1_1);
		
		cpf_cliente = new JTextField();
		cpf_cliente.setColumns(10);
		cpf_cliente.setBounds(109, 150, 117, 20);
		panel.add(cpf_cliente);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Data de Nascimento:");
		lblNewLabel_1_1_1_1_1.setBounds(10, 181, 123, 14);
		panel.add(lblNewLabel_1_1_1_1_1);
		
		data_nascimento = new JTextField();
		data_nascimento.setColumns(10);
		data_nascimento.setBounds(132, 178, 94, 20);
		panel.add(data_nascimento);
		
		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Telefone de Contato:");
		lblNewLabel_1_1_1_1_1_1.setBounds(10, 209, 123, 14);
		panel.add(lblNewLabel_1_1_1_1_1_1);
		
		telefone_contato = new JTextField();
		telefone_contato.setColumns(10);
		telefone_contato.setBounds(132, 206, 94, 20);
		panel.add(telefone_contato);
		
		JLabel lblNewLabel_1_1_1_1_1_1_1 = new JLabel("Endereço do Cliente:");
		lblNewLabel_1_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_1_1_1_1_1_1.setBounds(10, 237, 178, 14);
		panel.add(lblNewLabel_1_1_1_1_1_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("CEP:");
		lblNewLabel_2.setBounds(10, 264, 46, 14);
		panel.add(lblNewLabel_2);
		
		cep = new JTextField();
		cep.setBounds(41, 261, 112, 20);
		panel.add(cep);
		cep.setColumns(10);
		
		local = new JTextField();
		local.setColumns(10);
		local.setBounds(218, 258, 103, 20);
		panel.add(local);
		
		JLabel lblNewLabel_2_1 = new JLabel("Local:");
		lblNewLabel_2_1.setBounds(177, 262, 46, 14);
		panel.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Bairro:");
		lblNewLabel_2_1_1.setBounds(10, 292, 63, 14);
		panel.add(lblNewLabel_2_1_1);
		
		bairro = new JTextField();
		bairro.setColumns(10);
		bairro.setBounds(59, 289, 94, 20);
		panel.add(bairro);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Número:");
		lblNewLabel_2_1_1_1.setBounds(331, 261, 63, 14);
		panel.add(lblNewLabel_2_1_1_1);
		
		numero = new JTextField();
		numero.setColumns(10);
		numero.setBounds(388, 258, 86, 20);
		panel.add(numero);
		
		JLabel lblNewLabel_2_1_1_2 = new JLabel("Cidade:");
		lblNewLabel_2_1_1_2.setBounds(177, 292, 63, 14);
		panel.add(lblNewLabel_2_1_1_2);
		
		cidade = new JTextField();
		cidade.setColumns(10);
		cidade.setBounds(228, 289, 92, 20);
		panel.add(cidade);
		
		JLabel lblNewLabel_2_1_1_2_1 = new JLabel("Estado:");
		lblNewLabel_2_1_1_2_1.setBounds(10, 320, 63, 14);
		panel.add(lblNewLabel_2_1_1_2_1);
		
		estado = new JTextField();
		estado.setColumns(10);
		estado.setBounds(59, 317, 94, 20);
		panel.add(estado);
		
		JLabel lblNewLabel_3 = new JLabel("Senha Cliente:");
		lblNewLabel_3.setBounds(10, 357, 86, 14);
		panel.add(lblNewLabel_3);
		
		senha_cliente = new JTextField();
		senha_cliente.setBounds(102, 354, 98, 20);
		panel.add(senha_cliente);
		senha_cliente.setColumns(10);
		
		JButton btnNewButton = new JButton("Enviar");
		btnNewButton.setBounds(385, 409, 89, 41);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Voltar");
		btnNewButton_1.setBounds(7, 427, 89, 23);
		panel.add(btnNewButton_1);
		
		//mensagem de abertura de cona realizada com sucesso! ps: realocar no local certo
		//JOptionPane.showMessageDialog(null, * "Conta aberta com sucesso!");
		
	}
}
