package com.springMysqlConnect.learnspringdatajpa;
import java.util.List;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import com.springMysqlConnect.learnspringdatajpa.entities.Category;
import com.springMysqlConnect.learnspringdatajpa.entities.Product;
import com.springMysqlConnect.learnspringdatajpa.repositories.CategoryRepo;
import com.springMysqlConnect.learnspringdatajpa.repositories.ProductRepo;

@SpringBootApplication
public class LearnspringdatajpaApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(LearnspringdatajpaApplication.class, args);
		CategoryRepo categoryRepo = context.getBean(CategoryRepo.class);
		ProductRepo productRepo = context.getBean(ProductRepo.class);
		
//		Product product1 = new Product();
//		product1.setPId("pid1");
//		product1.setProductName("Iphone 14 max pro");
//		
//		Product product2 = new Product();
//		product2.setPId("pid2");
//		product2.setProductName("Samsung s22 ultra");
//		
//		Product product3 = new Product();
//		product3.setPId("pid3");
//		product3.setProductName("Samsung TV12341");
//		
//		Category category1 = new Category();
//		category1.setCId("cid1");
//		category1.setTitle("Electronics");
//		
//		Category category2 = new Category();
//		category2.setCId("cid2");
//		category2.setTitle("Mobile Phone");
//		
//		List<Product> Category1products = category1.getProducts();
//		Category1products.add(product1);
//		Category1products.add(product2);
//		Category1products.add(product3);
//		
//		List<Product> Category2products = category2.getProducts();
//		Category2products.add(product1);
//		Category2products.add(product2);
//		
//		categoryRepo.save(category1);
//		categoryRepo.save(category2);
		
		Category category = categoryRepo.findById("cid1").get();
		System.out.println(category.getProducts().size());
		
		Category category2 = categoryRepo.findById("cid2").get();
		System.out.println(category2.getProducts().size());
		
		Product product = productRepo.findById("pid1").get();
		System.out.println(product.getCategories().size());
	}
	
}
