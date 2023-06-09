package com.minutes.jpa.hibernate.demojpa.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.minutes.jpa.hibernate.demojpa.entity.Course;
import com.minutes.jpa.hibernate.demojpa.entity.Review;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class CourseRepository {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	EntityManager em;
	
	public Course findById(Long id) {
		return em.find(Course.class, id);
	}
	
	public void deleteById(Long id) {
		Course course = findById(id);
		em.remove(course);
	}
	
	public Course save(Course course) {
		if(course.getId() == null) {
			em.persist(course);
		}
		else {
			em.merge(course);
		}
		return course;
	}
	
	public void playWithEntityManager() {
		Course course1 = new Course("Web Services in 100 Steps");
		em.persist(course1);
		
		Course course2 = findById(10001L);
		course2.setName("JPA iN 50 Steps - Updated");
	}

	public void addHardcodedReviewsForCourse() {
		//get the course 10003
		Course course = findById(10003L);
		logger.info("course.getReviews() -> {}",course.getReviews());
		//add 2 reviews to it
		Review review1 = new Review("5","Great Hands-on Stuff");
		Review review2 = new Review("5","Hatsoff.");
		
		//setting the relationship
		course.addReview(review1);
		review1.setCourse(course);
		course.addReview(review2);
		review2.setCourse(course);
		
		//save it to the database
		em.persist(review1);
		em.persist(review2);
		
	}
	
	public void addReviewsForCourse(Long courseId, List<Review> reviews) {
		//get the course 10003
		Course course = findById(courseId);
		logger.info("course.getReviews() -> {}",course.getReviews());
		
		for(Review review : reviews) {
		//setting the relationship
		course.addReview(review);
		review.setCourse(course);
		
        //save it to the database
		em.persist(review);
		
		}
	}
	
}
