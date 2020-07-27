package com.personalPage.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.personalPage.model.User;
import com.personalPage.modelDto.UserRegistrationDto;

public interface UserService extends UserDetailsService {

	List<User> getAllUsers();
	User findByEmail(String email);
	Optional<User> getUserById(long id);
	User save(UserRegistrationDto registration);
	void deleteUserById(long id);
}
