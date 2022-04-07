package com.felix.agenda.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.felix.agenda.domain.model.Agenda;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long>{

}
