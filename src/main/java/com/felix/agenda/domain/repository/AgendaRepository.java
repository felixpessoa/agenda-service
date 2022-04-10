package com.felix.agenda.domain.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.felix.agenda.domain.model.Agenda;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long>{
	
	Optional<Agenda> findByHorario(LocalDateTime horario);

}
