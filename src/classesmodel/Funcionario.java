package classesmodel;

import java.sql.Date;

public class Funcionario extends Usuario{
	
	private String codigoFuncionario;
    private String cargo;
    
    public Funcionario(int id, String nome, String cpf, Date dataNascimento, String telefone, Endereco endereco,String tipouser, String senha, String codigoFuncionario, String cargo) {
		super(nome, cpf, dataNascimento, telefone, endereco, tipouser, senha);
		this.codigoFuncionario = codigoFuncionario;
		this.cargo = cargo;
		
		// TODO Auto-generated constructor stub
	}

    //getters e setters
	public String getCodigoFuncionario() {
		return codigoFuncionario;
	}

	public void setCodigoFuncionario(String codigoFuncionario) {
		this.codigoFuncionario = codigoFuncionario;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	//metodos

	public void AbrirConta(Conta conta){
        // a implementar
    }

    public void encerrarConta(Conta conta){
        // a implementar
    }

    public Conta consultarDadosConta(int numeroConta){
        return null; //a implementar
    }

    public Cliente consultarDadosCliente(int idCliente){
        return null; // a implementar
    }

    public void alterarDadosConta(Conta conta){
        //a implementar
    }

    public void alterarDadosCliente(Cliente cliente){
        //a implementar
    }

    public void cadastrarFuncionario(Funcionario funcionario){
        // ai implementar
    }

    public void gerarRelatorioMovimentacao(){
        // a implementar
    }

	@Override
	public boolean login(String senha) {
		// TODO Auto-generated method stub
		return false;
	}



}
