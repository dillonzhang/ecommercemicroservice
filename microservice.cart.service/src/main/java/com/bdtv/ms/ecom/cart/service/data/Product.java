package com.bdtv.ms.ecom.cart.service.data;

/**
 * @author dangao on 5/10/2018.
 * @version 1.0
 */
public class Product
{
	private Long id;
	private String code;
	private String name;
	private String description;
	private double price;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice()
	{
		return price;
	}

	public void setPrice(final double price)
	{
		this.price = price;
	}
}
