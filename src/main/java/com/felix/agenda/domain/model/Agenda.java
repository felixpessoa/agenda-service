package com.felix.agenda.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "agendas")
public class Agenda implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long agendaId;
	
	private String descricao;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime horario;
	
	@JsonFormat(pattern = "HH:mm dd/MM/yyyy")
	private LocalDateTime dataCriacaoAgenda;
	
	@ManyToOne
	private Paciente paciente;

	public Agenda() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Agenda(Long agendaId, String descricao, LocalDateTime horario, LocalDateTime dataCriacaoAgenda,
			Paciente paciente) {
		super();
		this.agendaId = agendaId;
		this.descricao = descricao;
		this.horario = horario;
		this.dataCriacaoAgenda = dataCriacaoAgenda;
		this.paciente = paciente;
	}

	public Long getAgendaId() {
		return agendaId;
	}

	public void setAgendaId(Long agendaId) {
		this.agendaId = agendaId;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDateTime getHorario() {
		return horario;
	}

	public void setHorario(LocalDateTime horario) {
		this.horario = horario;
	}

	public LocalDateTime getDataCriacaoAgenda() {
		return dataCriacaoAgenda;
	}

	public void setDataCriacaoAgenda(LocalDateTime dataCriacaoAgenda) {
		this.dataCriacaoAgenda = dataCriacaoAgenda;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	@Override
	public int hashCode() {
		return Objects.hash(agendaId, dataCriacaoAgenda, descricao, horario, paciente);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Agenda other = (Agenda) obj;
		return Objects.equals(agendaId, other.agendaId) && Objects.equals(dataCriacaoAgenda, other.dataCriacaoAgenda)
				&& Objects.equals(descricao, other.descricao) && Objects.equals(horario, other.horario)
				&& Objects.equals(paciente, other.paciente);
	}

	@Override
	public String toString() {
		return "Agenda [agendaId=" + agendaId + ", descricao=" + descricao + ", horario=" + horario
				+ ", dataCriacaoAgenda=" + dataCriacaoAgenda + ", paciente=" + paciente + "]";
	}

	

}