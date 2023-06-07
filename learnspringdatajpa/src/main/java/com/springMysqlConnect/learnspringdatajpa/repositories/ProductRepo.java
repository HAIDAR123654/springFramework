package com.springMysqlConnect.learnspringdatajpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springMysqlConnect.learnspringdatajpa.entities.Product;


public interface ProductRepo extends JpaRepository<Product, String>{

}
