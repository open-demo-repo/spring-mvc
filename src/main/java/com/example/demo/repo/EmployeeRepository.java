package com.example.demo.repo;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.domain.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}