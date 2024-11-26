package classesmodel;

import java.sql.Date;
import java.time.*;


public abstract class Usuario {
    private int id;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private String telefone;
    private Endereco endereco;
    private String tipouser;
    private String senha;
    
    public Usuario(int id,String nome,String cpf, LocalDate dataNascimento, String telefone, Endereco endereco,String tipouser, String senha) {
		this.id = id;
    	this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.telefone = telefone;
		this.endereco = endereco;
		this.tipouser = tipouser;
		this.senha = senha;
	}
    
    //getters e setters

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate Date) {
		this.dataNascimento = Date;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getTipouser() {
		return tipouser;
	}

	public void setTipouser(String tipouser) {
		this.tipouser = tipouser;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public abstract boolean login(String senha);

    public void logout(){
    }

    public String consultarDados(){
        return ""; 
    }

	

    

}
