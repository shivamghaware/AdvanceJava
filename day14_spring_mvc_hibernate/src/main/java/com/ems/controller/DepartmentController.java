package com.ems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ems.service.DepartmentService;

@Controller
@RequestMapping("/departments")
public class DepartmentController {
	//depcy
	@Autowired
	private DepartmentService departmentService;
	public DepartmentController() {
		System.out.println("in constr "+getClass());
	}
	/*
	 * URI - /ctx/departments/list
	 * Method - GET
	 * Resp - explcitly : LVN ,implicit - Model attribute : model map
	 * 
	 */
	@GetMapping("/list")
	public String renderDepartmentList(Model modelMap) {
		System.out.println("in list depts "+modelMap);//{}
		modelMap.addAttribute("dept_list",departmentService.getAllDepartments());
		return "/depts/list";//AVN - /WEB-INF/views/depts/list.jsp
	}

}
