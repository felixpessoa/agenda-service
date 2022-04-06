package com.felix.agenda.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "pacientes")
public class Paciente implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pacienteID;
	
	private String nome;
	private String sobrenome;

	@Email
	private String email;
	
	@CPF
	private String cpf;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataCriacao;

	public Paciente() {
		super();
		this.setDataCriacao(LocalDateTime.now());
	}

	public Paciente(Long pacienteID, String nome, String sobrenome, @Email String email, @CPF String cpf,
			LocalDateTime dataCriacao) {
		super();
		this.pacienteID = pacienteID;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.cpf = cpf;
		this.setDataCriacao(LocalDateTime.now());
	}

	public Long getPacienteID() {
		return pacienteID;
	}

	public void setPacienteID(Long pacienteID) {
		this.pacienteID = pacienteID;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf, email, pacienteID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Paciente other = (Paciente) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(email, other.email)
				&& Objects.equals(pacienteID, other.pacienteID);
	}

	@Override
	public String toString() {
		return "Paciente [pacienteID=" + pacienteID + ", nome=" + nome + ", sobrenome=" + sobrenome + ", email=" + email
				+ ", cpf=" + cpf + ", dataCriacao=" + dataCriacao + "]";
	}
	
	
	

}
