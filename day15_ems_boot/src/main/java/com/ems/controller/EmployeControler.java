package com.ems.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller("/employees")
public class EmployeControler {
	
	@PostMapping("/list")
	public String getEmps() {
		
		return "emps";
	}

}
