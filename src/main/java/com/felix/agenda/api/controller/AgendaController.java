package com.felix.agenda.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.felix.agenda.api.mapper.AgendaMapper;
import com.felix.agenda.api.request.AgendaRequest;
import com.felix.agenda.api.response.AgendaResponse;
import com.felix.agenda.domain.model.Agenda;
import com.felix.agenda.domain.service.AgendaService;

import lombok.AllArgsConstructor;

@CrossOrigin("*")
@RestController
@RequestMapping("/agenda")
@AllArgsConstructor
public class AgendaController {

	private final AgendaService agendaService;
	private final AgendaMapper agendaMapper;

	@GetMapping
	public ResponseEntity<List<AgendaResponse>> findAllAgenda() {
		List<Agenda> agendas = agendaService.findAllAgenda();
		List<AgendaResponse> agendaResponses = agendaMapper.toAgendaResponseList(agendas);
		return ResponseEntity.status(HttpStatus.OK).body(agendaResponses);
	}

	@GetMapping("/{agendaId}")
	public ResponseEntity<AgendaResponse> findByIdAgenda(@PathVariable Long agendaId) {

		Optional<Agenda> optAgenda = agendaService.findByIdAgenda(agendaId);

		if (optAgenda.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		AgendaResponse agendaResponse = agendaMapper.toAgendaResponse(optAgenda.get());

		return ResponseEntity.status(HttpStatus.OK).body(agendaResponse);

	}

	@PostMapping
	public ResponseEntity<AgendaResponse> creadeAgenda(@Valid @RequestBody AgendaRequest agendaRequest) {
		Agenda agenda = agendaMapper.toAgenda(agendaRequest);
		Agenda agendaCreate = agendaService.createAgenda(agenda);
		AgendaResponse agendaResponse = agendaMapper.toAgendaResponse(agendaCreate);
		return ResponseEntity.status(HttpStatus.CREATED).body(agendaResponse);
	}

}
