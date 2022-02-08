package com.learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.dto.Register;

@Repository
public interface UserRepository extends JpaRepository<Register, Integer> {

	// Used for duplication check
	Boolean existsByEmail(String email);
	
	// Used for authentication check
	Boolean existsByEmailAndPassword(String email, String password);
}
