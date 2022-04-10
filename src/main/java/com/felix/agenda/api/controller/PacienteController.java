package com.felix.agenda.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.felix.agenda.api.mapper.PacienteMapper;
import com.felix.agenda.api.request.PacienteRequest;
import com.felix.agenda.api.response.PacienteResponse;
import com.felix.agenda.domain.model.Paciente;
import com.felix.agenda.domain.service.PacienteService;

import lombok.AllArgsConstructor;

@CrossOrigin("*")
@RestController
@RequestMapping("/paciente")
@AllArgsConstructor
public class PacienteController {
	
	private PacienteService pacienteService;
	private PacienteMapper pacienteMapper;
	
	@PostMapping
	public ResponseEntity<PacienteResponse> create(@Valid @RequestBody PacienteRequest pacienteRequest){
		Paciente paciente = pacienteMapper.toPaciente(pacienteRequest);
		Paciente newPaciente = pacienteService.createPaciente(paciente);
		PacienteResponse pacienteResponse = pacienteMapper.toPacienteResponse(newPaciente);
		return ResponseEntity.status(HttpStatus.CREATED).body(pacienteResponse);
	}
	
	@GetMapping
	 public ResponseEntity<List<PacienteResponse>> findAllPaciente(){
		 List<Paciente> pacientes = pacienteService.findAllPaciente();
		 List<PacienteResponse> pacienteResponses = pacienteMapper.toPacienteResponseList(pacientes);
		 return ResponseEntity.status(HttpStatus.OK).body(pacienteResponses);
	 }
	
	@GetMapping("/{pacienteId}")
	public ResponseEntity<PacienteResponse> findByIdPaciente(@PathVariable Long pacienteId){
		Optional<Paciente> optPaciente = pacienteService.findByIdPaciente(pacienteId);
		 
		if (optPaciente.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(pacienteMapper.toPacienteResponse(optPaciente.get()));
	}

	
	@PutMapping("/{pacienteId}")
	public ResponseEntity<PacienteResponse> findByIdPaciente(@Valid @PathVariable Long pacienteId, @RequestBody PacienteRequest pacienteRequest){
		Paciente paciente = pacienteMapper.toPaciente(pacienteRequest);
		Paciente pacienteCreate = pacienteService.atualizarPaciente(pacienteId, paciente);
		PacienteResponse pacienteResponse = pacienteMapper.toPacienteResponse(pacienteCreate);
		return ResponseEntity.status(HttpStatus.OK).body(pacienteResponse);
	}
	
	@DeleteMapping("/{pacienteId}")
	public ResponseEntity<Void> delete(@PathVariable Long pacienteId){
		pacienteService.delete(pacienteId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	

}
