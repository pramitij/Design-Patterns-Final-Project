package com.inventory.decorator;

import com.inventory.api.InventoryCartAPI;

public class ProductInitilize implements InventoryCartAPI{

	double totalCost;
	
	public ProductInitilize() {
		this.totalCost = 0;
	}

	@Override
	public double getCost() {
		// TODO Auto-generated method stub
		return this.totalCost;
	}

}
