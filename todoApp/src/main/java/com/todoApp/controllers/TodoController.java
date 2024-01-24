package com.todoApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.todoApp.dto.TodoDto;
import com.todoApp.dto.TodoResponse;
import com.todoApp.service.TodoService;

@RestController
@RequestMapping("/todo/")
public class TodoController {
	
	private TodoService todoService;
	
	@Autowired
	public TodoController(TodoService todoService) {
		this.todoService = todoService;
	}
	
	
	@PostMapping("create")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> createTodo(@RequestBody TodoDto todoDto){
		
		todoService.createTodo(todoDto);
		
		return new ResponseEntity<>("Activity was created successfully",HttpStatus.CREATED);
	}
	
	@GetMapping("todos")
	public ResponseEntity<TodoResponse> getAllTodos(
			@RequestParam(value = "pageNo",defaultValue = "0",required = false) int pageNo,
			@RequestParam(value = "pageSize",defaultValue = "10", required = false) int pageSize
			){
		return new ResponseEntity<>(todoService.getAllTodo(pageNo, pageSize),HttpStatus.OK);
	}
	
	@PutMapping("update/{id}")
	public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto,@PathVariable("id") int todoId){
		TodoDto responseDto = todoService.updateTodo(todoDto, todoId);
		return new ResponseEntity<>(responseDto,HttpStatus.OK);
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<String> deleteTodo(@PathVariable("id") int id){
		todoService.deleteTodo(id);
		return new ResponseEntity<>("Activity is deleted",HttpStatus.OK);
	}

}
