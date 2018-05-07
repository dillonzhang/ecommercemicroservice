package com.bdtv.ms.ecom.order.service.controller;

import java.util.Set;

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

import com.bdtv.ms.ecom.order.service.entity.Order;
import com.bdtv.ms.ecom.order.service.entity.OrderEntry;
import com.bdtv.ms.ecom.order.service.service.OrderService;

@RestController
@RequestMapping("/orderentryapi")
public class OrderEntryController {

	@Autowired
	private OrderService orderService;
	
	@ApiOperation(value="get OrderEntries By orderId", nickname= "getOrderEntriesById", notes="this is the method to get Order Entries by Order ID")
	@ApiImplicitParam(name = "orderId", value = "Entry orderId", required = true, dataType = "Long", paramType="path")
	@GetMapping("/order/{orderId}/orderentries")
	public ResponseEntity<Set<OrderEntry>> getOrderById(@PathVariable Long orderId) {
		Set<OrderEntry> entries = this.orderService.getOrderEntriesByOrderId(orderId);
		return ResponseEntity.status(HttpStatus.OK).body(entries);
	}
	
	@ApiOperation(value="create a order entry", nickname= "createOrderEntry", notes="this is the method to create a order entry")
	@ApiImplicitParams({
	       @ApiImplicitParam(name = "orderId", value = "order ID", required = true, dataType = "Long", paramType="path"),
	       @ApiImplicitParam(name = "orderEntry", value = "Entry order entry", required = true, dataType = "OrderEntry")
	})
	@PostMapping("/order/{orderId}/orderentry")
	public ResponseEntity<OrderEntry> createOrderEntry(@PathVariable Long orderId, @Valid @RequestBody OrderEntry orderEntry) {
		OrderEntry oe = this.orderService.createOrderEntry(orderId, orderEntry);
		return ResponseEntity.status(HttpStatus.OK).body(oe);
	}
	
	@ApiOperation(value="update order entry information", notes="update the order entry information according the orderId, orderEntryId and order Entry Model in the parameters")
	@ApiImplicitParams({
	       @ApiImplicitParam(name = "orderId", value = "order ID", required = true, dataType = "Long", paramType="path"),
	       @ApiImplicitParam(name = "orderEntryId", value = "order entry ID", required = true, dataType = "Long", paramType="path"),
	       @ApiImplicitParam(name = "orderEntry", value = "Entry order entry", required = true, dataType = "OrderEntry")
	})
	@PutMapping("/order/{orderId}/orderentry/{orderEntryId}")
	public ResponseEntity<OrderEntry> updateOrder(@PathVariable Long orderId, @PathVariable Long orderEntryId, @Valid @RequestBody OrderEntry orderEntry) {
		OrderEntry oe = this.orderService.updateOrderEntry(orderId, orderEntryId, orderEntry);
		return ResponseEntity.status(HttpStatus.OK).body(oe);
	}
	
	@ApiOperation(value="delete order information", notes="delete the order entry information according the OrderID and OrderEntryID")
	@ApiImplicitParams({
	       @ApiImplicitParam(name = "orderId", value = "order ID", required = true, dataType = "Long", paramType="path"),
	       @ApiImplicitParam(name = "orderEntryId", value = "order entry ID", required = true, dataType = "Long", paramType="path"),
	})
	@DeleteMapping("/order/{orderId}/orderentry/{orderEntryId}")
	public ResponseEntity<Order> deleteOrder(@PathVariable Long orderId, @PathVariable Long orderEntryId) {
		this.orderService.deleteOrderEntry(orderId, orderEntryId);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
