package com.shepherd.todoAppV2.dto;

import java.util.Set;

import com.shepherd.todoAppV2.models.Role;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;


@Builder
public record CreateUserRequest(
		
		
		@NotBlank(message = "name is mandatory")
		@NotNull(message = "Invalid Name: Name is null")
		String name,
		
		@NotBlank(message = "username is mandatory")
		@NotNull(message = "Invalid Name: Name is null")
		String username,
		
		@NotBlank(message = "password is mandatory")
		@NotNull(message = "Invalid password")
		String password,
		
		@NotBlank(message = "confirming password is mandatory")
		@NotNull(message = "Invalid confirming")
		String confirm_password,
		Set<Role> authorities
		/*
		 * 
		 * https://salithachathuranga94.medium.com/validation-and-exception-handling-in-spring-boot-51597b580ffd
		 * https://medium.com/@mibatman01/spring-boot-validation-and-exception-handling-2023-edition-5df8c0c5ec6a
		 * https://medium.com/@bubu.tripathy/building-dynamic-spring-boot-validators-a066e6996df6
		 * https://medium.com/@bereketberhe27/spring-boot-custom-validation-7af89a64f805
		 * https://stackoverflow.com/questions/42280355/spring-rest-api-validation-should-be-in-dto-or-in-entity
		 * https://snyk.io/blog/guide-to-input-validation-with-spring-boot/
		 * https://stackoverflow.com/questions/65400172/validating-password-and-confirmed-password-spring-boot
		 * */
		) {

}
