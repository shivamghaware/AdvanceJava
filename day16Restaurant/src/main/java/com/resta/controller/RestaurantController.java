package com.resta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resta.entities.Restaurant;
import com.resta.services.RestaurantService;

@RestController
@RequestMapping("/")
@CrossOrigin
public class RestaurantController {
	
	@Autowired
	private RestaurantService restaurantService;

	@GetMapping
	public List<Restaurant> getAllRestaurants(){
		return restaurantService.getAllRestaurants();
	}
	
	@GetMapping("/{restau_id}")
	public Restaurant getRestaurantsByID(@PathVariable Long restau_id){
		return restaurantService.getRestaurantById(restau_id);
	}
	
	@PostMapping
	public String AddNewRestaurants(@RequestBody Restaurant restaurant) {
		return restaurantService.createNewRestaurant(restaurant);
	}
	
	@PutMapping("/{restau_id}")
	public String updateRestaurants(@PathVariable Long restau_id,@RequestBody Restaurant restaurant) {
		return restaurantService.updateExistingRestaurant(restau_id,restaurant);
	}
	
	@DeleteMapping("/{restau_id}")
	public String deleteRestaurants(@PathVariable Long restau_id) {
		return restaurantService.deleteExistingRestaurant(restau_id);
	}
	
	@DeleteMapping
	public String inActivateRestaurants(@RequestBody Long restau_id) {
		return restaurantService.inActivateExistingRestaurant(restau_id);
	}
	
}
