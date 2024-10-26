package com.lorenzomiscoli.spring_security_basic_auth_db.configuration;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.lorenzomiscoli.spring_security_basic_auth_db.entities.User;
import com.lorenzomiscoli.spring_security_basic_auth_db.repositories.UserRepository;

import jakarta.annotation.PostConstruct;

@Component
public class UserInsertHandler {

	private final UserRepository userRepo;

	private final PasswordEncoder encoder;

	public UserInsertHandler(UserRepository userRepo, PasswordEncoder encoder) {
		this.userRepo = userRepo;
		this.encoder = encoder;
	}

	@PostConstruct
	public void insertUser() {
		var user = new User();
		user.setId(1);
		user.setUsername("user");
		user.setPassword(encoder.encode("userPassword"));
		userRepo.save(user);
	}

}
