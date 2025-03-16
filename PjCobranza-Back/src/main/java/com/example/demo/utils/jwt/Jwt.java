package com.example.demo.utils.jwt;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class Jwt {

	final String SECRET_KEY ="promocionsantamariagoretty0059promocionsantamariagoretty0059promocionsantamariagoretty0059";
	
	Key secretKey = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), SignatureAlgorithm.HS256.getJcaName());
	
	public String createToken(String username) {
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + (1000*60*60*24)))
				.signWith(secretKey,SignatureAlgorithm.HS256)
				.compact();
	}
	
	public String claimsUsername(String token) {
		return Jwts.parserBuilder()
					.setSigningKey(secretKey)
					.build()
					.parseClaimsJws(token)
					.getBody()
					.getSubject();				
	}
	
	public Boolean isValidToken(String token , String username) {
		return claimsUsername(token).equals(username) && !isTokenExpired(token);
	}
	
	public Boolean isTokenExpired(String token) {
     Date fechaVencimientos= Jwts.parserBuilder()
			.setSigningKey(secretKey)
			.build()
			.parseClaimsJws(token)
			.getBody()
			.getExpiration();
		
     return fechaVencimientos.before(new Date());
	}
	
	
	
}
