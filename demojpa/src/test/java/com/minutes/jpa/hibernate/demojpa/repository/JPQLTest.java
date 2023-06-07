package com.minutes.jpa.hibernate.demojpa.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.minutes.jpa.hibernate.demojpa.DemojpaApplication;
import com.minutes.jpa.hibernate.demojpa.entity.Course;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
 
@RunWith(SpringRunner.class)
@SpringBootTest(classes=DemojpaApplication.class)
class JPQLTest {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;
	
	@Test
	void jpql_basics() {
		Query query = em.createNamedQuery("query_get_all_courses");
		List resultList = query.getResultList();
	    logger.info("Select c From Course c -> {}",resultList);
	}
	
	@Test
	void jpql_typed() {
		 TypedQuery<Course>query = em.createNamedQuery("query_get_all_courses", Course.class);
		 List<Course> resultList = query.getResultList();
		 logger.info("Select c From Course c -> {}",resultList);
	}
	
	@Test
	void jpql_where() {
		 TypedQuery<Course>query = em.createNamedQuery("query_get_100_Step_courses", Course.class);
		 List<Course> resultList = query.getResultList();
		 logger.info("Select c From Course c where name like '%100 Steps' -> {}",resultList);
	}
	
	
 
	
}
