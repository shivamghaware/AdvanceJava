package com.catalog.entities;

public class Product {
	private long productId;
	private String productName;
	private double price;
	private int stock;
	private long categoryId;
	public Product(long productId, String productName, double price, int stock, long categoryId) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.stock = stock;
		this.categoryId = categoryId;
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", price=" + price + ", stock="
				+ stock + ", categoryId=" + categoryId + "]";
	}
	
	
}
