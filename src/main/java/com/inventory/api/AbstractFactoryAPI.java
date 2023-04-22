package com.inventory.api;

import com.inventory.command.Communication;

public abstract class AbstractFactoryAPI {
	/**
	 * Returns an object
	 */
	public abstract Communication getObject();
}
