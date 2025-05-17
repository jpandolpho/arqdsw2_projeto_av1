package br.edu.ifsp.arqdsw2.projeto_av1.model.entity;

public class Cidade {
	private String nome;
	
	public Cidade() {}

	public Cidade(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
