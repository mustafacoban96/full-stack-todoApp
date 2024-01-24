package com.todoApp.dto;

import java.util.List;

import lombok.Data;

@Data
public class TodoResponse {
	
	private List<TodoDto> content;
	private int pageNo;
	private int pageSize;
	private long totalElements;
	private int totalPages;
	private boolean last;
}
