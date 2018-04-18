package com.bdtv.ms.ecom.customer.service.data;


import java.math.BigDecimal;
import java.util.Set;

public class Order {

	private Long id;
	
	private String name;
	  
	private BigDecimal totalPrice;
	
	private BigDecimal subTotal;
	
	private BigDecimal tax;
	
    private Set<OrderEntry> orderEntries;  
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public BigDecimal getTax() {
		return tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	public Set<OrderEntry> getOrderEntries() {
		return orderEntries;
	}

	public void setOrderEntries(Set<OrderEntry> orderEntries) {
		this.orderEntries = orderEntries;
	}
	
}
