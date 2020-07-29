package com.personalPage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.personalPage.model.ConfirmationToken;

public interface TokenRepository extends JpaRepository<ConfirmationToken, Long> {
	 ConfirmationToken findByConfirmationToken(String confirmationToken);
}
