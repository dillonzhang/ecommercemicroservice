package com.bdtv.ms.ecom.order.service.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bdtv.ms.ecom.order.service.service.exception.ResourceNotFoundException;
import com.bdtv.ms.ecom.order.service.data.Product;
import com.bdtv.ms.ecom.order.service.entity.Order;
import com.bdtv.ms.ecom.order.service.repository.OrderRepository;
import com.bdtv.ms.ecom.order.service.service.OrderService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class DefaultOrderService implements OrderService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public Order createOrder(Order order) {
		return orderRepository.save(order);
	}

	@Override
	public Order getOrderById(Long orderId) {
		return orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderId));
	}

	@Override
	public List<Order> getOrders() {
		return orderRepository.findAll();
	}

	@Override
	public void deleteOrderById(Long orderId) {
		Order o = getOrderById(orderId);
		orderRepository.delete(o);
	}

	@Override
	public Order updateOrder(Long orderId, Order order) {
		Order o = getOrderById(orderId);
		o.setCustomerId(order.getCustomerId());
		o.setName(order.getName());
		orderRepository.save(o);
		return o;
	}

	@HystrixCommand(fallbackMethod = "getProductbyIdFallback")
	@Override
	public Product getProductById(Long productId) {
		return restTemplate.getForObject("http://microservice.product.service/productapi/product/"+ productId, Product.class);
	}
	
	protected Product getProductbyIdFallback(Long productId)
	{
		Product p = new Product();
		p.setCode("001");
		p.setName("Fake One");
		p.setDescription("this is a fake product for fallback");
		return p;
	}

}
