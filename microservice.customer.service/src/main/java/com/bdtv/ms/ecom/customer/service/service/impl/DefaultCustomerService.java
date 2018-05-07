package com.bdtv.ms.ecom.customer.service.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bdtv.ms.ecom.customer.service.data.Order;
import com.bdtv.ms.ecom.customer.service.entity.Customer;
import com.bdtv.ms.ecom.customer.service.repository.CustomerRepository;
import com.bdtv.ms.ecom.customer.service.service.CustomerService;
import com.bdtv.ms.ecom.customer.service.service.exception.ResourceNotFoundException;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class DefaultCustomerService implements CustomerService {
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public Customer createCustomer(@NotNull Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Customer getCustomerById(@NotNull Long customerId) {
		return customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customerId));
	}

	@Override
	public List<Customer> getCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Boolean deleteCustomerById(@NotNull Long customerId) {
		Customer c = customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customerId));
		customerRepository.delete(c);
		return true;
	}

	@Override
	public Customer updateCustomer(@NotNull Long customerId, @NotNull Customer customer) {
		Customer c = customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customerId));
		c.setName(customer.getName());
		c.setEmail(customer.getEmail());
		customerRepository.save(c);
		return c;
	}

	@HystrixCommand(fallbackMethod = "findAllOrdersByCustomerIdFallback")
	@Override
	public List<Order> getOrdersByCustomerId(Long customerId) {
		Order[] orderArray = restTemplate.getForObject("http://microservice.order.service/orderapi/customer/"+ customerId, Order[].class);
		return Arrays.asList(orderArray);
	}
	
	protected List<Order> findAllOrdersByCustomerIdFallback(Long id)
	{
		Order order = new Order();
		order.setId(Long.valueOf(007));
		order.setName("I am the fallback order, haha");
		List<Order> orders =  new ArrayList<Order>();
		orders.add(order);
		return orders;
	}

}
