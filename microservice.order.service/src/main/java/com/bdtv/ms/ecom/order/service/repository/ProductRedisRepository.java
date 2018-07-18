package com.bdtv.ms.ecom.order.service.repository;

import com.bdtv.ms.ecom.order.service.data.Product;

public interface ProductRedisRepository {
	void saveProduct(Product p);
	void updateProduct(Product p);
	void deleteProduct(Product p);
	Product findProduct(Long productId);
}
