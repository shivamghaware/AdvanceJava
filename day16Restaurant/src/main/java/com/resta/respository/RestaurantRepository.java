package com.resta.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.resta.entities.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

}
