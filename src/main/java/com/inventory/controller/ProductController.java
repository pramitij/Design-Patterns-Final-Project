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
import com.inventory.model.Product;
import com.inventory.repository.BuyerRepository;
import com.inventory.repository.ProductRepository;
import com.inventory.strategy.ProductStrategy;
import com.inventory.strategy.StrategyTrigger;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private BuyerRepository buyerRepository;
	
	@PostMapping("/save")
	public void save(@RequestBody Product product) {
		StrategyTrigger trig = new StrategyTrigger(new ProductStrategy(productRepository, product, buyerRepository));
		trig.executeAdd();
	}

	@GetMapping("/getAll")
	public List<Product> getAll() {
		return productRepository.getProducts();
	}
	
	@GetMapping("/getProduct/{id}")
	public Product getProduct(@PathVariable int id) {
		return productRepository.getProductbyID(id);
	}
	
	@PutMapping("/update")
	public void update(@RequestBody Product product) {
		StrategyTrigger trig = new StrategyTrigger(new ProductStrategy(productRepository, product));
		trig.executeUpdate();
	}
	
	@DeleteMapping("/delete/{id}")
	public void deletebyID(@PathVariable int id) {
		StrategyTrigger trig = new StrategyTrigger(new ProductStrategy(productRepository, id));
		trig.executeDelete();
	}

}
