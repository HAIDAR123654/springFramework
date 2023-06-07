package com.springMysqlConnect.learnspringdatajpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springMysqlConnect.learnspringdatajpa.entities.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, String>{

}
