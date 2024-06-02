package com.shepherd.todoAppV2.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public record TodoResponse(
		
		Long id,
		String content,
		@JsonFormat(pattern="yyyy-MM-dd")
		LocalDateTime createDate
		
		
		
		) {

}
