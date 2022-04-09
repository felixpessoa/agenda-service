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
		
		String existeCpfTrue = "";
		String existeEmailTrue = "";
		
		if(findByCpf(paciente)) {
			existeCpfTrue = "CPF já cadastrado";
		}
		
		if(findByEmail(paciente)) {
			existeEmailTrue = "Email já cadastrado";
		}
		
		if(findByCpf(paciente) || findByEmail(paciente)) {
			throw new BusinessException(existeCpfTrue + " " + existeEmailTrue);
		}
		
		return pacienteRepository.save(paciente);
	}
	
	public Paciente upDate(Paciente paciente) {
		
		Paciente oldPaciente = new Paciente();
		
		String existeCpfTrue = "";
		String existeEmailTrue = "";
		
		if(findByCpf(paciente)) {
			existeCpfTrue = "CPF já cadastrado";
		}
		
		if(findByEmail(paciente)) {
			existeEmailTrue = "Email já cadastrado";
		}
		
		if(findByCpf(paciente) || findByEmail(paciente)) {
			throw new BusinessException(existeCpfTrue + " " + existeEmailTrue);
		}
		
		oldPaciente.setNome(paciente.getNome());
		oldPaciente.setSobrenome(paciente.getSobrenome());
		oldPaciente.setCpf(paciente.getCpf());
		oldPaciente.setEmail(paciente.getEmail());
		
		return pacienteRepository.save(oldPaciente);
		
		
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
	
	private boolean findByCpf(Paciente paciente) {
		
		boolean existeCpf = false;
		Optional<Paciente> optPacienteCPF = pacienteRepository.findByCpf(paciente.getCpf());
		if(optPacienteCPF.isPresent()){
			if(!optPacienteCPF.get().getPacienteID().equals(paciente.getPacienteID())){
				existeCpf = true;
			}
		}
		return existeCpf;
	}
	
	private boolean findByEmail(Paciente paciente) {
		boolean existeEmail = false;
		Optional<Paciente> optPacietneEmail = pacienteRepository.findByEmail(paciente.getEmail());
		if(optPacietneEmail.isPresent()) {
			if(!optPacietneEmail.get().getPacienteID().equals(paciente.getPacienteID())) {
				existeEmail = true;
			}
		}
		return existeEmail;
	}
	
}
