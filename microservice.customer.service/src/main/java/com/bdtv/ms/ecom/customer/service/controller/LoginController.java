package com.bdtv.ms.ecom.customer.service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bdtv.ms.ecom.customer.service.data.Auth;
import com.bdtv.ms.ecom.customer.service.entity.Customer;
import com.bdtv.ms.ecom.customer.service.service.CustomerService;

@RestController
@RequestMapping("/loginapi")
public class LoginController {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(LoginController.class);

	@Autowired
	private CustomerService customerService;

	@PostMapping("/login")
	public ResponseEntity<Auth> getCustomerById(@RequestBody Auth auth) {
		LOGGER.info("call microservice.customer.service");
		try {
			Customer c = customerService.getCustomerByEmail(auth
					.getCustomerEmail());
			// TODO hard code here for test
			Auth auth1 = new Auth();
			if (c != null && auth.getCustomerPwd().equals("123456")) {
				// TODO get oauth token and send back
				auth1 = customerService.getAuthByCustomer(c);
			}
			return ResponseEntity.status(HttpStatus.OK).body(auth1);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
				null);
	}
}
