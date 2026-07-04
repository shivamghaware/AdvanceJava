package com.resta.services;
import java.util.List;
import com.resta.entities.Restaurant;

public interface RestaurantService {
	List<Restaurant> getAllRestaurants();

	String createNewRestaurant(Restaurant restaurant);
	
	Restaurant getRestaurantById(Long restau_id);

	String updateExistingRestaurant(Long restau_id, Restaurant restaurant);

	String deleteExistingRestaurant(Long restau_id);

	String inActivateExistingRestaurant(Long restau_id);	
	
}
