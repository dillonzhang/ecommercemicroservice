package com.bdtv.ms.ecom.product.service.service.impl;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdtv.ms.ecom.product.service.entity.Product;
import com.bdtv.ms.ecom.product.service.repository.ProductRepository;
import com.bdtv.ms.ecom.product.service.service.ProductService;
import com.bdtv.ms.ecom.product.service.service.exception.ResourceNotFoundException;

@Service
public class DefaultProductService implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public Product createProduct(@NotNull Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product getProductById(@NotNull Long productId) {
		return productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
	}

	@Override
	public List<Product> getProducts() {
		return productRepository.findAll();
	}

	@Override
	public void deleteProductById(@NotNull Long productId) {
		Product p = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
		productRepository.delete(p);
	}

	@Override
	public Product updateProduct(@NotNull Long productId, @NotNull Product product) {
		Product p = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
		p.setCode(product.getCode());
		p.setName(product.getName());
		p.setDescription(product.getDescription());
		productRepository.save(p);
		return p;
	}
}
