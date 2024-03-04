package com.shepherd.todoAppV2.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shepherd.todoAppV2.dto.AuthRequest;
import com.shepherd.todoAppV2.dto.AuthResponse;
import com.shepherd.todoAppV2.dto.CreateUserRequest;
import com.shepherd.todoAppV2.dto.UserResponse;
import com.shepherd.todoAppV2.models.User;
import com.shepherd.todoAppV2.service.JwtService;
import com.shepherd.todoAppV2.service.UserService;

import lombok.extern.slf4j.Slf4j;



@RestController
@RequestMapping("/auth")
@Slf4j
@CrossOrigin(origins = "http://localhost:3000/")
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
	public ResponseEntity<String> register(@RequestBody CreateUserRequest request) {
		
		if(userService.isUsernameTaken(request.username())) {
			return new ResponseEntity<>("username is taken",HttpStatus.CONFLICT);
		}
		 
		if(!request.confirm_password().equals(request.password())) {
			return new ResponseEntity<>("Password didn't match",HttpStatus.BAD_REQUEST);
		}
		userService.createUser(request);
		return new ResponseEntity<>("user registered successfully",HttpStatus.CREATED);
	}
	
	
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> generateToken(@RequestBody AuthRequest request) {
		
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));
		if(authentication.isAuthenticated()) {
			String token = jwtService.generateToken(request.username());
			UserResponse user = userService.getByUsername(request.username());
			return new ResponseEntity<>(new AuthResponse(token,user),HttpStatus.OK);
		}
		log.info("invalid username " + request.username());
		throw new UsernameNotFoundException("Invalid username {}" + request.username());
	}

}
