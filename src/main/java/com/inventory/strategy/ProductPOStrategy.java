package com.inventory.strategy;

import com.inventory.api.StrategyAPI;
import com.inventory.model.ProductPO;
import com.inventory.repository.ProductPORepository;

public class ProductPOStrategy implements StrategyAPI{

	private ProductPORepository productPORepository;
	private int id;
	private ProductPO productPO;
	
	public ProductPOStrategy(ProductPORepository productPORepository, int id) {
		super();
		this.productPORepository = productPORepository;
		this.id = id;
	}
	
	public ProductPOStrategy(ProductPORepository productPORepository, ProductPO productPO) {
		super();
		this.productPORepository = productPORepository;
		this.productPO = productPO;
	}

	@Override
	public void add() {
		productPORepository.save(productPO);
	}

	@Override
	public void update() {
		productPORepository.update(productPO);
	}

	@Override
	public void delete() {
		ProductPO productPO = productPORepository.getProductPObyID(id);
		productPORepository.delete(productPO);
	}

	
}
