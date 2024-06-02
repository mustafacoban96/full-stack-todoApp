package com.shepherd.todoAppV2.controllers;



import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.shepherd.todoAppV2.dto.CreateTodoRequest;
import com.shepherd.todoAppV2.models.Todo;
import com.shepherd.todoAppV2.service.TodoService;

import jakarta.validation.Valid;


@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/todo")
public class TodoController {
	
	private final TodoService todoService;
	
	
	public TodoController(TodoService todoService) {
		
		this.todoService = todoService;
	}
	
	
	@PostMapping("/{userId}/createTodo")
	public Todo createTodo(@Valid @RequestBody CreateTodoRequest request,@PathVariable(value="userId") Long userId) {
		return todoService.createTodo(request,userId);
	}
	
	

}
