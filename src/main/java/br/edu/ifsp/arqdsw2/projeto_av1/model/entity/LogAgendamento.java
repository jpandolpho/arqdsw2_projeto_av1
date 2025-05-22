package br.edu.ifsp.arqdsw2.projeto_av1.model.entity;

import java.util.Date;

public class LogAgendamento {
	private Status estado;
	private Date horaMudanca;
	
	public LogAgendamento() {}

	public LogAgendamento(Status estado, Date horaMudanca) {
		this.estado = estado;
		this.horaMudanca = horaMudanca;
	}

	public Status getEstado() {
		return estado;
	}

	public void setEstado(Status estado) {
		this.estado = estado;
	}

	public Date getHoraMudanca() {
		return horaMudanca;
	}

	public void setHoraMudanca(Date horaMudanca) {
		this.horaMudanca = horaMudanca;
	}
}
