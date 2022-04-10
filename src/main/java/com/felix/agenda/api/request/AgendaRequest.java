package com.felix.agenda.api.request;

import java.time.LocalDateTime;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgendaRequest {
	
	@NotBlank
	private String descricao;
	
	@NotNull
	@Future
	@JsonFormat(pattern = "HH:mm dd/MM/yyyy")
	private LocalDateTime horario;
	@NotNull
	private Long pacienteId;

}
