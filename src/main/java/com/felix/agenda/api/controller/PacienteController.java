package com.felix.agenda.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	private final PacienteService pacienteService;
	
	@PostMapping
	public ResponseEntity<PacienteResponse> create(@RequestBody PacienteRequest pacienteRequest){
		Paciente paciente = PacienteMapper.toPaciente(pacienteRequest);
		Paciente newPaciente = pacienteService.createPaciente(paciente);
		PacienteResponse pacienteResponse = PacienteMapper.toPacienteResponse(newPaciente);
		return ResponseEntity.status(HttpStatus.CREATED).body(pacienteResponse);
	}
	
	@GetMapping
	 public ResponseEntity<List<Paciente>> findAllPaciente(){
		 List<Paciente> pacientes = pacienteService.findAllPaciente();
		 return ResponseEntity.status(HttpStatus.OK).body(pacientes);
	 }
	
	@GetMapping("/{pacienteId}")
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
	
	@DeleteMapping("/{pacienteId}")
	public ResponseEntity<Void> delete(@PathVariable Long pacienteId){
		pacienteService.delete(pacienteId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	

}
