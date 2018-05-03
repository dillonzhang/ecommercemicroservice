package com.bdtv.ms.ecom.order.service.service;

import java.util.List;

import com.bdtv.ms.ecom.order.service.data.Product;
import com.bdtv.ms.ecom.order.service.entity.Order;

public interface OrderService {
	Order createOrder(Order order);
	
	Order getOrderById(Long orderId);
	
	List<Order> getOrders();
	
	void deleteOrderById(Long orderId);
	
	Order updateOrder(Long orderId, Order order);
	
	Product getProductById(Long productId);
}
