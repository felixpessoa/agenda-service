package com.felix.agenda.api.request;

import javax.validation.constraints.Email;

import org.hibernate.validator.constraints.br.CPF;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PacienteRequest {

	private String nome;
	private String sobrenome;
	@Email
	private String email;
	@CPF
	private String cpf;
	
}
