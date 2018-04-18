package com.bdtv.ms.ecom.customer.service.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;






import com.bdtv.ms.ecom.customer.service.data.Order;
import com.bdtv.ms.ecom.customer.service.entity.Customer;
import com.bdtv.ms.ecom.customer.service.repository.CustomerRepository;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private RestTemplate restTemplate;
	  
	@Autowired
	private CustomerRepository productRepository;
	
	@GetMapping("/{id}")
	public Optional<Customer> findById(@PathVariable Long id) {
		Optional<Customer> customer = this.productRepository.findById(id);
		return customer;
	}
	
	@GetMapping("/{id}/orders")
	public List<Order> findAllById(@PathVariable Long id) {
		Order[] orderArray = restTemplate.getForObject("http://localhost:8010/order/customer/"+ id, Order[].class);
		return Arrays.asList(orderArray);
	}
}
