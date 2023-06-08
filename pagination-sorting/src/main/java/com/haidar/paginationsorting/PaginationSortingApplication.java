package com.haidar.paginationsorting;

import com.haidar.paginationsorting.dto.APIResponse;
import com.haidar.paginationsorting.entity.Product;
import com.haidar.paginationsorting.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping(value = "/products")
@AllArgsConstructor
public class PaginationSortingApplication {

	private ProductService productService;

	@GetMapping
	private APIResponse<List<Product>> getProducts(){
		List<Product> allProduct = productService.findAllProduct();
		return new APIResponse<>(allProduct.size(), allProduct);
	}
	@GetMapping(value = "/{field}")
	private APIResponse<List<Product>> getProductsWithSort(@PathVariable String field){
		List<Product> allProduct = productService.findProductWithSorting(field);
		return new APIResponse<>(allProduct.size(), allProduct);
	}
	@GetMapping(value = "/pagination/{offset}/{pageSize}")
	private APIResponse<Page<Product>> getProductsWithPagination(@PathVariable int offset, @PathVariable int pageSize){
		Page<Product> allProduct = productService.findProductWithPagination(offset, pageSize);
		return new APIResponse<>(allProduct.getSize(), allProduct);
	}
	@GetMapping(value = "/paginationAndSort/{offset}/{pageSize}/{field}")
	private APIResponse<Page<Product>> getProductsWithPaginationAndSort(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field){
		Page<Product> allProduct = productService.findProductWithPaginationAndSorting(offset, pageSize, field);
		return new APIResponse<>(allProduct.getSize(), allProduct);
	}


	public static void main(String[] args) {
		SpringApplication.run(PaginationSortingApplication.class, args);
	}

}
