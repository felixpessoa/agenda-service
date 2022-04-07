package com.felix.agenda.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.felix.agenda.domain.model.Paciente;
import com.felix.agenda.domain.service.PacienteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/paciente")
@RequiredArgsConstructor
public class PacienteController {
	
	private final PacienteService pacienteService;
	
	@PostMapping
	public ResponseEntity<Paciente> create(@RequestBody Paciente paciente){
		Paciente newPaciente = pacienteService.createPaciente(paciente);
		return ResponseEntity.status(HttpStatus.CREATED).body(newPaciente);
	}
	
	@GetMapping
	 public ResponseEntity<List<Paciente>> findAllPaciente(){
		 List<Paciente> pacientes = pacienteService.findAllPaciente();
		 return ResponseEntity.status(HttpStatus.OK).body(pacientes);
	 }

}
