package com.shepherd.todoAppV2.service.impl;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.shepherd.todoAppV2.dto.CreateTodoRequest;
import com.shepherd.todoAppV2.dto.TodoResponse;
import com.shepherd.todoAppV2.mappers.TodoMapper;
import com.shepherd.todoAppV2.models.Todo;
import com.shepherd.todoAppV2.models.User;
import com.shepherd.todoAppV2.repository.TodoRepository;
import com.shepherd.todoAppV2.repository.UserRepository;
import com.shepherd.todoAppV2.service.TodoService;


@Service
public class TodoServiceImpl implements TodoService{
	
	private final TodoRepository todoRepository;
	private final UserRepository userRepository;
	private final TodoMapper todoMapper;
	
	public TodoServiceImpl(TodoRepository todoRepository,UserRepository userRepository,TodoMapper todoMapper) {
		this.todoRepository = todoRepository;
		this.userRepository = userRepository;
		this.todoMapper = todoMapper;
	}
	
	
	
	
	

	@Override
	public Todo createTodo(CreateTodoRequest request,Long userId) {
		Optional<User> userOptional = userRepository.findById(userId);
		 if (!userOptional.isPresent()) {
	            throw new RuntimeException("User not found with id: " + userId);
	        }
		User user = userOptional.get();
		Todo newTodo = Todo.builder()
				.content(request.content())
				.createDate(LocalDateTime.now())
				.user(user)
				.build();
		return todoRepository.save(newTodo);
	
	}






	@Override
	public List<TodoResponse> listTodo(Long userId) {
		List<Todo> myActivities = todoRepository.findAllByUserId(userId);
		
		
		/*
		 *  List<TodoResponse> responseList = myActivities.stream()
        .map(TodoMapper::todoEntityToDto)
        .collect(Collectors.toList());
		 * 
		 * */
		List<TodoResponse> responseList = myActivities.stream().map(activity -> todoMapper.todoEntityToDto(activity))
					.collect(Collectors.toList());
		
		return responseList;
	}
	
	
	
	
	

}
