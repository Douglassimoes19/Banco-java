package classesmodel;

import java.time.LocalDateTime;
import java.util.List;

public class Relatorio {
    private String tipo;
    private LocalDateTime dataGeracao;
    private List<String> dados;

	public Relatorio(String tipo, LocalDateTime dataGeracao, List<String> dados) {
		super();
		this.tipo = tipo;
		this.dataGeracao = dataGeracao;
		this.dados = dados;
	}
	
	//getters e setters
		public String getTipo() {
			return tipo;
		}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public LocalDateTime getDataGeracao() {
		return dataGeracao;
	}

	public void setDataGeracao(LocalDateTime dataGeracao) {
		this.dataGeracao = dataGeracao;
	}

	public List<String> getDados() {
		return dados;
	}

	public void setDados(List<String> dados) {
		this.dados = dados;
	}
	

	//metodos
    public void gerarRelatorioGeral(){
        //a implementar
    }

    public void exportarParaExcel(){
        // a implementar
    }

    

}
