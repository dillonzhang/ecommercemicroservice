package com.bdtv.ms.ecom.cart.service.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * @author dangao on 5/10/2018.
 * @version 1.0
 */
@Entity
@Table(name = "carts")
public class Cart
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	@NotNull
	private String name;

	@Column
	@NotNull
	private BigDecimal totalPrice;

	@Column
	private BigDecimal subTotal;

	@Column
	private BigDecimal tax;

	@Column
	@NotNull
	private Long customerId;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cart")
	private List<CartEntry> entries;

	@Column(updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createTime;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date modifiedTime;

	public Long getId()
	{
		return id;
	}

	public void setId(final Long id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(final String name)
	{
		this.name = name;
	}

	public BigDecimal getTotalPrice()
	{
		return totalPrice;
	}

	public void setTotalPrice(final BigDecimal totalPrice)
	{
		this.totalPrice = totalPrice;
	}

	public BigDecimal getSubTotal()
	{
		return subTotal;
	}

	public void setSubTotal(final BigDecimal subTotal)
	{
		this.subTotal = subTotal;
	}

	public BigDecimal getTax()
	{
		return tax;
	}

	public void setTax(final BigDecimal tax)
	{
		this.tax = tax;
	}

	public Long getCustomerId()
	{
		return customerId;
	}

	public void setCustomerId(final Long customerId)
	{
		this.customerId = customerId;
	}

	public List<CartEntry> getEntries()
	{
		return entries;
	}

	public void setEntries(final List<CartEntry> entries)
	{
		this.entries = entries;
	}

	public Date getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(final Date createTime)
	{
		this.createTime = createTime;
	}

	public Date getModifiedTime()
	{
		return modifiedTime;
	}

	public void setModifiedTime(final Date modifiedTime)
	{
		this.modifiedTime = modifiedTime;
	}
}
