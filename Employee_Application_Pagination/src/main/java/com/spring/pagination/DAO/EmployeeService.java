package com.spring.pagination.DAO;

import org.springframework.data.domain.Page;

import com.spring.pagination.model.Employee;

public interface EmployeeService {

	void saveEmployee(Employee employee);

	Iterable<Employee> findEmployees();

	Employee getEmployeeById(Long id);

	void deleteEmployee(Long id);

	Page<Employee> findPage(int pageNo, int pageSize, String sortBy);
}
