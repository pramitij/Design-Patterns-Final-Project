package com.inventory.api;

import com.inventory.model.Product;
import com.inventory.observer.Action;

public abstract class ObserverAPI {
	
	protected Action action;
	
	public abstract void update(Product product);
}
