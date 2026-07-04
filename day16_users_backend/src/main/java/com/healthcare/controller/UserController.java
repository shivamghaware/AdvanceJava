package com.healthcare.controller;

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

import com.healthcare.entities.UserDetails;
import com.healthcare.service.UserService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/users")
public class UserController {
	//depcy
	@Autowired
	private UserService userService;
	
	/*
	 * Desc - get all users
	 * URI - /users
	 * Method - GET
	 * Request - no
	 * Resp - List<User> -> Json[] : Jackson 3rd party vendor used for HttpMessageConverter
	 * H.M Key - GET /users
	 * Value - ....UserController.getAllUsers
	 */
	@GetMapping
	public List<UserDetails> getAllUsers() {
		System.out.println("in get all users");
		return userService.getAllUserDetails();
	}
	/* 
	 * Desc - add new user 
	 * URI - /users
	 * Method - POST
	 * Request -  payload : request body
	 * Resp - message
	 * Handler Mapping  Key - POST /users
	 * Value - ....UserController.addNewUser
	 */
	@PostMapping
	public String addNewUser(@RequestBody UserDetails user)
	{
		System.out.println("in add user "+user);
		//invoke service layer method
		return userService.addNewUser(user);
	}
	/*
	 * Desc - Hard delete user details
	  URI - /users/{userId}
	 * Method - DELETE
	 * Request -  path var : user id
	 * Resp - message
	 * Handler Mapping  Key - DELETE /users/{userId}
	 * Value - ....UserController.deleteUserDetails
	 */
	@DeleteMapping("/{userId}")
	@Operation(description = "Hard delete user details")
	public String deleteUserDetails(@PathVariable Long userId) {
		System.out.println("in delete user detail "+userId);
		//invoke service layer
		return userService.deleteUserDetails(userId);
	}
	/*
	 * Desc - Get User details by id
	  URI - /users/{userId}
	 * Method - GET
	 * Request -  path var : user id
	 * Resp - user details
	 * Handler Mapping  Key - GET /users/{userId}
	 * Value - ....UserController.getUserDetails
	 */
	@GetMapping("/{uid}")
	public UserDetails getUserDetailsById(@PathVariable Long uid)
	{
		System.out.println("in get user "+uid);
		return userService.getUserDetails(uid);
	}
	/*
	 * Desc - Update User details 
	  URI - /users/{userId}
	 * Method - PUT
	 * Request -  path var : user id
	 * Resp - message
	 * Handler Mapping  Key - PUT /users/{userId}
	 * Value - ....UserController.updateUserDetails
	 */
	@PutMapping("/{userId}")
	@Operation(description = "Update user details")
	public String updateUser(@PathVariable Long userId,@RequestBody UserDetails details) {
		System.out.println("in update "+userId+" "+details);
		return userService.updateUserDetails(userId,details);
	}

}
