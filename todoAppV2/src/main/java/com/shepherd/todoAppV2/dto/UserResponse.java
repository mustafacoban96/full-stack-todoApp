package com.shepherd.todoAppV2.dto;

import java.util.Set;

import com.shepherd.todoAppV2.models.Role;

public record UserResponse(
		Long userId,
		String name,
		String username,
		Set<Role> roles
		) {
	
	
	

}
