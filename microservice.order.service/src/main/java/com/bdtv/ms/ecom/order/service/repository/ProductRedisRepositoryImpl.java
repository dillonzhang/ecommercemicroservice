package com.bdtv.ms.ecom.order.service.repository;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.bdtv.ms.ecom.order.service.data.Product;

@Repository
public class ProductRedisRepositoryImpl implements ProductRedisRepository {

	private static final String HASH_NAME = "product::com.bdtv.ms.ecom.order.service.data.Product";

	@Autowired
	private RedisTemplate<String, Product> redisTemplate;
	private HashOperations<String, String, Product> hashOperations;

	public ProductRedisRepositoryImpl() {
		super();
	}

	@PostConstruct
	private void init() {
		this.hashOperations = redisTemplate.opsForHash();
	}

	@Override
	public void saveProduct(Product p) {
		hashOperations.put(HASH_NAME + p.getId(), HASH_NAME + p.getId(), p);
	}

	@Override
	public void updateProduct(Product p) {
		hashOperations.put(HASH_NAME + p.getId(), HASH_NAME + p.getId(), p);
	}

	@Override
	public void deleteProduct(Product p) {
		hashOperations.delete(HASH_NAME + p.getId(), p.getId());
	}

	@Override
	public Product findProduct(Long productId) {
		return hashOperations.get(HASH_NAME + productId, productId);
	}

}
