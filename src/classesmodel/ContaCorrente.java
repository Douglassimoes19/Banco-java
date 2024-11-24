package classesmodel;

import java.sql.Date;
import java.time.LocalDate;

public class ContaCorrente extends Conta{
	
	private double limite;
    private Date dataVencimento;
    
    public ContaCorrente(String numero, String agencia, double saldo, Cliente cliente, double limite,String conta, Date dataVencimento) 
    {
		super(numero, agencia, saldo, cliente,conta);
		this.limite = limite;
		this.dataVencimento = dataVencimento;
		
		// TODO Auto-generated constructor stub
	}
    
    
    //getters e setters

	public double getLimite() {
		return limite;
	}

	public void setLimite(double limite) {
		this.limite = limite;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	//metodos
	@Override
    public void depositar(double valor){
        setSaldo(getSaldo() + valor);
    }

    @Override
    public void sacar(double valor){
        if (getSaldo() + limite >= valor){
        	setSaldo(getSaldo() - valor) ;
            
        }
  
    }

    public double consultarLimite(){
        return limite;

    }
    
    @Override
    public String toString() {
        return "ContaCorrente{" +
               "numeroConta=" + getNumero() + 
               ", agencia='" + getAgencia() + '\'' + 
               ", saldo=" + getSaldo() + 
               ", limite=" + limite + 
               ", dataVencimento=" + dataVencimento +
               '}';
    }


}
