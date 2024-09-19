package com.spring.pagination.DAO;

import org.hibernate.FetchNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.spring.pagination.model.Employee;
import com.spring.pagination.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public void saveEmployee(Employee employee) {
		employeeRepository.save(employee);

	}

	@Override
	public Iterable<Employee> findEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(Long id) {

		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new FetchNotFoundException("Employee", id));
		return employee;

	}

	@Override
	public void deleteEmployee(Long id) {

		employeeRepository.deleteById(id);
	}

	@Override
	public Page<Employee> findPage(int pageNo, int pageSize, String sortBy) {

		Sort sort = Sort.by(Sort.Direction.ASC, sortBy);

		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

		return employeeRepository.findAll(pageable);
	}
}
