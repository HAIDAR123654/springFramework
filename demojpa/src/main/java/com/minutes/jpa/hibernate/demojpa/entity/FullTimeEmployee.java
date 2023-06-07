package com.minutes.jpa.hibernate.demojpa.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;

@Entity
public class FullTimeEmployee extends Employee{

	protected FullTimeEmployee() {};
	private BigDecimal salary;
	
	public FullTimeEmployee(String name, BigDecimal salary) {
		super(name);
		this.salary = salary;
		
	}
	
}
