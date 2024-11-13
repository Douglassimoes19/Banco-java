package classesmodel;

public class ConexaoBanco {
    private String url;
    private String usuario;
    private String senha;
    
    //getters e setters

    public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	//metodos
	public void conectar(){
        // implementar conexao
    }

    public void desconectar(){
        //implementar desconexao
    }

    
}
