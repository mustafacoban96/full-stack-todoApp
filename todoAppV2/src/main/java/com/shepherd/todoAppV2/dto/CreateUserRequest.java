package com.shepherd.todoAppV2.dto;

import java.util.Set;

import com.shepherd.todoAppV2.models.Role;

import lombok.Builder;


@Builder
public record CreateUserRequest(
		
		String name,
		String username,
		String password,
		String confirm_password,
		Set<Role> authorities
		
		) {

}
