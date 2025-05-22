package br.edu.ifsp.arqdsw2.projeto_av1.model.entity;

public class ImagemServ {
	private String caminho;
	private String descricao;
	
	public ImagemServ() {}

	public ImagemServ(String caminho, String descricao) {
		this.caminho = caminho;
		this.descricao = descricao;
	}

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
