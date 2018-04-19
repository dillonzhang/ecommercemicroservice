package com.bdtv.ms.ecom.order.service.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bdtv.ms.ecom.order.service.entity.Order;
import com.bdtv.ms.ecom.order.service.repository.OrderRepository;


@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderRepository orderRepository;
	
	@GetMapping("/{id}")
	public Optional<Order> findById(@PathVariable Long id) {
		Optional<Order> order = this.orderRepository.findById(id);
		return order;
	}
	
	@GetMapping("/customer/{customerId}")
	public List<Order> findAllByCustomerId(@PathVariable Long customerId) {
		// TODO, need create find query to replac this one
		List<Order> orders = this.orderRepository.findAll();
		return orders.stream().filter(o -> o.getCustomerId().equals(customerId)).collect(Collectors.toList());
	}
}
