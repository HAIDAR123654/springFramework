package com.codedecode.demo.service;

import java.util.List;

import com.codedecode.demo.entity.Employee;

public interface EmployeeServiceInterface {

	Employee addEmployee(Employee employee);

	List<Employee> getAllEmployee();

	Employee getEmpById(Long empidL);

	void deleteEmpById(Long empidL);

	Employee updateEmployee(Employee employee);

	Employee partialUpdate(Long empidL, Employee employee);

	

	
}
