package com.inventory.observer;

import java.util.ArrayList;
import java.util.List;

import com.inventory.api.ObserverAPI;
import com.inventory.model.Product;

public class Action {

	private List<ObserverAPI> observers = new ArrayList<ObserverAPI>();
	   
	   public void setState(Product product) {
	      notifyAllObservers(product);
	   }

	   public void attach(ObserverAPI observer){
	      observers.add(observer);		
	   }

	   public void notifyAllObservers(Product product){
	      for (ObserverAPI observer : observers) {
	         observer.update(product);
	      }
	   } 	
	
}
