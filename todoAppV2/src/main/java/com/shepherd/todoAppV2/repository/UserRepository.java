package com.shepherd.todoAppV2.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shepherd.todoAppV2.models.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findByUsername(String username);

}
