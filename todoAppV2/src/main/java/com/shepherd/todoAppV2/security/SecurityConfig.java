package com.shepherd.todoAppV2.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.shepherd.todoAppV2.models.Role;
import com.shepherd.todoAppV2.service.TokenBlackListService;
import com.shepherd.todoAppV2.service.UserService;

import jakarta.servlet.http.HttpServletResponse;
//https://howtodoinjava.com/spring-security/jwt-auth-vuejs-spring-boot-security/
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	private final JwtAuthFilter jwtAuthFilter;
	private final UserService userService;
	private PasswordEncoder passwordEncoder;
	private final JwtAuthEntryPoint jwtAuthEntryPoint;
	private final TokenBlackListService tokenBlackListService;
	
	public SecurityConfig(JwtAuthFilter jwtAuthFilter, UserService userService, PasswordEncoder passwordEncoder,
			JwtAuthEntryPoint jwtAuthEntryPoint,TokenBlackListService tokenBlackListService) {
		
		this.jwtAuthFilter = jwtAuthFilter;
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
		this.jwtAuthEntryPoint = jwtAuthEntryPoint;
		this.tokenBlackListService = tokenBlackListService;
	}
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		return http
				.csrf(AbstractHttpConfigurer::disable)
				.exceptionHandling((exceptionHandling) -> exceptionHandling
						.authenticationEntryPoint(jwtAuthEntryPoint)
				)
				.authorizeHttpRequests(x -> 
						x
						.requestMatchers("/auth/**","/auth/register/**","/auth/login/**").permitAll()
						.requestMatchers("/admin/todo/**").hasRole(Role.ROLE_ADMIN.getValue())
						.requestMatchers("/todo/**").hasAnyRole(Role.ROLE_ADMIN.getValue(),Role.ROLE_USER.getValue())
						)
				.formLogin(AbstractHttpConfigurer::disable)
				.sessionManagement(x -> x.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authenticationProvider())
				.addFilterBefore(jwtAuthFilter,UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(new JwtBlacklistFilter(tokenBlackListService), JwtAuthFilter.class)
				.build();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userService);
		authenticationProvider.setPasswordEncoder(passwordEncoder);
		return authenticationProvider;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
		return authenticationConfiguration.getAuthenticationManager();
	}

}
