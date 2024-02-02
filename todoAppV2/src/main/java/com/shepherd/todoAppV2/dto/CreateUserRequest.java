package com.shepherd.todoAppV2.dto;

import java.util.Set;

import com.shepherd.todoAppV2.models.Role;

import lombok.Builder;


@Builder
public record CreateUserRequest(
		
		String name,
		String username,
		String password,
		Set<Role> authorities
		
		) {

}
