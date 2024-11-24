package classescontroller;

import java.sql.Date;

import classesdao.ContaDao;
import classesmodel.Cliente;
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
	            
	            contaDAO.inserirContaPoupanca(numero, agencia, saldo, conta, cliente, taxaRendimento);
	            
	            boolean result = contaDAO.inserirContaPoupanca(numero, agencia, saldo, conta, cliente, taxaRendimento);
	            
	            if(result) {
	            	return " Sua Conta foi criada com sucesso!";
	            }
	        } catch (IllegalArgumentException e) {
	            throw new RuntimeException("Erro ao cadastrar conta poupança: " + e.getMessage());
	        }
	        
	        return "Erro ao efetuar cadastro";
	    }

	    public void cadastrarContaCorrente(String numero, String agencia, double saldo, String conta, Cliente cliente, double limite, Date dataVencimento) {
	        try {
	            validarDadosContaCorrente(numero, limite, dataVencimento);
	            //contaDAO.inserirContaCorrente(conta);
	        } catch (IllegalArgumentException e) {
	            throw new RuntimeException("Erro ao cadastrar conta corrente: " + e.getMessage());
	        }
	    }

	    private void validarDadosContaPoupanca(String numero, double taxaRendimento) {
	        if (numero == null || numero.isEmpty()) {
	            throw new IllegalArgumentException("Número da conta não pode ser vazio.");
	        }
	        if (taxaRendimento <= 0.0) {
	            throw new IllegalArgumentException("A taxa de rendimento deve ser maior que zero.");
	        }
	    }
 
	    private void validarDadosContaCorrente(String numero, double limite, Date dataVencimento) {
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
}



