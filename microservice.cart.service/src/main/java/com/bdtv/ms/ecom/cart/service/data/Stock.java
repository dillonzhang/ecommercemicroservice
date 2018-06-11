package com.bdtv.ms.ecom.cart.service.data;

/**
 * @author dangao on 5/28/2018.
 * @version 1.0
 */
public class Stock
{
	private Long id;

	private Long productId;

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
