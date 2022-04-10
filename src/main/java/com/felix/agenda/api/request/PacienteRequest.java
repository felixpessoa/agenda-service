package com.felix.agenda.api.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CPF;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PacienteRequest {
	
	@NotBlank(message = "Nome do paciente é obrigatório")
	private String nome;
	private String sobrenome;
	@Email
	private String email;
	@NotBlank(message = "CPF do paciente é obrigatório")
	@CPF
	private String cpf;

	
}
