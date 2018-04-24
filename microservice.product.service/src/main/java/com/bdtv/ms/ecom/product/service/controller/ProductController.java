package com.bdtv.ms.ecom.product.service.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bdtv.ms.ecom.product.service.entity.Product;
import com.bdtv.ms.ecom.product.service.service.ProductService;

@RestController
@RequestMapping("/productapi")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/product/{id}")
	public Product findById(@PathVariable Long id) {
		return this.productService.getProductById(id);
	}
	
	@GetMapping("/products")
	public List<Product> findAllProducts() {
		return this.productService.getProducts();
	}
	
	@PostMapping("/product")
	public Product createProduct(@Valid @RequestBody Product product) {
		return this.productService.createProduct(product);
	}
	
	@PutMapping("/product/{id}")
	public Product updateProduct(@PathVariable(value = "id") Long productId, @Valid @RequestBody Product product) {
		return this.productService.updateProduct(productId, product);
	}
	
	@DeleteMapping("/product/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable(value = "id") Long productId) {
		return this.productService.deleteProductById(productId);
	}
}
