package com.bdtv.ms.ecom.customer.service.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.bdtv.ms.ecom.customer.service.data.Auth;
import com.bdtv.ms.ecom.customer.service.data.Order;
import com.bdtv.ms.ecom.customer.service.entity.Customer;
import com.bdtv.ms.ecom.customer.service.repository.CustomerRepository;
import com.bdtv.ms.ecom.customer.service.service.CustomerService;
import com.bdtv.ms.ecom.customer.service.service.exception.ResourceNotFoundException;
import com.fasterxml.jackson.databind.JsonNode;
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
		return customerRepository.findById(customerId).orElseThrow(
				() -> new ResourceNotFoundException("Customer", "id",
						customerId));
	}

	@Override
	public List<Customer> getCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Boolean deleteCustomerById(@NotNull Long customerId) {
		Customer c = customerRepository.findById(customerId).orElseThrow(
				() -> new ResourceNotFoundException("Customer", "id",
						customerId));
		customerRepository.delete(c);
		return true;
	}

	@Override
	public Customer updateCustomer(@NotNull Long customerId,
			@NotNull Customer customer) {
		Customer c = customerRepository.findById(customerId).orElseThrow(
				() -> new ResourceNotFoundException("Customer", "id",
						customerId));
		c.setName(customer.getName());
		c.setEmail(customer.getEmail());
		customerRepository.save(c);
		return c;
	}

	@HystrixCommand(fallbackMethod = "findAllOrdersByCustomerIdFallback")
	@Override
	public List<Order> getOrdersByCustomerId(Long customerId) {
		Order[] orderArray = restTemplate.getForObject(
				"http://microservice.order.service/orderapi/customer/"
						+ customerId, Order[].class);
		return Arrays.asList(orderArray);
	}

	protected List<Order> findAllOrdersByCustomerIdFallback(Long id) {
		Order order = new Order();
		order.setId(Long.valueOf(007));
		order.setName("I am the fallback order, haha");
		List<Order> orders = new ArrayList<Order>();
		orders.add(order);
		return orders;
	}

	@Override
	public Customer getCustomerByEmail(String email) {
		return customerRepository.findByEmail(email)
				.orElseThrow(
						() -> new ResourceNotFoundException("Customer",
								"email", email));
	}

	@Override
	public Auth getAuthByCustomer(Customer customer) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("client_id", "client_2");
		map.add("client_secret", "123456");
		map.add("grant_type", "password");
		map.add("username", "user_1");
		map.add("password", "123456");
		map.add("scope", "select");

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(
				map, headers);
		ResponseEntity<JsonNode> response = restTemplate.postForEntity(
				"http://microservice.auth.service/oauth/token", request,
				JsonNode.class);
		JsonNode tokenNode = response.getBody();
		Auth a = new Auth();
		a.setAccessToken(tokenNode.get("access_token").toString());
		a.setRefreshToken(tokenNode.get("refresh_token").toString());
		a.setTokenType(tokenNode.get("token_type").toString());
		return a;
	}

}
