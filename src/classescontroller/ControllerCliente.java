package classescontroller;

import classesmodel.Cliente;
import classesmodel.*;

import java.util.List;

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
                return contaDao.atualizarSaldo(conta);  // Método responsável por persistir no banco
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
