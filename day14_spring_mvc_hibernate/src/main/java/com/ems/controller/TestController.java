package com.ems.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller //mandatory
/*
 * To avoid repeatative base URL pattern
 * @RequestMapping - add class level base URL pattern
 */
@RequestMapping("/test")
public class TestController {
	public TestController() {
		System.out.println("in cnstr "+getClass());
	}
	@GetMapping("/test1")
	public ModelAndView testModelAndView() {
		System.out.println("in test m n v ");
		//ModelAndView(String lvn,String attrName,Object value)
		return new ModelAndView("dummy/display1", "server_ts",LocalDateTime.now());//  AVN - /WEB-INF/views/dummy/display1.jsp
	}
	/*
	 * URI - /ctx/test/test2
	 * Method - GET
	 * Model Attribute(data | result) - Using Model map
	 * Resp - explicit - LVN : String
	 * impl - model map(.....)
	 * 
	 */
	@GetMapping("/test2")
	public String testModelMap(Model modelMap) {
		System.out.println("in test model "+modelMap);//{}
		modelMap.addAttribute("server_date", LocalDate.now())
		.addAttribute("server_time", LocalTime.now());
		return "dummy/display2";
	}

}
