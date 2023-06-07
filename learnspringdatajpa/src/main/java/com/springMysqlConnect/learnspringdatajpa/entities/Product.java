package com.springMysqlConnect.learnspringdatajpa.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Transactional
@Table(name = "jpa_product")
@Data
@NoArgsConstructor
public class Product {

	@Id
	private String pId;
	private String productName;
	
	@ManyToMany(mappedBy = "products",fetch = FetchType.EAGER)
	private List<Category> categories = new ArrayList<>();

	
}
