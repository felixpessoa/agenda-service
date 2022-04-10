package com.felix.agenda.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.felix.agenda.domain.model.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long>{
	
	Optional<Paciente> findByCpf(String cpf);
	Optional<Paciente> findByEmail(String email);
	
	
}
