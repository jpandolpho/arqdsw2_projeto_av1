package br.edu.ifsp.arqdsw2.projeto_av1.model.entity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public abstract class Usuario {
	private int id;
	private String nome;
	private String endereco;
	private String email;
	private String senha;
	private List<Agendamento> agendamentos;
	
	public Usuario() {agendamentos = new ArrayList<Agendamento>();}
	
	public Usuario(String nome, String endereco, String email, String senha) {
		init(nome,endereco,email,hashSHA256(senha));
	}
	
	public Usuario(String nome, String endereco, String email, String senha, Boolean fromDB) {
		if(fromDB) {
			init(nome,endereco,email,senha);
		}else {
			init(nome,endereco,email,hashSHA256(senha));
		}
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public List<Agendamento> getAgendamentos() {
		return new ArrayList<Agendamento>(agendamentos);
	}
	
	public void addAgendamento(Agendamento a) {
		agendamentos.add(a);
	}

	protected void init(String nome, String endereco, String email, String senha) {
		this.nome = nome;
		this.endereco = endereco;
		this.email = email;
		this.senha = senha;
		agendamentos = new ArrayList<Agendamento>();
	}

	public static boolean authenticate(Usuario fromSystem, String email, String senha) {
		if(fromSystem!=null) {
			return hashSHA256(senha).equals(fromSystem.senha) && email.equals(fromSystem.email);
		}
		return false;
	}
	
	protected static String hashSHA256(String senha) {
		try {
			var digest = MessageDigest.getInstance("SHA-256");
			
			byte[] hashBytes = digest.digest(senha.getBytes());
			
			var sb = new StringBuilder();
			for (byte b : hashBytes) {
				String hex = Integer.toHexString(0xff & b);
				if (hex.length() == 1) {
					sb.append('0');
				}
				sb.append(hex);
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao criptografar.", e);
		}
		
	}
}
