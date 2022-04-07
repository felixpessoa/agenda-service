package com.felix.agenda.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.felix.agenda.domain.repository.AgendaRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AgendaService {
	
	private AgendaRepository agendaRepository;
	
	
	
	
	

}
