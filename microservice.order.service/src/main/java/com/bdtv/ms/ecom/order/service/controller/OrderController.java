package com.bdtv.ms.ecom.order.service.controller;

import javax.validation.Valid;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bdtv.ms.ecom.order.service.data.Product;
import com.bdtv.ms.ecom.order.service.entity.Order;
import com.bdtv.ms.ecom.order.service.service.OrderService;


@RestController
@RequestMapping("/orderapi")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@ApiOperation(value="get Order By orderId", nickname= "getOrderById", notes="this is the method to get Order by ID")
	@ApiImplicitParam(name = "orderId", value = "Entry orderId", required = true, dataType = "Long", paramType="path")
	@GetMapping("/order/{orderId}")
	public ResponseEntity<Order> getOrderById(@PathVariable Long orderId) {
		Order order = this.orderService.getOrderById(orderId);
		return ResponseEntity.status(HttpStatus.OK).body(order);
	}
	
	@ApiOperation(value="get Product By product", nickname= "getProductById", notes="this is the method to get Product by ID")
	@ApiImplicitParam(name = "productId", value = "Entry productId", required = true, dataType = "Long", paramType="path")
	@GetMapping("/product/{productId}")
	public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
		Product product = this.orderService.getProductById(productId);
		return ResponseEntity.status(HttpStatus.OK).body(product);
	}
	
	@ApiOperation(value="create a order", nickname= "createOrder", notes="this is the method to create a order")
	@ApiImplicitParam(name = "order", value = "Entry order", required = true, dataType = "Order")
	@PostMapping("/order")
	public ResponseEntity<Order> createOrder(@Valid @RequestBody Order order) {
		Order o = this.orderService.createOrder(order);
		return ResponseEntity.status(HttpStatus.OK).body(o);
	}
	
	@ApiOperation(value="update order information", notes="update the order information according the ID and order Model in the parameters")
	@ApiImplicitParams({
	       @ApiImplicitParam(name = "id", value = "order ID", required = true, dataType = "Long", paramType="path"),
	       @ApiImplicitParam(name = "order", value = "Entry order", required = true, dataType = "Order")
	})
	@PutMapping("/order/{id}")
	public ResponseEntity<Order> updateOrder(@PathVariable(value = "id") Long orderId, @Valid @RequestBody Order order) {
		Order o = this.orderService.updateOrder(orderId, order);
		return ResponseEntity.status(HttpStatus.OK).body(o);
	}
	
	@ApiOperation(value="delete order information", notes="update the order information according the ID and order Model in the parameters")
	@ApiImplicitParam(name = "id", value = "order ID", required = true, dataType = "Long", paramType="path")
	@DeleteMapping("/order/{id}")
	public ResponseEntity<Order> deleteOrder(@PathVariable(value = "id") Long orderId) {
		this.orderService.deleteOrderById(orderId);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
