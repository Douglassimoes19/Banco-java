package classescontroller;

import classesviewer.AberturaContaMenuViewer;
import classesmodel.*;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

import javax.swing.JOptionPane;

import classesdao.ClienteDao;
import classesdao.ContaDao;


public class ControllerCliente{
    private Cliente cliente;
    private ClienteDao clienteDao;
    private ContaDao contaDao;
    private Funcionario funcionario;

    public ControllerCliente(Cliente cliente, Funcionario funcionario) {
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.contaDao = new ContaDao();
        this.clienteDao = new ClienteDao();
    }
    
 // Método que verifica se o cliente já existe e, se não, salva o cliente e cria a conta
    public String processarCadastroCliente(String cpf, String nome, LocalDate dataNascimento, String telefone, 
                                           String cep, String local, String bairro, String numero, 
                                           String cidade, String estado, String agencia, String numeroConta, 
                                           String senha, String contaTipo, double limite, LocalDate dataV) {
        try {
        	
        	int numeroCasa = Integer.parseInt(numero);
            
            // Verificar se o cliente já existe
            if (clienteDao.clienteExiste(cpf)) {
            	
            	JOptionPane.showMessageDialog(null, "Cliente ja existe!"); 
            	new AberturaContaMenuViewer( funcionario).setVisible(true);
            	
            	return "error";
               
            }else {
            	// Criar o cliente
                Cliente novoCliente = new Cliente(0, nome, cpf, dataNascimento, telefone,
                                                  new Endereco(cep, local, numeroCasa, bairro, cidade, estado),"CLIENTE",
                                                  senha);

                // Salvar o cliente e a conta no banco 
                clienteDao.inserirCliente(novoCliente);
                //contaDao.inserirContaPoupanca(contaPoupanca);
                if(contaTipo.equalsIgnoreCase("POUPANÇA")) {
                	ControllerConta conta = new ControllerConta();

                    String resultado = conta.cadastrarContaPoupanca(numeroConta,  agencia, 0,  contaTipo,  senha, novoCliente, 0.75);

                    return resultado;
                }else {
                	ControllerConta conta = new ControllerConta();
                	
                	String resultado = conta.cadastrarContaCorrente(numeroConta,  agencia, 0,  contaTipo,  senha, novoCliente, limite, dataV);

                    return resultado;
                }
                
            }

            
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Erro ao criar conta: " + ex.getMessage();
        }
    }


    // Salva ou atualiza o cliente no banco de dados
    public boolean salvarCliente() {
        if (cliente.getId() == 0) {
            return clienteDao.inserirCliente(cliente);  // Inserir novo cliente
        } else {
            return clienteDao.atualizarCliente(cliente);  // Atualizar cliente existente
        }
    }

    // Realiza o depósito para o cliente
    public boolean depositar(Cliente cliente, double valor) {
    	 Conta conta = cliente.getConta();
    	    if (conta != null) {
    	        // Chama o DAO para realizar o depósito
    	        return contaDao.depositar(conta, valor);
    	    }
    	    return false;
    }

    // Realiza o saque para o cliente
    public boolean sacar(Cliente cliente, double valor) {
        if (cliente.getConta() != null) {
            Conta conta = cliente.getConta();
            
            // Verifica se o saldo é suficiente para o saque
            if (conta.getSaldo() >= valor) {
                // Atualiza o saldo da conta na memória
                conta.setSaldo(conta.getSaldo() - valor);
                
                // Atualiza o saldo da conta no banco de dados
                return contaDao.atualizarSaldo(conta);  // atualiza saldo no banco
            }
        }
        return false;  // Retorna false se o saque não for possível
    }


    // Retorna o saldo do cliente
    public double consultarSaldo() {
        return cliente.consultarSaldo(cliente);
    }

    // Retorna o extrato do cliente
    public String consultarExtrato() {
        return cliente.consultarExtrato();
    }

    // retorna o limite do cliente
    public double consultarLimite() {
        // Verifica se a conta do cliente é uma instância de ContaCorrente
        if (cliente.getConta() instanceof ContaCorrente) {
            // Faz o cast para ContaCorrente e retorna o limite
            ContaCorrente contaCorrente = (ContaCorrente) cliente.getConta();
            return contaCorrente.getLimite();
        } else {
            // Caso não seja uma ContaCorrente, retorna 0.0
            return 0.0;
        }
    }
    // Realiza o login
    public boolean realizarLogin(String senha) {
        return cliente.login(senha);
    }

    // Realiza o logout 
    public void realizarLogout() {
        cliente.logout();
    }

    // Recupera o cliente por ID do banco de dados
    public Cliente obterClientePorId(int id) {
        return clienteDao.buscarClientePorId(id);
    }

    // Verifica se o cliente está logado
    public boolean clienteLogado() {
        return cliente != null && cliente.getId() > 0;
    }
    
    public List<Conta> buscarContasPorCliente(Cliente cliente) {
        return clienteDao.buscarContasPorCliente(cliente);
    }
    
    public ContaPoupanca consultarContaPoupanca(Cliente cliente) {
        // Chama o método do DAO para buscar a conta poupança do cliente
        return contaDao.buscarContaPoupancaPorCliente(cliente);
    }
    
    public Cliente buscarPorCpf(String cpf) {
        if (cpf == null || cpf.isEmpty()) {
            throw new IllegalArgumentException("O CPF não pode ser nulo ou vazio.");
        }

        return clienteDao.buscarPorCpf(cpf);
    }
    
    public boolean atualizarCliente(Cliente cliente) {
        
        return clienteDao.atualizarCliente(cliente); // Retorna true se a atualização foi bem-sucedida
    }
    
}
