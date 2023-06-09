package com.minutes.jpa.hibernate.demojpa.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.minutes.jpa.hibernate.demojpa.entity.Course;
import com.minutes.jpa.hibernate.demojpa.entity.Passport;
import com.minutes.jpa.hibernate.demojpa.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class StudentRepository {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;
	
	public Student findById(Long id) {
		return em.find(Student.class, id);
	}
	
	public Student save(Student student) {
		if(student.getId() == null) {
			em.persist(student);
		}
		else {
			em.merge(student);
		}
		return student;
	}
	
	public void deleteById(Long id) {
		Student student = findById(id);
		em.remove(student);
	}
	
	public void saveStudentWithPassport() {
		Passport passport = new Passport("Z123456");
		em.persist(passport);
		Student student = new Student("Mike");
		student.setPassport(passport);
		em.persist(student);
	}
	
	public void someOperationToUnderstandPersistenceContext() {
		//Database Operation 1 - Retrieve student
		Student student = em.find(Student.class, 20001L);
		//Persistence Context (student)
		
		//Database Operation 2 - Retrieve passport
		Passport passport = student.getPassport();
		//Persistence Context (student, passport)
		
		//Database Operation 3 - update passport
	    passport.setNumber("E123457");
	    //Persistence Context (student, passport++)
	    
	    //Database Operation 4 - update student
	    student.setName("Ranga - updated");
	    //Persistence Context (student++, passport++)
		
	}
	
//	public void insertHardcodedStudentAndCourse() {
//		Student student = new Student("Jack");
//		Course course = new Course("Microservices in 100 Steps");
//		em.persist(student);
//		em.persist(course);
//		
//		student.addCourses(course);
//		course.addStudents(student);
//		em.persist(student);
//		
//	}
	public void insertStudentAndCourse(Student student, Course course) {
//		Student student = new Student("Jack");
//		Course course = new Course("Microservices in 100 Steps");
        student.addCourses(course);
		course.addStudents(student);
		em.persist(student);
		em.persist(course);
		
	}
}
