package com.felix.agenda.api.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgendaResponse {

	private Long agendaId;
	private String descricao;
	@JsonFormat(pattern = " HH:mm dd/MM/yyyy")
	private LocalDateTime horario;
	
	private PacienteResponse paciente;
}
