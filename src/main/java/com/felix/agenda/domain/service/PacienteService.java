package com.felix.agenda.domain.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

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
		boolean findCpf = findByCpf(paciente);
		boolean findEmail = findByEmail(paciente);
		
		if(findCpf) {
			existeCpfTrue = "CPF já cadastrado";
		}

		if(findEmail) {
			existeEmailTrue = "Email já cadastrado";
		}
		
		if(findCpf || findEmail) {
			throw new BusinessException(" "+existeCpfTrue + " " + existeEmailTrue+" ");
		}
		
		if(paciente.getPacienteId()== null) {
		paciente.setDataCriacao(LocalDateTime.now());
		}
		return pacienteRepository.save(paciente);
		
	}

	
	public Paciente atualizarPaciente(Long pacienteId, Paciente paciente) {
		Optional<Paciente> optPaciente = this.findByIdPaciente(pacienteId);
		if (optPaciente.isEmpty()) {
			throw new BusinessException("Paciente não cadastrado!");
		}
		paciente.setPacienteId(pacienteId);
		paciente.setDataCriacao(optPaciente.get().getDataCriacao());
		return createPaciente(paciente);
	}
	

	public List<Paciente> findAllPaciente() {
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
			if(!optPacienteCPF.get().getPacienteId().equals(paciente.getPacienteId())){
				existeCpf = true;
			}
		}
		return existeCpf;
	}
	
	private boolean findByEmail(Paciente paciente) {
		boolean existeEmail = false;
		Optional<Paciente> optPacietneEmail = pacienteRepository.findByEmail(paciente.getEmail());
		if(optPacietneEmail.isPresent()) {
			if(!optPacietneEmail.get().getPacienteId().equals(paciente.getPacienteId())) {
				existeEmail = true;
			}
		}
		return existeEmail;
	}
}
