package com.inventory.observer;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.inventory.api.ObserverAPI;
import com.inventory.model.Product;
import com.inventory.repository.ProductRepository;

public class UpdateDataBase extends ObserverAPI{

	private ProductRepository productRepository;
	
	public UpdateDataBase(Action action, ProductRepository productRepository) {
		this.action = action;
		this.productRepository = productRepository;
		this.action.attach(this);
		
	}

	@Override
	public void update(Product product) {
		if(productRepository.productExists(product.getProductName()))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product already exists");
		productRepository.save(product);
	}

}
