package com.felix.agenda.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.felix.agenda.api.request.PacienteRequest;
import com.felix.agenda.api.response.PacienteResponse;
import com.felix.agenda.domain.model.Paciente;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class PacienteMapper {

	private ModelMapper modelMapper;

	public Paciente toPaciente(PacienteRequest pacienteRequest) {
		return modelMapper.map(pacienteRequest, Paciente.class);
	}

	public PacienteResponse toPacienteResponse(Paciente paciente) {
		return modelMapper.map(paciente, PacienteResponse.class);
	}

	public List<PacienteResponse> toPacienteResponseList(List<Paciente> pacientes) {
		return pacientes.stream().map(this::toPacienteResponse).collect(Collectors.toList());
	}

//	public static Paciente toPaciente(PacienteRequest pacienteRequest) {
//		Paciente paciente = new Paciente();
//		paciente .setNome(pacienteRequest.getNome());
//		paciente.setSobrenome(pacienteRequest.getSobrenome());
//		paciente.setCpf(pacienteRequest.getCpf());
//		paciente.setEmail(pacienteRequest.getEmail());
//		return paciente;
//	}

//	public static PacienteResponse toPacienteResponse(Paciente paciente) {
//		PacienteResponse pacienteResponse = new PacienteResponse();
//		pacienteResponse.setPacienteId(paciente.getPacienteId());
//		pacienteResponse .setNome(paciente.getNome());
//		pacienteResponse.setSobrenome(paciente.getSobrenome());
//		pacienteResponse.setCpf(paciente.getCpf());
//		pacienteResponse.setEmail(paciente.getEmail());
//		pacienteResponse.setDataCriacao(paciente.getDataCriacao());
//		return pacienteResponse;
//	}

//	public static List<PacienteResponse> toPacienteResponseList(List<Paciente> pacientes){
//		List<PacienteResponse> pacienteResponses = new ArrayList<>();
//		for(Paciente paciente : pacientes) {
//			pacienteResponses.add(toPacienteResponse(paciente));
//		}
//		return pacienteResponses;
//	}

}
