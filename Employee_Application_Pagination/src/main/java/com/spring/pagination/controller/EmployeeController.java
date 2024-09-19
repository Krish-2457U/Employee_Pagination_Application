package com.spring.pagination.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.pagination.DAO.EmployeeService;
import com.spring.pagination.model.Employee;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

//	@ResponseBody
//	@GetMapping("/employees-test")
//	public Page<Employee> employeesTest() {
//		return employeeService.findPage(0, 5, "employeename");
//	}

	@GetMapping("/employees")
	public String employees(
			@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "5") int pageSize,
			@RequestParam(defaultValue = "employeename") String sortBy,
			Model model) {
		Page<Employee> employeePage = employeeService.findPage(pageNo, pageSize, sortBy);
		model.addAttribute("employeePage", employeePage);
		model.addAttribute("sortBy", sortBy);
		return "employees";
	}

	@GetMapping("/register")
	public String registration(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "registration";
	}

	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		employeeService.saveEmployee(employee);
		return "redirect:/employees";
	}

	@GetMapping("/updateEmployee/{id}")
	public String updateEmployee(Model model, @PathVariable Long id) {

		Employee employee = employeeService.getEmployeeById(id);
		model.addAttribute("employee", employee);
		return "updateForm";

	}

	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable Long id) {
		employeeService.deleteEmployee(id);
		return "redirect:/employees";
	}


}
