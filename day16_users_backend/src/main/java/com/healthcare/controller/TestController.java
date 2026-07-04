package com.healthcare.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // @Controller at the class level + @ResponseBody added implicitly on ret type
				// of all request handling methods - @GetMapping | @PostMapping....
//singleton & eager spring bean
@RequestMapping("/test")
public class TestController {
	public TestController() {
		System.out.println("in cnstr " + getClass());
	}
	/*
	 * URI - /test 
	 * Method - GET
	 * Request -nil
	 * Resp -  List<Integer>
	 */
	@GetMapping
	public List<Integer> renderList()
	{
		System.out.println("in render list");
		return List.of(10, 20, 30);
	}
}
