package com.spring.pagination.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.pagination.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
