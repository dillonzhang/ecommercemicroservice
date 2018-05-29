package com.bdtv.ms.ecom.cart.service.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


/**
 * @author dangao on 5/10/2018.
 * @version 1.0
 */
@Entity
public class CartEntry
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	@NotNull
	private BigDecimal totalPrice;

	@Column
	@NotNull
	private BigDecimal subTotal;

	@Column
	private BigDecimal tax;

	@Column
	@NotNull
	private Long productId;

	@Column
	@NotNull
	private Long count;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name = "cart_id", nullable = false)
	private Cart cart;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public BigDecimal getTotalPrice()
	{
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice)
	{
		this.totalPrice = totalPrice;
	}

	public BigDecimal getSubTotal()
	{
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal)
	{
		this.subTotal = subTotal;
	}

	public BigDecimal getTax()
	{
		return tax;
	}

	public void setTax(BigDecimal tax)
	{
		this.tax = tax;
	}

	public Long getProductId()
	{
		return productId;
	}

	public void setProductId(Long productId)
	{
		this.productId = productId;
	}

	public Long getCount()
	{
		return count;
	}

	public void setCount(Long count)
	{
		this.count = count;
	}

	public Cart getCart()
	{
		return cart;
	}

	public void setCart(final Cart cart)
	{
		this.cart = cart;
	}
}
