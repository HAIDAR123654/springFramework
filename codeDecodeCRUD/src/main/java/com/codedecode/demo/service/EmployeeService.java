package com.codedecode.demo.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codedecode.demo.custom.exception.BusinessException;
import com.codedecode.demo.entity.Employee;
import com.codedecode.demo.repos.EmployeeCrudRepo;

@Service
public class EmployeeService implements EmployeeServiceInterface {

	@Autowired
	private EmployeeCrudRepo employeeCrudRepo;

	@Override
	public Employee addEmployee(Employee employee) {
		
		Pattern pattern = Pattern.compile("^[a-zA-Z]+$");
		Matcher matcher = pattern.matcher(employee.getName());
		boolean isMatch = matcher.matches();
		
		if (!isMatch) {
			throw new BusinessException("600", "Please send proper name, Besides english alphabates no charater are allowed");
		}
		
		if (employee.getName().isEmpty() || employee.getName().length() == 0 || isMatch == false) {
			throw new BusinessException("601", "Please send proper name, Its blank");
		}
		try {
			Employee savedEmployee = employeeCrudRepo.save(employee);
			return savedEmployee;
		} catch (IllegalArgumentException e) {
			throw new BusinessException("602", "given employee is null" + e.getMessage());
		} catch (Exception e) {
			throw new BusinessException("603",
					"Something went wrong in Service layer while saving the employee" + e.getMessage());
		}
	}

	@Override
	public List<Employee> getAllEmployee() {
		List<Employee> empList = null;
		try {
			empList = employeeCrudRepo.findAll();
		} catch (Exception e) {
			throw new BusinessException("605", " Something went wrong in service layes whuile fetching all employess.");
		}
		if (empList.isEmpty())
			throw new BusinessException("604", "Hey List is completely empty, we have nothing to show you");
		return empList;
	}

	@Override
	public Employee getEmpById(Long empidL) {
		Employee gottenEmp = null;
		try {
			gottenEmp = employeeCrudRepo.findById(empidL).get();
		} catch (IllegalArgumentException e) {
			throw new BusinessException("606",
					"given employee id is null, Please send some id to be searched" + e.getMessage());
		} catch (NoSuchElementException e) {
			throw new BusinessException("607", "given employee is not exist in database " + e.getMessage());
		}
		return gottenEmp;
	}

	@Override
	public void deleteEmpById(Long empidL) {
		try {
			employeeCrudRepo.findById(empidL).get();
			employeeCrudRepo.deleteById(empidL);
		} catch (NoSuchElementException e) {
			throw new BusinessException("609", "given employee is not exist in database " + e.getMessage());
		}
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		Employee savedEmp = null;
		try {
		employeeCrudRepo.findById(employee.getId()).get();
		savedEmp = employeeCrudRepo.save(employee);
		}catch(NoSuchElementException e) {
			throw new BusinessException("613", "given employee is not exist in database " + e.getMessage());
		}
		
		return savedEmp;
	}

	@Override
	public Employee partialUpdate(Long empidL, Employee employee) {
		Employee savedEmp = null;
		try {
		Employee existingEmp = employeeCrudRepo.findById(empidL).get();
		if(employee.getName() != null) {
			existingEmp.setName(employee.getName());
		}
		if(employee.getCity() != null) {
			existingEmp.setCity(employee.getCity());
		}
		savedEmp = employeeCrudRepo.save(existingEmp);
		}catch(NoSuchElementException e) {
			throw new BusinessException("616", "given employee is not exist in database " + e.getMessage());
		}
		
		return savedEmp;
	}
}
