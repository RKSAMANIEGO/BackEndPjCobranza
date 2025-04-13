package com.example.demo.configuration;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.demo.utils.jwt.FilterAuthentication;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final FilterAuthentication filterAuthentication;
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
				.csrf(c-> c.disable())
				.cors(c->c.configurationSource(corsConfigurationSource()))
				.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(req -> {
					req.requestMatchers("/actuator/**").permitAll();
					req.requestMatchers(HttpMethod.POST, "/auth/log-in").permitAll();
					req.requestMatchers(HttpMethod.PUT, "/auth/onChangePassword").permitAll();
					req.requestMatchers("/api/**").authenticated();
				})
				.addFilterBefore(filterAuthentication, UsernamePasswordAuthenticationFilter.class)
				.build(); 
	}

    @Bean
    UrlBasedCorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(List.of("http://localhost:5173","https://backendpjcobranza-production.up.railway.app"));
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config); 
        return source;
    }
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration conf) throws Exception {
		return conf.getAuthenticationManager();
	}

	
	@Bean
	AuthenticationProvider provider(UserDetailsService userDetailService) {
		
		DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
		dao.setUserDetailsService(userDetailService);
		dao.setPasswordEncoder(encoder());
		return dao;	
	}
	
	@Bean
	PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
}
