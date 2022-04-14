package com.felix.agenda.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.felix.agenda.domain.model.Usuario;
import com.felix.agenda.domain.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {
	
	private final UsuarioRepository usuarioRepository;
	private final PasswordEncoder encoder;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> findAllUsuario(){
		return ResponseEntity.ok(usuarioRepository.findAll());
	}
	
	@PostMapping
	public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario){
		usuario.setSenha(encoder.encode(usuario.getSenha()));
		return ResponseEntity.ok(usuarioRepository.save(usuario));
	}
	
	@GetMapping("/valdiarsenha")
	public ResponseEntity<Boolean> validarSenha(@RequestParam String login, @RequestParam String senha){
		
		Optional<Usuario> optUsuario = usuarioRepository.findByLogin(login);
		
		if(optUsuario.isEmpty()) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
		}
		
		boolean valid  = encoder.matches(senha, optUsuario.get().getSenha());
		
		HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
		
		
		return ResponseEntity.status(status).body(valid);
	}
	

}
