package com.bdtv.ms.ecom.product.service.service;

import java.util.List;


import com.bdtv.ms.ecom.product.service.entity.Product;

public interface ProductService {

	Product createProduct(Product product);
	
	Product getProductById(Long productId);
	
	List<Product> getProducts();
	
	void deleteProductById(Long productId);
	
	Product updateProduct(Long productId, Product product);
}
