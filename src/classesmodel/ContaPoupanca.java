package classesmodel;

public class ContaPoupanca extends Conta{
    
	private double taxaRendimento;
    
	public ContaPoupanca(String numero, String agencia, double saldo, String conta, Cliente cliente, double taxaRendimento) {
		super(numero, agencia, saldo, cliente,conta);
		this.taxaRendimento = taxaRendimento;
		// TODO Auto-generated constructor stub
	}

    //getters e setters
    public double getTaxaRendimento() {
		return taxaRendimento;
	}

	public void setTaxaRendimento(double taxaRendimento) {
		this.taxaRendimento = taxaRendimento;
	}

	//metodos
		@Override
	    public void depositar(double valor){
	        setSaldo(getSaldo() + valor);
	    }

	    @Override
	    public void sacar(double valor){
	        if (getSaldo() >= valor){
	        	setSaldo(getSaldo() - valor) ;
	            
	        }
	  
	    }

    public double calcularRendimento(){
    	double rendimento = getSaldo() * taxaRendimento;
        return rendimento;
    }

 
}
