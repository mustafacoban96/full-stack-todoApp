package com.shepherd.todoAppV2.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.shepherd.todoAppV2.security.SecurityGlobalVariables;

@Service
public class JwtService {
	
	
	
	@Value("${jwt.key}")
	private String SECRET;
	
	@Value("${jwt.expiration}")
	private long jwtExpiration;
	
	@Value("${refresh.expiration}")
	private long refreshExpiration;
	
	
	
	
	public String generateToken(String username) {
		Map<String, Object> claims = new HashMap<>();
		
		return createToken(claims,username,jwtExpiration);
	}
	
	

	 
	private String createToken(Map<String, Object> claims, String username, long jwtExpiration) {
		
		return Jwts.builder()
				.setClaims(claims)    
				.setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
				.signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
	}
	
	private Key getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	public Boolean validateToken(String token, UserDetails userDetails) {
		String username = extractUsername(token);
		Date expiration = extractExpiration(token);
		
		
		
		return userDetails.getUsername().equals(username) && expiration.after(new Date());
	}
	
	private Date extractExpiration(String token) {
		Claims claims = Jwts
				.parserBuilder()
				.setSigningKey(getSignKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
		return claims.getExpiration();
	}
	
	
	
	
	public String extractUsername(String token) {
		Claims claims = Jwts
				.parserBuilder()
				.setSigningKey(getSignKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
		return claims.getSubject();
	}
	
	
	
	
	
	
	/* refresh Token part*/
	

}
