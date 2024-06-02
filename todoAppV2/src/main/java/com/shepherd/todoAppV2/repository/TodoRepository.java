package com.shepherd.todoAppV2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shepherd.todoAppV2.models.Todo;
import com.shepherd.todoAppV2.models.User;

public interface TodoRepository extends JpaRepository<Todo, Long>{
	List<User> findByUserId(Long userId);

}
