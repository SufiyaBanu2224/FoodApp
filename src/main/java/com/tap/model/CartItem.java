package com.tap.model;

public class CartItem {
	private int itemId;
	private int restaurantId;
	private int quantity;
	private String name;
	private double price;
	private String description;
	private String imagePath; 
	

	public CartItem() {
		
	}

	

	public CartItem(int itemId, int restaurantId, int quantity, String name, double price, String description,
			String imagePath) {
		super();
		this.itemId = itemId;
		this.restaurantId = restaurantId;
		this.quantity = quantity;
		this.name = name;
		this.price = price;
		this.description = description;
		this.imagePath = imagePath;
	}



	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getImagePath() {
		return imagePath;
	}
	
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	@Override
	public String toString() {
		return "CartItem [itemId=" + itemId + ", restaurantId=" + restaurantId + ", quantity=" + quantity + ", name="
				+ name + ", price=" + price + ", description=" + description + ", imagePath=" + imagePath + "]";
	}
	
	

	
	
	
	
	
}
