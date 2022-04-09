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

		Optional<Paciente> optPacienteCPF = pacienteRepository.findByCpf(paciente.getCpf());

		if (optPacienteCPF.isPresent()) {
			if (!optPacienteCPF.get().getPacienteId().equals(paciente.getPacienteId())) {
				existeCpf = true;
			}
		}

		if (existeCpf) {
			throw new BusinessException("CPF já cadastrado");
		}

		return pacienteRepository.save(paciente);
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

}
