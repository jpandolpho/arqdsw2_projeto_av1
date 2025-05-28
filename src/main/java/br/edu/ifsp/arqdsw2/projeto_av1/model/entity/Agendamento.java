package br.edu.ifsp.arqdsw2.projeto_av1.model.entity;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.edu.ifsp.arqdsw2.projeto_av1.model.enums.Status;

public class Agendamento {
	private int id;
	private Status estado;
	private Date criacao;
	private Date diaMes;
	private Time horaInicio;
	private Time horaFim;
	private List<LogAgendamento> log;
	
	public Agendamento() {log = new ArrayList<LogAgendamento>();}

	public Agendamento(int id, Status estado, Date criacao, Date diaMes, Time horaInicio, Time horFim) {
		this.id = id;
		this.estado = estado;
		this.criacao = criacao;
		this.diaMes = diaMes;
		this.horaInicio = horaInicio;
		this.horaFim = horFim;
		log = new ArrayList<LogAgendamento>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDiaMes() {
		return diaMes;
	}

	public void setDiaMes(Date diaMes) {
		this.diaMes = diaMes;
	}

	public Time getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Time horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Time getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(Time horaFim) {
		this.horaFim = horaFim;
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
	
	public void addAllMudanças(List<LogAgendamento> logs) {
		for(LogAgendamento log : logs) {
			addMudanca(log);
		}
	}
}
