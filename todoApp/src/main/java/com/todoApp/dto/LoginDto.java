package com.todoApp.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginDto {
	
	@Valid
	
	@NotBlank(message = "username can not include space")
	@NotNull(message = "username is mandatory")
	@Size(min = 2, max = 50, message = "please enter a valid username")
	private String username;
	
	@NotBlank(message = "username can not include space")
	@NotNull(message = "username is mandatory")
	@Size(min = 6, max = 12, message = "password's length  between 6-12")
	private String password;
}
