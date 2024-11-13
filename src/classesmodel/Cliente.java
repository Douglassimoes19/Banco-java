package classesmodel;

import java.time.LocalDate;

public class Cliente extends Usuario {
    
	private String senha;
	
	public Cliente(int id, String nome, LocalDate dataNascimento, String telefone, Endereco endereco, String senha) {
		super(id, nome, dataNascimento, telefone, endereco);
		this.senha = senha;
		// TODO Auto-generated constructor stub
	}

    
    //getters e setterss

    public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	//metodos

	public double consultarSaldo(){
        return 0.0; // a implementar
    }

    public void depositar(double valor){
        //  a implementar
    }

    public boolean sacar(double valor){
        return true; // ai implementar
    }

    public String consultarExtrato(){
        return ""; // a implementar
    }

    public double consultarLimite(){
        return 0.0; // a implementar
    }

	@Override
	public boolean login(String senha) {
		// TODO Auto-generated method stub
		return false;
	}


}
