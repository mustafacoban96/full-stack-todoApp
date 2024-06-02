package com.shepherd.todoAppV2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateTodoRequest(
		
		@NotBlank(message = "content is required")
		@NotNull(message = "Invalid activity")
		@Size(min = 2, max = 200, message = "Invalid activity")
		String content
		
		
		
		) {

}
