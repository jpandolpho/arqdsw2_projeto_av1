package br.edu.ifsp.arqdsw2.projeto_av1.model.entity;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

enum DiaSemana{
	SEG,
	TER,
	QUA,
	QUI,
	SEX,
	SAB,
	DOM
}

public class Disponibilidade {
	private Date diaMes;
	private DiaSemana diaSemana;
	private LocalTime horaInicio;
	private LocalTime horFim;
	private List<Agendamento> agendamentos;
	
	public Disponibilidade() {}

	public Disponibilidade(Date diaMes, DiaSemana diaSemana, LocalTime horaInicio, LocalTime horFim) {
		this.diaMes = diaMes;
		this.diaSemana = diaSemana;
		this.horaInicio = horaInicio;
		this.horFim = horFim;
		agendamentos = new ArrayList<Agendamento>();
	}

	public Date getDiaMes() {
		return diaMes;
	}

	public void setDiaMes(Date diaMes) {
		this.diaMes = diaMes;
	}

	public DiaSemana getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(DiaSemana diaSemana) {
		this.diaSemana = diaSemana;
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public LocalTime getHorFim() {
		return horFim;
	}

	public void setHorFim(LocalTime horFim) {
		this.horFim = horFim;
	}

	public List<Agendamento> getAgendamentos() {
		return new ArrayList<Agendamento>(agendamentos);
	}
	
	public void addAgendamento(Agendamento a) {
		agendamentos.add(a);
	}
}
