package com.shepherd.todoAppV2.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

	
	
	@GetMapping("/todo")
	public String adminTodo() {
		return "Hello admin todo";
	}
}
