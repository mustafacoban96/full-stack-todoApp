package com.shepherd.todoAppV2.dto;


public record AuthResponse(
		String accsess_token,
	    UserResponse userInfo,
	    String type
		) {
	
	 public AuthResponse(String token,UserResponse userInfo) {
	        this(token,userInfo,"Bearer ");
	    }

	

}
