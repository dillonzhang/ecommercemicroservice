package com.bdtv.ms.ecom.microservice.product.service.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bdtv.ms.ecom.microservice.product.service.entity.Product;
import com.bdtv.ms.ecom.microservice.product.service.repository.ProductRepository;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping("/{id}")
	public Optional<Product> findById(@PathVariable Long id) {
		Optional<Product> product = this.productRepository.findById(id);
		return product;
	}
}
