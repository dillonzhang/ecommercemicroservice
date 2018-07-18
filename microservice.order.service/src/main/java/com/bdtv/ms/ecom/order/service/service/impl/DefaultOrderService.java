package com.bdtv.ms.ecom.order.service.service.impl;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bdtv.ms.ecom.order.service.data.Product;
import com.bdtv.ms.ecom.order.service.entity.Order;
import com.bdtv.ms.ecom.order.service.entity.OrderEntry;
import com.bdtv.ms.ecom.order.service.repository.OrderEntryRepository;
import com.bdtv.ms.ecom.order.service.repository.OrderRepository;
import com.bdtv.ms.ecom.order.service.repository.ProductRedisRepository;
import com.bdtv.ms.ecom.order.service.service.OrderService;
import com.bdtv.ms.ecom.order.service.service.exception.ResourceNotFoundException;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class DefaultOrderService implements OrderService {

	private static final Logger logger = LoggerFactory
			.getLogger(DefaultOrderService.class);

	@Autowired
	private ProductRedisRepository productRedisRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private RedisTemplate<String, Order> redisTemplate;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderEntryRepository orderEntryRepository;

	@HystrixCommand(commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")})
	@Override
	public Order createOrder(Order order) {
		order.getOrderEntries().stream().forEach(oe -> oe.setOrder(order));
		Order savedOrder = orderRepository.save(order);
		redisTemplate.opsForHash().put(
				"order::com.bdtv.ms.ecom.order.service.entity.order:"
						+ order.getId(),
				"order::com.bdtv.ms.ecom.order.service.entity.order:"
						+ order.getId(), order);
		return savedOrder;
	}

	@HystrixCommand(commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")})
	@Override
	@Cacheable(value = "order", key = "'com.bdtv.ms.ecom.order.service.entity.order:'+#orderId")
	public Order getOrderById(Long orderId) {
		return orderRepository.findById(orderId).orElseThrow(
				() -> new ResourceNotFoundException("Order", "id", orderId));
	}

	@HystrixCommand(commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")})
	@Override
	public List<Order> getOrders() {
		return orderRepository.findAll();
	}

	@HystrixCommand(commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")})
	@Override
	@CacheEvict(value = "order", key = "'com.bdtv.ms.ecom.order.service.entity.order:'+#orderId")
	public void deleteOrderById(Long orderId) {
		Order o = getOrderById(orderId);
		orderRepository.delete(o);
	}

	@HystrixCommand(commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")})
	@Override
	@CachePut(value = "order", key = "'com.bdtv.ms.ecom.order.service.entity.order:'+#orderId")
	public Order updateOrder(Long orderId, Order order) {
		Order o = getOrderById(orderId);
		o.setCustomerId(order.getCustomerId());
		o.setName(order.getName());
		o.setSubTotal(order.getSubTotal());
		o.setTax(order.getTax());
		o.setTotalPrice(order.getTotalPrice());
		orderRepository.save(o);
		return o;
	}

	@HystrixCommand(fallbackMethod = "getProductbyIdFallback", threadPoolKey = "productByIdThreadPool", threadPoolProperties = {
			@HystrixProperty(name = "coreSize", value = "30"),
			@HystrixProperty(name = "maxQueueSize", value = "10")})
	@Override
	public Product getProductById(Long productId, String accessToken) {
		logger.debug(
				"Order service try to read Product {} from ProductService",
				productId);

		Product prod = null;

		prod = findProductFromRedisCache(productId);

		if (prod != null) {
			logger.debug("Find Product {} from Redis Cache :  {}", productId,
					prod);
			return prod;
		}

		logger.debug("Unable to locate Product from Redis Cache: {} ",
				productId);

		// TODO use Feign
		prod = restTemplate.getForObject(
				"http://microservice.product.service/productapi/product/"
						+ productId + "?access_token=" + accessToken,
				Product.class);

		chacheProductToRedisCache(prod);

		return prod;
	}

	private void chacheProductToRedisCache(Product prod) {
		try {
			productRedisRepository.saveProduct(prod);
		} catch (Exception e) {
			logger.warn(
					"There is an Exception when save Product to Redis Cache : ",
					e);
		}
	}

	private Product findProductFromRedisCache(Long productId) {
		try {
			return productRedisRepository.findProduct(productId);
		} catch (Exception e) {
			// Cache all the exception here. Redis issue should not block the
			// retrieve Product process
			logger.warn(
					"There is an Exception when retrieve Product form Redis Cache : ",
					e);
			return null;
		}
	}

	protected Product getProductbyIdFallback(Long productId, String accessToken) {
		Product p = new Product();
		p.setCode("001");
		p.setName("Fake One");
		p.setDescription("this is a fake product for fallback");
		return p;
	}

	@HystrixCommand(commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")})
	@Override
	public OrderEntry createOrderEntry(Long orderId, OrderEntry orderEntry) {
		return orderRepository
				.findById(orderId)
				.map(order -> {
					orderEntry.setOrder(order);
					return orderEntryRepository.save(orderEntry);
				})
				.orElseThrow(
						() -> new ResourceNotFoundException("Order", "id",
								orderId));
	}

	@HystrixCommand(commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")})
	@Override
	public Set<OrderEntry> getOrderEntriesByOrderId(Long orderId) {
		return orderRepository
				.findById(orderId)
				.map(order -> {
					return order.getOrderEntries();
				})
				.orElseThrow(
						() -> new ResourceNotFoundException("Order", "id",
								orderId));
	}

	@HystrixCommand
	@Override
	public OrderEntry updateOrderEntry(Long orderId, Long orderEntryId,
			OrderEntry orderEntry) {
		if (!orderRepository.existsById(orderId)) {
			throw new ResourceNotFoundException("Order", "id", orderId);
		}

		return orderEntryRepository
				.findById(orderEntryId)
				.map(oe -> {
					oe.setCount(orderEntry.getCount());
					oe.setSubTotal(orderEntry.getSubTotal());
					oe.setTax(orderEntry.getTax());
					oe.setTotalPrice(orderEntry.getTotalPrice());
					return orderEntryRepository.save(oe);
				})
				.orElseThrow(
						() -> new ResourceNotFoundException("OrderEntry", "id",
								orderEntryId));
	}

	@HystrixCommand(commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")})
	@Override
	public void deleteOrderEntry(Long orderId, Long orderEntryId) {
		if (!orderRepository.existsById(orderId)) {
			throw new ResourceNotFoundException("Order", "id", orderId);
		}

		orderEntryRepository
				.findById(orderEntryId)
				.map(oe -> {
					orderEntryRepository.delete(oe);
					return ResponseEntity.ok().build();
				})
				.orElseThrow(
						() -> new ResourceNotFoundException("OrderEntry", "id",
								orderEntryId));
	}

}
