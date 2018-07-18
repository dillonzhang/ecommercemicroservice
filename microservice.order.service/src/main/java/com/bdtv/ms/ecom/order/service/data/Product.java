package com.bdtv.ms.ecom.order.service.data;

import java.io.Serializable;

public class Product implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4821653519010608002L;
	private Long id;
	private String code;
	private String name;
	private String description;

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

}
