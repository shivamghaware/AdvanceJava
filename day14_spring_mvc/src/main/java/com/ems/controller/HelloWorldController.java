package com.ems.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //mandatory class level annotation to declare a spring bean - req handling controller=Handler
//singleton & eager
public class HelloWorldController {
	public HelloWorldController() {
		System.out.println("in constr "+getClass());
	}
	/*
	 * To add req handling method to intercept GET request (doGet)
	 * In HandlerMapping Bean
	 * key - / + GET
	 * value - com.ems.controller.HelloWorldController.renderHomePage
	 */
	@GetMapping("/")
	public String renderHomePage() {
		System.out.println("in render home page");
		return "home";
	}
	/*
	 * Handler rets LVN -> D.S
	 */

}
