package com.felix.agenda.config.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.felix.agenda.data.DateUserData;
import com.felix.agenda.domain.model.Usuario;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	
	private final AuthenticationManager authenticationManager;
	
	public static final String TOKEN_SENHA = "4dc80bf7-0842-4f93-9206-2d811d649fc2";
	
	public static final int TOKEN_EXPIRACAO = 600_000;

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			Usuario usuario = new ObjectMapper()
					.readValue(request.getInputStream(), Usuario.class);
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					usuario.getLogin(),
					usuario.getPassword(),
					new ArrayList<>()
					));
		} catch (IOException e) {
			throw new RuntimeException("Falha ao authenticar usuario", e);
		}
		
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
			
		DateUserData dateUserData = (DateUserData) authResult.getPrincipal();
		
		String token = JWT.create()
				.withSubject(dateUserData.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis()+ TOKEN_EXPIRACAO))
				.sign(Algorithm.HMAC512(TOKEN_SENHA));
		
		response.getWriter().write(token);
		response.getWriter().flush();
	}
	
	
	
	

}
