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

import com.inventory.model.PurchaseOrder;
import com.inventory.repository.ProductPORepository;
import com.inventory.repository.ProductRepository;
import com.inventory.repository.PurchaseOrderRepository;
import com.inventory.strategy.PurchaseOrderStrategy;
import com.inventory.strategy.StrategyTrigger;

@RestController
@RequestMapping("/purchaseOrder")
public class PurchaseOrderController {

	@Autowired
	private PurchaseOrderRepository purchaseOrderRepository;

	@Autowired
	private ProductPORepository productPORepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@PostMapping("/save")
	public void save(@RequestBody PurchaseOrder purchaseOrder) {
		int id = purchaseOrderRepository.save(purchaseOrder);
		PurchaseOrder insertedPO = purchaseOrderRepository.getPurchaseOrderbyID(id);
		StrategyTrigger trig = new StrategyTrigger(new PurchaseOrderStrategy
				(purchaseOrderRepository, productPORepository, productRepository, insertedPO ,purchaseOrder));
		trig.executeAdd();
	}

	@GetMapping("/getAll")
	public List<PurchaseOrder> getAll() {
		return purchaseOrderRepository.getPurchaseOrders();
	}
	
	@GetMapping("/getPurchaseOrder/{id}")
	public PurchaseOrder getPurchaseOrder(@PathVariable int id) {
		return purchaseOrderRepository.getPurchaseOrderbyID(id);
	}
	
	@PutMapping("/update")
	public void update(@RequestBody PurchaseOrder purchaseOrder) {
		StrategyTrigger trig = new StrategyTrigger(new PurchaseOrderStrategy(purchaseOrderRepository, purchaseOrder));
		trig.executeUpdate();
	}
	
	@DeleteMapping("/delete/{id}")
	public void deletebyID(@PathVariable int id) {
		StrategyTrigger trig = new StrategyTrigger(new PurchaseOrderStrategy(purchaseOrderRepository, id));
		trig.executeDelete();
	}
}
