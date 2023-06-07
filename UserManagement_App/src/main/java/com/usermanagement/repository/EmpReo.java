package com.usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usermanagement.entity.Employee;

public interface EmpReo extends JpaRepository<Employee, Integer> {

}
