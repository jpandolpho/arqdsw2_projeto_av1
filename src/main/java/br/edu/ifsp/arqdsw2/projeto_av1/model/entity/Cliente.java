package br.edu.ifsp.arqdsw2.projeto_av1.model.entity;

public class Cliente extends Usuario {
	private String cpf;
	private String contato;
	
	public Cliente() {}

	public Cliente(String cpf, String contato, String nome, String endereco, String email, String senha, Boolean fromDB) {
		super();
		if(fromDB) {
			init(cpf, contato, nome, endereco, email, senha);
		}else {
			init(cpf, contato, nome, endereco, email, hashSHA256(senha));
		}
	}

	public Cliente(String cpf, String contato, String nome, String endereco, String email, String senha) {
		super();
		init(cpf, contato, nome, endereco, email, hashSHA256(senha));
	}
	
	private void init(String cpf, String contato, String nome, String endereco, String email, String senha) {
		init(nome,endereco,email,senha);
		this.cpf = cpf;
		this.contato = contato;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}
}
