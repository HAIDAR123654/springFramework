package org.example.service;

import org.example.dto.Product;
import org.example.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveProductInfo() {
            for (int i = 1; i <= 10; i++) {
                Product product = new Product();
                product.setId(i);
                product.setName("Test Product " + i);
                productRepo.saveProduct(product);

                if (i == 7) {
                    throw new RuntimeException("some error occurred..");
                }
            }

    }
}
