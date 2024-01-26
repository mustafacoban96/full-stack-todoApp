package com.todoApp.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

public class GlobalExceptionHandler {
	
	
	
	@ExceptionHandler(TodoNotFoundException.class)
	public ResponseEntity<ErrorObject> handleTodoNotFound(TodoNotFoundException ex,WebRequest request){
		
		ErrorObject errorObject = new ErrorObject();
		
		
		errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
		errorObject.setMessage(ex.getMessage());
		errorObject.setTimestamp(new Date());
		
		return new ResponseEntity<ErrorObject>(errorObject,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorObject> handleUserNotFound(UserNotFoundException ex, WebRequest request){
		
		ErrorObject errorObject = new ErrorObject();
		
		
		errorObject.setMessage(ex.getMessage());
		errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
		errorObject.setTimestamp(new Date());
		
		return new ResponseEntity<ErrorObject>(errorObject,HttpStatus.NOT_FOUND);
	}
	
	

}
