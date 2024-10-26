package com.lorenzomiscoli.spring_security_basic_auth_db.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.lorenzomiscoli.spring_security_basic_auth_db.entities.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	public Optional<User> findUserByUsername(String username);
	
}
