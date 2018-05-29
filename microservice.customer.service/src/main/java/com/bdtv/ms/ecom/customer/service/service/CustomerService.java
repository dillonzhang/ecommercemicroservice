package com.bdtv.ms.ecom.customer.service.service;

import java.util.List;

import com.bdtv.ms.ecom.customer.service.data.Auth;
import com.bdtv.ms.ecom.customer.service.data.Order;
import com.bdtv.ms.ecom.customer.service.entity.Customer;

public interface CustomerService {
	Customer createCustomer(Customer customer);

	Customer getCustomerById(Long customerId);

	Customer getCustomerByEmail(String email);

	List<Customer> getCustomers();

	Boolean deleteCustomerById(Long customerId);

	Customer updateCustomer(Long customerId, Customer customer);

	List<Order> getOrdersByCustomerId(Long customerId);

	Auth getAuthByCustomer(Customer customer);
}
