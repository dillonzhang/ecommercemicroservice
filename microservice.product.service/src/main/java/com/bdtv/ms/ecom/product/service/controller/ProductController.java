package com.bdtv.ms.ecom.product.service.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
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
@RefreshScope
@RequestMapping("/productapi")
public class ProductController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;

	@Value("${mockEnabled}")
	private String mockEnabled;
	
	@ApiOperation(value="get product by ID", notes="this is the method to retrive a product by ID")
	@ApiImplicitParam(name = "id", value = "product ID", required = true, dataType = "Long", paramType="path")
	@GetMapping("/product/{id}")
	public ResponseEntity<Product> findById(@PathVariable Long id) {
		LOGGER.debug("mockEnabled is {} ", this.mockEnabled);
		if ("true".equalsIgnoreCase(mockEnabled)){
			Product product = this.createMockProduct();
			return ResponseEntity.status(HttpStatus.OK).body(product);
		}

		try {
			Product p = this.productService.getProductById(id);
			return ResponseEntity.status(HttpStatus.OK).body(p);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	@ApiOperation(value="get all products", notes="this is the method to retrive all products")
	@GetMapping("/products")
	public ResponseEntity<List<Product>> findAllProducts() {
		try {
			List<Product> pList = this.productService.getProducts();
			return ResponseEntity.status(HttpStatus.OK).body(pList);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	@ApiOperation(value="create a product", notes="this is the method to create a product")
	@ApiImplicitParam(name = "product", value = "Entry Product", required = true, dataType = "Product")
	@PostMapping("/product")
	public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
		try {
			Product p = this.productService.createProduct(product);
			return ResponseEntity.status(HttpStatus.OK).body(p);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	@ApiOperation(value="update product information", notes="update the Product information according the ID and Product Model in the parameters")
	@ApiImplicitParams({
	       @ApiImplicitParam(name = "id", value = "product ID", required = true, dataType = "Long", paramType="path"),
	       @ApiImplicitParam(name = "product", value = "Entry Product", required = true, dataType = "Product")
	})
	@PutMapping("/product/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") Long productId, @Valid @RequestBody Product product) {
		try {
			Product p = this.productService.updateProduct(productId, product);
			return ResponseEntity.status(HttpStatus.OK).body(p);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	@ApiOperation(value="delete product by ID", notes="this is the method to delete a product by ID")
	@ApiImplicitParam(name = "id", value = "product ID", required = true, dataType = "Long", paramType="path")
	@DeleteMapping("/product/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable(value = "id") Long productId) {
		try {
			this.productService.deleteProductById(productId);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e)
		{
			LOGGER.error(e.getMessage(), e);
		} 
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	private Product createMockProduct(){
		Product product = new Product();
		product.setId(100L);
		product.setCode("001");
		product.setName("Mock Product");
		product.setDescription("Description for mock product");

		return product;
	}
}
