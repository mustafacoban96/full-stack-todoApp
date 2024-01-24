package com.todoApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todoApp.models.Todo;

public interface TodoRepository extends JpaRepository<Todo, Integer>{

}
