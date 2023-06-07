package com.minutes.jpa.hibernate.demojpa.repository;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.minutes.jpa.hibernate.demojpa.DemojpaApplication;
import com.minutes.jpa.hibernate.demojpa.entity.Course;
import com.minutes.jpa.hibernate.demojpa.entity.Review;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
 
@RunWith(SpringRunner.class)
@SpringBootTest(classes=DemojpaApplication.class)
class CourseRepositoryTest {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CourseRepository repository;
	
	@Autowired
	EntityManager em;
	
	@Test
	void findById_basics() {
		Course course = repository.findById(10001L);
		assertEquals("JPA in 50 Steps", course.getName());
	}
	
	@Test
	@DirtiesContext
	void deleteById_basics() {
		repository.deleteById(10002L);
		assertNull(repository.findById(10002L));
	}
	
	@Test
	@DirtiesContext
	void update_basics() {
		Course course = repository.findById(10001L);
		assertEquals("JPA in 50 Steps", course.getName());
		course.setName("JPA in 50 Steps - Updated");
		repository.save(course);
		
		Course course1 = repository.findById(10001L);
		assertEquals("JPA in 50 Steps - Updated", course1.getName());
		
	}
	
	@Test
	@DirtiesContext
	void save_basics() {
		Course course = repository.save(new Course("Spring MVC in 10 Steps"));
		Course course1 = repository.findById(course.getId());
		assertEquals("Spring MVC in 10 Steps", course1.getName());
		
	}
	
	@Test
	@DirtiesContext
	void playWithEntityManagerTest() {
		repository.playWithEntityManager();
		
	}
	
	@Test
	@Transactional
	void retrieveReviewsForCourse() {
		Course course = repository.findById(10001L);
		logger.info("{}",course.getReviews());
		
	}
	
	@Test
	@Transactional
	void retrieveCourseForReview() {
		Review review = em.find(Review.class, 50001L);
		logger.info("{}",review.getCourse());
		
	}
 
	
}
