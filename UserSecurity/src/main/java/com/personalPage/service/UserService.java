package com.personalPage.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.personalPage.model.User;

public interface UserService extends UserDetailsService {

	List<User> getAllUsers();
	User findByEmail(String email);
	Optional<User> getUserById(long id);
	User save(User registration);
	void deleteUserById(long id);
}
