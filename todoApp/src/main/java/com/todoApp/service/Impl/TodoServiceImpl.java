package com.todoApp.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.todoApp.dto.TodoDto;
import com.todoApp.dto.TodoResponse;
import com.todoApp.exceptions.TodoNotFoundException;
import com.todoApp.models.Todo;
import com.todoApp.repository.TodoRepository;
import com.todoApp.service.TodoService;


@Service
public class TodoServiceImpl implements TodoService{
	
	
	private TodoRepository todoRepository;
	
	@Autowired
	public TodoServiceImpl(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}

	@Override
	public Todo createTodo(TodoDto todoDto) {
		Todo todo = mapToEntity(todoDto);
		
		return todoRepository.save(todo);
	}
	
	
	
	@Override
	public TodoResponse getAllTodo(int pageNo, int pageSize) {
		
		
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		
		Page<Todo> todos = todoRepository.findAll(pageable);
		List<Todo> listOfTodos = todos.getContent();
		List<TodoDto> content = listOfTodos.stream().map(todo -> mapToDto(todo)).collect(Collectors.toList());
		
		TodoResponse todoResponse = new TodoResponse();
		todoResponse.setContent(content);
		todoResponse.setPageNo(todos.getNumber());
		todoResponse.setPageSize(todos.getSize());
		todoResponse.setTotalElements(todos.getTotalElements());
		todoResponse.setTotalPages(todos.getTotalPages());
		todoResponse.setLast(todos.isLast());
		
		
		return todoResponse;
	}
	
	@Override
	public TodoDto updateTodo(TodoDto todoDto, int id) {
		Todo todo = todoRepository.findById(id).orElseThrow(() -> new TodoNotFoundException("Activity could not be updated"));
		
		todo.setContent(todoDto.getContent());
		
		Todo updatedTodo = todoRepository.save(todo);
		
		
		return mapToDto(updatedTodo);
	}
	
	@Override
	public void deleteTodo(int id) {
		Todo todo = todoRepository.findById(id).orElseThrow(() -> new TodoNotFoundException("Activity could not delete..."));
		todoRepository.delete(todo);
		
	}
	
	private TodoDto mapToDto(Todo todo) {
		
		TodoDto todoDto = new TodoDto();
		todoDto.setContent(todo.getContent());
		todoDto.setId(todo.getId());
		todoDto.setCreateAt(todo.getCreateAt());
		
		return todoDto;
	}
	
	private Todo mapToEntity(TodoDto todoDto) {
		
		Todo todo = new Todo();
		todo.setId(todoDto.getId());
		todo.setContent(todoDto.getContent());
		todo.setCreateAt(todo.getCreateAt());
		
		return todo;
	}

	
	
	
	

}
