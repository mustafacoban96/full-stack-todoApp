package com.todoApp.dto;

import java.util.Date;

import lombok.Data;



@Data
public class TodoDto {
	
	
	private long id;
	private String content;
	private Date createAt;

}
