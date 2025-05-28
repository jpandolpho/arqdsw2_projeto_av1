package br.edu.ifsp.arqdsw2.projeto_av1.model.entity;

import br.edu.ifsp.arqdsw2.projeto_av1.model.enums.DiaSemana;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Disponibilidade {
	private Date diaMes;
	private DiaSemana diaSemana;
	private LocalTime horaInicio;
	private LocalTime horaFim;
	private List<Agendamento> agendamentos;
	private int prestadorId;
	
	public Disponibilidade() {agendamentos = new ArrayList<Agendamento>();}

	public Disponibilidade(Date diaMes, DiaSemana diaSemana, LocalTime horaInicio, LocalTime horFim) {
		this.diaMes = diaMes;
		this.diaSemana = diaSemana;
		this.horaInicio = horaInicio;
		this.horaFim = horFim;
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

	public LocalTime getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(LocalTime horFim) {
		this.horaFim = horFim;
	}
	
	public int getPrestadorId() {
	    return prestadorId;
	}

	public void setPrestadorId(int prestadorId) {
	    this.prestadorId = prestadorId;
	}

	public List<Agendamento> getAgendamentos() {
		return new ArrayList<Agendamento>(agendamentos);
	}
	
	public void addAgendamento(Agendamento a) {
		agendamentos.add(a);
	}
}
