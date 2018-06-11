package com.bdtv.ms.ecom.stock.service.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


/**
 * @author dangao on 5/24/2018.
 * @version 1.0
 */


@Entity
@Table
public class Stock
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private Long productId;

	@Column
	@NotNull
	private Long stockLevel;

	public Long getId()
	{
		return id;
	}

	public void setId(final Long id)
	{
		this.id = id;
	}

	public Long getProductId()
	{
		return productId;
	}

	public void setProductId(final Long productId)
	{
		this.productId = productId;
	}

	public Long getStockLevel()
	{
		return stockLevel;
	}

	public void setStockLevel(final Long stockLevel)
	{
		this.stockLevel = stockLevel;
	}
}
