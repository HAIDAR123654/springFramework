package com.minutes.jpa.hibernate.demojpa;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.minutes.jpa.hibernate.demojpa.entity.FullTimeEmployee;
import com.minutes.jpa.hibernate.demojpa.entity.PartTimeEmployee;
import com.minutes.jpa.hibernate.demojpa.repository.EmployeeRepository;

@SpringBootApplication
public class DemojpaApplication implements CommandLineRunner{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private EmployeeRepository employeerepository;
	
	public static void main(String[] args) {
		SpringApplication.run(DemojpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		studentrepository.saveStudentWithPassport();
//		repository.playWithEntityManager();
//		courserepository.addHardcodedReviewsForCourse();
		
//		List<Review> reviews = new ArrayList<>();
//		reviews.add(new Review("5","Great Hands-on Stuff"));
//		reviews.add(new Review("5","Hatsoff."));
		
//		courserepository.addReviewsForCourse(10003L, reviews);
//		studentrepository.insertHardcodedStudentAndCourse();
//		studentrepository.insertStudentAndCourse(new Student("Jack"),new Course("Microservices in 100 Steps"));
		employeerepository.insert(
	    		new PartTimeEmployee("Jill",new BigDecimal("50")));
		
		employeerepository.insert(
	    		new FullTimeEmployee("Jack",new BigDecimal("10000")));
	    logger.info("retrieveAllFullEmployees Employees -> {}",employeerepository.retrieveAllFullEmployees());
	    logger.info("retrieveAllPartEmployees Employees -> {}",employeerepository.retrieveAllPartEmployees());
	}

}
