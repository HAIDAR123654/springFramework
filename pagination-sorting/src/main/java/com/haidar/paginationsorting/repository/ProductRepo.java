package com.haidar.paginationsorting.repository;

import com.haidar.paginationsorting.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Integer> {
}
