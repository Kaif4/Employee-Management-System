package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

import java.util.List;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmployeeServiceImpl employeeServiceimpl;

	//login
	@GetMapping("/in")
	public String login(){
		return "login";
	}
	@GetMapping("/login")
	public String login_final(Model model)
	{

		return "login";
	}
	//Display list of Employees
	@GetMapping ("/index")
	public String viewHomePage(Model model)
	{
		List<Employee> listEmployees = employeeService.getAllEmployees();
		model.addAttribute("listEmployees",listEmployees);
		return "index";
	}
	
	@GetMapping("/showNewEmployeeForm")
	public String showNewEmployeeForm(Model model)
	{
		//create model attribute to bind form data
		Employee employee=new Employee();
		model.addAttribute("employee",employee);
		return "new_employee";
	}
	
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee employee)
	{
		//save employee to DB
		employeeService.saveEmployee(employee);
		return "redirect:/index";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") long id,Model model)
	{
		//get employee from service 
		Employee employee = employeeService.getEmployeeById(id);
		
		//set employee as a model attribute to pre-populate the form
		model.addAttribute("employee",employee);
		
		return "update_employee";	
	}
	
	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable ( value = "id") long id)
	{
		//call delete employee method
		this.employeeService.deleteEmployeeById(id);
		return "redirect:/index";
	}
}
