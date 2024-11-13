package classesmodel;

import java.time.LocalDate;

public class Funcionario extends Usuario{
	
	private String codigoFuncionario;
    private String cargo;
    private String senha;
    
    public Funcionario(int id, String nome, LocalDate dataNascimento, String telefone, Endereco endereco, String codigoFuncionario, String cargo, String senha) {
		super(id, nome, dataNascimento, telefone, endereco);
		this.codigoFuncionario = codigoFuncionario;
		this.cargo = cargo;
		this.senha = senha;
		
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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
