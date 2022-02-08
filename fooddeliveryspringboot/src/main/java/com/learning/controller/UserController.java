package com.learning.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learning.dto.Register;
import com.learning.exception.AlreadyExistsException;
import com.learning.exception.IdNotFoundException;
import com.learning.repository.UserRepository;
import com.learning.service.UserService;

@RestController
public class UserController {

	
	@Autowired
	UserService userService = null;

	@Autowired
	UserRepository userRepository = null;

	
	
	@PostMapping("/register")
	public ResponseEntity<?> addUser(@Valid @RequestBody Register register) throws AlreadyExistsException {

		Register result = userService.addUser(register);
		System.out.println(result);

		return ResponseEntity.status(201).body(result);
	}

	
	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody Map<String, String> userCred) {

		Boolean authUser = userRepository.existsByEmailAndPassword(userCred.get("email"), userCred.get("password"));
		Map<String, String> map = new HashMap<String, String>();

		if (authUser == true) {
			map.put("Message", "Success");
			return ResponseEntity.ok(map);
		} else {
			map.put("Message", "Failure");
			return ResponseEntity.ok(map);
		}
	}

	
	@GetMapping("/users")
	public ResponseEntity<?> getAllUserDetails() {

		Optional<List<Register>> allUserDetails = userService.getAllUserDetails();

		if (allUserDetails.isEmpty()) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("Message", "No Record Found");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(map);
		}
		return ResponseEntity.ok(allUserDetails.get());
	}

	
	@GetMapping("/users/{id}")
	public ResponseEntity<?> getUserById(@PathVariable("id") Integer id) throws IdNotFoundException {

		Register userDetails = userService.getUserById(id);
		return ResponseEntity.ok(userDetails);
	}

	
	@PutMapping("/users/{id}")
	public ResponseEntity<?> updateUserDetails(@PathVariable("id") Integer id, @Valid @RequestBody Map<String, String> updateDetails) throws IdNotFoundException {
		
		Register userDetails = userService.getUserById(id);
		
		userDetails.setEmail(updateDetails.get("email"));
		userDetails.setPassword(updateDetails.get("password"));
		userDetails.setAddress(updateDetails.get("address"));
		
		Register updatedDetails = userRepository.save(userDetails);
		
		return ResponseEntity.ok(updatedDetails);
	}

	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> deleteUserByID(@PathVariable("id") Integer id) throws IdNotFoundException {

		String result = userService.deleteUserById(id);
		Map<String, String> map = new HashMap<String, String>();

		map.put("Message", result);
		return ResponseEntity.ok(map);
	}

}
