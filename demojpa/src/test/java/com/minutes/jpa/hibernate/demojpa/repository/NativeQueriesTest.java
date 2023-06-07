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
import jakarta.transaction.Transactional;
 
@RunWith(SpringRunner.class)
@SpringBootTest(classes=DemojpaApplication.class)
class NativeQueriesTest {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;
	
	@Test
	void native_queries_basic() {
		Query query = em.createNativeQuery("SELECT * FROM COURSE", Course.class);
		List resultList = query.getResultList();
	    logger.info("SELECT * FROM COURSE -> {}",resultList);
	}
	
	@Test
	void native_queries_with_parameter() {
		Query query = em.createNativeQuery("SELECT * FROM COURSE where id=?", Course.class);
		query.setParameter(1, 10001L);
		List resultList = query.getResultList();
	    logger.info("SELECT * FROM COURSE where id=? -> {}",resultList);
	}
	
	@Test
	void native_queries_with_named_parameter() {
		Query query = em.createNativeQuery("SELECT * FROM COURSE where id=:id", Course.class);
		query.setParameter("id", 10001L);
		List resultList = query.getResultList();
	    logger.info("SELECT * FROM COURSE where id=:id -> {}",resultList);
	}
	
	@Test
	@Transactional
	void native_queries_to_update() {
		Query query = em.createNativeQuery("Update Course set last_updated_date=CURRENT_DATE()", Course.class);
		int noOfRowsUpdated = query.executeUpdate();
	    logger.info("noOfRowsUpdated -> {}",noOfRowsUpdated);
	}
	
	
 
	
}
