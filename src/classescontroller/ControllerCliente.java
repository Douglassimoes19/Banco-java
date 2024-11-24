package classescontroller;

import classesmodel.Cliente;
import classesmodel.*;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

import classesdao.ClienteDao;
import classesdao.ContaDao;


public class ControllerCliente{
    private Cliente cliente;
    private ClienteDao clienteDao;
    private ContaDao contaDao;

    public ControllerCliente(Cliente cliente) {
        this.cliente = cliente;
        this.contaDao = new ContaDao();
        this.clienteDao = new ClienteDao();
    }
    
 // Método que verifica se o cliente já existe e, se não, salva o cliente e cria a conta
    public String processarCadastroCliente(String cpf, String nome, Date dataNascimento, String telefone, 
                                           String cep, String local, String bairro, String numero, 
                                           String cidade, String estado, String agencia, String numeroConta, 
                                           String senha) {
        try {
        	
        	int numeroCasa = Integer.parseInt(numero);
            
            // Verificar se o cliente já existe
            if (clienteDao.clienteExiste(cpf)) {
                return "Cliente já possui uma conta cadastrada.";
            }

            // Criar o cliente
            Cliente novoCliente = new Cliente(0, nome, cpf, dataNascimento, telefone,
                                              new Endereco(cep, local, numeroCasa, bairro, cidade, estado),"CLIENTE",
                                              senha);

            // Salvar o cliente e a conta no banco 
            clienteDao.inserirCliente(novoCliente);
            //contaDao.inserirContaPoupanca(contaPoupanca);
            String tipoConta = "POUPANÇA";
            ControllerConta conta = new ControllerConta();

            String resultado = conta.cadastrarContaPoupanca(numeroConta,  agencia, 0,  tipoConta,  senha, novoCliente, 0.75);

            return resultado;
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
}
