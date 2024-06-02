package com.shepherd.todoAppV2.service;

import com.shepherd.todoAppV2.dto.CreateTodoRequest;
import com.shepherd.todoAppV2.models.Todo;

public interface TodoService {
	
	public Todo createTodo(CreateTodoRequest todo, Long userId);
	

}
