package com.inventory.decorator;

import com.inventory.api.InventoryCartAPI;

public class InventoryCartDecorator implements InventoryCartAPI{
	
	InventoryCartAPI cart;
		
	public InventoryCartDecorator(InventoryCartAPI cart) {
		this.cart = cart;
	}

	@Override
	public double getCost() {
		return this.cart.getCost();
	}

}
