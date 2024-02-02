package com.shepherd.todoAppV2.controllers;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shepherd.todoAppV2.dto.AuthRequest;
import com.shepherd.todoAppV2.dto.CreateUserRequest;
import com.shepherd.todoAppV2.models.User;
import com.shepherd.todoAppV2.service.JwtService;
import com.shepherd.todoAppV2.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthenticationController {
	
	private final UserService userService;
	private final JwtService jwtService;
	private AuthenticationManager authenticationManager;
	
	
	public AuthenticationController(UserService userService, JwtService jwtService,AuthenticationManager authenticationManager) {
		
		this.userService = userService;
		this.jwtService = jwtService;
		this.authenticationManager = authenticationManager;
	}
	
	@PostMapping("/register")
	public User register(@RequestBody CreateUserRequest request) {
		return userService.createUser(request);
	}
	
	@PostMapping("/login")
	public String generateToken(@RequestBody AuthRequest request) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));
		if(authentication.isAuthenticated()) {
			return jwtService.generateToken(request.username());
		}
		log.info("invalid username " + request.username());
		throw new UsernameNotFoundException("Invalid username {}" + request.username());
	}

}
