package br.edu.ifsp.arqdsw2.projeto_av1.model.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Agendamento {
	private Status estado;
	private Date criacao;
	private List<LogAgendamento> log;
	
	public Agendamento() {}

	public Agendamento(Status estado, Date criacao) {
		this.estado = estado;
		this.criacao = criacao;
		log = new ArrayList<LogAgendamento>();
	}

	public Status getEstado() {
		return estado;
	}

	public void setEstado(Status estado) {
		this.estado = estado;
	}

	public Date getCriacao() {
		return criacao;
	}

	public void setCriacao(Date criacao) {
		this.criacao = criacao;
	}

	public List<LogAgendamento> getLog() {
		return new ArrayList<LogAgendamento>(log);
	}
	
	public void addMudanca(LogAgendamento alteracao) {
		log.add(alteracao);
	}
}
