package com.todoApp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private CustomerDetailsService customerDetailsService;
	
	
	
	@Autowired
	public SecurityConfig(CustomerDetailsService customerDetailsService) {

		this.customerDetailsService = customerDetailsService;
	}


	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		.csrf(csrf -> csrf.disable())
		.authorizeHttpRequests( auth -> auth
				
				.requestMatchers("/todo/**")
				.permitAll()
				.anyRequest()
				.authenticated())
		.httpBasic(Customizer.withDefaults());
		return http.build();
	}
	
	
	@Bean
	public UserDetailsService users() {
		UserDetails admin = User.builder()
				.username("admin")
				.password("password")
				.roles("ADMIN")
				.build();
		
		UserDetails user = User.builder()
				.username("user")
				.password("password")
				.roles("USER")
				.build();
		
		return new InMemoryUserDetailsManager(admin,user);
	}
	
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}