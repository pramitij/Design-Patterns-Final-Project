package com.inventory.strategy;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.inventory.api.StrategyAPI;
import com.inventory.model.Product;
import com.inventory.observer.Action;
import com.inventory.observer.UpdateBuyers;
import com.inventory.observer.UpdateDataBase;
import com.inventory.repository.BuyerRepository;
import com.inventory.repository.ProductRepository;

public class ProductStrategy implements StrategyAPI{

	private ProductRepository productRepository;
	private int id;
	private Product product;
	private BuyerRepository buyerRepository;
	
	public ProductStrategy(ProductRepository productRepository, int id) {
		this.productRepository = productRepository;
		this.id = id;
	}
	
	public ProductStrategy(ProductRepository productRepository, Product product) {
		this.productRepository = productRepository;
		this.product = product;
	}
	
	public ProductStrategy(ProductRepository productRepository, Product product, BuyerRepository buyerRepository) {
		this.productRepository = productRepository;
		this.product = product;
		this.buyerRepository = buyerRepository;
	}

	@Override
	public void add() {
		
		Action a = new Action();
		new UpdateBuyers(a, buyerRepository);
		new UpdateDataBase(a, productRepository);
		
		a.setState(product);
	}
	
	@Override
	public void update() {
		productRepository.update(product);
	}
	
	@Override
	public void delete() {
		Product product = productRepository.getProductbyID(id);
		productRepository.delete(product);
	}
}
