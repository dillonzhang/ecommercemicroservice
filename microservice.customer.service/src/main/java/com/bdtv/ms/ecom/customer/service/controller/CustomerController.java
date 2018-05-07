package com.bdtv.ms.ecom.customer.service.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.annotations.ApiIgnore;

import com.bdtv.ms.ecom.customer.service.data.Order;
import com.bdtv.ms.ecom.customer.service.entity.Customer;
import com.bdtv.ms.ecom.customer.service.service.CustomerService;

@RestController
@Api(value = "customer")
@RequestMapping("/customerapi")
public class CustomerController{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	private LoadBalancerClient loadBalancerClient;
	
	@Autowired
	private CustomerService customerService;
	
	@ApiOperation(value="get Customer By CustomerId", nickname= "getCustomerById", notes="this is the method to get customer by ID")
	@ApiImplicitParam(name = "customerId", value = "Entry CustomerID", required = true, dataType = "Long", paramType="path")
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable Long customerId) {
		LOGGER.info("call microservice.customer.service");
		try {
			Customer c = customerService.getCustomerById(customerId);
			return ResponseEntity.status(HttpStatus.OK).body(c);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	@ApiOperation(value="get Orders By CustomerId", nickname= "getOrdersByCustomerId", notes="this is the method to get current customer's orders")
	@ApiImplicitParam(name = "customerId", value = "Entry CustomerID", required = true, dataType = "Long", paramType="path")
	@GetMapping("/{customerId}/orders")
	public ResponseEntity<List<Order>> getOrdersByCustomerId(@PathVariable Long customerId) {
		try {
			List<Order> orderList = customerService.getOrdersByCustomerId(customerId);
			return ResponseEntity.status(HttpStatus.OK).body(orderList);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	@ApiIgnore
	@GetMapping("/log-customer-instance")
	public String logUserInstance() {
	  ServiceInstance serviceInstance = this.loadBalancerClient.choose("microservice.order.service");
	  CustomerController.LOGGER.info("{}:{}:{}", serviceInstance.getServiceId(), serviceInstance.getHost(), serviceInstance.getPort());
	  return String.format("%s:%s:%s", serviceInstance.getServiceId(), serviceInstance.getHost(), serviceInstance.getPort());
	}

	@ApiOperation(value="create a customer", nickname= "createCustomer", notes="this is the method to create a product")
	@ApiImplicitParam(name = "customer", value = "Entry Customer", required = true, dataType = "Customer")
	@PostMapping(value = "/customer")
	public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) {
		try {
			Customer c = customerService.createCustomer(customer);
			return ResponseEntity.status(HttpStatus.OK).body(c);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
}
