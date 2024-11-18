package classesmodel;

import java.sql.Date;
import java.time.LocalDate;

public class Cliente extends Usuario {
    
	
	public Cliente(int id, String nome, String cpf, Date dataNascimento, String telefone, Endereco endereco,String tipouser, String senha) {
		super(nome, cpf, dataNascimento, telefone, endereco, tipouser, senha);
		
		// TODO Auto-generated constructor stub
	}

    
    //getters e setterss
	
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
