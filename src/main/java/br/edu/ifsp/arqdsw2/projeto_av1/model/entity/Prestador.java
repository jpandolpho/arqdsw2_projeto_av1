package br.edu.ifsp.arqdsw2.projeto_av1.model.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Prestador extends Usuario {
	private String nomeFantasia;
	private String fotoPerfil;
	private String especialidade;
	private String descricao;
	private Date dataCriacao;
	private List<ImagemServ> imagens;
	private List<Disponibilidade> disponibilidades;
	
	public Prestador() {}

	public Prestador(String nomeFantasia, String fotoPerfil, String especialidade, String descricao, Date dataCriacao, 
			String nome, String endereco, String email, String senha, Boolean fromDB) {
		super();
		if(fromDB) {
			init(nomeFantasia, fotoPerfil, especialidade, descricao, dataCriacao, nome,endereco,email,senha);
		}else {
			init(nomeFantasia, fotoPerfil, especialidade, descricao, dataCriacao, nome,endereco,email,hashSHA256(senha));
		}
	}

	public Prestador(String nomeFantasia, String fotoPerfil, String especialidade, String descricao, Date dataCriacao,
			String nome, String endereco, String email, String senha) {
		super();
		init(nomeFantasia, fotoPerfil, especialidade, descricao, dataCriacao, nome,endereco,email,hashSHA256(senha));
	}

	private void init(String nomeFantasia, String fotoPerfil, String especialidade, String descricao,
			Date dataCriacao, String nome, String endereco, String email, String senha) {
		init(nome,endereco,email,senha);
		this.nomeFantasia = nomeFantasia;
		this.fotoPerfil = fotoPerfil;
		this.especialidade = especialidade;
		this.dataCriacao = dataCriacao;
		imagens = new ArrayList<ImagemServ>();
		disponibilidades = new ArrayList<Disponibilidade>();
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getFotoPerfil() {
		return fotoPerfil;
	}

	public void setFotoPerfil(String fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public List<ImagemServ> getImagens() {
		return new ArrayList<ImagemServ>(imagens);
	}

	public List<Disponibilidade> getDisponibilidades() {
		return new ArrayList<Disponibilidade>(disponibilidades);
	}
	
	public void addImagem(ImagemServ i) {
		imagens.add(i);
	}
	
	public void addDisponibilidade(Disponibilidade d) {
		disponibilidades.add(d);
	}
}
