package com.felix.agenda.api.mapper;

import com.felix.agenda.api.request.PacienteRequest;
import com.felix.agenda.api.response.PacienteResponse;
import com.felix.agenda.domain.model.Paciente;

public class PacienteMapper {

	public static Paciente toPaciente(PacienteRequest pacienteRequest) {
		Paciente paciente = new Paciente();
		paciente .setNome(pacienteRequest.getNome());
		paciente.setSobrenome(pacienteRequest.getSobrenome());
		paciente.setCpf(pacienteRequest.getCpf());
		paciente.setEmail(pacienteRequest.getEmail());
		return paciente;
	}
	
	public static PacienteResponse toPacienteResponse(Paciente paciente) {
		PacienteResponse pacienteResponse = new PacienteResponse();
		pacienteResponse.setPacienteId(paciente.getPacienteId());
		pacienteResponse .setNome(paciente.getNome());
		pacienteResponse.setSobrenome(paciente.getSobrenome());
		pacienteResponse.setCpf(paciente.getCpf());
		pacienteResponse.setEmail(paciente.getEmail());
		pacienteResponse.setDataCriacao(paciente.getDataCriacao());
		return pacienteResponse;
	}
	
	
}
