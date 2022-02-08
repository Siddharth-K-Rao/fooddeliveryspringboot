package com.learning.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.learning.dto.Food;
import com.learning.exception.IdNotFoundException;

public interface FoodService {

	public Food addFood(Food food);
	public Food getFoodById(Integer id) throws IdNotFoundException;
	public String updateFoodDetails(Integer id, Food food);
	public String deleteFoodById(Integer id) throws IdNotFoundException;
	public Optional<List<Food>> getAllFoodDetails();
}
