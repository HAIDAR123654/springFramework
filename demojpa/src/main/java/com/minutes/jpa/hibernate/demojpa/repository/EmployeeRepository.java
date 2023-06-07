package com.minutes.jpa.hibernate.demojpa.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.minutes.jpa.hibernate.demojpa.entity.Employee;
import com.minutes.jpa.hibernate.demojpa.entity.FullTimeEmployee;
import com.minutes.jpa.hibernate.demojpa.entity.PartTimeEmployee;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class EmployeeRepository {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	public void insert(Employee employee) {
		em.persist(employee);
	}

	public List<PartTimeEmployee> retrieveAllPartEmployees(){
		return em.createQuery("select e from PartTimeEmployee e",PartTimeEmployee.class)
				.getResultList();
	}
	
	public List<FullTimeEmployee> retrieveAllFullEmployees(){
		return em.createQuery("select e from FullTimeEmployee e",FullTimeEmployee.class)
				.getResultList();
	}
}
