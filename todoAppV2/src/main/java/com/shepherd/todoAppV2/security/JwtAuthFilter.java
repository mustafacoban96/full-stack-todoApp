package com.shepherd.todoAppV2.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.shepherd.todoAppV2.service.JwtService;
import com.shepherd.todoAppV2.service.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter{
	
	private final JwtService jwtService;
	private UserService userService;
	
	public JwtAuthFilter(JwtService jwtService, UserService userService) {
		this.jwtService = jwtService;
		this.userService = userService;
	}

	@Override
	protected void doFilterInternal(
			HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain filterChain)
			throws ServletException, IOException {
			
		String authHeader = request.getHeader("Authorization");
		String token = null;
		String username = null;
		 
		if(authHeader != null && authHeader.startsWith("Bearer ")) {
			token = authHeader.substring(7);
			username = jwtService.extractUsername(token);
		}
		
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails user = userService.loadUserByUsername(username);
			log.info("token validate " + username);
			
			if(jwtService.validateToken(token, user)) {
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		
		filterChain.doFilter(request, response);
		
	}
	
	
	/*
	 * https://docs.oracle.com/javase/8/docs/technotes/guides/security/jgss/tutorials/glossary.html
	 * 
	 * -*-*-*-*-*-*-*-*Class UsernamePasswordAuthenticationToken-*-*-*-*-*-*-*-*-*
	 * 
	 * To authorize access to resources, applications first need to authenticate the source of the request. 
	 * The JAAS framework defines the term subject to represent the source of a request. 
	 * A subject may be any entity, such as a person or service.
	 * A subject is represented by the javax.security.auth.Subject class.
	---Authentication----
	Authentication represents the process by which the identity of a subject is verified, and must be performed in a secure fashion; otherwise a perpetrator may impersonate others to gain access to a system. 
	Authentication typically involves the subject demonstrating some form of evidence to prove its identity. 
	Such evidence may be information only the subject would likely know or have (such as a password or fingerprint), or it may be information only the subject could produce (such as signed data using a private key).
	---Principal---
	Once authenticated, a Subject is populated with associated identities, or Principals (of type java.security.Principal). 
	A Subject may have many Principals. 
	For example, a person may have a name Principal ("John Doe") and an SSN Principal ("123-45-6789"), which distinguish it from other Subjects.
	---Credential---
	In addition to associated Principals, a Subject may own security-related attributes, which are referred to as credentials. 
	A credential may contain information used to authenticate the subject to new services. 
	Such credentials include passwords, Kerberos tickets, and public key certificates. 
	Credentials might also contain data that enables the subject to perform certain activities. 
	Cryptographic keys, for example, represent credentials that enable the subject to sign or encrypt data. 
	Public and private credential classes are not part of the core Java SE API. 
	Any class, therefore, can represent a credential.
	 * 
	 * 
	 * */

}
