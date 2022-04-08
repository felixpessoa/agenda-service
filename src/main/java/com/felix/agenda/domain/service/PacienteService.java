package com.felix.agenda.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.felix.agenda.domain.model.Paciente;
import com.felix.agenda.domain.repository.PacienteRepository;
import com.felix.agenda.exception.BusinessException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PacienteService {
	
	private PacienteRepository pacienteRepository;
	
	
	public Paciente createPaciente(Paciente paciente) {
		
		boolean existeCpf = false;
		boolean existeEmail = false;
		String existeCpfTrue = "";
		String existeEmailTrue = "";
		
		Optional<Paciente> optPacienteCPF = pacienteRepository.findByCpf(paciente.getCpf());
		Optional<Paciente> optPacienteEmail = pacienteRepository.findByEmail(paciente.getEmail());
		
		if(optPacienteCPF.isPresent() || optPacienteEmail.isPresent()) {
			if((!optPacienteCPF.get().getPacienteID().equals(paciente.getPacienteID())) 
					|| (!optPacienteEmail.get().getPacienteID().equals(paciente.getPacienteID()))) {
				existeCpf = true;
			}
		}
		
		if(existeCpf) {
			existeCpfTrue = "CPF já cadastrado";
		}
		if(existeEmail) {
			existeEmailTrue = "Email já cadastrado";
		}
		
		if(existeCpf || existeEmail) {
			throw new BusinessException(existeCpfTrue + " " + existeEmailTrue);
		}
		
		return pacienteRepository.save(paciente);
	}
	
	public List<Paciente> findAllPaciente(){
		return pacienteRepository.findAll();
	}
	
	public Optional<Paciente> findByIdPaciente(Long pacienteId) {
		return pacienteRepository.findById(pacienteId);
	}
	
	public void delete(Long pacienteId) {
		pacienteRepository.deleteById(pacienteId);
	}
	
	
	
}
