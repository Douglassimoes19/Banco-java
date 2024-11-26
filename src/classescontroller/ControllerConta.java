package classescontroller;

import java.sql.Date;
import java.time.LocalDate;

import classesdao.ContaDao;
import classesmodel.Cliente;
import classesmodel.Conta;
import classesmodel.ContaCorrente;
import classesmodel.ContaPoupanca;

public class ControllerConta {

	    private ContaDao contaDAO;

	    public ControllerConta() {
	        this.contaDAO = new ContaDao();
	    }

	    public String cadastrarContaPoupanca(String numero, String agencia, double saldo, String conta,String senha, Cliente cliente, double taxaRendimento) {
	        try {
	            validarDadosContaPoupanca(numero, taxaRendimento);
	            
	            //contaDAO.inserirContaPoupanca(numero, agencia, saldo, conta, cliente, taxaRendimento);
	            
	            boolean result = contaDAO.inserirContaPoupanca(numero, agencia, saldo, conta, cliente, taxaRendimento);
	            
	            if(result) {
	            	return "sucesso";
	            }
	        } catch (IllegalArgumentException e) {
	            throw new RuntimeException("Erro ao cadastrar conta poupança: " + e.getMessage());
	        }
	        
	        return "Erro ao efetuar cadastro";
	    }

	    public String cadastrarContaCorrente(String numero, String agencia, double saldo, String conta,String senha, Cliente cliente, double limite, LocalDate dataVencimento) {
	        try {
	            validarDadosContaCorrente(numero, limite, dataVencimento);
	            boolean result = contaDAO.inserirContaCorrente(numero,  agencia, saldo,  conta,  senha, cliente, limite, dataVencimento);
	            if(result) {
	            	return "sucesso";
	            }
	            
	        } catch (IllegalArgumentException e) {
	            throw new RuntimeException("Erro ao cadastrar conta corrente: " + e.getMessage());
	        }
	        return "Erro ao efetuar cadastro";
	    }

	    private void validarDadosContaPoupanca(String numero, double taxaRendimento) {
	        if (numero == null || numero.isEmpty()) {
	            throw new IllegalArgumentException("Número da conta não pode ser vazio.");
	        }
	        if (taxaRendimento <= 0.0) {
	            throw new IllegalArgumentException("A taxa de rendimento deve ser maior que zero.");
	        }
	    }
	    
	    public boolean encerrarConta(String numeroConta) throws Exception {
	        return contaDAO.excluirConta(numeroConta);
	    }
 
	    private void validarDadosContaCorrente(String numero, double limite, LocalDate dataVencimento) {
	        if (numero == null || numero.isEmpty()) {
	            throw new IllegalArgumentException("Número da conta não pode ser vazio.");
	        }
	        if ( limite< 0) {
	            throw new IllegalArgumentException("O limite não pode ser negativo.");
	        }
	        if (dataVencimento == null ) {
	            throw new IllegalArgumentException("Data de vencimento não pode ser vazia.");
	        }
	    }
	    
	    public Conta buscarPorNumeroConta(String numeroConta) {
	        // Chama o método na DAO para buscar a conta no banco de dados
	        return contaDAO.buscarContaPorNumeroConta(numeroConta);
	    }
}



