package com.hms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hms.services.UserService;

@Controller
@RequestMapping("/auth")
public class LoginController {
	@Autowired
	UserService userservice;
	
	@GetMapping("/login")
	public String showLoginForm() {
		return "auth/login";
	}
	
	@PostMapping("/login") 
    public String authenticateUser(Model map,@RequestParam String email,@RequestParam String password) {
        System.out.println("Email received: " + email);
        map.addAttribute("user", userservice.authenticateUser(email, password));
        return "db/welcome"; 
    }
}
