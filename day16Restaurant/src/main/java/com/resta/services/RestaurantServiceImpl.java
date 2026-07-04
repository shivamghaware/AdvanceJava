package com.resta.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resta.entities.Restaurant;
import com.resta.exceptions.ResourceNotFoundException;
import com.resta.respository.RestaurantRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RestaurantServiceImpl implements RestaurantService {
	
	@Autowired
	private RestaurantRepository restaurantRepository;

	@Override
	public List<Restaurant> getAllRestaurants() {
		return restaurantRepository.findAll();
	}

	@Override
	public String createNewRestaurant(Restaurant restaurant) {
		restaurantRepository.save(restaurant);
		return "Success with ID:"+restaurant.getId();
	}
	
	@Override
	public Restaurant getRestaurantById(Long restau_id) {
		return restaurantRepository.findById(restau_id).orElseThrow(()->new ResourceNotFoundException("Restaurant with ID: "+restau_id+" Not Found!"));
	}

	@Override
	public String updateExistingRestaurant(Long restau_id, Restaurant restaurant) {
		Restaurant updationRestaurant=getRestaurantById(restau_id);
		updationRestaurant.setAddress(restaurant.getAddress());
		updationRestaurant.setStatus(restaurant.isStatus());

		return "Successfully Updated";
	}
	
	@Override
	public String deleteExistingRestaurant(Long restau_id) {
		restaurantRepository.deleteById(restau_id);
		return "Successfully deleted";
	}

	@Override
	public String inActivateExistingRestaurant(Long restau_id) {
		Restaurant restaurant=getRestaurantById(restau_id);
		restaurant.setStatus(false);
		return "Successfully Inactivated";
	}

		

}
