package com.felix.agenda.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.felix.agenda.domain.model.Paciente;
import com.felix.agenda.domain.repository.PacienteRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PacienteService {
	
	private PacienteRepository pacienteRepository;
	
	
	public Paciente createPaciente(Paciente paciente) {
		
		boolean existeCpf = false;
		
		Optional<Paciente> optPaciente = pacienteRepository.findByCpf(paciente.getCpf());
		
		if(optPaciente.isPresent()) {
			if(!optPaciente.get().getPacienteID().equals(paciente.getPacienteID())) {
				existeCpf = true;
			}
		}
		
		if(existeCpf) {
			return exception;
		}
		
		return pacienteRepository.save(paciente);
	}
	
	public List<Paciente> findAllPaciente(){
		return pacienteRepository.findAll();
	}
	
	public Optional<Paciente> findByIdPaciente(Long pacienteId) {
		return pacienteRepository.findById(pacienteId);
	}
	
	public void deletet(Long pacienteId) {
		pacienteRepository.deleteById(pacienteId);
	}
	
	
	
}
