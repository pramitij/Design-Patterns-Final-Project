package com.inventory.observer;

import java.util.List;

import com.inventory.api.Facade;
import com.inventory.facade.SendMessage;
import com.inventory.model.Buyer;
import com.inventory.model.Product;
import com.inventory.repository.BuyerRepository;

public class NotifyBuyers extends Buyer{

	private List<Buyer> buyers;
	private Product product;
	private BuyerRepository buyerRepository;

	public NotifyBuyers(Product product, BuyerRepository buyerRepository) {
		this.product = product;
		this.buyerRepository = buyerRepository;
	}
	public void notifyAllBuyers() {
		StringBuilder sb =new StringBuilder();
		buyers = buyerRepository.getBuyers();
		
		for (Buyer buyer : buyers) {
			System.out.println(buyer.getOwnerName() + " notified of Product " + product.getProductName() + " addition");
			sb.append("Hello "+buyer.getOwnerName()).append(", new product available : ");
			sb.append(product.getProductName()+"\n");
			
			SendMessage.message(sb.toString());
		}
	}
}
