package com.example.demo.utils.jwt;

import java.io.IOException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FilterAuthentication extends OncePerRequestFilter{

	
	private final Jwt utils;

	private final UserDetailsService userDetails;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
	
			String authorization = request.getHeader("Authorization");
			
			String token;
			String username;
			
			try {
				if(authorization!=null && !authorization.isEmpty() && authorization.startsWith("Bearer")) {
					token = authorization.substring(7);
					username = utils.claimsUsername(token);
				
					
					if(utils.isValidToken(token, username) && SecurityContextHolder.getContext().getAuthentication()==null) {
					
						UserDetails usuarioDetalle =userDetails.loadUserByUsername(username);
						
						Authentication authenticacion = new UsernamePasswordAuthenticationToken(usuarioDetalle.getUsername(), null,usuarioDetalle.getAuthorities());
						
						SecurityContextHolder.getContext().setAuthentication(authenticacion);	
					}
				}
			
			} catch (AuthenticationException e) {
				throw new RuntimeException("Token No Valido, error "+e.getMessage());
			}
			

			
				
		filterChain.doFilter(request, response);
	}

}
