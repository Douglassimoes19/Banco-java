package classesmodel;

public class ContaPoupanca extends Conta{
    
	private double taxaRendimento;
    
	public ContaPoupanca(int numero, String agencia, double saldo, Cliente cliente, double taxaRendimento) {
		super(numero, agencia, saldo, cliente);
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

	@Override
    public void depositar(double valor){
        saldo += valor;
    }

    @Override
    public boolean sacar(double valor){
        if (saldo >= valor){
            saldo -= valor;
            return true;
        }
        return false;
    }

    public double calcularRendimento(){
        return saldo * taxaRendimento;
    }

 
}
