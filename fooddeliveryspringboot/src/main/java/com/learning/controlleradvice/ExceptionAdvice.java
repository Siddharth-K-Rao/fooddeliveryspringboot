package com.learning.controlleradvice;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.learning.exception.AlreadyExistsException;
import com.learning.exception.FoodTypeNotFoundException;
import com.learning.exception.IdNotFoundException;


@ControllerAdvice
public class ExceptionAdvice {
	
	@ExceptionHandler(AlreadyExistsException.class)
	public ResponseEntity<?> alreadyRecordExistsExceptionHandler(){
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("Message", "Record Already Exists");
		return ResponseEntity.badRequest().body(map);
	}
	
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<?> idNotFoundExceptionHandler(IdNotFoundException e){
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("Message", e.getMessage());
		return ResponseEntity.badRequest().body(map);
	}
	
	@ExceptionHandler(FoodTypeNotFoundException.class)
	public ResponseEntity<?> foodTypeNotFoundException() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("Message", "Sorry, Food Type Not Found");
		return ResponseEntity.badRequest().body(map);
	}
	
}
