package com.bdtv.ms.ecom.order.service.service;

import java.util.List;
import java.util.Set;

import com.bdtv.ms.ecom.order.service.data.Product;
import com.bdtv.ms.ecom.order.service.entity.Order;
import com.bdtv.ms.ecom.order.service.entity.OrderEntry;

public interface OrderService {
	Order createOrder(Order order);
	
	Order getOrderById(Long orderId);
	
	List<Order> getOrders();
	
	void deleteOrderById(Long orderId);
	
	Order updateOrder(Long orderId, Order order);
	
	Product getProductById(Long productId);
	
	OrderEntry createOrderEntry(Long orderId, OrderEntry orderEntry);
	
	Set<OrderEntry> getOrderEntriesByOrderId(Long orderId);
	
	OrderEntry updateOrderEntry(Long orderId, Long orderEntryId, OrderEntry orderEntry);
	
	void deleteOrderEntry(Long orderId, Long orderEntryId);
}
