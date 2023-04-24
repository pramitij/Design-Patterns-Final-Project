package com.inventory.strategy;

import com.inventory.api.StrategyAPI;

public class StrategyTrigger {

	private StrategyAPI strategy;

	public StrategyTrigger(StrategyAPI strategy) {
		this.strategy = strategy;
	}
	
	public void executeAdd() {
		strategy.add();
	}
	
	public void executeDelete() {
		strategy.delete();
	}
	
	public void executeUpdate() {
		strategy.update();
	}
}
