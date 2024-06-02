package com.shepherd.todoAppV2.mappers;

import org.springframework.stereotype.Component;

import com.shepherd.todoAppV2.dto.TodoResponse;
import com.shepherd.todoAppV2.models.Todo;


@Component
public class TodoMapper {
	
	
	public static  TodoResponse todoEntityToDto(Todo todo) {
		if(todo == null) {
			return null;
		}
		
		return new TodoResponse(todo.getId(),todo.getContent(), todo.getCreateDate());
	}
	
	
	
	
	
	
	

}
