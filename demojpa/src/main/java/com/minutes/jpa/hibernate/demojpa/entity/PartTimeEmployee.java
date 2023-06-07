package com.minutes.jpa.hibernate.demojpa.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;

@Entity
public class PartTimeEmployee extends Employee{

	protected PartTimeEmployee() {};
	private BigDecimal hourlyWage;
	
	public PartTimeEmployee(String name, BigDecimal hourlyWage) {
		super(name);
		this.hourlyWage = hourlyWage;
		
	}
	
}
