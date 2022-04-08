package com.felix.agenda.api.controller;

import java.util.List;
import java.util.Optional;

import org.hibernate.annotations.OnDelete;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@GetMapping("/{id}")
	public ResponseEntity<Paciente> findByIdPaciente(@PathVariable Long pacienteId){
		Optional<Paciente> optPaciente = pacienteService.findByIdPaciente(pacienteId);
		 
		if (optPaciente.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		Paciente paciente = optPaciente.get();
		
		return ResponseEntity.status(HttpStatus.OK).body(paciente);
	}
	
	@PutMapping
	public ResponseEntity<Paciente> findByIdPaciente(@RequestBody Paciente paciente){
		Paciente pacientecreate = pacienteService.createPaciente(paciente);
		return ResponseEntity.status(HttpStatus.OK).body(pacientecreate);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long pacienteId){
		pacienteService.delete(pacienteId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	

}
