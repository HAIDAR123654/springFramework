package com.haidar.paginationsorting.service;

import com.haidar.paginationsorting.entity.Product;
import com.haidar.paginationsorting.repository.ProductRepo;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepo productRepo;

    @PostConstruct
    public void initDB(){
        List<Product> products = IntStream.rangeClosed(1, 200).mapToObj(i -> new Product(
                "product" + i, new Random().nextInt(100), (long) new Random().nextInt(50000)))
                .toList();
        productRepo.saveAll(products);
    }

    public List<Product> findAllProduct(){
        return productRepo.findAll();
    }

    public List<Product> findProductWithSorting(String field){
        return productRepo.findAll(Sort.by(Sort.Direction.ASC, field));
    }

    public Page<Product> findProductWithPagination(int offset, int pageSize){
        return productRepo.findAll(PageRequest.of(offset, pageSize));
    }
    public Page<Product> findProductWithPaginationAndSorting(int offset, int pageSize, String field){
        return productRepo.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
    }
}
