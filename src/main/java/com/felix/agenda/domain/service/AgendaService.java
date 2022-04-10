package com.felix.agenda.domain.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.felix.agenda.domain.model.Agenda;
import com.felix.agenda.domain.model.Paciente;
import com.felix.agenda.domain.repository.AgendaRepository;
import com.felix.agenda.exception.BusinessException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AgendaService {

	private AgendaRepository agendaRepository;
	private PacienteService pacienteService;

	public List<Agenda> findAllAgenda() {
		return agendaRepository.findAll();
	}

	public Optional<Agenda> findByIdAgenda(Long agendaId) {
		return agendaRepository.findById(agendaId);
	}

	public Agenda createAgenda(Agenda agenda) {
		Optional<Paciente> optPaciente = pacienteService.findByIdPaciente(agenda.getPaciente().getPacienteId());
		if (optPaciente.isEmpty()) {
			throw new BusinessException("Paciente não encontrado");
		}

		Optional<Agenda> optHorario = agendaRepository.findByHorario(agenda.getHorario());

		if (optHorario.isPresent()) {
			throw new BusinessException("Já existe agendamento para este horário");
		}

		agenda.setPaciente(optPaciente.get());
		agenda.setDataCriacaoAgenda(LocalDateTime.now());

		return agendaRepository.save(agenda);
	}

}
