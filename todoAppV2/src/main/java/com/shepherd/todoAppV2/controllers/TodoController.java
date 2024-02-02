package com.shepherd.todoAppV2.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todo")
public class TodoController {
	
	
	@GetMapping
	public String todo() {
		return "Todo home page";
	}

}
