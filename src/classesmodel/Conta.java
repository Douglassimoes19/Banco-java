package classesmodel;

public abstract class Conta {
    private int numero;
    private String agencia;
    private double saldo;
    private Cliente cliente;
    
    //getters e setters

    public Conta(int numero, String agencia, double saldo, Cliente cliente) {
		super();
		this.numero = numero;
		this.agencia = agencia;
		this.saldo = saldo;
		this.cliente = cliente;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	//metodos
	
    public void depositar(double valor){
        setSaldo(getSaldo() + valor);
    }

   
    public void sacar(double valor){
        if (getSaldo() >= valor){
        	setSaldo(getSaldo() - valor) ; 
        }
    }

    public double consultarSaldo() {
    	return saldo;
    }
 
}
