package com.ems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ems.entities.Employee;
import com.ems.service.EmployeeService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	//depcy
	@Autowired
	private EmployeeService empService;
	
/*
 * URL 
http://localhost:8080/ems/employees/list
Method = POST
In payload 
deptId=3
Resp - List all emps under specified department id.
 */
	 @RequestMapping("/list")
	 public String renderEmpList(Model map,@RequestParam Long deptId,HttpSession session) {
		 //Long deptId=Long.parseLong(request.getParameter("deptId"));
		 //save dept id under sesiso  scope
		 session.setAttribute("dept_id", deptId);
		 System.out.println("in emp list "+map+" "+deptId);
		 //get emp list from service layer & add it under model map
		 map.addAttribute("emp_list", empService.getEmpsByDepartmentId(deptId));
		 return "emps/list";		 		
	 }
	 /*
	  * Handler rets LVN (map) -> D.S
	  * D.S sends -> LVN -> V.R -> AVN -> D.S
	  * D.S adds model attr unde rq scope -> forwards to JSP
	  */
	 
	 /*
	  * After clicking on delete link
URL 
http://localhost:8080/ems/employees/delete?empId=6
Method  - GET
Resp - soft delete emp details

	  */
	 @GetMapping("/delete")
	 public String softDeleteEmpDetails(Model map , @RequestParam Long empId,HttpSession hs)
	 {
		 System.out.println("in soft del emp "+empId);
		 //get dept id from session scope
		 Long did=(Long) hs.getAttribute("dept_id");
		 //invoke service layer -> get message -> model attribute
		 map.addAttribute("message",empService.deleteEmpDetails(empId));
		 return "redirect:/employees/list?deptId="+did;		
	 }
	 /*
	  * URL  http://host:port/ctx/employees/update?empId=9
		Method = GET
	  */
	 @GetMapping("/update")
	 public String renderUpdateForm(@RequestParam Long empId,Model map)
	 {
		 System.out.println("in render update form "+empId);
		 //call service method to get emp details by id from DB -> add it under model attr map
		 map.addAttribute("emp_details", empService.getEmpDetailsById(empId));
		 return "emps/edit";
		 
	 }
	 /*
	  * URL - http://localhost:8080/ems/employees/update?empId=9
	  * Method- POST
	  */
	 @PostMapping("/update")
	 public String processUpdateForm(@RequestParam Long empId ,@ModelAttribute(name = "emp_details") Employee emp,HttpSession session) {
		 System.out.println("in process update form "+empId+" "+emp);
		 Long deptId=(Long) session.getAttribute("dept_id");
		 //invoke service layer to update existing details
		 System.out.println(empService.updateEmpDetails(empId,emp));
		 //redirect emp list
		 return "redirect:/employees/list?deptId="+deptId;
		 
	 }
	
}
