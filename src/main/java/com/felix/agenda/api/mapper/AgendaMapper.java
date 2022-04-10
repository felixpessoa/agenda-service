package com.felix.agenda.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.felix.agenda.api.request.AgendaRequest;
import com.felix.agenda.api.response.AgendaResponse;
import com.felix.agenda.domain.model.Agenda;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AgendaMapper {

	private ModelMapper modelMapper;
	
	public Agenda toAgenda(AgendaRequest agendaRequest) {
		return modelMapper.map(agendaRequest, Agenda.class);
	}

	public AgendaResponse toAgendaResponse(Agenda agenda) {
		return modelMapper.map(agenda, AgendaResponse.class);
	}

	public List<AgendaResponse> toAgendaResponseList(List<Agenda> agendas) {
		return agendas.stream().map(this::toAgendaResponse).collect(Collectors.toList());
	}
	
}
