package com.minutes.jpa.hibernate.demojpa.repository;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.minutes.jpa.hibernate.demojpa.DemojpaApplication;
import com.minutes.jpa.hibernate.demojpa.entity.Passport;
import com.minutes.jpa.hibernate.demojpa.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
 
@RunWith(SpringRunner.class)
@SpringBootTest(classes=DemojpaApplication.class)
class StudentRepositoryTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	StudentRepository repository;
	
	@Autowired
	EntityManager em;
	
	@Test
	void someTest() {
		repository.someOperationToUnderstandPersistenceContext();
		
	}
	
	@Test
	@Transactional
	void retrieveStudentAndPassportDetails() {
		Student student = em.find(Student.class, 20001L);
		logger.info("student -> {}",student);
		logger.info("passport -> {}",student.getPassport());
	}
	
	@Test
	@Transactional
	void retrievePassportAndStudentDetails() {
		Passport passport = em.find(Passport.class, 40001L);
		logger.info("passport -> {}",passport);
		logger.info("student -> {}",passport.getStudent());
	}
	
	@Test
	@Transactional
	void retrieveStudentAndCourse() {
		Student student = em.find(Student.class, 20001L);
		logger.info("student -> {}",student);
		logger.info("Courses -> {}",student.getCourses());
	}
	
	
}
