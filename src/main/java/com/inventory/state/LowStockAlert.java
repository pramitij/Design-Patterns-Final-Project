package com.inventory.state;

import com.inventory.api.StateAPI;
import com.inventory.facade.SendMessage;
import com.inventory.model.Product;
import com.inventory.repository.ProductRepository;

public class LowStockAlert extends StateAPI{

	Product product;
	ProductRepository productRepository;
	
	public LowStockAlert(Product product, ProductRepository productRepository) {
		this.product = product;
		this.productRepository = productRepository;
	}

	@Override
	public void action(State state,int stock) {
		SendMessage.message("\n******\nLOW STOCK for "+product.getProductName()+"\n*****\n");
		product.setQuantity(stock);
		productRepository.update(product);
	}

}
