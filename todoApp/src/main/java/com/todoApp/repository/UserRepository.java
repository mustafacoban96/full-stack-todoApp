package com.todoApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todoApp.models.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{
	
	Optional<UserEntity> findByUsername(String username);
	Boolean existsByUsername(String username);

}
