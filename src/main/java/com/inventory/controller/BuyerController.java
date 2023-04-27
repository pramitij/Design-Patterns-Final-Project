package com.inventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.inventory.model.Buyer;
import com.inventory.repository.BuyerRepository;
import com.inventory.strategy.BuyerStrategy;
import com.inventory.strategy.StrategyTrigger;

@RestController
@RequestMapping("/buyer")
public class BuyerController {
	@Autowired
	private BuyerRepository buyerRepository;
	
	@PostMapping("/save")
	public void save(@RequestBody Buyer buyer) {
		StrategyTrigger trig = new StrategyTrigger(new BuyerStrategy(buyerRepository, buyer));
		trig.executeAdd();
	}

	@GetMapping("/getAll")
	public List<Buyer> getAll() {
		return buyerRepository.getBuyers();
	}
	
	@GetMapping("/getBuyer/{id}")
	public Buyer getBuyer(@PathVariable int id) {
		return buyerRepository.getBuyerbyID(id);
	}
	
	@PutMapping("/update")
	public void update(@RequestBody Buyer buyer) {
		StrategyTrigger trig = new StrategyTrigger(new BuyerStrategy(buyerRepository, buyer));
		trig.executeUpdate();
	}
	
	@DeleteMapping("/delete/{id}")
	public void deletebyID(@PathVariable int id) {
		StrategyTrigger trig = new StrategyTrigger(new BuyerStrategy(buyerRepository, id));
		trig.executeDelete();
	}
}