package br.edu.ifsp.arqdsw2.projeto_av1.model.entity;

import java.util.ArrayList;
import java.util.List;

public class Cidade {
	private String nome;
	private List<Usuario> usuarios;
	
	public Cidade() {
		usuarios = new ArrayList<Usuario>();
	}

	public Cidade(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<Usuario> getUsuarios(){
		return new ArrayList<Usuario>(usuarios);
	}
	
	public void addUsuario(Usuario u) {
		usuarios.add(u);
	}
}
