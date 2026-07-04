package com.simple.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/test")
public class TimeController {

	@GetMapping("/test1")
	public ModelAndView getDateTime1() {
	
		return new ModelAndView("date/date1", "server_ts",LocalDateTime.now());
	}
	
	@GetMapping("/test2")
	public String getDateTime2(Model model) {
		model.addAttribute("date", LocalDate.now());
		model.addAttribute("time", LocalTime.now());
		return "date/date2";
	}
}
