package com.felix.agenda.api.response;

import java.time.LocalDateTime;

import javax.validation.constraints.Email;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PacienteResponse {
	
	private Long pacienteId;
	private String nome;
	private String sobrenome;
	@Email
	private String email;
	@CPF
	private String cpf;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataCriacao;
}
