package com.todoApp.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;
	private String password;
	
	
	/*
	 * Hibernate has few fetching strategies to optimize the Hibernate generated select statement, so that it can be as efficient as possible. 
	 * The fetching strategy is declared in the mapping relationship to define how Hibernate fetch its related collections and entities.
	 * https://mkyong.com/hibernate/hibernate-fetching-strategies-examples/
	 * https://howtodoinjava.com/hibernate/hibernate-jpa-cascade-types/
	 * https://blog.stackademic.com/the-complete-guide-to-spring-data-jpa-building-a-bookstore-application-from-scratch-part-iii-8a1de3bc9949
	 * 
	 * 
	 * */ 
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name = "user_roles",
			joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name="role_id",referencedColumnName = "id")
			)
	private List<Role> roles = new ArrayList<>();
	

}
