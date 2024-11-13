package classesmodel;

import java.time.LocalDate;

public abstract class Usuario {
    private int id;
    private String nome;
    private LocalDate dataNascimento;
    private String telefone;
    private Endereco endereco;
    
    public Usuario(int id, String nome, LocalDate dataNascimento, String telefone, Endereco endereco) {
		this.id = id;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.telefone = telefone;
		this.endereco = endereco;
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

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
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

	public abstract boolean login(String senha);

    public void logout(){
        // a implementar
    }

    public String consultarDados(){
        return ""; // a implementar
    }

    

}
