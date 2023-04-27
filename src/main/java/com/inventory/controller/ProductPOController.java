package com.inventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.inventory.model.ProductPO;
import com.inventory.repository.ProductPORepository;
import com.inventory.strategy.ProductPOStrategy;
import com.inventory.strategy.StrategyTrigger;

@RestController
@RequestMapping("/productPO")
public class ProductPOController {

	@Autowired
	private ProductPORepository productPORepository;
	
	@PostMapping("/save")
	public void save(@RequestBody ProductPO productPO) {
		StrategyTrigger trig = new StrategyTrigger(new ProductPOStrategy(productPORepository, productPO));
		trig.executeAdd();
	}

	@GetMapping("/getAll")
	public List<ProductPO> getAll() {
		return productPORepository.getProductPOs();
	}
	
	@GetMapping("/getProductPO/{id}")
	public ProductPO getProductPO(@PathVariable int id) {
		return productPORepository.getProductPObyID(id);
	}
	
	@PutMapping("/update")
	public void update(@RequestBody ProductPO productPO) {
		StrategyTrigger trig = new StrategyTrigger(new ProductPOStrategy(productPORepository, productPO));
		trig.executeUpdate();
	}
	
	@DeleteMapping("/delete/{id}")
	public void deletebyID(@PathVariable int id) {
		StrategyTrigger trig = new StrategyTrigger(new ProductPOStrategy(productPORepository, id));
		trig.executeDelete();
	}
}
