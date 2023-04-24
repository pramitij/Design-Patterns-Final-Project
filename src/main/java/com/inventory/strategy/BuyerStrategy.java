package com.inventory.strategy;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.inventory.api.StrategyAPI;
import com.inventory.model.Buyer;
import com.inventory.repository.BuyerRepository;

public class BuyerStrategy implements StrategyAPI {

	private int id;
	private BuyerRepository buyerRepository;
	private Buyer buyer;
		
	public BuyerStrategy(BuyerRepository buyerRepository, Buyer buy) {
		this.buyerRepository = buyerRepository;
		this.buyer = buy;
	}

	public BuyerStrategy(BuyerRepository buyerRepository, int id) {
		this.id = id;
		this.buyerRepository = buyerRepository;
	}

	@Override
	public void add() {
		if(buyerRepository.companyExists(buyer.getCompanyName()))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Company already exists");
		buyerRepository.save(buyer);
	}

	@Override
	public void update() {
		buyerRepository.update(buyer);
	}

	@Override
	public void delete() {
		Buyer buyer = buyerRepository.getBuyerbyID(id);
		buyerRepository.delete(buyer);
	}

}