package com.inventory.strategy;

import java.util.List;

import com.inventory.api.InventoryCartAPI;
import com.inventory.api.StrategyAPI;
import com.inventory.decorator.ParameterizedDecorator;
import com.inventory.decorator.ProductInitilize;
import com.inventory.model.Product;
import com.inventory.model.ProductPO;
import com.inventory.model.PurchaseOrder;
import com.inventory.repository.ProductPORepository;
import com.inventory.repository.ProductRepository;
import com.inventory.repository.PurchaseOrderRepository;
import com.inventory.state.LowStockAlert;
import com.inventory.state.State;
import com.inventory.state.StockUpdate;


public class PurchaseOrderStrategy implements StrategyAPI{

	private PurchaseOrderRepository purchaseOrderRepository;
	private ProductPORepository productPORepository;
	private ProductRepository productRepository;
	private int id;
	private PurchaseOrder purchaseOrder;
	private PurchaseOrder insertedPO; 
	
	public PurchaseOrderStrategy(PurchaseOrderRepository purchaseOrderRepository,
			ProductPORepository productPORepository, ProductRepository productRepository, PurchaseOrder insertedPO ,PurchaseOrder purchaseOrder) {
		
		this.purchaseOrderRepository = purchaseOrderRepository;
		this.productPORepository = productPORepository;
		this.productRepository = productRepository;
		this.purchaseOrder = purchaseOrder;
		this.insertedPO = insertedPO;
	}
	
	public PurchaseOrderStrategy(PurchaseOrderRepository purchaseOrderRepository, PurchaseOrder purchaseOrder) {
		this.purchaseOrderRepository = purchaseOrderRepository;
		this.purchaseOrder = purchaseOrder;
	}


	public PurchaseOrderStrategy(PurchaseOrderRepository purchaseOrderRepository, int id) {
		super();
		this.purchaseOrderRepository = purchaseOrderRepository;
		this.id = id;
	}

	@Override
	public void add() {
	
		InventoryCartAPI cart = new ProductInitilize();
		System.out.println("Inserted "+insertedPO.getId());
		List<ProductPO> productPOs = insertedPO.getProducts();
		//purchaseOrderRepository.save(insertedPO);
		
		for(ProductPO proPO : productPOs) {
			State s = new State();
			productPORepository.save(proPO);
			//Product product = productRepository.getProductbyID(proPO.getProduct().getId());
			Product product = proPO.getProduct();
			
			cart = new ParameterizedDecorator(cart, product, proPO);
			int difference = product.getQuantity() - proPO.getQuantity();
			
			if(difference <= 100) {
				
				LowStockAlert low = new LowStockAlert(product, productRepository);
				low.action(s, difference);
			}else {
				
				StockUpdate stock = new StockUpdate(product, productRepository);
				stock.action(s, difference);
			}

		}
		insertedPO.setTotalAmount(cart.getCost());
		purchaseOrderRepository.update(insertedPO);
	}

	@Override
	public void update() {
		purchaseOrderRepository.update(purchaseOrder);
	
	}

	@Override
	public void delete() {
		PurchaseOrder purchaseOrder = purchaseOrderRepository.getPurchaseOrderbyID(id);
		purchaseOrderRepository.delete(purchaseOrder);
		
	}

}