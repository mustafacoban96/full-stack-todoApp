package com.shepherd.todoAppV2.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.shepherd.todoAppV2.dto.CreateUserRequest;
import com.shepherd.todoAppV2.dto.UserResponse;
import com.shepherd.todoAppV2.models.User;
import com.shepherd.todoAppV2.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;


@Service
public class UserService implements UserDetailsService{
	
	
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder;
	
	public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
		
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> user = userRepository.findByUsername(username);
		return user.orElseThrow(EntityNotFoundException::new);
	}

	
	public UserResponse getByUsername(String username) {
		Optional<User> user = userRepository.findByUsername(username);
		
		return UserToUserResponse(user);
	}
	
	public boolean isUsernameTaken(String username) {
		return userRepository.existsByUsername(username);
	}

	
	public User createUser(CreateUserRequest request) {
	
		User newUser = User.builder()
				.name(request.name())
				.username(request.username())
				.password(passwordEncoder.encode(request.password()))
				.authorities(request.authorities())
				.accountNonExpired(true)
				.credentialsNonExpired(true)
				.isEnabled(true)
				.accountNonLocked(true)
				.build();
				
		return userRepository.save(newUser);
	}
	
	
	private UserResponse UserToUserResponse(Optional<User> user) {
		User user2 = user.get();
		return new UserResponse(user2.getId(),user2.getName(),user2.getUsername(),user2.getAuthorities()) ;
		
	}
	
	
	
	
	
	
}
