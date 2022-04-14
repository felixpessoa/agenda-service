package com.felix.agenda.domain.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.felix.agenda.data.DateUserData;
import com.felix.agenda.domain.model.Usuario;
import com.felix.agenda.domain.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DateUserService implements UserDetailsService{

	private final UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> optUsuario = usuarioRepository.findByLogin(username);
		if(optUsuario.isEmpty()){
			throw new UsernameNotFoundException("Usuário ["+username+"] não encontrado" );
		}
		
		return new DateUserData(optUsuario);
	}

}
