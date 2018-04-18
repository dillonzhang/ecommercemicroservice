package com.bdtv.ms.ecom.customer.service.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
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
	
	  private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private RestTemplate restTemplate;
	  
	@Autowired
	private CustomerRepository productRepository;
	
	@Autowired
	private LoadBalancerClient loadBalancerClient;
	
	@GetMapping("/{id}")
	public Optional<Customer> findById(@PathVariable Long id) {
		Optional<Customer> customer = this.productRepository.findById(id);
		return customer;
	}
	
	@GetMapping("/{id}/orders")
	public List<Order> findAllById(@PathVariable Long id) {
		Order[] orderArray = restTemplate.getForObject("http://microservice.order.service/order/customer/"+ id, Order[].class);
		return Arrays.asList(orderArray);
	}
	
	@GetMapping("/log-customer-instance")
	public String logUserInstance() {
	  ServiceInstance serviceInstance = this.loadBalancerClient.choose("microservice.order.service");
	  // 打印当前选择的是哪个节点
	  CustomerController.LOGGER.info("{}:{}:{}", serviceInstance.getServiceId(), serviceInstance.getHost(), serviceInstance.getPort());
	  return String.format("%s:%s:%s", serviceInstance.getServiceId(), serviceInstance.getHost(), serviceInstance.getPort());
	}
}
