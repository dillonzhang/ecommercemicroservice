package com.bdtv.ms.ecom.product.service.data;

public class ProductChange {
	private String type;
	private String action;
	private String productId;

	public ProductChange(String type, String action, String productId) {
		this.type = type;
		this.action = action;
		this.productId = productId;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}

}
