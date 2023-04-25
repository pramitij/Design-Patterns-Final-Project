package com.inventory.decorator;

import com.inventory.api.InventoryCartAPI;
import com.inventory.model.Product;
import com.inventory.model.ProductPO;

public class ParameterizedDecorator extends InventoryCartDecorator{

	int quantity;
	double price;
	
	public ParameterizedDecorator(InventoryCartAPI cart, Product product, ProductPO proPo) {
		super(cart);
		this.quantity = proPo.getQuantity();
		this.price = product.getPrice();
	}
	
	public double getCost() {
		return super.getCost()+(this.quantity * this.price);
	}
}
