package com.todoApp.service;

import com.todoApp.dto.TodoDto;
import com.todoApp.dto.TodoResponse;
import com.todoApp.models.Todo;

public interface TodoService {
	
	Todo createTodo(TodoDto todoDto);
	TodoResponse getAllTodo(int pageNo, int pageSize);
	TodoDto updateTodo(TodoDto todoDto,int id);
	void deleteTodo(int id);

}
