package com.inventory.state;

import com.inventory.api.StateAPI;
import com.inventory.model.Product;
import com.inventory.repository.ProductRepository;

public class StockUpdate extends StateAPI{

	Product product;
	ProductRepository productRepository;
	
	public StockUpdate(Product product, ProductRepository productRepository) {
		this.product = product;
		this.productRepository = productRepository;
	}

	@Override
	public void action(State state, int stock) {
		
		product.setQuantity(stock);
		productRepository.update(product);
	
	}
}