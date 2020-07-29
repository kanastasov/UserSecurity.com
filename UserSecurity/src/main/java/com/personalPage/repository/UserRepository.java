package com.personalPage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.personalPage.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
}
