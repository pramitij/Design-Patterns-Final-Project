package com.inventory.observer;

import com.inventory.api.ObserverAPI;
import com.inventory.model.Product;
import com.inventory.repository.BuyerRepository;

public class UpdateBuyers extends ObserverAPI{

	private BuyerRepository buyerRepository;
	
	public UpdateBuyers(Action action, BuyerRepository buyerRepository) {
		this.action = action;
		this.action.attach(this);
		this.buyerRepository = buyerRepository;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(Product product) {
		NotifyBuyers notify = new NotifyBuyers(product, buyerRepository);
		notify.notifyAllBuyers();
		
	}

}
